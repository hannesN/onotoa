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
package de.topicmapslab.onotoa.search.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;

/**
 * @author sip
 * 
 */
@TreeNode
public class Container {

	@Children
	private List<Object> list = new ArrayList<Object>();

	/**
	 * @return the list
	 */
	public List<Object> getList() {
		if (this.list == null)
			return Collections.emptyList();
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}

}
