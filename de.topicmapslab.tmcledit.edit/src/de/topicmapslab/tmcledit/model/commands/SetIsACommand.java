/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;


import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetIsACommand extends AbstractConnectionCommand {

	public SetIsACommand(List<TopicType> newList, TopicType topic) {
		super("Set is a relationship", newList, topic);
	}

	@Override
	protected EdgeType getEdgeType() {
		return EdgeType.IS_ATYPE;
	}

}
