package de.topicmapslab.tmcledit.domaindiagram.action;

import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Helper class to store data for a {@link SetTypeAction}
 * @author Hannes Niederhausen
 *
 */
public class SetTypeData implements Cloneable, Comparable<SetTypeData> {
	/** The type to set */
	public TopicType type;
	/** the schema containing the type */
	public TopicMapSchema schema;
	/** the edit domain containing the commandstack */
	public IOnotoaEditDomain editDomain;
	/** the constraint to type */
	public AbstractTypedConstraint typedConstraint;
	/** the kind of topic type */
	public KindOfTopicType kind;
	/** the featureId */
	public int featureId;

	/**
	 * Constructor
	 */
	public SetTypeData() {
		kind = KindOfTopicType.TOPIC_TYPE;
	}
	
	
	@Override
	public SetTypeData clone()  {
		SetTypeData newData = new SetTypeData();
		newData.type = this.type;
		newData.schema = this.schema;
		newData.editDomain = this.editDomain;
		newData.typedConstraint = this.typedConstraint;
		newData.kind = kind;
		newData.featureId = featureId;
		
		return newData;
	}


	public int compareTo(SetTypeData o) {
		return this.type.getName().toLowerCase().compareTo(
				o.type.getName().toLowerCase());
	}
}