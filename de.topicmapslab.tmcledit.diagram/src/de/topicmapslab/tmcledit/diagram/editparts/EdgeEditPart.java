package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.Edge;
import de.topicmapslab.tmcledit.diagram.model.EdgeType;
import de.topicmapslab.tmcledit.diagram.model.ModelPackage;

public class EdgeEditPart extends AdapterConnectionEditPart {
	
	protected IFigure createFigure()
	{
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		
		
		if (getCastedModel().getType() == EdgeType.IS_ATYPE) {
			PolygonDecoration deco = new PolygonDecoration();
			PointList points = new PointList();
			points.addPoint(new Point(-2, -2));
			points.addPoint(new Point(0, 0));
			points.addPoint(new Point(-2, 2));
			points.addPoint(new Point(-2, -2));
			deco.setTemplate(points);
			deco.setFill(true);
			deco.setBackgroundColor(ColorConstants.white);
			conn.setTargetDecoration(deco);
		} else if (getCastedModel().getType() == EdgeType.AKO_TYPE) {
			PolylineDecoration deco = new PolylineDecoration();
			PointList points = new PointList();
			points.addPoint(new Point(-2, -2));
			points.addPoint(new Point(0, 0));
			points.addPoint(new Point(-2, 2));
			deco.setTemplate(points);
			deco.setFill(false);
			conn.setTargetDecoration(deco);
		}
		
		
		return conn;
	}
	
	public Edge getCastedModel() {
		return (Edge) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new ConnectionEditPolicy() {
            protected Command getDeleteCommand(GroupRequest request) {
                return null;//new ConnectionDeleteCommand(getCastedModel());
            }
        });

	}

	@Override
	public void activate() {
		
		((Diagram)getRoot().getContents().getModel()).eAdapters().add(adapter);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		((Diagram)getRoot().getContents().getModel()).eAdapters().remove(adapter);
		super.deactivate();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER)
			return;
		
		if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__EDGES) {
			if (getTarget()!=null)
				getTarget().refresh();
			if (getSource()!=null)
				getSource().refresh();			
		} else
			refreshVisuals();
		
	}
	
	@Override
	public void setTarget(EditPart editPart) {
		super.setTarget(editPart);
	}

	@Override
	public void setSource(EditPart editPart) {
		super.setSource(editPart);
	}
}
