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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of the {@link IPSIProvider} interface.
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class PSIProvider implements IPSIProvider {

	private String name;

	/**
	 * This implementation takes the result of getSubjectIdentifier and filters all identifier 
	 * beginning with the given prefix. This is not a very performant implementation and should be
	 * overridden.
	 * 
	 * @param prefix the prefix
	 * @return a subset of getSubjectIdentifier
	 * 
	 * 
	 */
	public Set<PSIProviderResult> getSubjectIdentifierStartingWith(String prefix) {
	    Set<PSIProviderResult> tmp = getSubjectIdentifier();
	    if (tmp.isEmpty())
	    	return tmp;
	    
	    Set<PSIProviderResult> set = new HashSet<PSIProviderResult>(tmp.size());
	    
	    for (PSIProviderResult r : tmp) {
	    	if (r.getIdentifier().startsWith(prefix))
	    		set.add(r);
	    }
		
		return Collections.unmodifiableSet(set);
    }


	public void setName(String name) {
	    this.name = name;
	}
	
	protected String getName() {
	    return name==null ? "" : name;
    }
	
	
}
