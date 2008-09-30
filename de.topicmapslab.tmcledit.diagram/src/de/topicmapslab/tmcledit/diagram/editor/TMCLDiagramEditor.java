/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLDiagramEditor extends GraphicalEditorWithFlyoutPalette implements ISelectionChangedListener, ISelectionProvider {
	//extends EditorPart {

	private TopicMapSchema topicMapSchema;
	
//	private EditDomain editDomain;
	
	private RootEditPart rootEditPart;
//	private GraphicalViewer graphicalViewer;

	private ISelection currentSelection;
	private List<ISelectionChangedListener> selectionChangedListeners = Collections.emptyList();

	public TMCLDiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

		
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(topicMapSchema.getDiagram()); // set the contents of this editor
		viewer.addSelectionChangedListener(this);
		
		getSite().setSelectionProvider(this);
		// listen for dropped parts
		//viewer.addDropTargetListener(createTransferDropTargetListener());
	}
	
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(TMCLDiagramEditorUtil.getEditPartFactory());
		viewer.setRootEditPart(new ScalableFreeformRootEditPart());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));

		/*
		// configure the context menu provider
		ContextMenuProvider cmProvider =
				new ShapesEditorContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);
		*/
	}
	
	
	public RootEditPart getRootEditPart() {
		if (rootEditPart == null) {
			rootEditPart = new ScalableFreeformRootEditPart();
		}	
		return rootEditPart;
	}
	
	public EditingDomain getEditingDomain() {
		return null;
	}
	
	public TopicMapSchema getTopicMapSchema() {
		return topicMapSchema;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		URIEditorInput ei = (URIEditorInput) getEditorInput();
		Resource resource = new XMIResourceFactoryImpl().createResource(ei.getURI());
		resource.getContents().add(topicMapSchema);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("tmcl", new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		URIEditorInput ei = (URIEditorInput) input;
		File file = new File(ei.getURI().toFileString());
		if (file.exists()) {
			topicMapSchema = (TopicMapSchema) resSet.getResource(ei.getURI(), true).getContents().get(0);
			
		} else {
			ModelFactory modelInstance = ModelFactory.eINSTANCE;
			topicMapSchema = modelInstance.createTopicMapSchema();
			topicMapSchema.setDiagram(modelInstance.createDiagram());
			
			TopicType tt = modelInstance.createTopicType();
			tt.setId("wwid:Person");
			TypeNode tn = modelInstance.createTypeNode();
			tn.setPosX(50);
			tn.setPosY(50);
			tn.setType(tt);
			topicMapSchema.getTopicTypes().add(tt);
			topicMapSchema.getDiagram().getNodes().add((Node) tn);
			
			OccurenceType ot = modelInstance.createOccurenceType();
			ot.setId("wwid:Adresse");
			topicMapSchema.getTopicTypes().add(ot);
			
			OccurenceTypeConstraint otc = modelInstance.createOccurenceTypeConstraint();
			otc.setCardMax("*");
			otc.setCardMin("0");
			otc.setType(ot);
			otc.setName("Heimadresse");
			tt.getOccurenceConstraints().add(otc);
			
			NameType nt = modelInstance.createNameType();
			nt.setId("wwid:Vorname");
			
			NameTypeConstraint ntc = modelInstance.createNameTypeConstraint();
			ntc.setName("vorname");
			ntc.setType(nt);
			tt.getNameContraints().add(ntc);
			
		}
	}

	@Override
	public boolean isDirty() {
		
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
/*
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == EditPartViewer.class)
			return getGraphicalViewer();
		if (adapter == IWorkbenchPage.class)
			return getSite().getPage();
		if (adapter == ISelectionProvider.class)
			return getSite().getSelectionProvider();
		if (adapter == TMCLDiagramEditor.class)
			return this;
		if (adapter == EditDomain.class)
			return getEditingDomain();
		return super.getAdapter(adapter);
	}

*/
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
				Object model = part.getModel();
				if (model instanceof TypeNode) {
					TypeNode node = (TypeNode) model;
					currentSelection = new StructuredSelection(node.getType());
				} else if (model instanceof AssociationNode) {
					AssociationNode node = (AssociationNode) model;
					currentSelection = new StructuredSelection(node.getAssociationConstraint());
				}
					// TODO Connections
				
				fireSelectionChanged();
			}
		}
	}


	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (selectionChangedListeners==Collections.EMPTY_LIST)
			selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
			
		selectionChangedListeners.add(listener);
	}


	@Override
	public ISelection getSelection() {
		return (currentSelection==null) ? new StructuredSelection() : currentSelection;
	}


	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (selectionChangedListeners!=Collections.EMPTY_LIST)
			selectionChangedListeners.remove(listener);
	}


	@Override
	public void setSelection(ISelection selection) {
		currentSelection = selection;
	}
	
	private void fireSelectionChanged() {
		SelectionChangedEvent event = new SelectionChangedEvent(this, currentSelection);
		
		for (ISelectionChangedListener l : selectionChangedListeners) {
			l.selectionChanged(event);
		}
	}

}
