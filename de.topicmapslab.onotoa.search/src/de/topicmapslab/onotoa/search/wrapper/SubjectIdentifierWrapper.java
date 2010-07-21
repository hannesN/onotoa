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

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Wrapper class for the SubjectIdentifier of an TopicType 
 * 
 * @author Sebastian Lippert
 */

@TreeNode
public class SubjectIdentifierWrapper implements
		Comparable<SubjectIdentifierWrapper> {

	private TopicType topic;
	private String identifier;

	/**
	 * Constructor
	 * 
	 * @param type TopicType
	 * @param one SubjectIdentifier of the TopicType
	 */
	
	public SubjectIdentifierWrapper(TopicType type, String identifier) {

		this.topic = topic;
		this.identifier = identifier;

	}

	/**
	 * Getter for one SubjectIdentifier of a TopicType
	 * @return SubjectIdentifier 
	 */
	
	@Text
	public String getSubjectIdentifier() {
		return this.identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	public int compareTo(SubjectIdentifierWrapper o) {

		if (this.identifier.compareTo(o.identifier) == 0)
			return 0;
		else if (this.identifier.compareTo(o.identifier) <= -1)
			return -1;

		return 1;

	}

}
