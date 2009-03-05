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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import de.topicmapslab.tmcledit.model.provider.TmcleditEditPlugin;

/**
 * @generated
 */
public class AboutAction extends Action {

	
	
	public AboutAction() {
		setText("&About..");
		setId("about action");
	}
	
	@Override
	public void run() {
		Bundle bundle = TmcleditEditPlugin.getPlugin().getBundle();
		String version = (String) bundle.getHeaders().get(org.osgi.framework.Constants.BUNDLE_VERSION);
//		int i = version.lastIndexOf(".0.0");
//		if (i > -1)
//			version = version.substring(0, i);
		
		version = "\nVersion: " + version;
		String copyright = "\n(C) 2009, Hannes Niederhausen, Topic Maps Lab";
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(Messages.DiagramEditorActionBarAdvisor_AboutDialogMessage);
		buffer.append(version);
		buffer.append(copyright);
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openInformation(shell,
				Messages.DiagramEditorActionBarAdvisor_AboutDialogTitle,
				buffer.toString());
	}
}