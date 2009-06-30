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
package de.topicmapslab.tmcledit.application.splash;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.splash.BasicSplashHandler;
import org.osgi.framework.Bundle;

/**
 * @author Hannes Niederhausen
 * 
 */
public class OnotoaSplashHandler extends BasicSplashHandler {

	private Font versionFont;
	private Font copyrightFont;

	@Override
	public void init(Shell splash) {
		super.init(splash);
		createSplash(getContent());
		getContent().layout(true);
	}

	private void createSplash(Composite parent) {
		parent.setLayout(new SplashLayout());

		if (versionFont == null) {
			versionFont = new Font(parent.getDisplay(), "Arial", 12, SWT.BOLD);
		}
		Label label = new Label(parent, SWT.TRANSPARENT);
		label.setFont(versionFont);
		// read bundle version for program
		Bundle bundle = Platform.getBundle("de.topicmapslab.tmcledit.edit");
		if (bundle != null) {
			String version = (String) bundle.getHeaders().get(
					org.osgi.framework.Constants.BUNDLE_VERSION);
			version = "Version: " + version;
			label.setText(version);
		}
		label = new Label(parent, SWT.TRANSPARENT);
		label.setFont(versionFont);
		label.setText("Create your own Topic Map schema");

		if (copyrightFont == null)
			copyrightFont = new Font(parent.getDisplay(), "Arial", 8, SWT.NONE);

		label = new Label(parent, SWT.TRANSPARENT);
		label.setFont(copyrightFont);
		String tmp = "Developer in chief: Hannes Niederhausen\n"
				+ "Onotoa is a graduate from Topic Maps Lab\n"
				+ "(C) 2008, 2009 Hannes Niederhauen, Topic Maps Lab";
		label.setText(tmp);
	}

	@Override
	public void dispose() {
		if ((versionFont != null) && (!versionFont.isDisposed()))
			versionFont.dispose();
		super.dispose();
	}

	private class SplashLayout extends Layout {

		@Override
		protected Point computeSize(Composite composite, int hint, int hint2,
				boolean flushCache) {

			return new Point(400, 300);
		}

		@Override
		protected void layout(Composite composite, boolean flushCache) {
			// progress label
			Control child = composite.getChildren()[0];
			child.setLocation(10, 250);
			child.setSize(350, 18);

			// progress bar
			child = composite.getChildren()[1];
			child.setLocation(10, 280);
			child.setSize(350, 15);

			// version label
			child = composite.getChildren()[2];
			child.setLocation(40, 150);
			child.setSize(350, 18);

			// slogan label
			child = composite.getChildren()[3];
			child.setLocation(40, 130);
			child.setSize(350, 18);

			// copyright statement
			child = composite.getChildren()[4];
			child.setLocation(40, 250);
			child.setSize(350, 50);

		}

	}
}
