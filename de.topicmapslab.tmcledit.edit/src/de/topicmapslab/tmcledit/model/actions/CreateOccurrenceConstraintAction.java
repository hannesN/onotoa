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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.emf.common.command.Command;

import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 *
 */
public class CreateOccurrenceConstraintAction extends AbstractCreateConstraintAction {

	public CreateOccurrenceConstraintAction(ModelView view) {
	    super(view);
	    setText("Create &Occurrence Constraint...");
    }

	@Override
	protected Command getCommand() {
		return new CreateOccurrenceConstraintCommand(getTopicType());
	}

}
