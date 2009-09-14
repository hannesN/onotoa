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
package de.topicmapslab.tmcledit.model.util.extension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.psiprovider.IPSIProvider;



/**
 * The class which manages the extension points.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ExtensionManager {
	private static final String EXT_PSIPROVIDER = "psiprovider";
	
	private static final String ATT_ID = "id";
	private static final String ATT_ClASS = "class";
	private static final String ATT_NAME = "name";
	
	
	private List<PSIProviderInfo> psiProviderInfos = null;
	
	
	
	public List<PSIProviderInfo> getPsiProviderInfos() {
		if (psiProviderInfos==null)
			initPsiProvider();
		return psiProviderInfos;
    }
	
	
	private void initPsiProvider() {
		List<PSIProviderInfo> tmp = new ArrayList<PSIProviderInfo>();
		
		IExtensionPoint ep = Platform.getExtensionRegistry().getExtensionPoint(
				TmcleditEditPlugin.PLUGIN_ID, EXT_PSIPROVIDER);
		
		IExtension exts[] = ep.getExtensions();
		
		for (IExtension ext : exts) {
			IConfigurationElement[] confElemements = ext.getConfigurationElements();
			
			for (IConfigurationElement ce : confElemements) {
				String id = ce.getAttribute(ATT_ID);
				String name = ce.getAttribute(ATT_NAME);
				String clazz = ce.getAttribute(ATT_ClASS);
				
				Bundle bundle = Platform.getBundle(ext.getNamespaceIdentifier());
				
				try {
					Object o = bundle.loadClass(clazz);
					IPSIProvider prov = (IPSIProvider) ((Class<?>)o).newInstance();
					tmp.add(new PSIProviderInfo(name, id, prov));
					
				} catch (Exception e) {
					TmcleditEditPlugin.getPlugin().log(e);
				}
			}
		}
		if (tmp.size()==0) {
			tmp = null;
			psiProviderInfos = Collections.emptyList();
		} else {
			psiProviderInfos = Collections.unmodifiableList(tmp);
		}
			
			
	}
}
