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

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

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
	public boolean canExecute() {
		if (ModelIndexer.getTopicIndexer().getTopicTypeByName(name)!=null)
			return false;
	    return super.canExecute();
	}
	
	@Override
	protected boolean prepare() {
		if (topicType == null) {
			topicType = ModelIndexer.getTopicIndexer().createTopicType(KindOfTopicType.TOPIC_TYPE);
			topicType.setName(name);
		}
		return true;
	}

	public void execute() {
		schema.getTopicTypes().add(topicType);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		schema.getTopicTypes().remove(topicType);
	}
}