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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.search.dialogs.CleanSchemaDialog;
import de.topicmapslab.onotoa.search.searchImpl.NeverUsedTopicsSearcher;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * 
 * Handles clean feature for schemas. That means it provides the dialog and
 * executes the deletion for selected types.
 * 
 * @author Sebastian Lippert
 * 
 */
public class CleanSchemaHandler extends AbstractHandler {

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

		// get schema
		TopicMapSchema schema = view.getCurrentTopicMapSchema();

		// get unused topics
		NeverUsedTopicsSearcher searcher = new NeverUsedTopicsSearcher(schema);
		searcher.fetchResult();

		Collections.sort((List<? extends Comparable>) searcher.getResult().getList());

		// get shell and open search dialog for unused TopicTypes
		Shell shell = HandlerUtil.getActiveShell(event);
		CleanSchemaDialog dialog = new CleanSchemaDialog(shell, searcher.getReslutList());

		// initiate command for deletion
		DeleteTopicTypeCommand deleteCommand;

		if (dialog.open() == Window.OK)

			// iterate over selected TopicTypes and delete them from schema
			for (TopicType tt : dialog.getCleanList()) {

				deleteCommand = new DeleteTopicTypeCommand(tt);
				deleteCommand.canExecute();
				deleteCommand.execute();

			}

		return null;
	}
}
