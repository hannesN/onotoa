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
package de.topicmapslab.onotoa.search.container;

import java.util.List;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.searchImpl.ISearcher;

/**
 * Container for never used topics search
 * 
 * @author Sebastian Lippert
 */

@TreeNode
public class NeverUsedTopicsContainer extends AbstractContainer {

	/**
	 * Constructor
	 * 
	 * @param label
	 *            Label of the container
	 * @param searcher
	 *            Specific searcher for desired target
	 */

	public NeverUsedTopicsContainer(String label, ISearcher searcher) {
		super(label, searcher);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getter for container label
	 * 
	 * @return String label
	 */

	@Text
	public String getlabel() {
		return super.getLabel();
	}

	/**
	 * Getter for children list
	 * 
	 * @return List<Object> list of all children
	 */

	@Children
	public List<Object> getChildren() {
		return super.getContentList();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addAdapter() {
		// TODO Auto-generated method stub

	}

}
