package de.topicmapslab.tmcledit.diagram.editor;
import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorLauncher;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

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
 * This launcher switches to the Onotoa Perspective and sets the filename to the
 * Onotoa model view.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class OnototaLauncher implements IEditorLauncher {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IEditorLauncher#open(org.eclipse.core.runtime.IPath)
	 */
	public void open(IPath filePath) {
		System.out.println(filePath.toOSString());
		try {
			String filename = filePath.toOSString();

			File file = new File(filename);

			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			String modelViewId = "de.topicmapslab.tmcledit.extensions.views.ModelView";
			workbenchWindow.getWorkbench().showPerspective(
					"de.topicmapslab.tmcledit.extensions.OnotoaPerspective",
					workbenchWindow);
			page.showView(modelViewId);
			ViewPart modelView = (ViewPart) page.findView(modelViewId);
			if (modelView != null) {
				String key;
				// we check if the file exists and it is not empty
				if ((file.exists()) && (file.length() != 0L))
					key = "filename";
				else
					// if one of the cases is true we want a new file
					key = "newfilename";

				modelView.setPartProperty(key, filename);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
