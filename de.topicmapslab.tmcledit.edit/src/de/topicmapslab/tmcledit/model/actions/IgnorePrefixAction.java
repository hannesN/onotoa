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

import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.CreateAnnotationCommand;
import de.topicmapslab.tmcledit.model.validation.actions.ValidationAction;

/**
 * @author Adds a string to the ignore prefix annotation
 *
 */
public class IgnorePrefixAction extends ValidationAction {

	private final TopicMapSchema schema;
	private final String prefix;
	
	
	
	public IgnorePrefixAction(CommandStack cmdStack, TopicMapSchema schema, String prefix) {
		super(cmdStack);
		setText("Ignore..");
		this.schema = schema;
		this.prefix = prefix;
    }

	@Override
	public void run() {
	    getCommandStack().execute(new CreateAnnotationCommand(schema, "validator.ignoreprefix", prefix));
	}
	
}
