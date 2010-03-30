/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.tests.AbstractCardinalityConstraintTest;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Abstract Typed Cardinality Constraint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class AbstractTypedCardinalityConstraintComparator extends
		AbstractCardinalityConstraintComparator {

	public boolean equals(AbstractTypedCardinalityConstraint o1,
			AbstractTypedCardinalityConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1.getType(), o2.getType()) == false)
			return false;
		
		return true;

	}

} // AbstractTypedCardinalityConstraintTest
