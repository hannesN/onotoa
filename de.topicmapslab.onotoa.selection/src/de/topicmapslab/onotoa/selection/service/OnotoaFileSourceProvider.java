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
package de.topicmapslab.onotoa.selection.service;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

/**
 * Source provider for the file loaded variable
 * 
 * @author Hannes Niederhausen
 *
 */
public class OnotoaFileSourceProvider extends AbstractSourceProvider {
	/**
	 * name of the provided variable
	 */
	public static final String ONOTOA_FILE = "de.topicmapslab.onotoa.file.loaded";
	
	static final String[] NAMES = {ONOTOA_FILE};
	
	private HashMap<String, Boolean> sourceValue;
	
	/**
	 * Constructor
	 */
	public OnotoaFileSourceProvider() {
		sourceValue = new HashMap<String, Boolean>(1);
		setFileLoaded(false);
    }

	/**
     * Sets the new file loaded flag
     */
    void setFileLoaded(boolean loaded) {
	    sourceValue.put(ONOTOA_FILE, loaded);
	    fireSourceChanged(ISources.WORKBENCH, ONOTOA_FILE, loaded);
    }
	
    /**
     * 
     * {inheritDoc}
     */
	@Override
	public void dispose() {
	}


	/**
	 * 
	 * {inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
    @Override
	public Map getCurrentState() {
		return sourceValue;
	}

	/**
	 * 
	 * {inheritDoc}
	 */
	@Override
	public String[] getProvidedSourceNames() {
		return NAMES;
	}

}
