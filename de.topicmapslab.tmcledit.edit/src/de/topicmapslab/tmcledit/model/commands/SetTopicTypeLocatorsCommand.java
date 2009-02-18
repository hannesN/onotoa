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
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetTopicTypeLocatorsCommand extends AbstractTopicTypeIdentificationCommand {

	public SetTopicTypeLocatorsCommand(List<String> newList, TopicType type) {
		super("Set Type Subject Locators", newList, type);
	}

	@Override
	protected List<String> getStringList() {
		return type.getLocators();
	}

}
