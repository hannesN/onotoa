/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.tests.OnoObjectTest;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>TMCL Construct</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class TMCLConstructComperator extends OnoObjectComparator {

	public boolean equals(TMCLConstruct o1, TMCLConstruct o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (super.stringCompare(o1.getComment(), o2.getComment()) == false)
			return false;

		if (super.stringCompare(o1.getDescription(), o2.getDescription()) == false)
			return false;

		if (super.stringCompare(o1.getSee_also(), o2.getSee_also()) == false)
			return false;
//
//		if (super.objCompare(o1.getAnnotations(), o2.getAnnotations()) == false)
//			return false;
		return true;
	}
} // TMCLConstructTest
