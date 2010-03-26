/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Subject Locator Constraint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class SubjectLocatorConstraintComparator extends
		AbstractRegExpConstraintComparator {

	public boolean equals(SubjectLocatorConstraint o1,
			SubjectLocatorConstraintComparator o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;
		
		
		return true;

	}

} // SubjectLocatorConstraintTest
