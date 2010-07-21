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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.tmcledit.model.views.ValidationErrorView;

/**
 * @author Hannes Niederhausen
 *
 */
public class ShowValidationErrorViewHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow workbench = HandlerUtil.getActiveWorkbenchWindow(event);
		
		try {
			workbench.getActivePage().showView(ValidationErrorView.ID);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}

}
