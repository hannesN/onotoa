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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;

/**
 * 
 * Abstract superclass for all TopicTye wrappers.
 * 
 * Includes the interfaces Comparable and IDoubleClickHandler to sort all
 * wrappers when they are used for a view and handles the double click event.
 * 
 * @author Sebastian Lippert
 * 
 */

@TreeNode(imageMethod = "getImagePath")
public class TopicTypeWrapper implements Comparable<TopicTypeWrapper>, IDoubleClickHandler {

	private final TopicType topicType;
	private final String name;
	private final int type;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            of the TopicType
	 * @param type
	 *            int value of the type
	 * 
	 *            0 = no type 1 = TopicType 2 = OccurrenceType 3 = NameType 4 =
	 *            Role Type 5 = AssociationType
	 */

	public TopicTypeWrapper(TopicType topicType) {

		this.topicType = topicType;
		this.name = this.topicType.getName();
		this.type = this.topicType.getKind().getValue();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(TopicTypeWrapper o) {

		if (type == o.type) {

			if (name.compareTo(o.name) == 0)
				return 0;
			else if (name.compareTo(o.name) <= -1)
				return -1;
			return 1;

		} else if (type <= o.type)
			return -1;

		return 1;
	}

	/**
	 * Getter for Topic Type inside the wrapper
	 * 
	 * @return TopicType
	 */

	public TopicType getTopicType() {
		return this.topicType;
	}

	/**
	 * Getter for name of the Topic Type that is wrapped
	 * 
	 * @return name
	 */
	@Text
	public String getName() {
		return this.topicType.getName();
	}

	/**
	 * Detect type of a topic and returns image path
	 * 
	 * @return Path to specific image
	 */

	public String getImagePath() {

		switch (topicType.getKind()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener
	 * (org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#removeSelectionChangedListener
	 * (org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse
	 * .jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.topicmapslab.onotoa.search.wrapper.IDoubleClickHandler#doubleClickHappend
	 * ()
	 */
	public void doubleClickHappend() {
		Activator.getDefault().getSelectionService().setSelection(new StructuredSelection(topicType), this);
	}
}
