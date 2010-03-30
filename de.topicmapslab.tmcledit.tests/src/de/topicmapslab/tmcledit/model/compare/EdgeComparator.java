/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.compare;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.LabelPos;

public class EdgeComparator extends OnoObjectComparator {

	public boolean equals(Edge o1, Edge o2) {

		if ((o1 == null) ^ (o2 == null))
			return false;

		if ((o1 == null) && (o2 == null))
			return true;

		if (super.equals(o1, o2) == false)
			return false;

		if (o1.getType() != o2.getType())
			return false;

		NodeComparator comp = new NodeComparator();

		if (comp.equals(o1.getSource(), o2.getSource()) == false)
			return false;

		if (comp.equals(o1.getTarget(), o2.getTarget()) == false)
			return false;

		if (o1.getBendpoints().size() == o2.getBendpoints().size()) {
			if (bendpointListCompare(o1.getBendpoints(), o2.getBendpoints()) == false)
				return false;
		} else
			return false;

		if (o1.getLabelPositions().size() == o2.getLabelPositions().size()) {
			if (labelPositionListCompare(o1.getLabelPositions(), o2
					.getLabelPositions()) == false)
				return false;
		} else
			return false;
		
		RolePlayerConstraintComparator comp1 = new RolePlayerConstraintComparator();
		
		if (comp1.equals(o1.getRoleConstraint(),o2.getRoleConstraint()) == false)
			return false;

		return true;
	}

	/**
	 * Compares two lists with label positions
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean labelPositionListCompare(EList<LabelPos> list1,
			EList<LabelPos> list2) {

		LabelPosComparator comp = new LabelPosComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;
	}

	/**
	 * Compares two lists with bendpoints
	 * 
	 * @param list1
	 * @param list2
	 * @return boolean result of the compare
	 */

	protected boolean bendpointListCompare(EList<Bendpoint> list1,
			EList<Bendpoint> list2) {

		BendpointComparator comp = new BendpointComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}
} // EdgeTest
