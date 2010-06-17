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

import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateItemIdentifierConstraintCommand extends AbstractCommand {


	private final TopicType topicType;
	private final ItemIdentifierConstraint itemIdentifierConstraint;
	
	
	public CreateItemIdentifierConstraintCommand(TopicType topicType) {
		this(topicType, ModelFactory.eINSTANCE.createItemIdentifierConstraint());
	}
	
	public CreateItemIdentifierConstraintCommand(TopicType topicType,
			ItemIdentifierConstraint subjectIdentifierConstraint) {
		super("Create Subject Idnetifier Constraint");
		this.topicType = topicType;
		this.itemIdentifierConstraint = subjectIdentifierConstraint;
	}
	
	public void execute() {
		topicType.getItemIdentifierConstraints().add(itemIdentifierConstraint);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getItemIdentifierConstraints().remove(itemIdentifierConstraint);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}

	

}
