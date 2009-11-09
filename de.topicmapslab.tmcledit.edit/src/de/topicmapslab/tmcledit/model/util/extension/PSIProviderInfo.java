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

import de.topicmapslab.tmcledit.model.psiprovider.IPSIProvider;

/**
 * Little container containing the infos of an PSIProvider extenmsion
 * 
 * @author Hannes Niederhausen
 *
 */
public class PSIProviderInfo implements Comparable<PSIProviderInfo>{

	private final String name;
	private final String id;
	
	private final IPSIProvider provider;

	private transient boolean inUse = true;
	
	public PSIProviderInfo(String name, String id, IPSIProvider provider) {
	    super();
	    this.name = name;
	    this.id = id;
	    this.provider = provider;
    }
	
	public String getName() {
	    return name;
    }
	
	public IPSIProvider getProvider() {
	    return provider;
    }
	
	public String getId() {
	    return id;
    }
	
	public void setInUse(boolean inUse) {
	    this.inUse = inUse;
    }
	
	public boolean isInUse() {
	    return inUse;
    }

	/**
	 * Compares the name of the infos. If there are equal the ids will be compared
	 */
	public int compareTo(PSIProviderInfo o) {
		int r = name.compareTo(o.name);
		if (r==0)
			r = id.compareTo(o.id);
		
	    return r;
    }
}
