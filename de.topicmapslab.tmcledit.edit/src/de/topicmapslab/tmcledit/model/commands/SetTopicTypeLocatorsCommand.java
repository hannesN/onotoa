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
