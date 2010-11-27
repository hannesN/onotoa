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
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;

import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.psiprovider.PSIContentProposalProvider;

/**
 * 
 * Action that adds SubjectIdentifier to selected TopicType of Viewer
 * 
 * @author Sebastian Lippert
 * 
 */

public class NewSubjectIdentifierAction extends Action {

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

	public NewSubjectIdentifierAction(CommandStack commandStack, String label, Viewer viewer) {

		super(label, Action.AS_PUSH_BUTTON);
		this.viewer = viewer;
		this.commandStack = commandStack;

	}

	@Override
	public void run() {

		// get selection (Topic Type)
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();

		// check if selected element is a TopicType
		if (!(sel.getFirstElement() instanceof TopicTypeWrapper))
			return;

		topicType = ((TopicTypeWrapper) sel.getFirstElement()).getTopicType();

		// call InputDialog

		AssistDialog dia = new AssistDialog(viewer.getControl().getShell(), "New..",
		        "Please enter the new subject identifier.", "", new IInputValidator() {

			        public String isValid(String newText) {

				        // unique test
				        if (ModelIndexer.getTopicIndexer().getTopicTypeBySubjectIdentifier(newText) != null)
					        return "This Subject Identifier is already in use!";

				        // empty test
				        if (newText.equals(""))
					        return "";

				        return null;
			        }

		        });

		if (Dialog.OK == dia.open()) {

			String result = dia.getValue();
			List<String> list = new ArrayList<String>();
			list.add(result);
			SetTopicTypeIdentifiersCommand command = new SetTopicTypeIdentifiersCommand(list, topicType);

			// CommandStack execution to enable undo/redo operation
			commandStack.execute(command);

		}

	}

	private class AssistDialog extends InputDialog {
		public AssistDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue,
		        IInputValidator validator) {
			super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Control control = super.createDialogArea(parent);

			PSIContentProposalProvider proposalProvider = new PSIContentProposalProvider();
			proposalProvider.setName(topicType.getName());
			new ContentAssistCommandAdapter(getText(), new TextContentAdapter(), proposalProvider, null,
			        PSIContentProposalProvider.KEYS);

			return control;
		}
	}

}
