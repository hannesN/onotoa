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

import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Wrapper class for the name of an RoleType
 * 
 * @author Sebastian Lippert
 */

@TreeNode(image = "./roletype.gif")
public class RoleTypeWrapper extends AbstractTypeWrapper {

	private final TopicType type;

	/**
	 * Constructor
	 * 
	 * @param topicType
	 *            TopicType
	 */

	public RoleTypeWrapper(TopicType topicType) {
		super(topicType);
		this.type = topicType;
	}

	/**
	 * Getter for name of a RoleType
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
	 * @see
	 * de.topicmapslab.onotoa.search.wrapper.DoubleClickAction#doubleClickHappend
	 * ()
	 */
	public void doubleClickHappend() {
		Activator.getDefault().getSelectionService().setSelection(new StructuredSelection(type), this);
	}
}