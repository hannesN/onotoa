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
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;

/**
 * The factory to create a new {@link OccurrenceTypeConstraint}.
 * 
 * @author Hannes Niederhausen
 *
 */
public class OccurrenceConstraintCreationFactory implements CreationFactory {

	private OccurrenceType occurrenceType;
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public Object getNewObject() {
		OccurrenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		otc.setType(occurrenceType);
		
		return otc;
	}

	/**
	 * Sets the occurrence type 
	 * 
	 * @param occurrenceType
	 */
	public void setTopicType(OccurrenceType occurrenceType) {
		this.occurrenceType = occurrenceType;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public Object getObjectType() {
		return OccurrenceTypeConstraint.class;
	}
}