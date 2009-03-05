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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;

import de.topicmapslab.tmcledit.doc.Activator;
import de.topicmapslab.tmcledit.doc.dialogs.BrowserDialog;

/**
 * @author Hannes Hannes Niederhausen
 * 
 */
public class ShowLicenseHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event);

		Bundle bundle = Activator.getDefault().getBundle();
		URL url = bundle.getEntry("epl-v10.html");
		StringBuffer content = new StringBuffer();
		try {
			InputStream is = url.openStream();
			InputStreamReader reader = new InputStreamReader(is);
			int tmp;
			while ((tmp = reader.read()) != -1) {
				content.append((char) tmp);
			}

			BrowserDialog dlg = new BrowserDialog(shell, true);
			dlg.setTitle("License");
			dlg.setContent(content.toString());
			dlg.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
