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

import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;


public class SubjectIdentifierConstraintEditPart extends AbstractLabelEditPart {
	
	public SubjectIdentifierConstraintEditPart() {
		setEditable(false);
	}
	
	private SubjectIdentifierConstraint getCastedModel() {
		return (SubjectIdentifierConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		SubjectIdentifierConstraint sic = getCastedModel();
		getNameLabel().setText("Subject Identifier");
		
		
		StringBuffer buffer = new StringBuffer(50);
		
		if (!(".*".equals(sic.getRegexp()))) {
			buffer.append("[");
			buffer.append(sic.getRegexp());
			buffer.append("]");
			getRegExpLabel().setText(buffer.toString());
		} else {
			getRegExpLabel().setText(buffer.toString());
		}
		buffer.setLength(0);
		buffer.append(sic.getCardMin());
		buffer.append("..");
		buffer.append(sic.getCardMax());
		getCardLabel().setText(buffer.toString());
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

	
}
