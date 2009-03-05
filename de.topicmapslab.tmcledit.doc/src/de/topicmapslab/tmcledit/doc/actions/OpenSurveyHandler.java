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
package de.topicmapslab.tmcledit.doc.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.doc.dialogs.BrowserDialog;
import de.topicmapslab.tmcledit.extensions.Activator;

/**
 * @author Hannes Niederhausen
 *
 */
public class OpenSurveyHandler extends AbstractHandler{

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		BrowserDialog dlg = new BrowserDialog((Shell) null, false);
		dlg.setTitle("Online Survey");
		dlg.setUrl("http://survey.onotoa.topicmapslab.de/");
		dlg.open();
		Activator.getDefault().getPreferenceStore().setValue("survey_done", true);
		return null;
	}


}
