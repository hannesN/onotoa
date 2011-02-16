package de.topicmapslab.tmcledit.diagram.action;

import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Helper object to store the data for a {@link SetRoleAction}.
 * 
 * @author Hannes Niederhausen
 *
 */
public class SetRoleData implements Cloneable, Comparable<SetRoleData> {
	/** the role type */
	public TopicType role;
	/** the topic map schema */
	public TopicMapSchema schema;
	/** the edit domain */
	public IOnotoaEditDomain editDomain;
	/** an existing role player constraint*/
	public RolePlayerConstraint rpc;
	/** a role constraint to use */
	public RoleConstraint rc;

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SetRoleData clone()  {
		SetRoleData newData = new SetRoleData();
		newData.role = this.role;
		newData.schema = this.schema;
		newData.editDomain = this.editDomain;
		newData.rpc = this.rpc;
		
		return newData;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public int compareTo(SetRoleData o) {
		return this.role.getName().toLowerCase().compareTo(
				o.role.getName().toLowerCase());
	}
}