/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.OnoObject;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Ono Object</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class OnoObjectComparator extends MyCompare {

	public boolean equals(OnoObject o1, OnoObject o2) {

		if (o1 == null && o2 == null)
			return true;

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((super.intCompare(o1.getId(), o2.getId())) == false)
			return false;

		return true;
	}

} // OnoObjectTest
