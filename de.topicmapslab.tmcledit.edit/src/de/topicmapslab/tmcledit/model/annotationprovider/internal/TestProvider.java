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
package de.topicmapslab.tmcledit.model.annotationprovider.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationProposalProvider;

/**
 * @author mai00ckx
 *
 */
public class TestProvider implements IAnnotationProposalProvider {

	/**
	 * 
	 */
	public TestProvider() {
	}

	public Collection<String> getProposals() {
		String tmp[] = {"Test", "Prefix", "Abbba"};
		Set<String> r = new HashSet<String>(15);
		
		for (int i=0; i<15; i++) {
			r.add(tmp[i%3]+i);
		}
		
		return r;
	}

	public boolean newValuesAllowed() {
		return false;
	}

}
