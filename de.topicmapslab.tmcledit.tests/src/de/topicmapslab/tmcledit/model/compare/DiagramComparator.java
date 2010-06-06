/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;

public class DiagramComparator extends OnoObjectComparator {

	public boolean equals(Diagram o1, Diagram o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (stringCompare(o1.getName(), o2.getName()) == false)
			return false;

		if (o1.getComments().size() == o2.getComments().size()) {
			if (commentListCompare(o1.getComments(), o2.getComments()) == false)
				return false;
		} else
			return false;

		if (o1.getEdges().size() == o2.getEdges().size()) {
			if (edgesListCompare(o1.getEdges(), o2.getEdges()) == false)
				return false;
		} else
			return false;

		if (o1.getNodes().size() == o2.getNodes().size()) {
			if (nodesListCompare(o1.getNodes(), o2.getNodes()) == false)
				return false;
		} else
			return false;

		return true;

	}

	/**
	 * Compares two lists with node entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean nodesListCompare(EList<Node> list1, EList<Node> list2) {

		NodeComparator comp = new NodeComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	/**
	 * Compares two lists with edge entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean edgesListCompare(EList<Edge> list1, EList<Edge> list2) {

		EdgeComparator comp = new EdgeComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	/**
	 * Compares two lists with comment entries
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean commentListCompare(EList<Comment> list1,
			EList<Comment> list2) {

		CommentComparator comp = new CommentComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}
} // DiagramTest
