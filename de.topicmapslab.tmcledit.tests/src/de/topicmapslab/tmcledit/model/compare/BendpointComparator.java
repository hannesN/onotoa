/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Bendpoint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */

public class BendpointComparator extends OnoObjectComparator {

	public boolean equals(Bendpoint o1, Bendpoint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (super.intCompare(o1.getPosX(), o2.getPosX()) == false)
			return false;

		if (super.intCompare(o1.getPosY(), o2.getPosY()) == false)
			return false;

		return true;
	}

} // BendpointTest