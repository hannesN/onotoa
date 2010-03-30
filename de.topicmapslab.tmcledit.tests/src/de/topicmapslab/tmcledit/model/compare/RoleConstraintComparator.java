/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.RoleConstraint;

public class RoleConstraintComparator extends
		AbstractTypedCardinalityConstraintComparator {

	public boolean equals(RoleConstraint o1, RoleConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}

} // RoleConstraintTest
