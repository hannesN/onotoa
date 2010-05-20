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
package de.topicmapslab.tmcledit.model.annotationprovider;

import java.util.Collection;

/**
 * @author Hannes Niederhausen
 *
 */
public interface IAnnotationProposalProvider {
	
	
	/**
	 * Tells if the user may enter other values than those returned by getProposals().  
	 * 
	 * The default values is <code>true</code>;
	 * 
	 * @return <code>true</code> if new values are allowed, <code>false</code> else
	 */
	public boolean newValuesAllowed();
	
	/**
	 * Returns a set of valid values for the annotation. This may be used to provide a set of values, 
	 * where the user may choose from.
	 * 
	 * This method must not return <code>null</code>.
	 *  
	 * @return A collection containing valid values for the annotation.
	 */
	public Collection<String> getProposals();
}
