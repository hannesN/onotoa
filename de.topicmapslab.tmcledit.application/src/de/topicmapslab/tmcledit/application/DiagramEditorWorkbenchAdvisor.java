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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;


/**
 * @generated
 */
public class DiagramEditorWorkbenchAdvisor extends WorkbenchAdvisor {
	/**
	 * @generated
	 */
	public static final String PERSPECTIVE_ID = "de.topicmapslab.tmcledit.extensions.OnotoaPerspective"; //$NON-NLS-1$

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
		return new DiagramEditorWorkbenchWindowAdvisor(configurer);
	}
	
	@Override
	public void preStartup() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint ep = extensionRegistry.getExtensionPoint(
				"org.eclipse.ui", "preferencePages");
		Object token = ((ExtensionRegistry)extensionRegistry).getTemporaryUserToken();		
		for (IExtension e : ep.getExtensions()) {
			boolean remove = false;
			for (IConfigurationElement c : e.getConfigurationElements()) {
				String id = c.getAttribute("id");
				if (id.startsWith("org.eclipse.equinox.security.ui")) {
					remove = true;
					System.out.println(id);
				} else if (id.startsWith("org.eclipse.help.ui")) {
					remove = true;		System.out.println(id);
				}
				if (remove) {
					
					//extensionRegistry.removeExtension(e, token);
				}
			}
		}

		
	}

}
