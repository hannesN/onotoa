/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class NameConstraintCreationFactory implements
		CreationFactory {
	public Object getNewObject() {
		NameTypeConstraint ntc = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		
		int i=0;
		String n = "name";
		TopicType tt = null; 
		while ( (tt=ModelIndexer.getTopicIndexer().getTopicTypeByName(n+i)) != null) {
			i++;
		}
		tt = ModelFactory.eINSTANCE.createNameType();
		tt.setName(n+i);
		
		ntc.setType(tt);
		
		
		return ntc;
	}

	public Object getObjectType() {
		return NameTypeConstraint.class;
	}
}