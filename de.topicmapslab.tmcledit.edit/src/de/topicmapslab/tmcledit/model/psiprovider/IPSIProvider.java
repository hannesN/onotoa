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

import java.util.Set;

/**
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IPSIProvider {

	/**
	 * Returns a List of possible subject identifier.
	 * 
	 * @return
	 */
	public Set<PSIProviderResult> getSubjectIdentifier();
	
	/**
	 * Returns a list of subject identifier starting with the given prefix. 
	 * 
	 * @param prefix
	 * @return
	 */
	public Set<PSIProviderResult> getSubjectIdentifierStartingWith(String prefix);
	
	/**
	 * The name of the Topic which should get the subject identifier.
	 * 
	 * @param name the name of the topic
	 */
	public void setName(String name);
	
	
	
}
