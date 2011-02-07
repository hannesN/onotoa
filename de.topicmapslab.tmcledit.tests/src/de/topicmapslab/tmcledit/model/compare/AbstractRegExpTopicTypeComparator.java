/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;

public abstract class AbstractRegExpTopicTypeComparator extends
		TopicTypeComparator {

	public boolean equals(AbstractRegExpTopicType o1, AbstractRegExpTopicType o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getRegExp(), o2.getRegExp()) == false)
			return false;

		return true;

	}

} // AbstractRegExpTopicTypeTest
