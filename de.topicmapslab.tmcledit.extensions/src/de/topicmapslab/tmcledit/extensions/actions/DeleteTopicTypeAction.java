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
/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteTopicTypeAction extends Action {

	private TopicType type;
	private ModelView modelView;

	public DeleteTopicTypeAction(ModelView modelView) {
		setText("Delete Type");
		this.modelView = modelView;
	}

	public void setType(TopicType type) {
		this.type = type;
	}
	
	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?",
				"Do you really want to delete the selected type?")) {
			DeleteTopicTypeCommand cmd = new DeleteTopicTypeCommand(type);
			modelView.getEditingDomain().getCommandStack().execute(cmd);
		}

	}
}
