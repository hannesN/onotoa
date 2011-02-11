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
package de.topicmapslab.tmcledit.diagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;

/**
 * The factory to create a new node for an {@link AssociationTypeConstraint}
 * 
 * @author Hannes Niederhausen
 *
 */
public class AssociationNodeCreationFactory implements CreationFactory {

	private AssociationTypeConstraint atc = null;
	
	/**
	 * Sets the {@link AssociationTypeConstraint} to use
	 * @param atc
	 */
	public void setAssociationTypeConstraint(AssociationTypeConstraint atc) {
		this.atc = atc;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public Object getNewObject() {
		AssociationNode node = ModelFactory.eINSTANCE.createAssociationNode();
		node.setAssociationConstraint(atc);
		return node;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public Object getObjectType() {
		return AssociationNode.class;
	}

}
