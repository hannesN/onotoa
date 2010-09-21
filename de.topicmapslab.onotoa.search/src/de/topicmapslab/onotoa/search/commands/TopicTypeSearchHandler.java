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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.search.dialogs.TopicTypeSearchDialog;
import de.topicmapslab.onotoa.search.searchImpl.MainSearchHandler;
import de.topicmapslab.onotoa.search.views.SearchView;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Handler for the TopicType search.
 * 
 * @author Sebastian Lippert
 */
public class TopicTypeSearchHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */

	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get shell and open search dialog for TopicTypes
		Shell shell = HandlerUtil.getActiveShell(event);
		TopicTypeSearchDialog dialog = new TopicTypeSearchDialog(shell);

		// OK button in search dialog was presses
		if (dialog.open() == Window.OK) {

			// get active page and active view
			IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
			ModelView view = (ModelView) activePage.findView(ModelView.ID);

			if (view == null)
				return null;

			// get schema
			TopicMapSchema schema = view.getCurrentTopicMapSchema();

			if (view.getCurrentTopicMapSchema() == null)
				return null;

			// TODO Hannes: Real monitor
			MainSearchHandler handler = new MainSearchHandler(dialog.getSearchData(), schema,
			        new NullProgressMonitor());

			// sort results
			Collections.sort((List<? extends Comparable>) handler.getResultContainer().getList());

			try {

				// open view that displays that results
				SearchView searchView = (SearchView) activePage.findView(SearchView.ID);
				if (searchView == null)
					searchView = (SearchView) activePage.showView(SearchView.ID);
				else
					activePage.activate(searchView);

				// set results as content for the view
				searchView.setContent(handler.getResultContainer());
				searchView.removeContextMenu();

			} catch (PartInitException e) {
				throw new RuntimeException(e);
			}
		}

		return null;
	}
}
