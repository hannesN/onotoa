package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicType;

public class EdgeEditPart extends AdapterConnectionEditPart {
	
	private Label cardLabel;
	private Label typeLabel;
	
	protected IFigure createFigure()
	{
		PolylineConnection conn = (PolylineConnection) super.createFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		
		
		if (getCastedModel().getType() == EdgeType.AKO_TYPE) {
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
		} else if (getCastedModel().getType() == EdgeType.IS_ATYPE) {
			PolylineDecoration deco = new PolylineDecoration();
			PointList points = new PointList();
			points.addPoint(new Point(-2, -2));
			points.addPoint(new Point(0, 0));
			points.addPoint(new Point(-2, 2));
			deco.setTemplate(points);
			deco.setFill(false);
			conn.setTargetDecoration(deco);
		} else if (getCastedModel().getType() == EdgeType.ROLE_CONSTRAINT_TYPE) {
			addConnectionLabels(conn);			
		}
		return conn;
	}
	
	private void addConnectionLabels(PolylineConnection connection) {
		ConnectionEndpointLocator locator = new ConnectionEndpointLocator(connection, true);
		locator.setUDistance(15);
		locator.setVDistance(15);
		cardLabel = new Label();
		connection.add(cardLabel, locator);
		
		locator = new ConnectionEndpointLocator(connection, true);
		locator.setUDistance(35);
		locator.setVDistance(15);
		typeLabel = new Label();
		connection.add(typeLabel, locator);

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
		RoleTypeConstraints roleConstraint = getCastedModel().getRoleConstraint();
		if (roleConstraint!=null) {
			roleConstraint.eAdapters().add(adapter);
			if (roleConstraint.getType()!=null)
				roleConstraint.getType().eAdapters().add(adapter);
		}
		super.activate();
	}
	
	@Override
	public void deactivate() {
		((Diagram)getRoot().getContents().getModel()).eAdapters().remove(adapter);
		RoleTypeConstraints roleConstraint = getCastedModel().getRoleConstraint();
		if (roleConstraint!=null) {
			roleConstraint.eAdapters().remove(adapter);
			if (roleConstraint.getType()!=null)
				roleConstraint.getType().eAdapters().remove(adapter);
			
		}
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
		
		
		if (notification.getNotifier().equals(getCastedModel().getRoleConstraint())) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE) {
				TopicType tmp = (TopicType) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(adapter);
				tmp = (TopicType) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(adapter);
			}
			refreshVisuals();
		}
	}

	@Override
	protected void refreshVisuals() {
		if (getCastedModel().getRoleConstraint()!=null) {
			RoleTypeConstraints rtc = getCastedModel().getRoleConstraint();
			cardLabel.setText(rtc.getCardMin()+".."+rtc.getCardMax());
			if (rtc.getType()!=null)
				typeLabel.setText(rtc.getType().getName());
			else
				typeLabel.setText("no role type set");
		}
		
	}
}
