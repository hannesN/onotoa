/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.LabelPos;

public class LabelPosComparator extends OnoObjectComparator {

	public boolean equals(LabelPos o1, LabelPos o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (intCompare(o1.getPosX(), o2.getPosX()) == false)
			return false;

		if (intCompare(o1.getPosY(), o2.getPosY()) == false)
			return false;

		return true;

	}

} // LabelPosTest
