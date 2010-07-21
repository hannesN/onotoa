/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.MappingElement;

public class MappingElementComparator extends OnoObjectComparator {

	public boolean equals(MappingElement o1, MappingElement o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getKey(), o2.getKey()) == false)
			return false;

		if (stringCompare(o1.getValue(), o2.getValue()) == false)
			return false;

		return true;
	}

} // MappingElementTest
