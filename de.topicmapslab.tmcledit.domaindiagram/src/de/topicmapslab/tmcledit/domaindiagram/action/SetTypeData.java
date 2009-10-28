package de.topicmapslab.tmcledit.domaindiagram.action;

import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class SetTypeData implements Cloneable {
	public TopicType type;
	public TopicMapSchema schema;
	public DomainEditDomain editDomain;
	public AbstractTypedConstraint typedConstraint;
	public KindOfTopicType kind;
	public int featureId;

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
}