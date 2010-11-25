/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractCardinalityConstraint;

/**
 * Comparator to compare two {@link AbstractCardinalityConstraint}
 * @author 
 *
 */
public abstract class AbstractCardinalityConstraintComparator extends
		TMCLConstructComparator {

	
	/**
	 * Compares to constraints.
	 * @param o1
	 * @param o2
	 * @return <code>true</code> if the two constraints are equal 
	 */
	public boolean equals(AbstractCardinalityConstraint o1,
			AbstractCardinalityConstraint o2) {

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

		return true;

	}

} // AbstractCardinalityContraintTest
