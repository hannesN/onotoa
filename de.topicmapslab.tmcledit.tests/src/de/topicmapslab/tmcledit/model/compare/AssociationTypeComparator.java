/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;

public class AssociationTypeComparator extends ScopedTopicTypeComparator {

	public boolean equals(AssociationType o1, AssociationType o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		ReifierConstraintComparator comp = new ReifierConstraintComparator();

		if (comp.equals(o1.getReifierConstraint(), o2.getReifierConstraint()) == false)
			return false;

		if (o1.getRoleCombinations().size() == o2.getRoleCombinations().size()) {
			if (RoleCombinationConstraintListComparator(o1
					.getRoleCombinations(), o2.getRoleCombinations()) == false)
				return false;
		} else
			return false;

		if (o1.getRoles().size() == o2.getRoles().size()) {
			if (RoleConstraintListComparator(o1.getRoles(), o2.getRoles()) == false)
				return false;
		} else
			return false;

		return true;

	}

	/**
	 * Compares two lists with NameTypeConstraint RoleConstraints
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean RoleConstraintListComparator(EList<RoleConstraint> list1,
			EList<RoleConstraint> list2) {

		RoleConstraintComparator comp = new RoleConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	/**
	 * Compares two lists with NameTypeConstraint RoleCombinationConstraints
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean RoleCombinationConstraintListComparator(
			EList<RoleCombinationConstraint> list1,
			EList<RoleCombinationConstraint> list2) {

		RoleCombinationConstraintComparator comp = new RoleCombinationConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

} // AssociationTypeTest
