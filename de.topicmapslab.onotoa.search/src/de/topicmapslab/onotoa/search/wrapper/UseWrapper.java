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
package de.topicmapslab.onotoa.search.wrapper;

import java.util.List;

import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;

/**
 * This wrapper is like the TopicType wrapper, but he has got children that
 * represents the relation of the wrapped type to another.
 * 
 * @author Sebastian lippert
 * 
 */

@TreeNode(imageMethod = "getImagePath")
public class UseWrapper extends TopicTypeWrapper {

	@Children
	protected List<Object> kindOfUseList;

	/**
	 * Constructor
	 * 
	 * @param topicType
	 *            Type that should be wrapped
	 * @param kindList
	 *            List that holds at least one wrapper that describes a relation
	 *            between the wrapped type and the root
	 */

	public UseWrapper(TopicType topicType, List<Object> kindList) {

		super(topicType);
		this.kindOfUseList = kindList;

	}

	/**
	 * Getter for children list
	 * 
	 * @return CHildren list
	 */
	
	public List<Object> getKindOfUseList() {

		return kindOfUseList;
	}

	/**
	 * Getter for Topic Type inside the wrapper
	 * 
	 * @return TopicType
	 */

	public String getImagePath() {

		switch (getTopicType().getKind()) {
		case NO_TYPE:
			return ImageConstants.TOPIC;
		case ASSOCIATION_TYPE:
			return ImageConstants.ASSOCIATIONTYPE;
		case NAME_TYPE:
			return ImageConstants.NAMETYPE;
		case OCCURRENCE_TYPE:
			return ImageConstants.OCCURRENCETYPE;
		case ROLE_TYPE:
			return ImageConstants.ROLETYPE;
		default:
			return ImageConstants.TOPICTYPE;

		}

	}

}
