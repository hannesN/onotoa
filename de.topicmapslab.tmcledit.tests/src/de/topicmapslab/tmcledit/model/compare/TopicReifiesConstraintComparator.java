/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;

public class TopicReifiesConstraintComparator extends
		AbstractTypedCardinalityConstraintComparator {

	public boolean equals(TopicReifiesConstraint o1, TopicReifiesConstraint o2) {

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

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1.getType(), o2.getType()) == false)
			return false;

		return true;

	}

} // TopicReifiesConstraintTest
