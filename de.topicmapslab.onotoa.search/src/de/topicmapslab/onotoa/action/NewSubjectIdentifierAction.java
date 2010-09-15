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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import de.topicmapslab.onotoa.search.searchImpl.SubjectIdentifierSearcher;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;

/**
 * @author sip
 * 
 */
public class NewSubjectIdentifierAction extends Action {

	private TreeViewer viewer;
	private TopicType topicType;
	private CommandStack commandStack;

	/**
	 * @param string
	 * @param viewer
	 */
	public NewSubjectIdentifierAction(CommandStack commandStack ,String label, TreeViewer viewer) {

		super(label, Action.AS_PUSH_BUTTON);
		this.viewer = viewer;
		this.commandStack = commandStack;

	}

	@Override
	public void run() {

		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		topicType = ((TopicTypeWrapper) sel.getFirstElement()).getTopicType();

		InputDialog dlg = new InputDialog(viewer.getControl().getShell(), "New..",
		        "Please enter the new subject identifier.", "", new IInputValidator() {

			        public String isValid(String newText) {

				        SubjectIdentifierSearcher searcher = new SubjectIdentifierSearcher(
				                (TopicMapSchema) topicType.eContainer());
				        if (searcher.getIdentifierList().contains(newText))
					        return "This Subject Identifier is already in use!";
				        return null;
			        }

		        });

		if (Dialog.OK == dlg.open()) {

			String result = dlg.getValue();
			List<String> list = new ArrayList<String>();
			list.add(result);
			SetTopicTypeIdentifiersCommand command = new SetTopicTypeIdentifiersCommand(list, topicType);
			commandStack.execute(command);

		}

	}

}
