package de.topicmapslab.tmcledit.model.command.tests;

import java.util.List;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.AnnotationComparator;
import de.topicmapslab.tmcledit.model.compare.BendpointComparator;
import de.topicmapslab.tmcledit.model.compare.CommentComparator;
import de.topicmapslab.tmcledit.model.compare.DiagramComparator;
import de.topicmapslab.tmcledit.model.compare.EdgeComparator;
import de.topicmapslab.tmcledit.model.compare.MappingElementComparator;
import de.topicmapslab.tmcledit.model.compare.NameTypeConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.OccurrenceTypeConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.RoleCombinationConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.RoleConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.ScopeConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.TopicMapSchemaComparator;
import de.topicmapslab.tmcledit.model.compare.TopicReifiesConstraintComparator;
import de.topicmapslab.tmcledit.model.compare.TopicTypeComparator;

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

	public static boolean edgeListCompare(List<Edge> list1, List<Edge> list2) {

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

	public static boolean nameTypeConstraintListCompare(
			List<NameTypeConstraint> list1, List<NameTypeConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		NameTypeConstraintComparator comp = new NameTypeConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean nameTypeConstraintCompare(NameTypeConstraint o1,
			NameTypeConstraint o2) {

		BendpointComparator comp = new BendpointComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean topicMapSchemaListCompare(List<TopicMapSchema> list1,
			List<TopicMapSchema> list2) {

		if (list1.size() != list2.size())
			return false;

		TopicMapSchemaComparator comp = new TopicMapSchemaComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean topicMapSchemaCompare(TopicMapSchema o1,
			TopicMapSchema o2) {

		TopicMapSchemaComparator comp = new TopicMapSchemaComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean topicTypeListCompare(List<TopicType> list1,
			List<TopicType> list2) {

		if (list1.size() != list2.size())
			return false;

		TopicTypeComparator comp = new TopicTypeComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean topicTypeCompare(TopicType o1, TopicType o2) {

		TopicTypeComparator comp = new TopicTypeComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean occurrenceTypeConstraintListCompare(
			List<OccurrenceTypeConstraint> list1,
			List<OccurrenceTypeConstraint> list2) {

		if (list1.size() != list2.size())
			return false;

		OccurrenceTypeConstraintComparator comp = new OccurrenceTypeConstraintComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean occurrenceTypeConstraintCompare(
			OccurrenceTypeConstraint o1, OccurrenceTypeConstraint o2) {

		OccurrenceTypeConstraintComparator comp = new OccurrenceTypeConstraintComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

	public static boolean mappingElementListCompare(List<MappingElement> list1,
			List<MappingElement> list2) {

		if (list1.size() != list2.size())
			return false;

		MappingElementComparator comp = new MappingElementComparator();

		for (int i = 0; i < list1.size(); i++) {

			if (comp.equals(list1.get(i), list2.get(i)) == false)
				return false;

		}

		return true;

	}

	public static boolean mappingElementCompare(MappingElement o1,
			MappingElement o2) {

		MappingElementComparator comp = new MappingElementComparator();

		if (comp.equals(o1, o2) == false)
			return false;

		return true;

	}

}