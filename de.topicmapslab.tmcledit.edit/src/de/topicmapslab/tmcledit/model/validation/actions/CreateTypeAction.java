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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class CreateTypeAction extends TopicTypeAction {
	public CreateTypeAction(CommandStack cmdStack, KindOfTopicType type) {
		super(cmdStack, type);
		setText("Create New Type..");
	}
	
	@Override
	public void run() {
		NewTopicTypeWizard wizard = new NewTopicTypeWizard(getKindOfType());

		WizardDialog dlg = new WizardDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), wizard);
		
		if (dlg.open()==Dialog.OK) {
			TopicType tt = wizard.getNewTopicType();
			AbstractCommand cmd = getCommand(tt);
			getCommandStack().execute(cmd);
		}
	}

}
