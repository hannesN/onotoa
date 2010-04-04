/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Abstract Typed Constraint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class AbstractTypedConstraintComparator extends
		TMCLConstructComparator {

	public boolean equals(AbstractTypedConstraint o1, AbstractTypedConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}

} // AbstractTypedConstraintTest
