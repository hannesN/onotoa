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
package de.topicmapslab.tmcledit.model.index;

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationIndexer {

	private TopicMapSchema topicMapSchema;
	
	public AssociationIndexer() {
	
	}
	
	public void init(TopicMapSchema schema) {
		topicMapSchema = schema;	
	}
	
	public List<RolePlayerConstraint> getRolePlayerConstraintsFor(TopicType topicType) {
		List<RolePlayerConstraint> result = new ArrayList<RolePlayerConstraint>();
		
		for (AssociationTypeConstraint atc : topicMapSchema.getAssociationTypeConstraints()) {
			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				if (topicType.equals(rpc.getPlayer()))
					result.add(rpc);
			}
		}
		return result;
	}
}
