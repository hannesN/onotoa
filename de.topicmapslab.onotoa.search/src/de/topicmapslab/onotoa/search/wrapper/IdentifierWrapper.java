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
 * Wrapper class for the SubjectIdentifier of an TopicType
 * 
 * @author Sebastian Lippert
 */

@TreeNode(imageMethod = "getImagePath")
public class IdentifierWrapper implements Comparable<IdentifierWrapper>, IDoubleClickHandler {

	public static String SUBJECTIDENTIFIER = "si";
	public static String SUBJECTLOCATOR = "sl";

	private TopicType topicType;
	private String identifier;
	private String identifierType;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            TopicType
	 * @param identifier
	 *            Identifier of the TopicType
	 * @param identifierType
	 *            Type of the Identifier
	 */

	public IdentifierWrapper(TopicType topicType, String identifier, String identifierType) {

		this.topicType = topicType;
		this.identifier = identifier;
		this.identifierType = identifierType;

	}

	/**
	 * Getter for one SubjectIdentifier of a TopicType
	 * 
	 * @return SubjectIdentifier
	 */

	@Text
	public String getIdentifier() {
		return this.identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	public int compareTo(IdentifierWrapper o) {

		if (this.identifier.compareTo(o.identifier) == 0)
			return 0;
		else if (this.identifier.compareTo(o.identifier) <= -1)
			return -1;

		return 1;

	}

	public String getImagePath() {

		if (identifierType.equals(SUBJECTIDENTIFIER))
			return ImageConstants.SUBJECTIDENTIFIERCONSTRAINT;

		if (identifierType.equals(SUBJECTLOCATOR))
			return ImageConstants.SUBJECTLOCATORCONSTRAINT;

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#addSelectionChangedListener
	 * (org.eclipse.jface.viewers.ISelectionChangedListener)
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
	 */
	public ISelection getSelection() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse
	 * .jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

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
