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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.handlers.IHandlerService;

import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class ValidateAction extends Action {
	IWorkbenchPartSite site = null;
	
	public ValidateAction() {
		setText("Validate");
		setToolTipText("Validate the TMCL schema");
		setImageDescriptor(ImageProvider.getImageDescriptor(ImageConstants.VALIDATE));
	}
	
	
	
	public ValidateAction(IWorkbenchPartSite site) {
	    this();
	    this.site = site;
    }



	public void setSite(IWorkbenchPartSite site) {
		this.site = site;
	}
	
	@Override
	public void run() {
		try {
			IHandlerService handlerService = (IHandlerService) site.getService(IHandlerService.class);
			handlerService.executeCommand("de.topicmapslab.tmcledit.model.validatecommand", null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}