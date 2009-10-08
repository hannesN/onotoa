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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.GraphicalEditPart;

import de.topicmapslab.tmcledit.model.MappingElement;

public class PrefixMappingElementEditPart extends AdapterGraphicalEditPart {

	private Label keyLabel;
	private Label uriLabel;
	
	@Override
	protected IFigure createFigure() {
		return new Figure();
	}
	
	public Label getKeyLabel() {
		if (keyLabel == null) {
			keyLabel = new Label();
		}

		return keyLabel;
	}
	
	public Label getUriLabel() {
		if (uriLabel == null) {
			uriLabel = new Label();
		}

		return uriLabel;
	}
	
	@Override
	protected void createEditPolicies() {
	}
	@Override
	protected void refreshVisuals() {
		keyLabel.setText(getCastedModel().getKey());
		uriLabel.setText(getCastedModel().getValue());
	}
	
	private MappingElement getCastedModel() {
		return (MappingElement) getModel();
	}
	
	@Override
	public IFigure getContentPane() {
		return ((GraphicalEditPart)getParent()).getContentPane();
	}
	
	public void notifyChanged(Notification notification) {
		refreshVisuals();
	}

}
