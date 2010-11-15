/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

public class RolePlayerConstraintComparator extends
		AbstractCardinalityConstraintComparator {

	public boolean equals(RolePlayerConstraint o1, RolePlayerConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1.getPlayer(), o2.getPlayer()) == false)
			return false;

		RoleConstraintComparator comp1 = new RoleConstraintComparator();

		if (comp1.equals(o1.getRole(), o2.getRole()) == false)
			return false;

		return true;

	}

} // RolePlayerConstraintTest
