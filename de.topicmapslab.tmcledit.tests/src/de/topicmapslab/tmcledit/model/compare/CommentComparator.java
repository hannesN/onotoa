/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Comment</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentComparator extends NodeComparator {

	public boolean equals(Comment o1, Comment o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (super.stringCompare(o1.getContent(), o2.getContent()) == false)
			return false;

		if (super.intCompare(o1.getHeight(), o2.getHeight()) == false)
			return false;

		if (super.intCompare(o1.getWidth(), o2.getWidth()) == false)
			return false;

		return true;

	}

} // CommentTest
