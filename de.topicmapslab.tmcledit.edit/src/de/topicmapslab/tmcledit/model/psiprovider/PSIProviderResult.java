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
package de.topicmapslab.tmcledit.model.psiprovider;

/**
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public class PSIProviderResult {

	private final String description;
	
	private final String identifier;

	public PSIProviderResult(String identifier, String description) {
	    super();
	    this.description = description;
	    this.identifier = identifier;
    }

	public String getDescription() {
    	return description;
    }

	public String getIdentifier() {
    	return identifier;
    }
	
	
	
}
