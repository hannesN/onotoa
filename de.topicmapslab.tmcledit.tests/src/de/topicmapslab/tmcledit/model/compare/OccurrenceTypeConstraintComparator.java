/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.tests.AbstractTypedCardinalityConstraintTest;

import junit.textui.TestRunner;

public class OccurrenceTypeConstraintComparator extends
		AbstractTypedCardinalityConstraintComparator {

	public boolean equals(OccurrenceTypeConstraint o1,
			OccurrenceTypeConstraint o2) {

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

} // OccurrenceTypeConstraintTest
