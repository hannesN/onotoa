/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

import de.topicmapslab.tmcledit.diagram.RemoveFromDiagramAction;
import de.topicmapslab.tmcledit.diagram.DiagramController;
import de.topicmapslab.tmcledit.diagram.model.AssociationNode;
import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.Edge;
import de.topicmapslab.tmcledit.diagram.model.EdgeType;
import de.topicmapslab.tmcledit.diagram.model.Node;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLDiagramEditor extends GraphicalEditorWithFlyoutPalette
		implements ISelectionChangedListener, ISelectionProvider {
	//extends EditorPart {

	private Diagram diagram;
	
//	private EditDomain editDomain;
	
	private RootEditPart rootEditPart;
//	private GraphicalViewer graphicalViewer;

	private ISelection currentSelection;
	private List<ISelectionChangedListener> selectionChangedListeners = Collections.emptyList();

	private RemoveFromDiagramAction removeFromDiagramAction;

	public TMCLDiagramEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

		
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(diagram); // set the contents of this editor
		viewer.addSelectionChangedListener(this);
		viewer.setEditDomain(getEditDomain());
		
//		getEditDomain().getCommandStack().addCommandStackListener(this);
		getSite().setSelectionProvider(this);
		// listen for dropped parts
		viewer.addDropTargetListener(new TypeDropTransferListener(viewer));
	}
	
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(TMCLDiagramEditorUtil.getEditPartFactory());
		viewer.setRootEditPart(new ScalableFreeformRootEditPart());
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
		
		
		// configure the context menu provider
		ContextMenuProvider cmProvider =
				new TMCLEditorContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(cmProvider);
		getSite().registerContextMenu(cmProvider, viewer);
		
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
	
	public Diagram getDiagram() {
		return diagram;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		URIEditorInput ei = (URIEditorInput) getEditorInput();
		Resource resource = new XMIResourceFactoryImpl().createResource(ei.getURI());
		resource.getContents().add(diagram);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		getCommandStack().markSaveLocation();
		
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	protected void createActions() {
		removeFromDiagramAction = new RemoveFromDiagramAction(getEditDomain().getCommandStack());
		getActionRegistry().registerAction(removeFromDiagramAction);
		super.createActions();
	}
	
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = reg.getExtensionToFactoryMap();
		map.put("tmcl", new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		URIEditorInput ei = (URIEditorInput) input;
		File file = new File(ei.getURI().toFileString());
		if (file.exists()) {
			diagram = (Diagram) resSet.getResource(ei.getURI(), true).getContents().get(0);
			
		} else {
			ModelFactory modelInstance = ModelFactory.eINSTANCE;
			de.topicmapslab.tmcledit.diagram.model.ModelFactory diagramModel = de.topicmapslab.tmcledit.diagram.model.ModelFactory.eINSTANCE;
			
			TopicMapSchema schema = modelInstance.createTopicMapSchema(); 
			
			diagram = diagramModel.createDiagram(); 
			diagram.setTopicMapSchema(schema);
			
			TopicType tt = modelInstance.createTopicType();
			tt.setId("wwid:Person");
			schema.getTopicTypes().add(tt);
			
			TopicType tt2 = modelInstance.createTopicType();
			tt2.setId("wwid:Chef");
			tt2.getIsa().add(tt);
			schema.getTopicTypes().add(tt2);
			
			
			TypeNode tn = diagramModel.createTypeNode();
			tn.setPosX(50);
			tn.setPosY(50);
			tn.setTopicType(tt);
			diagram.getNodes().add((Node) tn);
			
			TypeNode tn2 = diagramModel.createTypeNode();
			tn2.setPosX(450);
			tn2.setPosY(50);
			tn2.setTopicType(tt2);
			diagram.getNodes().add((Node) tn2);
			
			Edge e = diagramModel.createEdge();
			e.setSource(tn2);
			e.setTarget(tn);
			e.setType(EdgeType.IS_ATYPE);
			diagram.getEdges().add(e);
			
			OccurenceType ot = modelInstance.createOccurenceType();
			ot.setId("wwid:Adresse");
			schema.getTopicTypes().add(ot);
			
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
		new DiagramController(diagram);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
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
		return super.getAdapter(adapter);
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
					currentSelection = new StructuredSelection(node.getTopicType());
				} else if (model instanceof AssociationNode) {
					AssociationNode node = (AssociationNode) model;
					currentSelection = new StructuredSelection(node.getAssociationTypeConstraint());
				}
					// TODO Connections
				
				fireSelectionChanged();
			}
		}
	}


	private void updateSelectionDependentActions(EditPart selection) {
		removeFromDiagramAction.setSelectedEditPart(selection);
		
		
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
	//	currentSelection = selection;
	}
	
	private void fireSelectionChanged() {
		if (currentSelection==null)
			currentSelection = new StructuredSelection();
		
		SelectionChangedEvent event = new SelectionChangedEvent(this, currentSelection);
		
		for (ISelectionChangedListener l : selectionChangedListeners) {
			l.selectionChanged(event);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void updateActions(List actionIds) {
		super.updateActions(actionIds);
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
}
