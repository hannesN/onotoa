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
package de.topicmapslab.onotoa.search.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;

import de.topicmapslab.onotoa.search.searchImpl.UseSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.onotoa.search.wrapper.UseWrapper;

/**
 * @author floppy
 * 
 */
public class NewUseSearchAction extends Action {

	private UseSearcher searcher;
	private Viewer viewer;
	private SearchView searchview;

	/**
	 * @param searcher
	 * @param viewer
	 * @param searchview
	 */
	public NewUseSearchAction(String label, UseSearcher searcher, Viewer viewer, SearchView searchview) {

		super(label);
		this.searcher = searcher;
		this.viewer = viewer;
		this.searchview = searchview;

	}

	@Override
	public void run() {

		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		Object o = sel.getFirstElement();

		if (o instanceof UseWrapper) {
			searcher.setTopicType(((UseWrapper) o).getTopicType());
			searchview.updateContent();
		}

	}

}
