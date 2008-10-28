package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import de.topicmapslab.tmcledit.diagram.policies.NodeGraphicalNodeEditPolicy;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;

public abstract class NodeEditPart extends AdapterGraphicalEditPart implements org.eclipse.gef.NodeEditPart{

	public NodeEditPart() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelSourceConnections() {
		
		List<Edge> resultList = new ArrayList<Edge>();
		Diagram diagram = (Diagram) getParent().getModel();
		for (Edge e : diagram.getEdges()) {
			if (e.getSource().equals(getModel())) {
				resultList.add(e);
			}
		}
		return resultList;
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());	
	}
		
	@Override
	protected void refreshVisuals() {
		// refreshing mapping element, because it should always be in the upper corner
        PrefixMappingEditPart pmep = ((DiagramEditPart)getParent()).getPrefixMappingEditPart();
        if (pmep!=null)
        	pmep.refreshVisuals();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelTargetConnections() {
		List<Edge> resultList = new ArrayList<Edge>();
		Diagram diagram = (Diagram) getParent().getModel();
		for (Edge e : diagram.getEdges()) {
			if (e.getTarget().equals(getModel())) {
				resultList.add(e);
			}
		}
		return resultList;
	}
	
	@Override
	public void activate() {
		super.activate();
		Diagram d = (Diagram) getParent().getModel();
		d.eAdapters().add(this);
	}
	
	@Override
	public void deactivate() {
		Diagram d = (Diagram) getParent().getModel();
		d.eAdapters().remove(this);
		super.deactivate();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

}