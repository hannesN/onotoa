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

import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Editpart for occurrence constraints
 * @author Hannes Niederhausen
 *
 */
public class OccurrenceTypeConstraintEditPart extends AbstractScopedLabeledEditPart {

	private static UnderlineLabelBorder border = new UnderlineLabelBorder();
	
	OccurrenceTypeConstraint getCastedModel() {
		return (OccurrenceTypeConstraint) getModel();
	}
	
	@Override
	protected boolean isEditable() {
		return false;
	}
	
	@Override
	protected void refreshVisuals() {
		OccurrenceTypeConstraint otc = getCastedModel();
		StringBuffer buffer = new StringBuffer();
		
		TopicType type = otc.getType();
		if (type!=null) {
			if (((OccurrenceType) type).isUnique())
				getNameLabel().setBorder(border);
			else
				getNameLabel().setBorder(null);
			getNameLabel().setText(type.getName());
		} else
			getNameLabel().setText("tmdm:subject");
		
		buffer.append(" : ");
		if (type instanceof OccurrenceType)
			buffer.append(((OccurrenceType) type).getDataType());
		else
			buffer.append("xsd:anyType");

		getTypeLabel().setText(buffer.toString());
		
		buffer.setLength(0);
		buffer.append(otc.getCardMin());
		buffer.append("..");
		buffer.append(otc.getCardMax());
		getCardLabel().setText(buffer.toString());

		if ( (type instanceof OccurrenceType) && (!(".*".equals(((OccurrenceType)type).getRegExp()))) ) {
			getRegExpLabel().setText("["+((OccurrenceType)type).getRegExp()+"]");
		} else {
			getRegExpLabel().setText("");
		}

//		clearScopeLables();
//		addScopeText();
		
//		getTypeLabel().setText(buffer.toString());
//		getFigure().revalidate();
//		getFigure().getParent().repaint();
		refreshChildren();
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
}
