/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;

public class AbstractUniqueValueTopicTypeComparator extends TopicTypeComparator {

	public boolean equals(AbstractUniqueValueTopicType o1,
			AbstractUniqueValueTopicType o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (o1.isUnique() != o2.isUnique())
			return false;

		return true;

	}
} // AbstractUniqueValueTopicTypeTest
