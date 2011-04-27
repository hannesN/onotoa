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

import de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider;

/**
 * Little container containing the infos of an ModelViewExtension.
 * 
 * @author Hannes Niederhausen
 * @version 1.1.2
 */
public class ModelViewExtensionInfo implements Comparable<ModelViewExtensionInfo>{

	private final String name;
	private final String id;
	
	private final IModelViewProvider provider;

	public ModelViewExtensionInfo(String name, String id, IModelViewProvider provider) {
	    super();
	    this.name = name;
	    this.id = id;
	    this.provider = provider;
    }
	
	public String getName() {
	    return name;
    }
	
	public IModelViewProvider getProvider() {
	    return provider;
    }
	
	public String getId() {
	    return id;
    }
	

	/**
	 * Compares the name of the infos. If there are equal the ids will be compared
	 */
	public int compareTo(ModelViewExtensionInfo o) {
		int r = name.compareTo(o.name);
		if (r==0)
			r = id.compareTo(o.id);
		
	    return r;
    }
}
