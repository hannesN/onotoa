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
package de.topicmapslab.tmcledit.diagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurenceConstraintCreationFactory implements CreationFactory {

	private TopicType occurenceType;
	
	@Override
	public Object getNewObject() {
		OccurenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurenceTypeConstraint();
		otc.setType(occurenceType);
		
		
		return otc;
	}

	public void setTopicType(TopicType topicType) {
		this.occurenceType = topicType;
	}
	
	@Override
	public Object getObjectType() {
		return OccurenceTypeConstraint.class;
	}
}