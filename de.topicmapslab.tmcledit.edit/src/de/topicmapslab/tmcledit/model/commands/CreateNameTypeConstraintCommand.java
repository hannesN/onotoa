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
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateNameTypeConstraintCommand extends AbstractCommand {


	private final TopicType topicType;
	private final NameTypeConstraint nameTypeConstraint;
	

	public CreateNameTypeConstraintCommand(TopicType topicType,
			NameTypeConstraint nameTypeConstraint) {
		super("Create Name Type Constraint");
		this.topicType = topicType;
		this.nameTypeConstraint = nameTypeConstraint;
	}
	
	@Override
	public void execute() {
		topicType.getNameContraints().add(nameTypeConstraint);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getNameContraints().remove(nameTypeConstraint);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}

}
