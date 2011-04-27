/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;

public class SubjectLocatorConstraintComparator extends
		AbstractRegExpConstraintComparator {

	public boolean equals(SubjectLocatorConstraint o1,
			SubjectLocatorConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getCardMin(), o2.getCardMin()) == false)
			return false;

		if (stringCompare(o1.getCardMax(), o2.getCardMax()) == false)
			return false;

		return true;

	}

} // SubjectLocatorConstraintTest
