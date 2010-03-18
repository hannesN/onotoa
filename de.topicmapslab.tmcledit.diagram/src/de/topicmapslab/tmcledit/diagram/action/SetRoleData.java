package de.topicmapslab.tmcledit.diagram.action;

import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class SetRoleData implements Cloneable, Comparable<SetRoleData> {
	public TopicType role;
	public TopicMapSchema schema;
	public IOnotoaEditDomain editDomain;
	public RolePlayerConstraint rpc;
	public RoleConstraint rc;

	public SetRoleData() {
	}
	
	
	@Override
	public SetRoleData clone()  {
		SetRoleData newData = new SetRoleData();
		newData.role = this.role;
		newData.schema = this.schema;
		newData.editDomain = this.editDomain;
		newData.rpc = this.rpc;
		
		return newData;
	}


	public int compareTo(SetRoleData o) {
		return this.role.getName().toLowerCase().compareTo(
				o.role.getName().toLowerCase());
	}
}