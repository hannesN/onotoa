/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;

public class AssociationTypeConstraintComparator extends
		AbstractTypedConstraintComparator {

	public boolean equals(AssociationTypeConstraint o1,
			AssociationTypeConstraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}

} // AssociationTypeConstraintTest
