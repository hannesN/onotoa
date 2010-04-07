/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;

public class AbstractRegExpConstraintComparator extends TMCLConstructComparator {

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
