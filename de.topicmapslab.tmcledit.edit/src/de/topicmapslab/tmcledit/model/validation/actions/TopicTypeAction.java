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
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public abstract class TopicTypeAction extends ValidationAction {

	protected KindOfTopicType kindOfType;

	public TopicTypeAction(CommandStack cmdStack, KindOfTopicType type) {
		super(cmdStack);
		this.kindOfType = type;
	}

	public TopicTypeAction(Object model) {
		super(model);
	}

	public KindOfTopicType getKindOfType() {
		return kindOfType;
	}

	protected abstract AbstractCommand getCommand(TopicType topicType);

}