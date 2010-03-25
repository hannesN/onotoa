/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;

public class SubjectIdentifierConstraintComparator extends
		AbstractRegExpConstraintComparator {

	public boolean equals(SubjectIdentifierConstraint o1,
			SubjectIdentifierConstraint o2) {

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

		if (stringCompare(o1.getRegexp(), o2.getRegexp()) == false)
			return false;

		return true;

	}
} // SubjectIdentifierConstraintTest
