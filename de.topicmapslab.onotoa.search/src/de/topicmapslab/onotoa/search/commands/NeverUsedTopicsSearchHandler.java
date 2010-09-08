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
package de.topicmapslab.onotoa.search.commands;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.onotoa.search.searchImpl.NeverUsedTopicsSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author sip
 * 
 */
public class NeverUsedTopicsSearchHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ModelView view = (ModelView) activePage.findView(ModelView.ID);

		if (view == null)
			return null;

		if (view.getCurrentTopicMapSchema() == null)
			return null;

		TopicMapSchema schema = view.getCurrentTopicMapSchema();
		NeverUsedTopicsSearcher searcher = new NeverUsedTopicsSearcher(schema);

		searcher.fetchResult();

		Collections.sort((List<? extends Comparable>) searcher.getResult().getList());

		try {

			SearchView searchView = (SearchView) activePage.findView(SearchView.ID);
			if (searchView == null)
				searchView = (SearchView) activePage.showView(SearchView.ID);
			else
				activePage.activate(searchView);

			searchView.setContent(searcher.getResult());

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}
