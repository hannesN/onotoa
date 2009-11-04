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
package de.topicmapslab.tmcledit.domaindiagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.dialogs.MessageDialog;

import de.topicmapslab.tmcledit.diagram.policies.AbstractDirectEditPolicy;
import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;

public class AbstractTypedConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getCommand(Object model, DirectEditRequest request) {

		if (model instanceof AbstractTypedConstraint) {
			
			Command cmd = new RenameTopicTypeCommand(((AbstractTypedConstraint) model).getType(), getNewString(request));
			if (cmd.canExecute())
				return cmd;
			else {
				String msg = null;
				if (getNewString(request).length()==0)
					msg = "Please enter a name.";
				else
					msg = "The name is already used.";
				MessageDialog.openError(Activator.getCurrentShell(), "Invalid name", msg);
			}
		}
		return null;
	}

}
