/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

public abstract class ScopedTopicTypeComparator extends TopicTypeComparator {

	public boolean equals(ScopedTopicType o1, ScopedTopicType o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (o1.getScope().size() == o2.getScope().size()) {
			if (ScopeConstraintListComparator(o1.getScope(), o2.getScope()) == false)
				return false;
		} else
			return false;

		return true;

	}

	/**
	 * Compares two lists with ScopeConstraint entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean ScopeConstraintListComparator(
			EList<ScopeConstraint> list1, EList<ScopeConstraint> list2) {

		ScopeConstraintComparator comp = new ScopeConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}
} // ScopedTopicTypeTest
