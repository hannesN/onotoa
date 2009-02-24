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
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeContainerEditPolicy extends ContainerEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==OccurrenceTypeConstraint.class) {
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			CreateOccurrenceConstraintCommand cmd = new CreateOccurrenceConstraintCommand(((TypeNodeEditPart)getHost()).getCastedModel().getTopicType(), (OccurrenceTypeConstraint) request.getNewObject());
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		}
		return null;
	}
}
