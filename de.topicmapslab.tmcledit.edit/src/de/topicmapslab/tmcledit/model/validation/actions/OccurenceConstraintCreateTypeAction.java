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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceConstraintCreateTypeAction extends CreateTypeAction {

	public OccurenceConstraintCreateTypeAction(CommandStack cmdStack) {
		super(cmdStack, KindOfTopicType.OCCURENCE_TYPE);
	}
	
	@Override
	protected AbstractCommand getCommand(TopicType topicType) {
		CompoundCommand cmd = new CompoundCommand();
		cmd.append(new CreateTopicTypeCommand(ModelIndexer.getInstance()
				.getTopicMapSchema(), topicType));
		cmd.append(new GenericSetCommand(getModelObject(),
				ModelPackage.OCCURENCE_TYPE_CONSTRAINT__TYPE,
				topicType));
		return cmd;
	}
}
