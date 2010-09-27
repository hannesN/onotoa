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

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1.getOtherPlayer(), o2.getOtherPlayer()) == false)
			return false;

		if (comp.equals(o1.getOtherRole(), o2.getOtherRole()) == false)
			return false;

		if (comp.equals(o1.getPlayer(), o2.getPlayer()) == false)
			return false;

		if (comp.equals(o1.getRole(), o2.getRole()) == false)
			return false;

		return true;

	}

} // RoleCombinationConstraintTest
