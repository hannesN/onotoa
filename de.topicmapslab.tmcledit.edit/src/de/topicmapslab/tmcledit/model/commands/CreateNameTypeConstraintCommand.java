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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateNameTypeConstraintCommand extends AbstractCommand {


	private final TopicType topicType;
	private final NameTypeConstraint nameTypeConstraint;
	private TopicMapSchema schema;
	
	public CreateNameTypeConstraintCommand(TopicType topicType) {
		this(topicType, ModelFactory.eINSTANCE.createNameTypeConstraint());
	}
	
	public CreateNameTypeConstraintCommand(TopicType topicType,
			NameTypeConstraint nameTypeConstraint) {
		super("Create Name Type Constraint");
		this.topicType = topicType;
		this.nameTypeConstraint = nameTypeConstraint;
	}
	
	public void execute() {
		topicType.getNameConstraints().add(nameTypeConstraint);
		if (schema!=null)
			schema.getTopicTypes().add(nameTypeConstraint.getType());
	}

	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getNameConstraints().remove(nameTypeConstraint);
		if (schema!=null)
			schema.getTopicTypes().remove(nameTypeConstraint.getType());
	}
	
	@Override
	protected boolean prepare() {
		if ( (nameTypeConstraint.getType()!=null) && (nameTypeConstraint.getType().eContainer()==null) ){
			schema = (TopicMapSchema) topicType.eContainer();
		}
		return true;
	}

}
