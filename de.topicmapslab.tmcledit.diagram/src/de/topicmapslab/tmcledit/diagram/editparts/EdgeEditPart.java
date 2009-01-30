package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Collections;
import java.util.List;

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
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RolePlayerConstraints;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetLabelPosCommand;

public class EdgeEditPart extends AdapterConnectionEditPart {
	protected IFigure createFigure()
	{
		figure = (PolylineConnection) super.createFigure();
		((PolylineConnection)figure).setConnectionRouter(new BendpointConnectionRouter());
		
		
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
			((PolylineConnection)figure).setTargetDecoration(deco);
		} else if (getCastedModel().getType() == EdgeType.IS_ATYPE) {
			PolylineDecoration deco = new PolylineDecoration();
			PointList points = new PointList();
			points.addPoint(new Point(-2, -2));
			points.addPoint(new Point(0, 0));
			points.addPoint(new Point(-2, 2));
			deco.setTemplate(points);
			deco.setFill(false);
			((PolylineConnection)figure).setTargetDecoration(deco);
		}
		return figure;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		if (getCastedModel().getType()==EdgeType.ROLE_CONSTRAINT_TYPE) {
			EList<LabelPos> labelPositions = getCastedModel().getLabelPositions();
			if (labelPositions.size()==0) {
				LabelPos pos = ModelFactory.eINSTANCE.createLabelPos();
				pos.setPosX(0);
				pos.setPosY(0);
				labelPositions.add(pos);
				pos = ModelFactory.eINSTANCE.createLabelPos();
				pos.setPosX(0);
				pos.setPosY(0);
				labelPositions.add(pos);				
			}
			return labelPositions;
		}
		return Collections.emptyList();
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		super.addChildVisual(childEditPart, index);
	}
	
	public Edge getCastedModel() {
		return (Edge) getModel();
	}
	
	@Override
	protected void removeChild(EditPart child) {
		if (child instanceof MoveableLabelEditPart)
			return;
		super.removeChild(child);
	}
	
	@Override
	protected void createEditPolicies() {
		
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {
			
			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return new NonResizableEditPolicy();
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected Command getMoveChildrenCommand(Request request) {
				if (request.getType()==RequestConstants.REQ_MOVE_CHILDREN) {
					TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
					ChangeBoundsRequest req = (ChangeBoundsRequest) request;
					List<EditPart> epList = req.getEditParts();
					
					MoveableLabelEditPart ep = (MoveableLabelEditPart) epList.get(0); 
					
					LabelPos pos = (LabelPos) ep.getModel();
					
					int x = pos.getPosX()+req.getMoveDelta().x;
					int y = pos.getPosY()+req.getMoveDelta().y;
					
										
					return new CommandAdapter(ed.getEditingDomain()
							.getCommandStack(), 
							new SetLabelPosCommand((LabelPos) ep.getModel(), x, y));
					
				}
				
				return null;
			}

			@Override
			protected Command createChangeConstraintCommand(EditPart child,
					Object constraint) {
				return null;
			}
			
		});
		
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
		RolePlayerConstraints roleConstraint = getCastedModel().getRoleConstraint();
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
		RolePlayerConstraints roleConstraint = getCastedModel().getRoleConstraint();
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
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ROLE_PLAYER_CONSTRAINTS__TYPE) {
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
			RolePlayerConstraints rtc = getCastedModel().getRoleConstraint();
			
			String typeText = "no role type set";
			if (rtc.getRole()!=null)
				typeText = rtc.getRole().getType().getName();
			
			if (getChildren().size()>0) {
				MoveableLabelEditPart cardEditPart = (MoveableLabelEditPart) getChildren().get(0);
				MoveableLabelEditPart typeEditPart = (MoveableLabelEditPart) getChildren().get(1);
				cardEditPart.setText(rtc.getCardMin()+".."+rtc.getCardMax());
				
				typeEditPart.setText(typeText);
				
				cardEditPart.refreshVisuals();
				typeEditPart.refreshVisuals();	
			}
		}
		
	}
}
