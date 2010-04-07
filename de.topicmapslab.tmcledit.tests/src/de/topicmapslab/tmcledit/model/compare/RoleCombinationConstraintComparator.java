/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;

public class RoleCombinationConstraintComparator extends
		TMCLConstructComparator {

	public boolean equals(RoleCombinationConstraint o1,
			RoleCombinationConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}

} // RoleCombinationConstraintTest
