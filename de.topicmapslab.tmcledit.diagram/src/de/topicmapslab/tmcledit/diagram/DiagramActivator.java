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
package de.topicmapslab.tmcledit.diagram;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.onotoa.selection.service.OnotoaSelectionService;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme;
import de.topicmapslab.tmcledit.diagram.preferences.PreferenceConstants;
import de.topicmapslab.tmcledit.diagram.preferences.SchemesXMLHandler;

/**
 * The activator class controls the plug-in life cycle
 */
public class DiagramActivator extends AbstractUIPlugin {

	/**
	 *  The plug-in ID
	 */
	public static final String PLUGIN_ID = "de.topicmapslab.tmcledit.diagram"; //$NON-NLS-1$

	// The shared instance
	private static DiagramActivator plugin;
	
	private List<ColorScheme> schemeList;
	private int currScheme;

	private IOnotoaSelectionService onotoaSelectionService;
	
	/**
	 * The constructor
	 */
	public DiagramActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		resetCache();
		ServiceReference servRef = context.getServiceReference(IOnotoaSelectionService.class.getName());
		onotoaSelectionService = (IOnotoaSelectionService) context.getService(servRef);
		
	}
	
	/**
	 * @return the {@link OnotoaSelectionService}
	 */
	public IOnotoaSelectionService getOnotoaSelectionService() {
		return onotoaSelectionService;
	}
	
	/**
	 * Loads a scheme list from the preference store and returns it
	 * 
	 * @return the loaded scheme list
	 */
	public List<ColorScheme> getSchemeList() {
		if (schemeList==null) {
			String xml = getPreferenceStore().getString(PreferenceConstants.P_COLOR_SCHEMES);
			if (xml==null)
				return Collections.emptyList();
			
			schemeList = SchemesXMLHandler.parseSchemeList(new ByteArrayInputStream(xml.getBytes()));
		
			if (schemeList==null)
				schemeList =Collections.emptyList();
			
			String activated = getPreferenceStore().getString(PreferenceConstants.P_ACTIVE_SCHEME);
			if (activated!=null) {
				for (int i=0; i<schemeList.size(); i++) {
					if (activated.equals(schemeList.get(i).getName())) {
						currScheme = i;
						return schemeList;
					}
				}
			}
		}	
		return schemeList;
	}
	
	/**
	 * Resets the cached scheme list
	 */
	public void resetCache() {
		schemeList = null;
		currScheme = -1;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * 
	 * @return the current chosen color scheme or if none chosen the default scheme
	 */
	public static ColorScheme getCurrentColorScheme() {
		if ( (getDefault().getSchemeList().size()==0) || (getDefault().currScheme==-1) )
			return ColorScheme.getDefault();
		else
			return getDefault().getSchemeList().get(getDefault().currScheme);
	}
	
	
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DiagramActivator getDefault() {
		return plugin;
	}
	
	/**
	 * returns the current shell representing the active workbench window
	 * @return the current shell
	 */
	public static Shell getCurrentShell() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}
