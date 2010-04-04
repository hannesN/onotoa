/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

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

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1.getType(), o2.getType()) == false)
			return false;

		if (o1.getPlayerConstraints().size() == o2.getPlayerConstraints()
				.size()) {
			if (rolePlayerConstraintListCompare(o1.getPlayerConstraints(), o2
					.getPlayerConstraints()) == false)
				return false;
		} else
			return false;

		return true;

	}

	protected boolean rolePlayerConstraintListCompare(
			EList<RolePlayerConstraint> list1, EList<RolePlayerConstraint> list2) {

		RolePlayerConstraintComparator comp = new RolePlayerConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;
	}

} // AssociationTypeConstraintTest
