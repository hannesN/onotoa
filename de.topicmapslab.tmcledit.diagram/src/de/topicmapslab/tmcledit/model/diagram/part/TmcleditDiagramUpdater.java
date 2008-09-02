package de.topicmapslab.tmcledit.model.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.AssociationsType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.ScopeType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint7EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicMapSchemaEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeAkoEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIsaEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.providers.TmcleditElementTypes;

/**
 * @generated
 */
public class TmcleditDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (TmcleditVisualIDRegistry.getVisualID(view)) {
		case NameTypeEditPart.VISUAL_ID:
			return getNameType_1001SemanticChildren(view);
		case ScopeTypeEditPart.VISUAL_ID:
			return getScopeType_1002SemanticChildren(view);
		case RoleTypeEditPart.VISUAL_ID:
			return getRoleType_1003SemanticChildren(view);
		case AssociationsTypeEditPart.VISUAL_ID:
			return getAssociationsType_1004SemanticChildren(view);
		case OccurenceTypeEditPart.VISUAL_ID:
			return getOccurenceType_1005SemanticChildren(view);
		case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return getNameTypeTopicTypeNameCompartment_5001SemanticChildren(view);
		case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
			return getScopeTypeTopicTypeNameCompartment_5004SemanticChildren(view);
		case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return getRoleTypeTopicTypeNameCompartment_5007SemanticChildren(view);
		case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return getAssociationsTypeTopicTypeNameCompartment_5010SemanticChildren(view);
		case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return getOccurenceTypeTopicTypeNameCompartment_5013SemanticChildren(view);
		case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
			return getTopicTypeTopicTypeNameCompartment_5016SemanticChildren(view);
		case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
			return getTopicTypeTopicTypOccurenceCompartment_5017SemanticChildren(view);
		case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
			return getTopicTypeIdentifierCompartment_5018SemanticChildren(view);
		case TopicMapSchemaEditPart.VISUAL_ID:
			return getTopicMapSchema_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameType_1001SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		NameType modelElement = (NameType) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraintEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraintEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraintEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getScopeType_1002SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ScopeType modelElement = (ScopeType) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraint2EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraint2EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint3EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleType_1003SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		RoleType modelElement = (RoleType) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraint3EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraint3EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint4EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationsType_1004SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		AssociationsType modelElement = (AssociationsType) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraint4EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraint4EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint5EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceType_1005SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		OccurenceType modelElement = (OccurenceType) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraint5EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraint5EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint6EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeTopicTypeNameCompartment_5001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		NameType modelElement = (NameType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeConstraintEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getScopeTypeTopicTypeNameCompartment_5004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		ScopeType modelElement = (ScopeType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint2EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleTypeTopicTypeNameCompartment_5007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		RoleType modelElement = (RoleType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeConstraint2EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationsTypeTopicTypeNameCompartment_5010SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		AssociationsType modelElement = (AssociationsType) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeConstraint3EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeTopicTypeNameCompartment_5013SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		OccurenceType modelElement = (OccurenceType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeConstraint4EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTopicTypeTopicTypeNameCompartment_5016SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		TopicType modelElement = (TopicType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getNameContraints().iterator(); it
				.hasNext();) {
			NameTypeConstraint childElement = (NameTypeConstraint) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeConstraint5EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTopicTypeTopicTypOccurenceCompartment_5017SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		TopicType modelElement = (TopicType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getOccurenceConstraints().iterator(); it
				.hasNext();) {
			OccurenceTypeConstraint childElement = (OccurenceTypeConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OccurenceTypeConstraint6EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTopicTypeIdentifierCompartment_5018SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		TopicType modelElement = (TopicType) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getSubjectIdentifierConstraints()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectIdentifierConstraint childElement = (de.topicmapslab.tmcledit.model.subjectIdentifierConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectIdentifierConstraint6EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getSubjectLocatorConstraint()
				.iterator(); it.hasNext();) {
			de.topicmapslab.tmcledit.model.subjectLocatorConstraint childElement = (de.topicmapslab.tmcledit.model.subjectLocatorConstraint) it
					.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubjectLocatorConstraint7EditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTopicMapSchema_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		TopicMapSchema modelElement = (TopicMapSchema) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getTopicTypes().iterator(); it
				.hasNext();) {
			TopicType childElement = (TopicType) it.next();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NameTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScopeTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RoleTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == AssociationsTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == OccurenceTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TopicTypeEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		{
			AssociationTypeConstraint childElement = modelElement
					.getAssociationTypeConstraints();
			int visualID = TmcleditVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == AssociationTypeConstraintEditPart.VISUAL_ID) {
				result.add(new TmcleditNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (TmcleditVisualIDRegistry.getVisualID(view)) {
		case TopicMapSchemaEditPart.VISUAL_ID:
			return getTopicMapSchema_79ContainedLinks(view);
		case NameTypeEditPart.VISUAL_ID:
			return getNameType_1001ContainedLinks(view);
		case ScopeTypeEditPart.VISUAL_ID:
			return getScopeType_1002ContainedLinks(view);
		case RoleTypeEditPart.VISUAL_ID:
			return getRoleType_1003ContainedLinks(view);
		case AssociationsTypeEditPart.VISUAL_ID:
			return getAssociationsType_1004ContainedLinks(view);
		case OccurenceTypeEditPart.VISUAL_ID:
			return getOccurenceType_1005ContainedLinks(view);
		case AssociationTypeConstraintEditPart.VISUAL_ID:
			return getAssociationTypeConstraint_1006ContainedLinks(view);
		case TopicTypeEditPart.VISUAL_ID:
			return getTopicType_1007ContainedLinks(view);
		case NameTypeConstraintEditPart.VISUAL_ID:
			return getNameTypeConstraint_2001ContainedLinks(view);
		case OccurenceTypeConstraintEditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2002ContainedLinks(view);
		case SubjectIdentifierConstraintEditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2003ContainedLinks(view);
		case SubjectLocatorConstraintEditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2004ContainedLinks(view);
		case SubjectLocatorConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2005ContainedLinks(view);
		case OccurenceTypeConstraint2EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2006ContainedLinks(view);
		case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2007ContainedLinks(view);
		case SubjectLocatorConstraint3EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2008ContainedLinks(view);
		case NameTypeConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2009ContainedLinks(view);
		case OccurenceTypeConstraint3EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2010ContainedLinks(view);
		case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2011ContainedLinks(view);
		case SubjectLocatorConstraint4EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2012ContainedLinks(view);
		case NameTypeConstraint3EditPart.VISUAL_ID:
			return getNameTypeConstraint_2013ContainedLinks(view);
		case OccurenceTypeConstraint4EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2014ContainedLinks(view);
		case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2015ContainedLinks(view);
		case SubjectLocatorConstraint5EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2016ContainedLinks(view);
		case NameTypeConstraint4EditPart.VISUAL_ID:
			return getNameTypeConstraint_2017ContainedLinks(view);
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2018ContainedLinks(view);
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2019ContainedLinks(view);
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2020ContainedLinks(view);
		case NameTypeConstraint5EditPart.VISUAL_ID:
			return getNameTypeConstraint_2021ContainedLinks(view);
		case OccurenceTypeConstraint6EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2022ContainedLinks(view);
		case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2023ContainedLinks(view);
		case SubjectLocatorConstraint7EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2024ContainedLinks(view);
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			return getRoleTypeConstraints_3001ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (TmcleditVisualIDRegistry.getVisualID(view)) {
		case NameTypeEditPart.VISUAL_ID:
			return getNameType_1001IncomingLinks(view);
		case ScopeTypeEditPart.VISUAL_ID:
			return getScopeType_1002IncomingLinks(view);
		case RoleTypeEditPart.VISUAL_ID:
			return getRoleType_1003IncomingLinks(view);
		case AssociationsTypeEditPart.VISUAL_ID:
			return getAssociationsType_1004IncomingLinks(view);
		case OccurenceTypeEditPart.VISUAL_ID:
			return getOccurenceType_1005IncomingLinks(view);
		case AssociationTypeConstraintEditPart.VISUAL_ID:
			return getAssociationTypeConstraint_1006IncomingLinks(view);
		case TopicTypeEditPart.VISUAL_ID:
			return getTopicType_1007IncomingLinks(view);
		case NameTypeConstraintEditPart.VISUAL_ID:
			return getNameTypeConstraint_2001IncomingLinks(view);
		case OccurenceTypeConstraintEditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2002IncomingLinks(view);
		case SubjectIdentifierConstraintEditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2003IncomingLinks(view);
		case SubjectLocatorConstraintEditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2004IncomingLinks(view);
		case SubjectLocatorConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2005IncomingLinks(view);
		case OccurenceTypeConstraint2EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2006IncomingLinks(view);
		case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2007IncomingLinks(view);
		case SubjectLocatorConstraint3EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2008IncomingLinks(view);
		case NameTypeConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2009IncomingLinks(view);
		case OccurenceTypeConstraint3EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2010IncomingLinks(view);
		case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2011IncomingLinks(view);
		case SubjectLocatorConstraint4EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2012IncomingLinks(view);
		case NameTypeConstraint3EditPart.VISUAL_ID:
			return getNameTypeConstraint_2013IncomingLinks(view);
		case OccurenceTypeConstraint4EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2014IncomingLinks(view);
		case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2015IncomingLinks(view);
		case SubjectLocatorConstraint5EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2016IncomingLinks(view);
		case NameTypeConstraint4EditPart.VISUAL_ID:
			return getNameTypeConstraint_2017IncomingLinks(view);
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2018IncomingLinks(view);
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2019IncomingLinks(view);
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2020IncomingLinks(view);
		case NameTypeConstraint5EditPart.VISUAL_ID:
			return getNameTypeConstraint_2021IncomingLinks(view);
		case OccurenceTypeConstraint6EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2022IncomingLinks(view);
		case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2023IncomingLinks(view);
		case SubjectLocatorConstraint7EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2024IncomingLinks(view);
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			return getRoleTypeConstraints_3001IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (TmcleditVisualIDRegistry.getVisualID(view)) {
		case NameTypeEditPart.VISUAL_ID:
			return getNameType_1001OutgoingLinks(view);
		case ScopeTypeEditPart.VISUAL_ID:
			return getScopeType_1002OutgoingLinks(view);
		case RoleTypeEditPart.VISUAL_ID:
			return getRoleType_1003OutgoingLinks(view);
		case AssociationsTypeEditPart.VISUAL_ID:
			return getAssociationsType_1004OutgoingLinks(view);
		case OccurenceTypeEditPart.VISUAL_ID:
			return getOccurenceType_1005OutgoingLinks(view);
		case AssociationTypeConstraintEditPart.VISUAL_ID:
			return getAssociationTypeConstraint_1006OutgoingLinks(view);
		case TopicTypeEditPart.VISUAL_ID:
			return getTopicType_1007OutgoingLinks(view);
		case NameTypeConstraintEditPart.VISUAL_ID:
			return getNameTypeConstraint_2001OutgoingLinks(view);
		case OccurenceTypeConstraintEditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2002OutgoingLinks(view);
		case SubjectIdentifierConstraintEditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2003OutgoingLinks(view);
		case SubjectLocatorConstraintEditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2004OutgoingLinks(view);
		case SubjectLocatorConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2005OutgoingLinks(view);
		case OccurenceTypeConstraint2EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2006OutgoingLinks(view);
		case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2007OutgoingLinks(view);
		case SubjectLocatorConstraint3EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2008OutgoingLinks(view);
		case NameTypeConstraint2EditPart.VISUAL_ID:
			return getNameTypeConstraint_2009OutgoingLinks(view);
		case OccurenceTypeConstraint3EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2010OutgoingLinks(view);
		case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2011OutgoingLinks(view);
		case SubjectLocatorConstraint4EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2012OutgoingLinks(view);
		case NameTypeConstraint3EditPart.VISUAL_ID:
			return getNameTypeConstraint_2013OutgoingLinks(view);
		case OccurenceTypeConstraint4EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2014OutgoingLinks(view);
		case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2015OutgoingLinks(view);
		case SubjectLocatorConstraint5EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2016OutgoingLinks(view);
		case NameTypeConstraint4EditPart.VISUAL_ID:
			return getNameTypeConstraint_2017OutgoingLinks(view);
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2018OutgoingLinks(view);
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2019OutgoingLinks(view);
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2020OutgoingLinks(view);
		case NameTypeConstraint5EditPart.VISUAL_ID:
			return getNameTypeConstraint_2021OutgoingLinks(view);
		case OccurenceTypeConstraint6EditPart.VISUAL_ID:
			return getOccurenceTypeConstraint_2022OutgoingLinks(view);
		case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
			return getSubjectIdentifierConstraint_2023OutgoingLinks(view);
		case SubjectLocatorConstraint7EditPart.VISUAL_ID:
			return getSubjectLocatorConstraint_2024OutgoingLinks(view);
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			return getRoleTypeConstraints_3001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTopicMapSchema_79ContainedLinks(View view) {
		TopicMapSchema modelElement = (TopicMapSchema) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_RoleTypeConstraints_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNameType_1001ContainedLinks(View view) {
		NameType modelElement = (NameType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getScopeType_1002ContainedLinks(View view) {
		ScopeType modelElement = (ScopeType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleType_1003ContainedLinks(View view) {
		RoleType modelElement = (RoleType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationsType_1004ContainedLinks(View view) {
		AssociationsType modelElement = (AssociationsType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceType_1005ContainedLinks(View view) {
		OccurenceType modelElement = (OccurenceType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationTypeConstraint_1006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTopicType_1007ContainedLinks(View view) {
		TopicType modelElement = (TopicType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2003ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2007ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2009ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2010ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2011ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2012ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2013ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2014ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2015ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2016ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2017ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2018ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2019ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2020ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2021ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2022ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2023ContainedLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2024ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoleTypeConstraints_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameType_1001IncomingLinks(View view) {
		NameType modelElement = (NameType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getScopeType_1002IncomingLinks(View view) {
		ScopeType modelElement = (ScopeType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleType_1003IncomingLinks(View view) {
		RoleType modelElement = (RoleType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationsType_1004IncomingLinks(View view) {
		AssociationsType modelElement = (AssociationsType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceType_1005IncomingLinks(View view) {
		OccurenceType modelElement = (OccurenceType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationTypeConstraint_1006IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTopicType_1007IncomingLinks(View view) {
		TopicType modelElement = (TopicType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2003IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2004IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2005IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2006IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2007IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2008IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2009IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2010IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2011IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2012IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2013IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2014IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2015IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2016IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2017IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2018IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2019IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2020IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2021IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2022IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2023IncomingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2024IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoleTypeConstraints_3001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameType_1001OutgoingLinks(View view) {
		NameType modelElement = (NameType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getScopeType_1002OutgoingLinks(View view) {
		ScopeType modelElement = (ScopeType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleType_1003OutgoingLinks(View view) {
		RoleType modelElement = (RoleType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationsType_1004OutgoingLinks(View view) {
		AssociationsType modelElement = (AssociationsType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceType_1005OutgoingLinks(View view) {
		OccurenceType modelElement = (OccurenceType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAssociationTypeConstraint_1006OutgoingLinks(View view) {
		AssociationTypeConstraint modelElement = (AssociationTypeConstraint) view
				.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_RoleTypeConstraints_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTopicType_1007OutgoingLinks(View view) {
		TopicType modelElement = (TopicType) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(modelElement));
		result
				.addAll(getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2003OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2004OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2005OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2006OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2007OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2008OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2009OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2010OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2011OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2012OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2013OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2014OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2015OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2016OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2017OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2018OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2019OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2020OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNameTypeConstraint_2021OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOccurenceTypeConstraint_2022OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectIdentifierConstraint_2023OutgoingLinks(
			View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getSubjectLocatorConstraint_2024OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRoleTypeConstraints_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_RoleTypeConstraints_3001(
			TopicMapSchema container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getRoleTypeConstraints().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof RoleTypeConstraints) {
				continue;
			}
			RoleTypeConstraints link = (RoleTypeConstraints) linkObject;
			if (RoleTypeConstraintsEditPart.VISUAL_ID != TmcleditVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			TopicType dst = link.getTopicType();
			AssociationTypeConstraint src = link.getAssociationTypeConstraint();
			result.add(new TmcleditLinkDescriptor(src, dst, link,
					TmcleditElementTypes.RoleTypeConstraints_3001,
					RoleTypeConstraintsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_RoleTypeConstraints_3001(
			TopicType target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != ModelPackage.eINSTANCE
					.getRoleTypeConstraints_TopicType()
					|| false == setting.getEObject() instanceof RoleTypeConstraints) {
				continue;
			}
			RoleTypeConstraints link = (RoleTypeConstraints) setting
					.getEObject();
			if (RoleTypeConstraintsEditPart.VISUAL_ID != TmcleditVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			AssociationTypeConstraint src = link.getAssociationTypeConstraint();
			result.add(new TmcleditLinkDescriptor(src, target, link,
					TmcleditElementTypes.RoleTypeConstraints_3001,
					RoleTypeConstraintsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_TopicType_Ako_3002(
			TopicType target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == ModelPackage.eINSTANCE
					.getTopicType_Ako()) {
				result.add(new TmcleditLinkDescriptor(setting.getEObject(),
						target, TmcleditElementTypes.TopicTypeAko_3002,
						TopicTypeAkoEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingFeatureModelFacetLinks_TopicType_Isa_3003(
			TopicType target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() == ModelPackage.eINSTANCE
					.getTopicType_Isa()) {
				result.add(new TmcleditLinkDescriptor(setting.getEObject(),
						target, TmcleditElementTypes.TopicTypeIsa_3003,
						TopicTypeIsaEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_RoleTypeConstraints_3001(
			AssociationTypeConstraint source) {
		TopicMapSchema container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof TopicMapSchema) {
				container = (TopicMapSchema) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getRoleTypeConstraints().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof RoleTypeConstraints) {
				continue;
			}
			RoleTypeConstraints link = (RoleTypeConstraints) linkObject;
			if (RoleTypeConstraintsEditPart.VISUAL_ID != TmcleditVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			TopicType dst = link.getTopicType();
			AssociationTypeConstraint src = link.getAssociationTypeConstraint();
			if (src != source) {
				continue;
			}
			result.add(new TmcleditLinkDescriptor(src, dst, link,
					TmcleditElementTypes.RoleTypeConstraints_3001,
					RoleTypeConstraintsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_TopicType_Ako_3002(
			TopicType source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getAko().iterator(); destinations
				.hasNext();) {
			TopicType destination = (TopicType) destinations.next();
			result.add(new TmcleditLinkDescriptor(source, destination,
					TmcleditElementTypes.TopicTypeAko_3002,
					TopicTypeAkoEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingFeatureModelFacetLinks_TopicType_Isa_3003(
			TopicType source) {
		Collection result = new LinkedList();
		for (Iterator destinations = source.getIsa().iterator(); destinations
				.hasNext();) {
			TopicType destination = (TopicType) destinations.next();
			result.add(new TmcleditLinkDescriptor(source, destination,
					TmcleditElementTypes.TopicTypeIsa_3003,
					TopicTypeIsaEditPart.VISUAL_ID));
		}
		return result;
	}

}
