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
package de.topicmapslab.onotoa.search.searchImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.topicmapslab.onotoa.search.container.NeverUsedTopicsContainer;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Sebastian Lippert
 * 
 */
public class NeverUsedTopicsSearcher implements ISearcher {

	private final TopicMapSchema schema;
	private NeverUsedTopicsContainer con;

	public NeverUsedTopicsSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new NeverUsedTopicsContainer("Never used Types", this);

	}

	/**
	 * {@inheritDoc}
	 */
	public void fetchResult() {

		// set for used types
		Set<TopicType> usedTypesSet = new HashSet<TopicType>();
		
		/*
		 * build a list of all used topics and remove this list from the list of
		 * all topics of the schema. The remaining ones are the never used ones
		 */

		// iterate over TopicTypes to get uses ones
		for (TopicType tt : schema.getTopicTypes()) {

			// add constraints from TopicType
			for (AbstractTypedConstraint constraint : ModelIndexer.getConstraintIndexer().getConstraintsByType(tt)) {
				usedTypesSet.add(constraint.getType());

				if (constraint instanceof AssociationTypeConstraint)
					for (RolePlayerConstraint playerConstraint : ((AssociationTypeConstraint) constraint)
					        .getPlayerConstraints())
						usedTypesSet.add(playerConstraint.getPlayer());
			}

			// add RoleTypes
			if (tt instanceof AssociationType)
				for (RoleConstraint roleConstraint : ((AssociationType) tt).getRoles()) {
					usedTypesSet.add(roleConstraint.getType());
				}

			// add used TopicTypes to exclude them from the later comparison
			if (tt.getKind().getValue() == 0)
				if (tt.getNameConstraints().size() != 0 
						|| tt.getOccurrenceConstraints().size() != 0
				        || tt.getIdentifiers().size() != 0 
				        || tt.getLocators().size() != 0
				        || tt.getItemIdentifierConstraints().size() != 0 
				        || tt.getAko().size() != 0
				        || tt.getIsa().size() != 0 
				        || ModelIndexer.getTopicIndexer().getUsedAsIsa(tt).size() != 0
				        || ModelIndexer.getTopicIndexer().getUsedAsAko(tt).size() != 0)
					usedTypesSet.add(tt);

		}

		// add TopicTypes that are used as scope to used list
		for (ScopedTopicType stt : ModelIndexer.getTopicIndexer().getScopedTopicTypes()) {
			for (ScopeConstraint sc : stt.getScope()) {
				usedTypesSet.add(sc.getType());
			}
		}

		// compare usedTypeSet with all types and detect unused types
		for (TopicType tt : schema.getTopicTypes()) {
			if (!usedTypesSet.contains(tt))
				con.addListElement(new TopicTypeWrapper(tt));
		}

	}

	/**
	 * {@inheritDoc}
	 */

	public NeverUsedTopicsContainer getResult() {
		Collections.sort((List<? extends Comparable>) con.getContentList());
		return con;
	}

	/**
	 * Get list of unused topics
	 * 
	 * @return list of unused topics
	 */
	public List<TopicType> getResultList() {

		List<TopicType> resultList = new ArrayList<TopicType>();
		for (Object wrapper : con.getContentList())
			resultList.add(((TopicTypeWrapper) wrapper).getTopicType());

		return resultList;
	}

	/**
	 * {@inheritDoc}
	 */

	public void refresh() {

		con.removeAllElements();
		fetchResult();

	}

}