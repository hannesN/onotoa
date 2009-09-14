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
package de.topicmapslab.tmcledit.application;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * @generated
 */
public class AboutHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Bundle bundle = Platform.getBundle("de.topicmapslab.tmcledit.edit");
		String version = (String) bundle.getHeaders().get(
				org.osgi.framework.Constants.BUNDLE_VERSION);

		version = "\nVersion: " + version;
		String copyright = "\n(C) 2009, Hannes Niederhausen, Topic Maps Lab";

		StringBuffer buffer = new StringBuffer();

		buffer.append(Messages.DiagramEditorActionBarAdvisor_AboutDialogMessage);
		buffer.append(version);
		buffer.append(copyright);

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		MessageDialog.openInformation(shell,
				Messages.DiagramEditorActionBarAdvisor_AboutDialogTitle, buffer
						.toString());
		return null;
	}
}