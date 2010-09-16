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
package de.topicmapslab.onotoa.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;

import de.topicmapslab.onotoa.search.searchImpl.SubjectLocatorSearcher;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeLocatorsCommand;

/**
 * 
 * Action that adds SubjectLocator to selected TopicType of Viewer
 * 
 * @author Sebastian Lippert
 * 
 */

public class NewSubjectLocatorAction extends Action {

	private Viewer viewer;
	private TopicType topicType;
	private CommandStack commandStack;

	/**
	 * @param comandStack
	 *            CommandStack
	 * @param label
	 *            Text for the action
	 * @param viewer
	 *            Viewer of the ContextMenu with this action
	 */
	
	public NewSubjectLocatorAction(CommandStack commandStack, String label, Viewer viewer) {

		super(label, Action.AS_PUSH_BUTTON);
		this.viewer = viewer;
		this.commandStack = commandStack;

	}

	@Override
	public void run() {

		// get selection (Topic Type)
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		topicType = ((TopicTypeWrapper) sel.getFirstElement()).getTopicType();

		// call InputDialog
		InputDialog dlg = new InputDialog(viewer.getControl().getShell(), "New..",
		        "Please enter the new subject locator.", "", new IInputValidator() {

			        public String isValid(String newText) {

			        	 // check if input is valid
				        SubjectLocatorSearcher searcher = new SubjectLocatorSearcher(
				                (TopicMapSchema) topicType.eContainer());
				      
				        // unique test
				        if (searcher.getLocatorList().contains(newText))
					        return "This Subject Locator is already in use!";

				        // empty test

				        if (newText.equals(""))
					        return "";

				        return null;
			        }

		        });

		if (Dialog.OK == dlg.open()) {

			String result = dlg.getValue();
			List<String> list = new ArrayList<String>();
			list.add(result);
			SetTopicTypeLocatorsCommand command = new SetTopicTypeLocatorsCommand(list, topicType);
		
			// CommandStack execution to enable undo/redo operation
			commandStack.execute(command);

			// fire event for listeners for eventually reactions
			if (getListeners().length != 0)
				firePropertyChange(new PropertyChangeEvent(this, IContextMenuAction.ADD_SUBJECTLOCATOR, "", result));

		}

	}

}
