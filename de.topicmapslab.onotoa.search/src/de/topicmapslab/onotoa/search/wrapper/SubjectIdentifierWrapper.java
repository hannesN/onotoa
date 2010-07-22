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

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.searchImpl.NonSubjectIdentifierTopicSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.PropertyDetailView;

/**
 * Wrapper class for the SubjectIdentifier of an TopicType
 * 
 * @author Sebastian Lippert
 */

@TreeNode
public class SubjectIdentifierWrapper implements
		Comparable<SubjectIdentifierWrapper>, IDoubleClickHandler {

	private TopicType topic;
	private String identifier;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            TopicType
	 * @param one
	 *            SubjectIdentifier of the TopicType
	 */

	public SubjectIdentifierWrapper(TopicType type, String identifier) {

		this.topic = type;
		this.identifier = identifier;

	}

	/**
	 * Getter for one SubjectIdentifier of a TopicType
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.topicmapslab.onotoa.search.wrapper.DoubleClickAction#doubleClickHappend
	 * ()
	 */
	public void doubleClickHappend() {

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ModelView view = ModelView.getInstance();
		if (view==null)
			return;
		
		view.setSelection(new StructuredSelection(topic));
		try {
			activePage.showView(PropertyDetailView.ID,null,IWorkbenchPage.VIEW_VISIBLE);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}

}
