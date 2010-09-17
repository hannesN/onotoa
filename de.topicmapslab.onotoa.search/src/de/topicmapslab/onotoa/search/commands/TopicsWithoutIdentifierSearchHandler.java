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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.onotoa.action.IContextMenuAction;
import de.topicmapslab.onotoa.action.NewSubjectIdentifierAction;
import de.topicmapslab.onotoa.action.NewSubjectLocatorAction;
import de.topicmapslab.onotoa.search.searchImpl.TopicsWithoutIdentifierSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Handles search for Topic Types without any identifier. That means topics
 * without SubjectLocator, SubjectIdentifier or ItemIdentifier.
 * 
 * After initiating the search, the results are displayed in a view and a
 * context menu will be established to add identifiers (refreshes view).
 * 
 * @author Sebastian Lippert
 * 
 */
public class TopicsWithoutIdentifierSearchHandler extends AbstractHandler implements IPropertyChangeListener {

	private TopicMapSchema schema;
	private TopicsWithoutIdentifierSearcher searcher;
	private SearchView searchView;
	private List<Action> actionList;
	private NewSubjectIdentifierAction siAction;
	private NewSubjectLocatorAction slAction;

	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get view
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ModelView view = (ModelView) activePage.findView(ModelView.ID);

		if (view == null)
			return null;

		if (view.getCurrentTopicMapSchema() == null)
			return null;

		// get CommandStack
		CommandStack commandStack = view.getEditingDomain().getCommandStack();

		// get schema of the seach
		schema = view.getCurrentTopicMapSchema();

		// search and sort results
		searcher = new TopicsWithoutIdentifierSearcher(schema);
		searcher.fetchResult();
		Collections.sort((List<? extends Comparable>) searcher.getResult().getList());

		try {

			// open searchView
			searchView = (SearchView) activePage.findView(SearchView.ID);
			if (searchView == null)
				searchView = (SearchView) activePage.showView(SearchView.ID);
			else
				activePage.activate(searchView);

			// initiate actions for context menu
			actionList = new ArrayList<Action>();
			siAction = new NewSubjectIdentifierAction(commandStack, "Add Subject Identifier..",
			        searchView.getTreeViewer());
			slAction = new NewSubjectLocatorAction(commandStack, "Add Subject Locator..", searchView.getTreeViewer());

			// add handler as listener
			siAction.addPropertyChangeListener(this);
			slAction.addPropertyChangeListener(this);

			actionList.add(siAction);
			actionList.add(slAction);

			// add context menu and results to view
			searchView.addContextMenu(actionList);
			searchView.setContent(searcher.getResult());

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse
	 * .jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {

		// check event
		if (event.getProperty().equals(IContextMenuAction.ADD_SUBJECTIDENTIFIER)
		        || event.getProperty().equals(IContextMenuAction.ADD_SUBJECTLOCATOR))
			refreshView();
	}

	/**
	 * Refresh the the SearchView to keep the shown Topic Types up to date after
	 * an PropertyChangeEvent was fired by an Action
	 */

	private void refreshView() {

		// clear all previous results
		searcher.getResult().getList().clear();

		// get new results and set them as new ones
		searcher.fetchResult();
		Collections.sort((List<? extends Comparable>) searcher.getResult().getList());
		searchView.setContent(searcher.getResult());

	}

}