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
 * Wrapper class for the name of an TopicType
 * 
 * @author Sebastian Lippert
 */

@TreeNode(image = "./topictype.gif")
public class TopicTypeWrapper implements Comparable<TopicTypeWrapper>,
		IDoubleClickHandler {

	private final TopicType type;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            TopicType
	 */

	public TopicTypeWrapper(TopicType type) {
		this.type = type;
	}

	/**
	 * Getter for name of a TopicType
	 * 
	 * @return name
	 */

	@Text
	public String getName() {
		return this.type.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	public int compareTo(TopicTypeWrapper o) {

		if (this.getName().compareTo(o.getName()) == 0)
			return 0;
		else if (this.getName().compareTo(o.getName()) <= -1)
			return -1;

		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.topicmapslab.onotoa.search.wrapper.DoubleClickAction#doubleClickHappend
	 * ()
	 */
	public void doubleClickHappend() {
		System.out.println("Non thing clicked");
	}

}
