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
public abstract class AbstractTypeWrapper implements Comparable<AbstractTypeWrapper>, IDoubleClickHandler {

	private final String name;
	private final int type;

	/**
	 * Constructor
	 * 
	 * @param name of the TopicType
	 * @param type int value of the type
	 * 
	 * 0 = no type
	 * 1 = TopicType
	 * 2 = OccurrenceType
	 * 3 = NameType
	 * 4 = Role Type
	 * 5 = AssociationType
	 */
	
	public AbstractTypeWrapper(String name, int type) {

		this.name = name;
		this.type = type;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(AbstractTypeWrapper o) {

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
}
