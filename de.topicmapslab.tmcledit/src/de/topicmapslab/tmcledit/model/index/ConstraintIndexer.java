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
package de.topicmapslab.tmcledit.model.index;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class ConstraintIndexer {
	private TopicMapSchema topicMapSchema;
	
	public void init(TopicMapSchema schema) {
		topicMapSchema = schema;	
	}
	
	public Set<AbstractTypedConstraint> getConstraintsByType(TopicType tt) {
		HashSet<AbstractTypedConstraint> result = new HashSet<AbstractTypedConstraint>();
		
		for (TopicType topictype : topicMapSchema.getTopicTypes()) {
			checkConstraints(tt, result, topictype.getNameContraints());
			checkConstraints(tt, result, topictype.getOccurrenceConstraints());
			if (tt instanceof ReifiableTopicType) {
				ReifierConstraint reifierConstraint = ((ReifiableTopicType)tt).getReifierConstraint();
				if (reifierConstraint!=null) {
					if (reifierConstraint.getType()!=null)
						if (tt.equals(reifierConstraint.getType()))
							result.add(reifierConstraint);
				}
			}
			if (tt instanceof ScopedTopicType) {
				checkConstraints(tt, result, ((ScopedTopicType)tt).getScope());
			}
			if (tt instanceof AssociationType) {
				checkConstraints(tt, result, ((AssociationType)tt).getRoles());
			}
		}
		checkConstraints(tt, result, topicMapSchema.getAssociationTypeConstraints());
		
		if (result.isEmpty())
			return Collections.emptySet();
		
		return Collections.unmodifiableSet(result);
	}

	private void checkConstraints(TopicType tt, HashSet<AbstractTypedConstraint> result, EList<? extends AbstractTypedConstraint> list) {
	    for (AbstractTypedConstraint atc : list) {
	    	if (tt.equals(atc.getType()))
	    		result.add(atc);
	    }
    }
	
}
