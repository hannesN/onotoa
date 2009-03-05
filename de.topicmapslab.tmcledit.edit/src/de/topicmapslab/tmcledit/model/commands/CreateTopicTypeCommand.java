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
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateTopicTypeCommand extends AbstractCommand {

	private TopicMapSchema schema;
	private String name;
	private TopicType topicType;

	public CreateTopicTypeCommand(TopicMapSchema schema, String name) {
		this.schema = schema;
		this.name = name;
		this.topicType = null;
	}

	public CreateTopicTypeCommand(TopicMapSchema schema, TopicType type) {
		this.schema = schema;
		this.name = type.getName();
		this.topicType = type;
	}

	@Override
	protected boolean prepare() {
		if (topicType == null) {
			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.setName(name);
		}
		return true;
	}

	public void execute() {
		schema.getTopicTypes().add(topicType);
	}

	@Override
	public boolean canUndo() {
		return super.canUndo();
	}

	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		schema.getTopicTypes().remove(topicType);
	}
}