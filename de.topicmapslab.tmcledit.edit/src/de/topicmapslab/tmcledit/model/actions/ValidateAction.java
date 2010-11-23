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

/**
 * This actions triggers an validation event.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ValidateAction extends Action {
	IWorkbenchPartSite site = null;
	
	/**
	 * Constructor
	 */
	public ValidateAction() {
		setText("Validate");
		setToolTipText("Validate the TMCL schema");
		setImageDescriptor(ImageProvider.getImageDescriptor(ImageConstants.VALIDATE));
	}
	
	
	/**
	 * Constructor
	 * @param site the {@link IWorkbenchPartSite} of the actions container
	 */
	public ValidateAction(IWorkbenchPartSite site) {
	    this();
	    this.site = site;
    }


	/**
	 * Sets the {@link IWorkbenchPartSite} of the container
	 * @param site the {@link IWorkbenchPartSite} of the container
	 */
	public void setSite(IWorkbenchPartSite site) {
		this.site = site;
	}
	
	/**
	 * {@inheritDoc}
	 */
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