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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;

/**
 * Source Provider to set Contexts for COmmands
 * 
 * @author Hannes Niederhausen
 *
 */
public class OnotoaSourceProvider extends AbstractSourceProvider {

	/**
	 * The key for the map
	 */
	public final String RCP = "rcp";
	
	private final String[] PROVIDED_SOURCE = new String[]{RCP};
	
	private final Map<String, String> map = new HashMap<String, String>();
	
	/**
	 * Constructor
	 */
	public OnotoaSourceProvider() {
		map.put(RCP, "true");
	}

	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	public Map getCurrentState() {
		return map;
	}

	public String[] getProvidedSourceNames() {
		return PROVIDED_SOURCE;
	}

}
