/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveIsAAction extends ValidationAction {

	private TopicType tt;
	private TopicType isA;

	public RemoveIsAAction(TopicType tt, TopicType isA) {
		super();
		this.tt = tt;
		this.isA = isA;
		setText("Remove isa relation");
	}
	
	@Override
	public void run() {
		List<TopicType> type = new ArrayList<TopicType>(tt.getIsa());
		type.remove(isA);
		
		SetIsACommand cmd = new SetIsACommand(type, tt);
		if (cmd.canExecute())
			cmd.execute();
	}

}
