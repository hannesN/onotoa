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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.onotoa.search.util.UseType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.util.ImageConstants;

/**
 * @author floppy
 * 
 */

@TreeNode(imageMethod = "getImagePath")
public class AssociationTypeConstraintWrapper implements Comparable<TopicTypeWrapper>, IDoubleClickHandler {

	private AssociationTypeConstraint atc;

	@Children
	private List<Object> kindOfUseList;

	/**
	 * Constructor
	 * 
	 * @param atc
	 *            AssociationTypeConstraint that should wrapped
	 */

	public AssociationTypeConstraintWrapper(AssociationTypeConstraint atc) {
		this.atc = atc;
		kindOfUseList = new ArrayList<Object>();
		kindOfUseList.add(new KindOfUseWrapper(UseType.AssociationType));
	}

	/**
	 * Getter for name label of teh wrapper
	 * 
	 * @return the name
	 */

	@Text
	public String getName() {
		return atc.getType().getName();
	}

	/**
	 * Getter for children list
	 * 
	 * @return children list
	 */

	public List<Object> getKindOfUseList() {
		return kindOfUseList;
	}

	/**
	 * Getter for the image path of the wrapper
	 * 
	 * @return image path
	 */

	public String getImagePath() {
		return ImageConstants.ASSOCIATIONCONSTRAINT_SM;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public ISelection getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void doubleClickHappend() {
		Activator.getDefault().getSelectionService().setSelection(new StructuredSelection(atc), this);

	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(TopicTypeWrapper o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
