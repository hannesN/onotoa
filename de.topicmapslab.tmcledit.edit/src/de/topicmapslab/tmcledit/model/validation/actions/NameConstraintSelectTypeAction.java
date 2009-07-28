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
import org.eclipse.emf.ecore.EObject;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;


/**
 * @author Hannes Niederhausen
 *
 */
public class NameConstraintSelectTypeAction extends SelectTypeAction {

	
	
	public NameConstraintSelectTypeAction(CommandStack cmdStack) {
		super(cmdStack, KindOfTopicType.NAME_TYPE);
		setText("Select Type");
	}
		
	@Override
	protected AbstractCommand getCommand(TopicType topicType) {
		return new GenericSetCommand((EObject) getModelObject(),
				ModelPackage.NAME_TYPE_CONSTRAINT__TYPE,
				topicType);
	}
}
