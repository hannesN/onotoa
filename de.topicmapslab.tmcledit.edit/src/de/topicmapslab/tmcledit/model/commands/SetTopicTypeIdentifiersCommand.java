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
public class SetTopicTypeIdentifiersCommand extends AbstractTopicTypeIdentificationCommand {

	public SetTopicTypeIdentifiersCommand(List<String> newList, TopicType type) {
		super("Set Type Subject Identifiers", newList, type);
	}

	@Override
	protected List<String> getStringList() {
		return type.getIdentifiers();
	}

	
	

}
