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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.onotoa.search.searchImpl.UseSearcher;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.onotoa.search.wrapper.UseWrapper;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * 
 * Class handles search for use of a TopicType. It calls TopicSelectionDialog to
 * select a specific Topic Type, the search implementation to search use cases
 * and finally a view to show the results.
 * 
 * @author Sebastian Lippert
 * 
 */
public class UseSearchHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// check if ModelIndexer exists
		if (ModelIndexer.getInstance() == null)
			return null;

		// get shell and open search dialog for TopicTypes
		Shell shell = HandlerUtil.getActiveShell(event);

		FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(shell, false);
		if ((dlg.open() != Dialog.OK) || (dlg.getResult().length == 0)) {
			return null;
		}

		// get active page and active view
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		final ModelView view = (ModelView) activePage.findView(ModelView.ID);

		if (view == null)
			return null;

		// get file for schema
		File file = Activator.getDefault().getSelectionService().getOnotoaFile();
		if (file == null)
			return null;

		// get schema
		Assert.isNotNull(file.getTopicMapSchema());
		TopicMapSchema schema = file.getTopicMapSchema();

		final UseSearcher searcher = new UseSearcher((TopicType) dlg.getResult()[0], schema);
		searcher.fetchResult();

		try {

			// open view that displays that results
			SearchView searchView = (SearchView) activePage.findView(SearchView.ID);
			if (searchView == null)
				searchView = (SearchView) activePage.showView(SearchView.ID);
			else
				activePage.activate(searchView);

			// set results as content for the view
			searchView.setContent(searcher.getResult());

			// clear old menu
			searchView.removeContextMenu();

//			List<Action> actionList = new ArrayList<Action>();
//			actionList.add(new Action("Find use") {
//				@Override
//				public void run() {
//					IStructuredSelection sel = (IStructuredSelection) view.getSelection();
//					Object o = sel.getFirstElement();
//
//					if (o instanceof UseWrapper) {
//						searcher.setTopicType(((UseWrapper) o).getTopicType());
//
//					}
//				}
//			});
//			searchView.addContextMenu(actionList);

		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
