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
/**
 * 
 */
package de.topicmapslab.tmcledit.application;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hannes Niederhausen
 *
 */
public class OpenDiagramAction extends Action {
	
	public OpenDiagramAction() {
		setText("Open..");
	}
	
	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell shell = workbench.getActiveWorkbenchWindow().getShell();
		FileDialog dlg = new FileDialog(shell);
		dlg.setFilterExtensions(new String[]{"*.ono", "*.tmcl"});
		dlg.setFilterPath(System.getProperty("user.home"));
		
		String path = dlg.open();
		if (path!=null) {
			if ( (!path.endsWith(".tmcl")) && ((!path.endsWith(".ono"))) )
				path += ".ono";
						
			DiagramEditorActionBarAdvisor.openModelView(workbench, path, false);
		}
		
	}
}
