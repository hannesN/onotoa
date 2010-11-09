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
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.commands.ModifyPrefixCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewPrefixMappingDialog;

/**
 * @author niederhausen
 *
 */
public class ModifyPrefix extends ValidationAction {

	private MappingElement mappingElement;

	public ModifyPrefix(CommandStack cmdStack, MappingElement me) {
	    super(cmdStack);
	    this.mappingElement = me;
	    setText("Modify Prefix...");
    }

	@Override
	public void run() {
		
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		NewPrefixMappingDialog dlg = new NewPrefixMappingDialog(shell);
		dlg.setKey(mappingElement.getKey());
		if (Dialog.OK==dlg.open()) {
			ModifyPrefixCommand cmd = new ModifyPrefixCommand(mappingElement, mappingElement.getKey(), dlg.getUri());
			if (cmd.canExecute()) {
				getCommandStack().execute(cmd);
			}
		}	
	}

}
