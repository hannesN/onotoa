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

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TmcleditApplication implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		String [] args = (String[]) context.getArguments().get("application.args");
		String newLoc = System.getProperty("user.home")+"/.onotoa";
		
		Location workspaceLoc = Platform.getInstanceLocation();
		workspaceLoc.release();
		workspaceLoc.set(new URL("file:"+newLoc), false);
		
		
		Display display = PlatformUI.createDisplay();
		
		try {
			DiagramEditorWorkbenchAdvisor advisor = new DiagramEditorWorkbenchAdvisor();
			advisor.setArguments(args);
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					advisor);
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	public void stop() {
	}
}
