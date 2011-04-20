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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.ArrayList;
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
import org.eclipse.swt.SWT;

import de.topicmapslab.tmcledit.domaindiagram.policies.LabelXYLayoutEditPolicy;
import de.topicmapslab.tmcledit.domaindiagram.policies.OnotoaBendpointEditPolicy;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * Editpart for edges
 * @author Hannes Niederhausen
 *
 */
public class EdgeEditPart extends AdapterConnectionEditPart {
	private BendpointConnectionRouter router;

	@Override
	protected IFigure createFigure() {
		PolylineConnection figure = new PolylineConnection() {
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
			figure.setAntialias(5);
			figure.setForegroundColor(ColorConstants.gray);
			figure.setLineStyle(SWT.LINE_DOT);
		} 
		return figure;
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
		
		super.activate();
	}

	@Override
	public void deactivate() {
		((Diagram) getRoot().getContents().getModel()).eAdapters().remove(
				adapter);
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
		

	}
}
