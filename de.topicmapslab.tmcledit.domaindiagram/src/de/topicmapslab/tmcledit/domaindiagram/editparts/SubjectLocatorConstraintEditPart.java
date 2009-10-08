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

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;

public class SubjectLocatorConstraintEditPart extends AbstractLabelEditPart {

	public SubjectLocatorConstraintEditPart() {
		setEditable(false);
	}

	private SubjectLocatorConstraint getCastedModel() {
		return (SubjectLocatorConstraint) getModel();
	}

	@Override
	protected void createEditPolicies() {
	}

	@Override
	protected void refreshVisuals() {
		SubjectLocatorConstraint slc = getCastedModel();
		getNameLabel().setText("Subject Locator");
		StringBuffer buffer = new StringBuffer(50);

		if (!(".*".equals(slc.getRegexp()))) {
			buffer.append("[");
			buffer.append(slc.getRegexp());
			buffer.append("]");
			getRegExpLabel().setText(buffer.toString());
		} else {
			getRegExpLabel().setText(buffer.toString());
		}
		buffer.setLength(0);
		buffer.append(slc.getCardMin());
		buffer.append("..");
		buffer.append(slc.getCardMax());
		getCardLabel().setText(buffer.toString());
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.SET)
			refreshVisuals();

	}

}
