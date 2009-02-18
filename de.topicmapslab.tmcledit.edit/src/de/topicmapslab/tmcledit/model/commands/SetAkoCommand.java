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
public class SetAkoCommand extends AbstractConnectionCommand {


	public SetAkoCommand(List<TopicType> newList, TopicType topic) {
		super("Set a kind of relationship", newList, topic);
	}

	@Override
	protected EdgeType getEdgeType() {
		return EdgeType.AKO_TYPE;
	}
}
