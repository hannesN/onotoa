/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.diagram.action.RemoveFromDiagramAction;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.FileUtil;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TMCLDiagramEditor extends GraphicalEditorWithFlyoutPalette
		implements ISelectionChangedListener, ISelectionProvider {
	// extends EditorPart {

	public static final String ID = "de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor";

	private Diagram diagram;

	private ScalableFreeformRootEditPart rootEditPart;

	private ISelection currentSelection;
	private List<ISelectionChangedListener> selectionChangedListeners = Collections
			.emptyList();

	private RemoveFromDiagramAction removeFromDiagramAction;
	private DeleteFromModelAction deleteFromModelAction;

	private DirtyAdapter dirtyAdapter;

	private OverviewOutlinePage outlinePage;

	public TMCLDiagramEditor() {
		setEditDomain(new TMCLEditDomain(this));
	}

	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(diagram); // set the contents of this editor
		viewer.addSelectionChangedListener(this);
		viewer.setEditDomain(getEditDomain());

		// getEditDomain().getCommandStack().addCommandStackListener(this);
		getSite().setSelectionProvider(this);
		// listen for dropped parts
		viewer.addDropTargetListener(new TypeDropTransferListener(viewer,
				diagram));
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(TMCLDiagramEditorUtil.getEditPartFactory());
		viewer.setRootEditPart(new ScalableFreeformRootEditPart());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));

		// configure the context menu provider
		ContextMenuProvider cmProvider = new TMCLEditorContextMenuProvider(
				viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);

	}

	public ScalableFreeformRootEditPart getRootEditPart() {
		if (rootEditPart == null) {
			rootEditPart = new ScalableFreeformRootEditPart();
		}
		return rootEditPart;
	}

	public EditingDomain getEditingDomain() {
		return ((TMCLEditorInput)getEditorInput()).getEditingDomain();
	}

	public Diagram getDiagram() {
		return diagram;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		try {
			FileUtil.saveFile((File)diagram.eContainer());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((File)diagram.eContainer()).setDirty(false);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	protected void createActions() {
		removeFromDiagramAction = new RemoveFromDiagramAction(getEditDomain()
				.getCommandStack());
		deleteFromModelAction = new DeleteFromModelAction(getEditDomain().getCommandStack());
		getActionRegistry().registerAction(deleteFromModelAction);
		getActionRegistry().registerAction(removeFromDiagramAction);
		super.createActions();
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		TMCLEditorInput ei = (TMCLEditorInput) input;
		this.diagram = ei.getDiagram();

		((TMCLEditDomain) getEditDomain()).setEditingDomain(ei
				.getEditingDomain());

		dirtyAdapter = new DirtyAdapter();
		((File)diagram.eContainer()).eAdapters().add(dirtyAdapter);
		
		IActionBars actionBars = getEditorSite().getActionBars();
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), ei
				.getUndoAction());
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), ei
				.getRedoAction());

		getActionRegistry().registerAction(ei.getUndoAction());
		getActionRegistry().registerAction(ei.getRedoAction());
	}
	
	@Override
	public boolean isSaveOnCloseNeeded() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	public boolean isDirty() {
		return ((File)diagram.eContainer()).isDirty();
	}

	@Override
	public void dispose() {
		((File)diagram.eContainer()).eAdapters().remove(dirtyAdapter);
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
		return super.getAdapter(adapter);
	}

	public IContentOutlinePage getContentOutlinePage() {
		if ( (outlinePage == null) && (null!=getGraphicalViewer()) ) {
			
			outlinePage = new OverviewOutlinePage((ScalableFreeformRootEditPart) getGraphicalViewer().getRootEditPart());
		}

		return outlinePage;
	}
	
	@Override
	protected PaletteRoot getPaletteRoot() {
		return TMCLDiagramEditorUtil.getPaletteRoot();
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();

		if (sel.isEmpty())
			return;
		else {
			if (sel.getFirstElement() instanceof EditPart) {
				EditPart part = (EditPart) sel.getFirstElement();
				updateSelectionDependentActions(part);
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
					EObject tmp = ((Edge)model).getRoleConstraint();
					if (tmp!=null)
						currentSelection = new StructuredSelection(tmp);
					else
						currentSelection = new StructuredSelection();
				} else {
					currentSelection = new StructuredSelection(model);
				}
				fireSelectionChanged();
			}
		}
	}

	private void updateSelectionDependentActions(EditPart selection) {
		removeFromDiagramAction.setSelectedEditPart(selection);
		deleteFromModelAction.setSelectedEditPart(selection);

	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (selectionChangedListeners == Collections.EMPTY_LIST)
			selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

		selectionChangedListeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return (currentSelection == null) ? new StructuredSelection()
				: currentSelection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (selectionChangedListeners != Collections.EMPTY_LIST)
			selectionChangedListeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		// currentSelection = selection;
	}

	private void fireSelectionChanged() {
		if (currentSelection == null)
			currentSelection = new StructuredSelection();

		SelectionChangedEvent event = new SelectionChangedEvent(this,
				currentSelection);

		for (ISelectionChangedListener l : selectionChangedListeners) {
			l.selectionChanged(event);
		}
	}

	public TMCLEditorInput getCastedEditorInput() {
		return (TMCLEditorInput) getEditorInput();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void updateActions(List actionIds) {
		super.updateActions(actionIds);
	}
	
	private class DirtyAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Boolean.class)==ModelPackage.FILE__DIRTY) {
				firePropertyChange(TMCLDiagramEditor.PROP_DIRTY);
				getCommandStack().markSaveLocation();
			}
		}
	}
	
}
