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

import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.onotoa.search.views.Container;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Sebastian Lippert
 * 
 */
public class NeverUsedTopicsSearcher implements ISearchImpl {

	private final TopicMapSchema schema;
	private Container con;

	public NeverUsedTopicsSearcher(TopicMapSchema schema) {

		this.schema = schema;
		con = new Container();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.topicmapslab.onotoa.search.searchImpl.ISearchImpl#fetchResult()
	 */
	public void fetchResult() {

		// set for used types
		Set<TopicType> usedTypesSet = new HashSet<TopicType>();

		// set for players
		Set<TopicType> playerSet = new HashSet<TopicType>();

		// iterate over TopicTypes
		for (TopicType tt : schema.getTopicTypes()) {

			// add constraints from TopicType
			for (AbstractTypedConstraint constraint : ModelIndexer.getConstraintIndexer().getConstraintsByType(tt)) {
				usedTypesSet.add(constraint.getType());

				if (constraint instanceof AssociationTypeConstraint)
					for (RolePlayerConstraint playerConstraint : ((AssociationTypeConstraint) constraint)
					        .getPlayerConstraints())
						playerSet.add(playerConstraint.getPlayer());
			}

			// add RoleTypes
			// ModelIndexer should do this, but he doesn't ... ?!?
			if (tt instanceof AssociationType)
				for (RoleConstraint roleConstraint : ((AssociationType) tt).getRoles()) {
					usedTypesSet.add(roleConstraint.getType());
				}

			// add used TopicTypes to exclude them from the later comparison
			if (tt.getKind().getValue() == 0)
				if (tt.getNameConstraints().size() != 0 || tt.getOccurrenceConstraints().size() != 0
				        || tt.getIdentifiers().size() != 0 || tt.getLocators().size() != 0
				        || tt.getItemIdentifierConstraints().size() != 0 || tt.getAko().size() != 0
				        || tt.getIsa().size() != 0 || ModelIndexer.getTopicIndexer().getUsedAsIsa(tt).size() != 0
				        || ModelIndexer.getTopicIndexer().getUsedAsAko(tt).size() != 0 || playerSet.contains(tt))
					usedTypesSet.add(tt);
		}

		// compare usedTypeSet with all types and detect unused types
		for (TopicType tt : schema.getTopicTypes()) {
			if (!usedTypesSet.contains(tt))
				con.getList().add(new TopicTypeWrapper(tt));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.topicmapslab.onotoa.search.searchImpl.ISearchImpl#getResult()
	 */
	public Container getResult() {
		return con;
	}

}