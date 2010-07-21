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

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

public class RemovePrefixMappingCommand extends AbstractCommand {

	private final MappingElement me;
	private final TopicMapSchema schema;
	private int index;

	public RemovePrefixMappingCommand(TopicMapSchema schema, MappingElement me) {
		super();
		this.schema = schema;
		this.me = me;
	}

	@Override
	protected boolean prepare() {
		index = schema.getMappings().indexOf(me);
		if (index == -1)
			return false;
		return true;
	}

	public void execute() {

		schema.getMappings().remove(me);
	}

	@Override
	public void undo() {
		schema.getMappings().add(index, me);
	}

	public void redo() {
		execute();
	}

	@Override
	public String getLabel() {
		return "Remove Prefix Mapping";
	}

}
