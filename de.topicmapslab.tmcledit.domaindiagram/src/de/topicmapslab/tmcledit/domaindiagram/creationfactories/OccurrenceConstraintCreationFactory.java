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
/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class OccurrenceConstraintCreationFactory implements CreationFactory {

	private TopicType occurrenceType;

	public Object getNewObject() {
		OccurrenceTypeConstraint otc = ModelFactory.eINSTANCE
				.createOccurrenceTypeConstraint();

		if (occurrenceType == null) {

			int i = 0;
			String n = "occurrence";
			TopicType tt = null;
			while ((tt = ModelIndexer.getTopicIndexer().getTopicTypeByName(
					n + i)) != null) {
				i++;
			}
			tt = ModelFactory.eINSTANCE.createOccurrenceType();
			tt.setName(n + i);
			occurrenceType = tt;
		}

		otc.setType(occurrenceType);

		return otc;
	}

	public void setTopicType(TopicType topicType) {
		this.occurrenceType = topicType;
	}

	public Object getObjectType() {
		return OccurrenceTypeConstraint.class;
	}
}