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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.handlers.IHandlerService;


/**
 * @generated
 */
public class DiagramEditorWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	/**
	 * @generated
	 */
	public DiagramEditorWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated
	 */
	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new DiagramEditorActionBarAdvisor(configurer);
	}

	/**
	 * @generated
	 */
	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 700));
		configurer.setTitle(Messages.DiagramEditorWorkbenchWindowAdvisor_Title);
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
	}

	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		IPreferenceStore preferenceStore = PlatformUI.getPreferenceStore();
		if (preferenceStore.getBoolean("shownow")) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			try {
				((IHandlerService) workbench
						.getService(IHandlerService.class)).executeCommand(
						"de.topicmapslab.tmcledit.doc.opensurveycommand",
						null);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			preferenceStore.setToDefault("shownow");
		}
	}
}
