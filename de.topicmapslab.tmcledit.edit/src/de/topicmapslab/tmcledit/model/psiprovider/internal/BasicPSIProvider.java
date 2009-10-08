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

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.psiprovider.PSIProvider;
import de.topicmapslab.tmcledit.model.psiprovider.PSIProviderResult;

/**
 * @author Hannes Niederhausen
 *
 */
public class BasicPSIProvider extends PSIProvider {
	public Set<PSIProviderResult> getSubjectIdentifier() {
		HashSet<String> uris = new HashSet<String>();
		
		TopicMapSchema topicMapSchema = ModelIndexer.getInstance().getTopicMapSchema();
		uris.add(topicMapSchema.getBaseLocator());
		
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			for (String id : tt.getIdentifiers()) {
				int lastIndexOf = id.lastIndexOf("/");
				if (lastIndexOf>0)
					uris.add(id.substring(0, lastIndexOf));
			}
		}
		
		Set<PSIProviderResult> resultSet = new HashSet<PSIProviderResult>(uris.size());
		for (String uri : uris)
			resultSet.add(new PSIProviderResult(uri, null));
		
	    return Collections.unmodifiableSet(resultSet);
    }

	
}
