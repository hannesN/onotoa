/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.Tool;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.topicmapslab.tmcledit.diagram.action.AbstractSelectionAction;
import de.topicmapslab.tmcledit.diagram.action.AddNameConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.AddOccurrenceConstraintAction;
import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;
import de.topicmapslab.tmcledit.diagram.editparts.MoveableLabelEditPart;
import de.topicmapslab.tmcledit.diagram.util.IPrintableDiagramEditor;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.util.io.FileUtil;

/**
 * @author Hannes Niederhausen
 * @param <Action>
 * 
 */
public class TMCLDiagramEditor extends GraphicalEditorWithFlyoutPalette
		implements ISelectionChangedListener, ISelectionProvider,
		IPrintableDiagramEditor {

	public static final String ID = "de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor";

	private Diagram diagram;

	private ScalableFreeformRootEditPart rootEditPart;

	private ISelection currentSelection;
	private List<ISelectionChangedListener> selectionChangedListeners = Collections
			.emptyList();

	private DirtyAdapter dirtyAdapter;

	private OverviewOutlinePage outlinePage;

	private TMCLEditorContextMenuProvider cmProvider;

	public TMCLDiagramEditor() {
		setEditDomain(new TMCLEditDomain(this));
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		setPartName(input.getName());
		getPalettePreferences().setPaletteState(
				FlyoutPaletteComposite.STATE_PINNED_OPEN);
		getPalettePreferences().setPaletteWidth(200);
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(diagram); // set the contents of this editor
		viewer.addSelectionChangedListener(this);
		viewer.setEditDomain(getEditDomain());

		getSite().setSelectionProvider(this);
		// listen for dropped parts
		viewer.addDropTargetListener(new TypeDropTransferListener(viewer,
				diagram));

		viewer.getControl().addMouseListener(new DiagramMouseListener());
	}

	protected org.eclipse.emf.common.command.CommandStack getEMFCommandStack() {
		return ((TMCLEditDomain) getEditDomain()).getEditingDomain()
				.getCommandStack();
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(TMCLDiagramEditorUtil.getEditPartFactory());
		viewer.setRootEditPart(getRootEditPart());
		viewer.setKeyHandler(new OnotoaKeyHandler(viewer, getActionRegistry()));

		cmProvider = new TMCLEditorContextMenuProvider(viewer,
				getActionRegistry(), diagram);
		getGraphicalViewer().setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);

		List<String> zoomContributions = new ArrayList<String>(5);
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);

		ZoomManager manager = (ZoomManager) getGraphicalViewer().getProperty(
				ZoomManager.class.toString());

		manager.setZoomLevelContributions(zoomContributions);

		IAction zoomIn = new ZoomInAction(manager);
		zoomIn.setAccelerator(0);
		IAction zoomOut = new ZoomOutAction(manager);

		getActionRegistry().registerAction(zoomIn);
		getActionRegistry().registerAction(zoomOut);

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.CTRL),
				MouseWheelZoomHandler.SINGLETON);
		getPalettePreferences().setPaletteState(
				FlyoutPaletteComposite.STATE_PINNED_OPEN);

		getGraphicalViewer()
				.addDropTargetListener(
						(TransferDropTargetListener) new TemplateTransferDropTargetListener(
								getGraphicalViewer()));

	}

	public ZoomManager getZoomManager() {
		return getRootEditPart().getZoomManager();
	}

	public ScalableFreeformRootEditPart getRootEditPart() {
		if (rootEditPart == null) {
			rootEditPart = new ScalableFreeformRootEditPart();
		}
		return rootEditPart;
	}

	public EditingDomain getEditingDomain() {
		return ((TMCLEditorInput) getEditorInput()).getEditingDomain();
	}

	public Diagram getDiagram() {
		return diagram;
	}

	@Override
	public ActionRegistry getActionRegistry() {
		return super.getActionRegistry();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			FileUtil.saveFile((File) diagram.eContainer(), getEditingDomain());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected PaletteViewerProvider createPaletteViewerProvider() {

		return new PaletteViewerProvider(getEditDomain()) {
			@Override
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer
						.addDragSourceListener(new TemplateTransferDragSourceListener(
								viewer));
			}
		};

	}

	@Override
	public void doSaveAs() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setText("Save As..");
		String result = dlg.open();
		if (result != null) {
			File currFile = (File) diagram.eContainer();
			if ((!result.endsWith(".ono")) && (!result.endsWith(".tmcl")))
				result += ".ono";
			currFile.setFilename(result);
			doSave(new NullProgressMonitor());
		}
	}

	@Override
	protected void createActions() {
		getActionRegistry().registerAction(
				new RemoveFromDiagramAction(getEMFCommandStack()));
		getActionRegistry().registerAction(
				new DeleteFromModelAction(getEMFCommandStack()));
		getActionRegistry().registerAction(
				new AddNameConstraintAction(getEMFCommandStack()));
		getActionRegistry().registerAction(
				new AddOccurrenceConstraintAction(getEMFCommandStack()));
		super.createActions();
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		TMCLEditorInput ei = (TMCLEditorInput) input;
		this.diagram = ei.getDiagram();

		((TMCLEditDomain) getEditDomain()).setEditingDomain(ei
				.getEditingDomain());

		setTitleImage(input.getImageDescriptor().createImage());

		dirtyAdapter = new DirtyAdapter();
		((File) diagram.eContainer()).eAdapters().add(dirtyAdapter);
		diagram.eAdapters().add(dirtyAdapter);

		ActionRegistry ar = ei.getActionRegistry();
		IActionBars actionBars = getEditorSite().getActionBars();

		IAction a = ar.getAction(ActionFactory.UNDO.getId());
		actionBars.setGlobalActionHandler(a.getId(), a);
		getActionRegistry().registerAction(a);

		a = ar.getAction(ActionFactory.REDO.getId());
		actionBars.setGlobalActionHandler(a.getId(), a);
		getActionRegistry().registerAction(a);

		a = ar.getAction(ActionFactory.SAVE.getId());
		actionBars.setGlobalActionHandler(a.getId(), a);
		getActionRegistry().registerAction(a);

		a = ar.getAction(ActionFactory.CLOSE.getId());
		getActionRegistry().registerAction(a);
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public boolean isDirty() {
		if (diagram.eContainer() == null)
			return false;
		return ((File) diagram.eContainer()).isDirty();
	}

	@Override
	public void dispose() {
		diagram.eAdapters().remove(dirtyAdapter);
		((File) diagram.eContainer()).eAdapters().remove(dirtyAdapter);
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == EditPartViewer.class)
			return getGraphicalViewer();
		if (adapter == IWorkbenchPage.class)
			return getSite().getPage();
		if (adapter == TMCLDiagramEditor.class)
			return this;
		if (adapter == ISelectionProvider.class)
			return this;
		if (adapter == EditingDomain.class)
			return getEditingDomain();
		if (adapter == CommandStack.class)
			return getEditDomain().getCommandStack();
		if (adapter == IContentOutlinePage.class)
			return getContentOutlinePage();
		if (adapter == ZoomManager.class)
			return getGraphicalViewer().getProperty(
					ZoomManager.class.toString());
		if (adapter == org.eclipse.emf.common.command.CommandStack.class)
			return getEditingDomain().getCommandStack();
		return super.getAdapter(adapter);
	}

	public IContentOutlinePage getContentOutlinePage() {
		if ((outlinePage == null) && (null != getGraphicalViewer())) {

			outlinePage = new OverviewOutlinePage(
					(ScalableFreeformRootEditPart) getGraphicalViewer()
							.getRootEditPart());
		}

		return outlinePage;
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		return TMCLDiagramEditorUtil.getPaletteRoot();
	}

	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();
		updateSelectionDependentActions(sel);
		if (sel.isEmpty())
			return;
		else {
			if (sel.getFirstElement() instanceof EditPart) {
				EditPart part = (EditPart) sel.getFirstElement();
				
				cmProvider.setSelectedEditPart(part);
				
				if (part instanceof MoveableLabelEditPart) {
					part = part.getParent();
				}

				Object model = part.getModel();
				if (model instanceof TypeNode) {
					TypeNode node = (TypeNode) model;
					currentSelection = new StructuredSelection(node
							.getTopicType());
				} else if (model instanceof AssociationNode) {
					AssociationNode node = (AssociationNode) model;
					currentSelection = new StructuredSelection(node
							.getAssociationConstraint());
				} else if (model instanceof Edge) {
					Edge edge = (Edge) model;
					EObject tmp = edge.getRoleConstraint();
					if (tmp != null)
						currentSelection = new StructuredSelection(tmp);
					else {
						tmp = ((TypeNode) edge.getSource()).getTopicType();
						currentSelection = new StructuredSelection(tmp);
					}
				} else {
					currentSelection = new StructuredSelection(model);
				}
				fireSelectionChanged();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void updateSelectionDependentActions(
			IStructuredSelection selelection) {
		Iterator<Action> it = getActionRegistry().getActions();
		while (it.hasNext()) {
			Object o = it.next();
			if (o instanceof AbstractSelectionAction) {
				((AbstractSelectionAction) o).setSelections(selelection);
			}
		}

	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (selectionChangedListeners == Collections.EMPTY_LIST)
			selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

		selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		return (currentSelection == null) ? new StructuredSelection()
				: currentSelection;
	}

	public void refresh() {
		getGraphicalViewer().getControl().redraw();
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (selectionChangedListeners != Collections.EMPTY_LIST)
			selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		// currentSelection = selection;
	}

	private void fireSelectionChanged() {
		if (currentSelection == null) {
			TMCLEditorInput ei = (TMCLEditorInput) getEditorInput();
			currentSelection = new StructuredSelection(ei.getDiagram());
		}
		SelectionChangedEvent event = new SelectionChangedEvent(this,
				currentSelection);

		for (ISelectionChangedListener l : selectionChangedListeners) {
			l.selectionChanged(event);
		}
	}

	public TMCLEditorInput getCastedEditorInput() {
		return (TMCLEditorInput) getEditorInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.topicmapslab.tmcledit.diagram.editor.IPrintableDiagramEditor#
	 * getPrintableFigure()
	 */
	public IFigure getPrintableFigure() {
		return getRootEditPart().getLayer(LayerConstants.PRINTABLE_LAYERS);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void updateActions(List actionIds) {
		super.updateActions(actionIds);
	}

	private final class DiagramMouseListener extends MouseAdapter {
		private boolean marquee = false;
		private MarqueeToolEntry met;

		public DiagramMouseListener() {
			init();
		}

		private void init() {
			PaletteGroup toolsGroup = (PaletteGroup) getEditDomain()
					.getPaletteViewer().getPaletteRoot().getChildren().get(0);
			met = (MarqueeToolEntry) toolsGroup.getChildren().get(1);
		}

		@Override
		public void mouseDoubleClick(MouseEvent e) {
			try {
				getEditorSite()
						.getPage()
						.showView(
								"de.topicmapslab.tmcledit.extensions.views.PropertyDetailView",
								null, IWorkbenchPage.VIEW_VISIBLE);
				fireSelectionChanged();

			} catch (PartInitException e1) {

			}
		}

		@Override
		public void mouseUp(MouseEvent e) {
			if (marquee) {
				if ((getEditDomain().getActiveTool() == null)
						|| (getEditDomain().getActiveTool().getClass() != SelectionTool.class)) {
					getEditDomain().getPaletteViewer().setActiveTool(null);
					cmProvider.setActive(false);
				} else {
					cmProvider.setActive(true);
				}
				marquee = false;
			}
		}

		@Override
		public void mouseDown(MouseEvent e) {
			if ((e.button == 3)) {
				if ((getEditDomain().getActiveTool() == null)
						|| (getEditDomain().getActiveTool().getClass() != SelectionTool.class)) {
					getEditDomain().getPaletteViewer().setActiveTool(null);
					cmProvider.setActive(false);
				} else {
					cmProvider.setActive(true);
				}
			}
			if (e.button == 1) {
				if ((getEditDomain().getActiveTool() == null)
						|| (getEditDomain().getActiveTool().getClass() == SelectionTool.class)) {
					if (currentSelection.isEmpty())
						return;
					if (((IStructuredSelection) currentSelection)
							.getFirstElement() instanceof Diagram) {

						getEditDomain().getPaletteViewer().setActiveTool(met);
						Tool tool = getEditDomain().getActiveTool();
						tool.mouseDown(e, getGraphicalViewer());
						marquee = true;
					}
				}
			}
		}
	}

	private class DirtyAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof File) {
				if (msg.getFeatureID(Boolean.class) == ModelPackage.FILE__DIRTY) {
					firePropertyChange(TMCLDiagramEditor.PROP_DIRTY);
					getCommandStack().markSaveLocation();
				} else if (msg.getFeatureID(List.class) == ModelPackage.FILE__DIAGRAMS) {
					if (msg.getEventType() == Notification.REMOVE) {
						if (msg.getOldValue().equals(getDiagram())) {
							getEditorSite().getPage().closeEditor(
									TMCLDiagramEditor.this, false);
						}

					}
				}
			} else if (msg.getNotifier().equals(diagram)) {
				if (msg.getFeatureID(String.class) == ModelPackage.DIAGRAM__NAME) {
					setPartName((String) msg.getNewValue());
					firePropertyChange(IEditorPart.PROP_TITLE);
				}
			}

		}
	}

}
