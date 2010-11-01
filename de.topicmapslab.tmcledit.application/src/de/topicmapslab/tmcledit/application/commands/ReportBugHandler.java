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
package de.topicmapslab.tmcledit.application.commands;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author bosso
 *
 */
public class ReportBugHandler extends AbstractHandler {

	private static final String URI = "http://code.google.com/a/eclipselabs.org/p/onotoa/issues/list";

	public Object execute(ExecutionEvent event) throws ExecutionException {

		
//		MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Report a Bug...", 
//			"Please report your bug at "+uriString);
		
		new Message(HandlerUtil.getActiveShell(event)).open();
		
		return null;
	}
	
	private class Message extends MessageDialog {

		public Message(Shell parentShell) {
			super(parentShell,  
					"Report a Bug...", 
					null, 
					"Please report your bug at ",
					INFORMATION, 
					new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 
					0);
			
		}
		
		@Override
		public Image getInfoImage() {
			return null;
		}
		
		@Override
		protected Control createCustomArea(Composite parent) {
			Link l = new Link(parent, SWT.NONE);
			l.setText("<a>"+URI+"</a>");
			l.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					try {
						Desktop.getDesktop().browse(new URI(URI));
					} catch (Exception e) {
						MessageDialog.openError(getShell(),
								"Can't open browser", 
								"The java runtime could not open your browser.\n" +
								"Please open the website manually.");
					}
				}

			});
			
			return l;
		}
		
	}
}
