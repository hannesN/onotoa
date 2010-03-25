/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Abstract Reg Exp Constraint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class AbstractRegExpConstraintComparator extends
		TMCLConstructComperator {

	public boolean equals(AbstractRegExpConstraint o1,
			AbstractRegExpConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getRegexp(), o2.getRegexp()) == false)
			return false;

		return true;
	}
} // AbstractRegExpConstraintTest
