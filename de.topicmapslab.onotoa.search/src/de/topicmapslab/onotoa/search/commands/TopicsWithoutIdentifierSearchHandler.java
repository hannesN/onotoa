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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.onotoa.action.NewSubjectIdentifierAction;
import de.topicmapslab.onotoa.action.NewSubjectLocatorAction;
import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.onotoa.search.searchImpl.TopicsWithoutIdentifierSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Handles search for Topic Types without any identifier. That means topics
 * without SubjectLocator, SubjectIdentifier or ItemIdentifier.
 * 
 * After initiating the search, the results are displayed in a view and a
 * context menu will be established to add identifiers.
 * 
 * @author Sebastian Lippert
 * 
 */
public class TopicsWithoutIdentifierSearchHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get view
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
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

		// get CommandStack
		CommandStack commandStack = view.getEditingDomain().getCommandStack();

		// get schema
		Assert.isNotNull(file.getTopicMapSchema());
		TopicMapSchema schema = file.getTopicMapSchema();

		// search and sort results
		TopicsWithoutIdentifierSearcher searcher = new TopicsWithoutIdentifierSearcher(schema);
		searcher.fetchResult();

		try {

			// open searchView
			SearchView searchView = (SearchView) activePage.findView(SearchView.ID);
			if (searchView == null)
				searchView = (SearchView) activePage.showView(SearchView.ID);
			else
				activePage.activate(searchView);

			// clear old content menu
			searchView.addContextMenu(null);
			
			// initiate actions for context menu
			List<Action> actionList = new ArrayList<Action>();
			actionList.add(new NewSubjectIdentifierAction(commandStack, "Add Subject Identifier..", searchView
			        .getTreeViewer()));
			actionList.add(new NewSubjectLocatorAction(commandStack, "Add Subject Locator..", searchView
			        .getTreeViewer()));

			// add context menu and results to view
			searchView.addContextMenu(actionList);
			searchView.setContent(searcher.getResult());

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}
