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
package de.topicmapslab.tmcledit.model.psiprovider.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.psiprovider.PSIProvider;
import de.topicmapslab.tmcledit.model.psiprovider.PSIProviderResult;

/**
 * @author Hannes Niederhausen
 *
 */
public class BasicPSIProvider extends PSIProvider {
	Set<PSIProviderResult> psis = null;
	
	public Set<PSIProviderResult> getSubjectIdentifier() {
		if (psis==null)
			init();
	    
	    return psis;
    }

	private void init() {
	    String psi[] = new String[]{"Lutz_Maicher", "Benjamin_Bock", "Hannes_Niederhausen", "JRTM", "RTM", "Daniel Dexter", "Onotoa", "Ontopia"};
		Set<PSIProviderResult> resultSet = new HashSet<PSIProviderResult>(10);
		String base = ModelIndexer.getInstance().getTopicMapSchema().getBaseLocator();
		if (base==null)
			base = "urn:onotoa/";
		if (!base.endsWith("/"))
			base += "/";
		
		 for (String s : psi) {
			 resultSet.add(new PSIProviderResult(base+s.toLowerCase(), null));
		 }
		
	    psis=Collections.unmodifiableSet(resultSet);
    }
	
}
