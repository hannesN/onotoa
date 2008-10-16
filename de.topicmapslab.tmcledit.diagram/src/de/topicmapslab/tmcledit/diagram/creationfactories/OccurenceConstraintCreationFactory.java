/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.TopicIndexer;

public class OccurenceConstraintCreationFactory implements CreationFactory {

	private TopicType occurenceType;
	
	@Override
	public Object getNewObject() {
		OccurenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurenceTypeConstraint();
		if (occurenceType==null) {
			occurenceType = TopicIndexer.getInstance().createTopicType("foo:occType");
			occurenceType.setKind(KindOfTopicType.OCCURENCE_TYPE);
		}
		otc.setType(occurenceType);
		
		
		return otc;
	}

	public void setTopicType(TopicType topicType) {
		this.occurenceType = topicType;
	}
	
	@Override
	public Object getObjectType() {
		return OccurenceTypeConstraint.class;
	}
}