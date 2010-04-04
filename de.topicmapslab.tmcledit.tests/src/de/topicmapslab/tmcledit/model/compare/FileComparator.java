/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;

public class FileComparator extends OnoObjectComparator {

	public boolean equals(File o1, File o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getFilename(), o2.getFilename()) == false)
			return false;
		
		if (stringCompare(o1.getNotes(), o2.getNotes()) == false)
			return false;

		if (o1.isDirty() != o2.isDirty())
			return false;

		TopicMapSchemaComparatror comp = new TopicMapSchemaComparatror();

		if (comp.equals(o1.getTopicMapSchema(), o2.getTopicMapSchema()) == false)
			return false;

		if (o1.getDiagrams().size() == o2.getDiagrams().size()) {
			if ((diagramListCompare(o1.getDiagrams(), o2.getDiagrams())) == false)
				return false;
		} else
			return false;

		return true;

	}

	/**
	 * Compares two lists with Diagrams
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	private boolean diagramListCompare(EList<Diagram> list1,
			EList<Diagram> list2) {

		DiagramComparator comp = new DiagramComparator();

		for (int i = 0; i < list1.size(); i++) {
			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;
		}

		return true;
	}
} // FileTest
