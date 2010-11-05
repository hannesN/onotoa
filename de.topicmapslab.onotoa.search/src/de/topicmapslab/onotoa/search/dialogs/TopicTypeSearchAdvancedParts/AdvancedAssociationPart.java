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
 * Class for specific implementation of the advanced part from the
 * AssociationType search dialogue. Adds specific input list and specific filter
 * buttons
 * 
 * @author Sebastian Lippert
 * 
 */

public class AdvancedAssociationPart extends AbstractTopicTypeSearchAdvancedPart {

	/**
	 * Constructor
	 * 
	 * Creates inputlist and call method that adds filter buttons
	 * 
	 */

	public AdvancedAssociationPart() {
		super(createInputList(), true);
		addFilterButtons();
	}

	/**
	 * Create inputList for advanced part, that contains TopicTypes (used as
	 * role) and RoleTypes
	 * 
	 * @return List<TopicTypes> inputList
	 */

	private static List<TopicType> createInputList() {

		// prepare stuff
		List<TopicType> inputList = new ArrayList<TopicType>();
		TopicType role;
		// search all TopicTypes that are used as role and RoleTypes and add
		// them
		for (AssociationType at : ModelIndexer.getTopicIndexer().getAssociationTypes()) {
			for (RoleConstraint rc : at.getRoles()) {
				role = rc.getType();
				if (!inputList.contains(role))
					inputList.add(role);
			}
		}
		// add all RoleTypes
		for (TopicType r : ModelIndexer.getTopicIndexer().getRoleTypes()){
			if (!inputList.contains(r))
				inputList.add(r);
		}
		return inputList;
	}

	/**
	 * Add filter buttons for TopicTypes and RoleTypes
	 */

	private void addFilterButtons() {

		addFilterButton(KindOfTopicType.TOPIC_TYPE);
		addFilterButton(KindOfTopicType.ROLE_TYPE);

	}
}
