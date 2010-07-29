package de.topicmapslab.tmcledit.application.commands;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.application.DiagramEditorActionBarAdvisor;
import de.topicmapslab.tmcledit.model.preferences.RecentUsedManager;

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
 * @author Hannes Niederhausen
 *
 */
public class OpenFileHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell shell = workbench.getActiveWorkbenchWindow().getShell();
		FileDialog dlg = new FileDialog(shell);
		dlg.setText("Open Onotoa Model..");
		dlg.setFilterExtensions(new String[]{"*.ono;*.tmcl", "*.ono", "*.tmcl"});
		dlg.setFilterPath(System.getProperty("user.home"));
		
		
		List<String> filesList = RecentUsedManager.getFilesList();
		if (!filesList.isEmpty()) {
			String file = filesList.get(0);
			int idx = file.lastIndexOf("/");
			if (idx==-1)
				return null;
			dlg.setFilterPath(file.substring(0, idx));
		}
		
		String path = dlg.open();
		if (path!=null) {
			if ( (!path.endsWith(".tmcl")) && ((!path.endsWith(".ono"))) )
				path += ".ono";
						
			DiagramEditorActionBarAdvisor.openModelView(workbench, path, false);
		}
		return null;
		
	}

}
