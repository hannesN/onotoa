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

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import de.topicmapslab.tmcledit.diagram.policies.OccurrenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurrenceTypeConstraintEditPart extends AbstractScopedLabeledEditPart {

	private static UnderlineLabelBorder border = new UnderlineLabelBorder();
	
	OccurrenceTypeConstraint getCastedModel() {
		return (OccurrenceTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new OccurrenceConstraintDirectEditPolicy());
	}

	@Override
	protected void refreshVisuals() {
		OccurrenceTypeConstraint otc = getCastedModel();
		StringBuffer buffer = new StringBuffer();
		
		if (otc.getType()!=null) {
			if (((OccurrenceType) otc.getType()).isUnique())
				getNameLabel().setBorder(border);
			else
				getNameLabel().setBorder(null);
			getNameLabel().setText(otc.getType().getName());
		} else
			getNameLabel().setText("tmdm:subject");
		
		buffer.append(" : ");
		if (otc.getType() instanceof OccurrenceType)
			buffer.append(((OccurrenceType) otc.getType()).getDataType());
		else
			buffer.append("xsd:anyType");
		buffer.append(" ");
		buffer.append(otc.getCardMin());
		buffer.append("..");
		buffer.append(otc.getCardMax());	
		clearScopeLables();
		addScopeText();
		
		getTypeLabel().setText(buffer.toString());
		getFigure().revalidate();
		getFigure().getParent().repaint();
	}

	@Override
	public void activate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();

	}
	
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getEventType()==Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				if (notification.getNewValue()!=null)
					((TopicType) notification.getNewValue()).eAdapters().add(this);
			
			}
		}
		refreshVisuals();
		
	}

	private static class UnderlineLabelBorder extends AbstractBorder {

		public Insets getInsets(IFigure figure) {
			return new Insets(0, 0, 1, 0);
		}

		@Override
		public Dimension getPreferredSize(IFigure figure) {
			Dimension preferredSize = figure.getPreferredSize();
			return new Dimension(preferredSize.width, preferredSize.height+1);
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			Rectangle rec = figure.getBounds();
			int y = rec.y+rec.height-1;
			graphics.pushState();
			graphics.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			graphics.drawLine(rec.x, y, rec.x+rec.width, y);
			graphics.popState();
		}
	}
}
