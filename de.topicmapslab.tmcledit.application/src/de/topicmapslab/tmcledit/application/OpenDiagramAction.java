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

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.FileDialog;

/**
 * @author Hannes Niederhausen
 *
 */
public class OpenDiagramAction extends WorkbenchWindowActionDelegate {
	@Override
	public void run(IAction action) {
		FileDialog dlg = new FileDialog(getWindow().getShell());
		dlg.setFilterExtensions(new String[]{"*.tmcl"});
		dlg.setFilterPath(System.getProperty("user.home"));
		
		String path = dlg.open();
		if (path!=null) {
			if (!path.endsWith(".tmcl"))
				path += ".tmcl";
						
			DiagramEditorActionBarAdvisor.openModelView(getWindow().getWorkbench(), path, false);
		}
		
	}
}
