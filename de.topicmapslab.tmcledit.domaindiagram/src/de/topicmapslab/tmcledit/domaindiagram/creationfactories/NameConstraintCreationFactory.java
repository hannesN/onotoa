/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;

/**
 * Creation factory for name constraints
 * @author Hannes Niederhausen
 *
 */
public class NameConstraintCreationFactory implements
		CreationFactory {
	public Object getNewObject() {
		NameTypeConstraint ntc = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		
		return ntc;
	}

	public Object getObjectType() {
		return NameTypeConstraint.class;
	}
}