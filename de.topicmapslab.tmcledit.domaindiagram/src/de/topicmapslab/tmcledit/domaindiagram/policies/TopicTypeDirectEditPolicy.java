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
package de.topicmapslab.tmcledit.domaindiagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.domaindiagram.editparts.NodeEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class TopicTypeDirectEditPolicy extends DirectEditPolicy {

	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNode tn = (TypeNode) ((NodeEditPart)getHost()).getModel();
			
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			RenameTopicTypeCommand cmd = new RenameTopicTypeCommand(tn.getTopicType(), newName);
			DomainEditDomain ed = (DomainEditDomain) getHost().getViewer().getEditDomain();
			
			if (cmd.canExecute())
				return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
			else {
				String msg = null;
				if (newName.length()==0)
					msg = "Please enter a name.";
				else
					msg = "The name is already used.";
				MessageDialog.openError(Activator.getCurrentShell(), "Invalid name", msg);
			}
			
			
		
		}
		return null;
	}

	
	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNodeEditPart tnep = ((TypeNodeEditPart)getHost());
			
			tnep.revertNameChange();
		}
	}
	
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		TypeNodeEditPart ep = (TypeNodeEditPart) getHost();
		ep.handleNameChange(value);
	}

}
