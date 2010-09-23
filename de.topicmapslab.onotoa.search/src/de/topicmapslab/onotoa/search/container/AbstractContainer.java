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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.searchImpl.ISearcher;
import de.topicmapslab.onotoa.search.views.SearchView;

/**
 * Container class that holds a list of Objects that will be displayed by a view.
 * 
 * @author Sebastian Lippert
 */

@TreeNode
public abstract class AbstractContainer implements IContainer {

	private final String label;
	protected final ISearcher searcher;
	protected SearchView view;

	public AbstractContainer(String label, ISearcher searcher) {
		this.label = label;
		this.searcher = searcher;
	}

	@Children
	protected List<Object> contentList;

	/**
	 * Get all Objects in the container.
	 * 
	 * @return the list
	 */

	public List<Object> getContentList() {
		if (this.contentList == null)
			return Collections.emptyList();
		return contentList;
	}

	/**
	 * Add element to list.
	 * 
	 * @param o
	 *            Object
	 */
	public void addListElement(Object o) {
		if (this.contentList == null)
			contentList = new ArrayList<Object>();
		contentList.add(o);
	}

	/**
	 * Add element to specific position.
	 * 
	 * @param i
	 *            index of position
	 * @param o
	 *            Object
	 */

	public void addListElement(int i, Object o) {
		if (this.contentList == null)
			contentList = new ArrayList<Object>();
		contentList.add(i, o);
	}

	/**
	 * Remove object from list.
	 * 
	 * @param o
	 *            Object
	 */

	public void removeListElement(Object o) {
		if (this.contentList != null)
			contentList.remove(o);
	}
	
	/**
	 * Remove all elements from list.
	 */
	
	public void removeAllElements(){
		if (this.contentList != null)
			contentList.clear();
	}

	/**
	 * Get label for search at result view.
	 * 
	 * @return label
	 */

	@Text
	public String getLabel() {
		return this.label;
	}

	/**
	 * Set view that displays this container.
	 * 
	 * @param view
	 *            SearchView
	 */

	public void setView(SearchView view) {
		this.view = view;
	}

	/**
	 * Does usually nothing.
	 */

	public void dispose() {
		// implementation in subclass
	}

	/**
	 * Refresh container entries.
	 */
	
	public void refresh() {
		searcher.refresh();
		contentList = searcher.getResult().getContentList();
	}

}
