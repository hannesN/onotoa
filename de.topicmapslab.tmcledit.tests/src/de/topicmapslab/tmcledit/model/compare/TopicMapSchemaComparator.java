/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TopicMapSchemaComparator extends TMCLConstructComparator {

	public boolean equals(TopicMapSchema o1, TopicMapSchema o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getBaseLocator(), o2.getBaseLocator()) == false)
			return false;

		if (stringCompare(o1.getName(), o2.getName()) == false)
			return false;

		if (stringListCompare(o1.getIncludes(), o2.getIncludes()) == false)
			return false;

		if (o1.getAssociationTypeConstraints().size() == o2
				.getAssociationTypeConstraints().size()) {
			if (aTypeConstraintsListCompare(o1.getAssociationTypeConstraints(),
					o2.getAssociationTypeConstraints()) == false)
				return false;
		} else
			return false;

		if (o1.getTopicTypes().size() == o2.getTopicTypes().size()) {
			if (topicTypeListCompare(o1.getTopicTypes(), o2.getTopicTypes()) == false)
				return false;
		} else
			return false;

		if (o1.getMappings().size() == o2.getMappings().size()) {
			if (mappingElementListCompare(o1.getMappings(), o2.getMappings()) == false)
				return false;
		} else
			return false;

		return true;

	}

	/**
	 * Compares two lists with MappingElement entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean mappingElementListCompare(EList<MappingElement> list1,
			EList<MappingElement> list2) {

		MappingElementComparator comp = new MappingElementComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;
		}

		return true;

	}

	/**
	 * Compares two lists with TopicType entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */
	protected boolean topicTypeListCompare(EList<TopicType> list1,
			EList<TopicType> list2) {

		TopicTypeComparator comp = new TopicTypeComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;
		}

		return true;
	}

	/**
	 * Compares two lists with AssociationTypeConstraint entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean aTypeConstraintsListCompare(
			EList<AssociationTypeConstraint> list1,
			EList<AssociationTypeConstraint> list2) {

		AssociationTypeConstraintComparator comp = new AssociationTypeConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;
		}

		return true;
	}
} // TopicMapSchemaTest
