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
package de.topicmapslab.tmcledit.domaindiagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Factory to create an associationnode via palette
 * 
 * @author Hannes Niederhausen
 *
 */
public class AssociationNodeCreationFactory implements CreationFactory {

	private AssociationTypeConstraint atc = null;
	
	/**
	 * Sets an already created constraint which needs a node
	 * @param atc the constraint which is the model for the node
	 */
	public void setAssociationTypeConstraint(AssociationTypeConstraint atc) {
		this.atc = atc;
	}
	
	public Object getNewObject() {
		AssociationNode node = ModelFactory.eINSTANCE.createAssociationNode();
		
		if (atc==null) {
			atc = ModelFactory.eINSTANCE.createAssociationTypeConstraint();
			if (atc.getType()==null) {
				int i = 0;
				String n = "association";
				TopicType tt = null;
				while ((tt = ModelIndexer.getTopicIndexer().getTopicTypeByName(
						n + i)) != null) {
					i++;
				}
				tt = ModelFactory.eINSTANCE.createAssociationType();
				tt.setName(n + i);
				atc.setType(tt);
			}
			node.setAssociationConstraint(atc);
			atc = null;
		} else {
			node.setAssociationConstraint(atc);
		}
		
		return node;
	}

	public Object getObjectType() {
		return AssociationNode.class;
	}

}