/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.compare.NodeComparator;

public class AssociationNodeComparator extends NodeComparator {

	public boolean equals(AssociationNode o1, AssociationNode o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;
	}
} // AssociationNodeTest
