/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Mapping Element</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappingElementComparator extends OnoObjectComparator {

	public boolean equals(MappingElement o1, MappingElement o2) {
		
		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;
		
		if (super.stringCompare(o1.getKey(), o2.getKey()) == false)
			return false;
		
		if (super.stringCompare(o1.getValue(), o2.getValue()) == false)
			return false;
		
		return true;
	}

} // MappingElementTest
