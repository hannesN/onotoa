package de.topicmapslab.tmcledit.model.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsCardMinCardMaxEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsRoleEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeIdEditPart;
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
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartmentEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class TmcleditVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "de.topicmapslab.tmcledit.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (TopicMapSchemaEditPart.MODEL_ID.equals(view.getType())) {
				return TopicMapSchemaEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				TmcleditDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ModelPackage.eINSTANCE.getTopicMapSchema().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((TopicMapSchema) domainElement)) {
			return TopicMapSchemaEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry
				.getModelID(containerView);
		if (!TopicMapSchemaEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (TopicMapSchemaEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TopicMapSchemaEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case NameTypeEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraintEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraintEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraintEditPart.VISUAL_ID;
			}
			break;
		case ScopeTypeEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraint2EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraint2EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraint3EditPart.VISUAL_ID;
			}
			break;
		case RoleTypeEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraint3EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraint3EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraint4EditPart.VISUAL_ID;
			}
			break;
		case AssociationsTypeEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraint4EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraint4EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraint5EditPart.VISUAL_ID;
			}
			break;
		case OccurenceTypeEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraint5EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraint5EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraint6EditPart.VISUAL_ID;
			}
			break;
		case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeConstraintEditPart.VISUAL_ID;
			}
			break;
		case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return SubjectLocatorConstraint2EditPart.VISUAL_ID;
			}
			break;
		case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeConstraint2EditPart.VISUAL_ID;
			}
			break;
		case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeConstraint3EditPart.VISUAL_ID;
			}
			break;
		case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeConstraint4EditPart.VISUAL_ID;
			}
			break;
		case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameTypeConstraint().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeConstraint5EditPart.VISUAL_ID;
			}
			break;
		case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getOccurenceTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return OccurenceTypeConstraint6EditPart.VISUAL_ID;
			}
			break;
		case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getsubjectIdentifierConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectIdentifierConstraint6EditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getsubjectLocatorConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return SubjectLocatorConstraint7EditPart.VISUAL_ID;
			}
			break;
		case TopicMapSchemaEditPart.VISUAL_ID:
			if (ModelPackage.eINSTANCE.getNameType().isSuperTypeOf(
					domainElement.eClass())) {
				return NameTypeEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getScopeType().isSuperTypeOf(
					domainElement.eClass())) {
				return ScopeTypeEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getRoleType().isSuperTypeOf(
					domainElement.eClass())) {
				return RoleTypeEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getAssociationsType().isSuperTypeOf(
					domainElement.eClass())) {
				return AssociationsTypeEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getOccurenceType().isSuperTypeOf(
					domainElement.eClass())) {
				return OccurenceTypeEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getAssociationTypeConstraint()
					.isSuperTypeOf(domainElement.eClass())) {
				return AssociationTypeConstraintEditPart.VISUAL_ID;
			}
			if (ModelPackage.eINSTANCE.getTopicType().isSuperTypeOf(
					domainElement.eClass())) {
				return TopicTypeEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry
				.getModelID(containerView);
		if (!TopicMapSchemaEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (TopicMapSchemaEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = TopicMapSchemaEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case NameTypeEditPart.VISUAL_ID:
			if (NameTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeIdentifierCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeConstraintEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectIdentifierConstraintEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraintEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ScopeTypeEditPart.VISUAL_ID:
			if (ScopeTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeIdentifierCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeTopicTypOccurenceCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeIdentifierCompartment3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeConstraint2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectIdentifierConstraint2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraint3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoleTypeEditPart.VISUAL_ID:
			if (RoleTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleTypeIdentifierCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeConstraint3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectIdentifierConstraint3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraint4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case AssociationsTypeEditPart.VISUAL_ID:
			if (AssociationsTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AssociationsTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AssociationsTypeIdentifierCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeConstraint4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectIdentifierConstraint4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraint5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OccurenceTypeEditPart.VISUAL_ID:
			if (OccurenceTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeIdentifierCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeConstraint5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectIdentifierConstraint5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraint6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeEditPart.VISUAL_ID:
			if (TopicTypeIdEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeIdentifierCompartment4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (NameTypeConstraintEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
			if (SubjectLocatorConstraint2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (NameTypeConstraint2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (NameTypeConstraint3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			if (NameTypeConstraint4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
			if (NameTypeConstraint5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
			if (OccurenceTypeConstraint6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
			if (SubjectIdentifierConstraint6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubjectLocatorConstraint7EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TopicMapSchemaEditPart.VISUAL_ID:
			if (NameTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScopeTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AssociationsTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OccurenceTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AssociationTypeConstraintEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TopicTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			if (RoleTypeConstraintsCardMinCardMaxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RoleTypeConstraintsRoleEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ModelPackage.eINSTANCE.getRoleTypeConstraints().isSuperTypeOf(
				domainElement.eClass())) {
			return RoleTypeConstraintsEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(TopicMapSchema element) {
		return true;
	}

}
