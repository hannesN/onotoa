/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Abstract Cardinality Contraint</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class AbstractCardinalityConstraintComparator extends
		TMCLConstructComperator {

	public boolean equals(AbstractCardinalityContraint o1,
			AbstractCardinalityContraint o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		return true;

	}

} // AbstractCardinalityContraintTest
