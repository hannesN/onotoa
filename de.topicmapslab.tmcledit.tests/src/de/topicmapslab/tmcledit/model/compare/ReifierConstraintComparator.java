/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.ReifierConstraint;

public class ReifierConstraintComparator extends
		AbstractTypedCardinalityConstraintComparator {

	public boolean equals(ReifierConstraint o1, ReifierConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}
} // ReifierConstraintTest
