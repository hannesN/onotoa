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
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.extensions.views.ValidationErrorView;

/**
 * @author Hannes Niederhausen
 *
 */
public class ShowErrorViewAction extends Action {

	public ShowErrorViewAction() {
		setText("Show Error View");
		setToolTipText("Show Validation Error View");
		
	}
	
	@Override
	public void run() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ValidationErrorView.ID);
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
	}

}
