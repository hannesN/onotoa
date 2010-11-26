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
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.search.Activator;
import de.topicmapslab.onotoa.search.dialogs.CleanSchemaDialog;
import de.topicmapslab.onotoa.search.searchImpl.NeverUsedTopicsSearcher;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
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

	/**
	 * {@inheritDoc}
	 */

	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get active page and active view
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

		// get unused topics
		NeverUsedTopicsSearcher searcher = new NeverUsedTopicsSearcher(schema);
		searcher.fetchResult();

		Collections.sort((List<? extends Comparable>) searcher.getResult().getContentList());

		// get shell and open search dialog for unused TopicTypes
		Shell shell = HandlerUtil.getActiveShell(event);
		CleanSchemaDialog dialog = new CleanSchemaDialog(shell, searcher.getResultList());

		// initiate command for deletion
		DeleteTopicTypeCommand deleteCommand;
		CompoundCommand compoundCommand = new CompoundCommand();

		if (dialog.open() == Window.OK) {

			// iterate over selected TopicTypes and delete them from schema
			for (TopicType tt : dialog.getCleanList()) {

				// create CompoundCommand which makes it possible to undo them
				// all at once
				deleteCommand = new DeleteTopicTypeCommand(tt);
				compoundCommand.append(deleteCommand);

			}

			// excute all
			commandStack.execute(compoundCommand);
		}
		return null;

	}
}
