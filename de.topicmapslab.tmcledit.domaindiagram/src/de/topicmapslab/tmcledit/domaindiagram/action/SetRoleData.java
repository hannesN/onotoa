package de.topicmapslab.tmcledit.domaindiagram.action;

import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

public class SetRoleData implements Cloneable {
	public RoleType role;
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
}