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

import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;


/**
 */
public class DiagramEditorWorkbenchAdvisor extends WorkbenchAdvisor {
	
	/**
	 * @generated
	 */
	public static final String PERSPECTIVE_ID = "de.topicmapslab.tmcledit.extensions.OnotoaPerspective"; //$NON-NLS-1$
	private String[] args;

	/**
	 * @generated
	 */
	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
		PlatformUI.getPreferenceStore().putValue(IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP, "true");
	}

	/**
	 * @generated
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		DiagramEditorWorkbenchWindowAdvisor advisor = new DiagramEditorWorkbenchWindowAdvisor(configurer);
		advisor.setArguments(args);
		return advisor;
	}

	public void setArguments(String[] args) {
		this.args = args;
	}

}
