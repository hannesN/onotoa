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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
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
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;

import de.topicmapslab.tmcledit.diagram.action.SetRoleAction;
import de.topicmapslab.tmcledit.diagram.action.SetRoleData;
import de.topicmapslab.tmcledit.diagram.policies.LabelXYLayoutEditPolicy;
import de.topicmapslab.tmcledit.diagram.policies.OnotoaBendpointEditPolicy;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * The editpart for an edge
 * 
 * @author Hannes Niederhausen
 *
 */
public class EdgeEditPart extends AdapterConnectionEditPart {
	private BendpointConnectionRouter router;

	@Override
	protected IFigure createFigure() {
		figure = new PolylineConnection() {
			@Override
			public void paint(Graphics graphics) {
				graphics.setAntialias(SWT.ON);
				super.paint(graphics);
			}
		};
		router = new BendpointConnectionRouter();
		((PolylineConnection) figure).setConnectionRouter(router);

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
			((PolylineConnection) figure).setTargetDecoration(deco);
		} else if (getCastedModel().getType() == EdgeType.IS_ATYPE) {
			PolylineDecoration deco = new PolylineDecoration();
			PointList points = new PointList();
			points.addPoint(new Point(-2, -2));
			points.addPoint(new Point(0, 0));
			points.addPoint(new Point(-2, 2));
			deco.setTemplate(points);
			deco.setFill(false);
			((PolylineConnection) figure).setTargetDecoration(deco);
		}

		return figure;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		if (getCastedModel().getType() == EdgeType.ROLE_CONSTRAINT_TYPE) {
			EList<LabelPos> labelPositions = getCastedModel()
					.getLabelPositions();
			if (labelPositions.size() == 0) {
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

	private Edge getCastedModel() {
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

		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LabelXYLayoutEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new OnotoaBendpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new ConnectionEditPolicy() {
					@Override
					protected Command getDeleteCommand(GroupRequest request) {
						return null;// new
									// ConnectionDeleteCommand(getCastedModel());
					}
				});

	}

	@Override
	public void activate() {
		((Diagram) getRoot().getContents().getModel()).eAdapters().add(adapter);
		RolePlayerConstraint roleConstraint = getCastedModel()
				.getRoleConstraint();
		if (roleConstraint != null) {
			roleConstraint.eAdapters().add(adapter);
			if (roleConstraint.getRole() != null) {
				roleConstraint.getRole().eAdapters().add(adapter);
				roleConstraint.getRole().getType().eAdapters().add(adapter);
			}
		}
		super.activate();
	}

	@Override
	public void deactivate() {
		((Diagram) getRoot().getContents().getModel()).eAdapters().remove(
				adapter);
		RolePlayerConstraint roleConstraint = getCastedModel()
				.getRoleConstraint();
		if (roleConstraint != null) {
			roleConstraint.eAdapters().remove(adapter);
			if (roleConstraint.getRole() != null) {
				roleConstraint.getRole().eAdapters().remove(adapter);
				roleConstraint.getRole().getType().eAdapters().remove(adapter);
			}

		}
		super.deactivate();
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER)
			return;

		if (notification.getNotifier() != null) {
			if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__EDGES) {
				if (getTarget() != null)
					getTarget().refresh();
				if (getSource() != null)
					getSource().refresh();
			} else if (notification.getNotifier().equals(
					getCastedModel().getRoleConstraint())) {
				if (notification.getFeatureID(TopicType.class) == ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE) {
					RoleConstraint tmp = (RoleConstraint) notification
							.getOldValue();
					if (tmp != null)
						tmp.eAdapters().remove(adapter);
					tmp = (RoleConstraint) notification.getNewValue();
					if (tmp != null)
						tmp.eAdapters().add(adapter);
				}
			}
		}
		refreshVisuals();
	}

	@Override
	protected void refreshVisuals() {
		// set bendpoints
		List<Bendpoint> points = null;
		if (getCastedModel().getBendpoints().size()>0) {
			points = new ArrayList<Bendpoint>(getCastedModel().getBendpoints().size());
			for (de.topicmapslab.tmcledit.model.Bendpoint bp : getCastedModel().getBendpoints()) {
				points.add(new AbsoluteBendpoint(bp.getPosX(), bp.getPosY()));
			}
		}
		router.setConstraint((Connection) getFigure(), points);
		getFigure().revalidate();
		if (getCastedModel().getRoleConstraint()!=null) {
			RolePlayerConstraint rtc = getCastedModel().getRoleConstraint();
			
			String roleText = "no role type set";
			if (rtc.getRole()!=null)
				roleText = rtc.getRole().getCardMin() + ".."
						+ rtc.getRole().getCardMax() + "\n"
						+ rtc.getRole().getType().getName();
			
			if (getChildren().size()>0) {
				MoveableLabelEditPart roleEditPart = (MoveableLabelEditPart) getChildren().get(0);
				MoveableLabelEditPart rolePlayerCardEditPart = (MoveableLabelEditPart) getChildren().get(1);
				
				roleEditPart.setText(roleText);
				
				rolePlayerCardEditPart.setText(rtc.getCardMin()+".."+rtc.getCardMax());
				
				
				rolePlayerCardEditPart.refreshVisuals();
				roleEditPart.refreshVisuals();
			}
		}

	}
	
	@Override
	public List<IContributionItem> getItems() {
		if (getCastedModel().getType()!=EdgeType.ROLE_CONSTRAINT_TYPE)
			return super.getItems();
		
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Role Type");

		Edge e = getCastedModel();
		
		AssociationNode n = (AssociationNode) e.getSource();
		AssociationType at = (AssociationType) n.getAssociationConstraint().getType();
		
		SetRoleData data = new SetRoleData();
		data.rpc = getCastedModel().getRoleConstraint();
		data.editDomain = getEditDomain();
		data.role = null;
		data.schema = (TopicMapSchema) at.eContainer();

		
		List <SetRoleData> dataList = new ArrayList<SetRoleData>();
		for (RoleConstraint rc : at.getRoles()) {
			SetRoleData d = data.clone();
			d.role =  rc.getType();
			dataList.add(d);
		}
		

		Collections.sort(dataList);
		
		for (SetRoleData d : dataList)
			subMenu.add(new SetRoleAction(d));
		
		result.add(subMenu);

		return result;
		
	}
}
