package de.topicmapslab.tmcledit.model.command.tests;

import java.util.List;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.compare.AnnotationComparator;
import de.topicmapslab.tmcledit.model.compare.BendpointComparator;
import de.topicmapslab.tmcledit.model.compare.CommentComparator;
import de.topicmapslab.tmcledit.model.compare.DiagramComparator;
import de.topicmapslab.tmcledit.model.compare.EdgeComparator;
import de.topicmapslab.tmcledit.model.compare.RoleCombinationConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.RoleConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.ScopeConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.TopicReifiesConstraintComparator;

public class Tools {

	public static boolean roleCombinationConstraintListCompare(
			List<RoleCombinationConstraint> list1,
			List<RoleCombinationConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		RoleCombinationConstraintComparator comp = new RoleCombinationConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean roleCombinationConstraintCompare(
			RoleCombinationConstraint o1, RoleCombinationConstraint o2) {

		RoleCombinationConstraintComparator comp = new RoleCombinationConstraintComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean roleConstraintListCompare(List<RoleConstraint> list1,
			List<RoleConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		RoleConstraintComparator comp = new RoleConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean roleConstraintCompare(RoleConstraint o1,
			RoleConstraint o2) {

		RoleConstraintComparator comp = new RoleConstraintComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean scopeConstraintListCompare(
			List<ScopeConstraint> list1, List<ScopeConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		ScopeConstraintComparator comp = new ScopeConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean scopeConstraintCompare(ScopeConstraint o1,
			ScopeConstraint o2) {

		ScopeConstraintComparator comp = new ScopeConstraintComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean topicReifiesConstraintsListCompare(
			List<TopicReifiesConstraint> list1,
			List<TopicReifiesConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		TopicReifiesConstraintComparator comp = new TopicReifiesConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean topicReifiesConstraintCompare(
			TopicReifiesConstraint o1, TopicReifiesConstraint o2) {

		TopicReifiesConstraintComparator comp = new TopicReifiesConstraintComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean annotationsListCompare(List<Annotation> list1,
			List<Annotation> list2) {

		if (list1.size() != list2.size())
			return false;

		AnnotationComparator comp = new AnnotationComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean annotationsCompare(Annotation o1, Annotation o2) {

		AnnotationComparator comp = new AnnotationComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean diagramListCompare(List<Diagram> list1,
			List<Diagram> list2) {

		if (list1.size() != list2.size())
			return false;

		DiagramComparator comp = new DiagramComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean diagramCompare(Diagram o1, Diagram o2) {

		DiagramComparator comp = new DiagramComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean commentListCompare(List<Comment> list1,
			List<Comment> list2) {

		if (list1.size() != list2.size())
			return false;

		CommentComparator comp = new CommentComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean commentCompare(Comment o1, Comment o2) {

		CommentComparator comp = new CommentComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean bendpointListCompare(List<Bendpoint> list1,
			List<Bendpoint> list2) {

		if (list1.size() != list2.size())
			return false;

		BendpointComparator comp = new BendpointComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean bendpointCompare(Bendpoint o1, Bendpoint o2) {

		BendpointComparator comp = new BendpointComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}
	
	public static boolean edgeListCompare(List<Edge> list1,
			List<Edge> list2) {

		if (list1.size() != list2.size())
			return false;

		EdgeComparator comp = new EdgeComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean edgeCompare(Edge o1, Edge o2) {

		BendpointComparator comp = new BendpointComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

}