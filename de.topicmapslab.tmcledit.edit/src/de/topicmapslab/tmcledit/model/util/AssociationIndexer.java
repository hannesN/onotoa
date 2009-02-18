/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationIndexer {

	private TopicMapSchema topicMapSchema;
	
	public AssociationIndexer() {
	
	}
	
	public void init(TopicMapSchema schema) {
		topicMapSchema = schema;	
	}
	
	public List<RolePlayerConstraint> getRolePlayerConstraintsFor(TopicType topicType) {
		List<RolePlayerConstraint> result = new ArrayList<RolePlayerConstraint>();
		
		for (AssociationTypeConstraint atc : topicMapSchema.getAssociationTypeConstraints()) {
			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				if (rpc.getPlayer().equals(topicType))
					result.add(rpc);
			}
		}
		return result;
	}
}
