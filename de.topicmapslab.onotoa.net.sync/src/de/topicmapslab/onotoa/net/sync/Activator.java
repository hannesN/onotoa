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
package de.topicmapslab.onotoa.net.sync;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.topicmapslab.onotoa.net.sync.common.ServerData;
import de.topicmapslab.onotoa.net.sync.common.util.ServerDataHandler;
import de.topicmapslab.onotoa.net.sync.common.util.ServerDataSerializer;
import de.topicmapslab.onotoa.net.sync.preferences.PreferenceConstants;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.topicmapslab.onotoa.net.sync";

	// The shared instance
	private static Activator plugin;
	
	private List<ServerData> serverDataCache;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public static List<ServerData> getServerData() {
		if (getDefault().serverDataCache==null) {
			String tmp = getDefault().getPreferenceStore().getString(PreferenceConstants.P_SERVER_DATA);
			if (tmp==null)
				getDefault().serverDataCache = Collections.emptyList();
			else {
				try {
					ServerDataHandler dh = new ServerDataHandler();
					SAXParserFactory.newInstance().newSAXParser().parse(new ByteArrayInputStream(tmp.getBytes()), dh);
					getDefault().serverDataCache = Collections.unmodifiableList(dh.getData());
				} catch (Exception e) {
					log(e);
					throw new RuntimeException(e);
				}
			}
		}
		return getDefault().serverDataCache;
	}
	
	public static void setServerData(List<ServerData> newData) {
		if (newData==null)
			return;
		
		String tmp = new ServerDataSerializer().serialize(newData);
		getDefault().getPreferenceStore().setValue(PreferenceConstants.P_SERVER_DATA, tmp);
		getDefault().serverDataCache = null;
	}
	
	public static void log(Exception e) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, "An exception occured", e));
	}

}
