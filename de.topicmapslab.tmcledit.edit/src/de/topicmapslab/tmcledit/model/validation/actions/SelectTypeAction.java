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
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class SelectTypeAction extends TopicTypeAction {

	public SelectTypeAction(CommandStack cmdStack, KindOfTopicType type) {
		super(cmdStack, type);

	}

	@Override
	public void run() {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		FilterTopicSelectionDialog dialog = new FilterTopicSelectionDialog(activeWorkbenchWindow.getShell(), kindOfType);

		if (dialog.open() == Dialog.OK) {
			if (dialog.getResult().length > 0) {
				TopicType type = (TopicType) dialog.getFirstResult();

				AbstractCommand cmd = getCommand(type);
				getCommandStack().execute(cmd);

			}
		}

	}
}
