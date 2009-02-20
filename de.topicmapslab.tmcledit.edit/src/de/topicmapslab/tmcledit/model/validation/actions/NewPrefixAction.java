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

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.CreatePrefixMappingCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewPrefixMappingDialog;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewPrefixAction extends ValidationAction {

	private TopicMapSchema schema;
	private String key;
	
	public NewPrefixAction(CommandStack cmdStack, TopicMapSchema schema, String prefix) {
		super(cmdStack);
		this.schema = schema;
		this.key = prefix;
		setText("Create new prefix entry");
	}

	@Override
	public void run() {
		NewPrefixMappingDialog dlg = new NewPrefixMappingDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell());
		dlg.setKey(key);
		if (Dialog.OK==dlg.open()) {
			CreatePrefixMappingCommand cmd = new CreatePrefixMappingCommand(schema, 
					dlg.getKey(), dlg.getUri());
			getCommandStack().execute(cmd);
		}	

	}

}
