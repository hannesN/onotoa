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
package de.topicmapslab.onotoa.search.dialogs.TopicTypeSearchAdvancedParts;

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.onotoa.search.dialogs.AbstractTopicTypeSearchAdvancedPart;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Class for specific implementation of the advanced part from the TopicType
 * search dialogue. Adds specific input list and specific filter buttons
 * 
 * @author Sebastian Lippert
 * 
 */

public class AdvancedTopicTypePart extends AbstractTopicTypeSearchAdvancedPart {

	/**
	 * Constructor
	 * 
	 * Creates inputlist and call method that adds filter buttons
	 * 
	 */

	public AdvancedTopicTypePart() {
		super(createInputList(), true);
		addFilterButtons();
	}

	/**
	 * Create inputList for advanced part, that contains all Topics but
	 * TopicTypes not used as role
	 * 
	 * @return List<TopicTypes> inputList
	 */

	private static List<TopicType> createInputList() {

		// prepare stuff
		List<TopicType> inputList = new ArrayList<TopicType>(ModelIndexer.getTopicIndexer().getTopicTypes());
		List<TopicType> removeList = new ArrayList<TopicType>();
		TopicType role;

		// select all previous found TopicTypes from list
		for (TopicType t : inputList) {
			if (t.getKind().getValue() == KindOfTopicType.TOPIC_TYPE_VALUE)
				removeList.add(t);
		}

		// remove all TopicTypes from list
		inputList.removeAll(removeList);

		// search all TopicTypes that are used as RoleType and add them
		for (AssociationType at : ModelIndexer.getTopicIndexer().getAssociationTypes()) {
			for (RoleConstraint rc : at.getRoles()) {
				role = rc.getType();
				// kindOf test, because RoleTypes are already in the list
				if (role.getKind().getValue() == KindOfTopicType.TOPIC_TYPE_VALUE)
					if (!inputList.contains(role))
						inputList.add(role);
			}
		}
		return inputList;
	}

	/**
	 * Add filter buttons for all TopicTypes
	 */

	private void addFilterButtons() {

		addFilterButton(KindOfTopicType.TOPIC_TYPE);
		addFilterButton(KindOfTopicType.OCCURRENCE_TYPE);
		addFilterButton(KindOfTopicType.NAME_TYPE);
		addFilterButton(KindOfTopicType.ROLE_TYPE);
		addFilterButton(KindOfTopicType.ASSOCIATION_TYPE);

	}

}
