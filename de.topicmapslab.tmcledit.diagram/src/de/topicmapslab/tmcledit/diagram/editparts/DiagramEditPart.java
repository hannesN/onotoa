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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.swt.SWT;

import de.topicmapslab.tmcledit.diagram.policies.DiagramLayoutEditPolicy;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DiagramEditPart extends AdapterGraphicalEditPart {

	private XYLayout layout;

	private PrefixMappingEditPart prefixMappingEditPart;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			// override to activate antialiasing
			figure = new FreeformLayer() {
				@Override
				public void paint(Graphics graphics) {
					graphics.setAntialias(SWT.ON);
					super.paint(graphics);
				}
			};
			figure.setOpaque(true);
			figure.setBackgroundColor(ColorConstants.white);
			layout = new FreeformLayout() {
				@SuppressWarnings("unchecked")
				@Override
				public void layout(IFigure parent) {
					Iterator children = parent.getChildren().iterator();
					Point offset = getOrigin(parent);
					IFigure f;
					int minX = 0;
					int minY = 0;
					while (children.hasNext()) {
						f = (IFigure)children.next();
						Rectangle bounds = (Rectangle)getConstraint(f);
						if (bounds == null) continue;

						if (bounds.width == -1 || bounds.height == -1) {
							Dimension preferredSize = f.getPreferredSize(bounds.width, bounds.height);
							bounds = bounds.getCopy();
							if (bounds.width == -1)
								bounds.width = preferredSize.width;
							if (bounds.height == -1)
								bounds.height = preferredSize.height;
						}
						bounds = bounds.getTranslated(offset);
						minX = Math.min(bounds.x, minX);
						minY = Math.min(bounds.y, minY);
						f.setBounds(bounds);
						
					}
					
					if (getPrefixMappingEditPart()!=null) {
						IFigure figure = getPrefixMappingEditPart().getFigure();
						figure.setLocation(new Point(minX, minY));
						figure.setSize(figure.getPreferredSize());
					}
				}
			};
			figure.setLayoutManager(layout);
		}
		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());

	}

	// tiny hack to omit a mess of listeners/adapters

	@Override
	protected void addChild(EditPart child, int index) {
		super.addChild(child, index);
		if (child instanceof PrefixMappingEditPart) {
			this.prefixMappingEditPart = (PrefixMappingEditPart) child;
		}
	}

	public PrefixMappingEditPart getPrefixMappingEditPart() {
		return prefixMappingEditPart;
	}

	// hack end

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		Diagram d = (Diagram) getModel();
		List result = new ArrayList();
		result.addAll(d.getNodes());
		result.add(((File) d.eContainer()).getTopicMapSchema().getMappings());
		result.addAll(d.getComments());
		return result;
	}

	@SuppressWarnings("unchecked")
	private void updateEdges(Edge edge) {
		// find editparts for edges nodes
		NodeEditPart sep = null;
		NodeEditPart tep = null;
		Iterator it = getChildren().iterator();
		while ((it.hasNext()) && ((sep == null) || (tep == null))) {
			Object nextObj = it.next();
			if (nextObj instanceof TypeNodeEditPart) {
				NodeEditPart tmpEp = (NodeEditPart) nextObj;
				if ((tmpEp.getModel().equals(edge.getSource()))
						|| (tmpEp.getModel().equals(edge.getTarget())))
					tmpEp.refresh();
			} else if (nextObj instanceof AssociationNodeEditPart) {
				if (edge.getSource().equals(
						((AssociationNodeEditPart) nextObj).getModel()))
					((NodeEditPart) nextObj).refresh();
			}
		}
	}

	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__EDGES) {
			if (notification.getEventType() == Notification.ADD) {
				updateEdges((Edge) notification.getNewValue());
			}
		} else if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__NODES) {
			refreshChildren();
		} else if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__COMMENTS) {
			refreshChildren();
		}
	}

}
