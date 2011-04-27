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
 * The command so set the subject identifier for a topic type.
 * 
 * @author Hannes Niederhausen
 *
 */
public class SetTopicTypeIdentifiersCommand extends AbstractTopicTypeIdentificationCommand {

	/**
	 * Constructor
	 * @param newList the new list of identifiers
	 * @param type the topic type 
	 */
	public SetTopicTypeIdentifiersCommand(List<String> newList, TopicType type) {
		super("Set Type Subject Identifiers", newList, type);
	}

	@Override
	protected List<String> getStringList() {
		return type.getIdentifiers();
	}

	
	

}
