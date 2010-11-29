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
package de.topicmapslab.onotoa.search.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.onotoa.search.searchImpl.SubjectLocatorSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * 
 * Handles Subject Locator search. That means it provides the dialog and add results
 * to result view.
 * 
 * @author Sebastian Lippert
 * 
 */

public class ListSubjectLocatorHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ModelView view = (ModelView) activePage.findView(ModelView.ID);

		if (view == null)
			return null;

		// check if ModelIndexer exists
		if (ModelIndexer.getInstance() == null)
			return null;
		
		// get file for schema
		File file = Activator.getDefault().getSelectionService().getOnotoaFile();
		if (file == null)
			return null;

		// get schema
		Assert.isNotNull(file.getTopicMapSchema());
		TopicMapSchema schema = file.getTopicMapSchema();
		
		SubjectLocatorSearcher slSearcher = new SubjectLocatorSearcher(
				schema);
		slSearcher.fetchResult();

		try {

			SearchView searchView = (SearchView) activePage.findView(SearchView.ID);
			if (searchView == null)
				searchView = (SearchView) activePage.showView(SearchView.ID);
			else
				activePage.activate(searchView);

			searchView.setContent(slSearcher.getResult());
			searchView.removeContextMenu();

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}