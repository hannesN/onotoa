package de.topicmapslab.tmcledit.model.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

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
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeAkoEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIsaEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditVisualIDRegistry;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationTypeConstraintViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationsTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationsTypeIdentifierCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationsTypeTopicTypOccurenceCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationsTypeTopicTypeNameCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.AssociationsTypeViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeConstraint2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeConstraint3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeConstraint4ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeConstraint5ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeConstraintViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.NameTypeViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraint2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraint3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraint4ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraint5ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraint6ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeConstraintViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeIdentifierCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeTopicTypOccurenceCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeTopicTypeNameCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.OccurenceTypeViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeConstraintsCardMinCardMaxViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeConstraintsRoleViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeConstraintsViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeIdentifierCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeTopicTypOccurenceCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeTopicTypeNameCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.RoleTypeViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.ScopeTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.ScopeTypeViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraint2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraint3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraint4ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraint5ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraint6ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectIdentifierConstraintViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint4ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint5ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint6ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraint7ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.SubjectLocatorConstraintViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicMapSchemaViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeAkoViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIdViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIdentifierCompartment2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIdentifierCompartment3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIdentifierCompartment4ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIdentifierCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeIsaViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeTopicTypOccurenceCompartment2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeTopicTypOccurenceCompartment3ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeTopicTypOccurenceCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeTopicTypeNameCompartment2ViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeTopicTypeNameCompartmentViewFactory;
import de.topicmapslab.tmcledit.model.diagram.view.factories.TopicTypeViewFactory;

/**
 * @generated
 */
public class TmceleditViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (TopicMapSchemaEditPart.MODEL_ID.equals(diagramKind)
				&& TmceleditVisualIDRegistry
						.getDiagramVisualID(semanticElement) != -1) {
			return TopicMapSchemaViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = TmceleditVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = TmceleditVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!TmceleditElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != TmceleditVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!TopicMapSchemaEditPart.MODEL_ID
						.equals(TmceleditVisualIDRegistry
								.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case NameTypeEditPart.VISUAL_ID:
				case ScopeTypeEditPart.VISUAL_ID:
				case RoleTypeEditPart.VISUAL_ID:
				case AssociationsTypeEditPart.VISUAL_ID:
				case OccurenceTypeEditPart.VISUAL_ID:
				case AssociationTypeConstraintEditPart.VISUAL_ID:
				case TopicTypeEditPart.VISUAL_ID:
				case NameTypeConstraintEditPart.VISUAL_ID:
				case OccurenceTypeConstraintEditPart.VISUAL_ID:
				case SubjectIdentifierConstraintEditPart.VISUAL_ID:
				case SubjectLocatorConstraintEditPart.VISUAL_ID:
				case SubjectLocatorConstraint2EditPart.VISUAL_ID:
				case OccurenceTypeConstraint2EditPart.VISUAL_ID:
				case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
				case SubjectLocatorConstraint3EditPart.VISUAL_ID:
				case NameTypeConstraint2EditPart.VISUAL_ID:
				case OccurenceTypeConstraint3EditPart.VISUAL_ID:
				case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
				case SubjectLocatorConstraint4EditPart.VISUAL_ID:
				case NameTypeConstraint3EditPart.VISUAL_ID:
				case OccurenceTypeConstraint4EditPart.VISUAL_ID:
				case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
				case SubjectLocatorConstraint5EditPart.VISUAL_ID:
				case NameTypeConstraint4EditPart.VISUAL_ID:
				case OccurenceTypeConstraint5EditPart.VISUAL_ID:
				case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
				case SubjectLocatorConstraint6EditPart.VISUAL_ID:
				case NameTypeConstraint5EditPart.VISUAL_ID:
				case OccurenceTypeConstraint6EditPart.VISUAL_ID:
				case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
				case SubjectLocatorConstraint7EditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != TmceleditVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case NameTypeIdEditPart.VISUAL_ID:
				case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				case TopicTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				case TopicTypeIdentifierCompartmentEditPart.VISUAL_ID:
					if (NameTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ScopeTypeIdEditPart.VISUAL_ID:
				case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
				case TopicTypeTopicTypOccurenceCompartment2EditPart.VISUAL_ID:
				case TopicTypeIdentifierCompartment3EditPart.VISUAL_ID:
					if (ScopeTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case RoleTypeIdEditPart.VISUAL_ID:
				case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				case RoleTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				case RoleTypeIdentifierCompartmentEditPart.VISUAL_ID:
					if (RoleTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case AssociationsTypeIdEditPart.VISUAL_ID:
				case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				case AssociationsTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				case AssociationsTypeIdentifierCompartmentEditPart.VISUAL_ID:
					if (AssociationsTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case OccurenceTypeIdEditPart.VISUAL_ID:
				case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				case OccurenceTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				case OccurenceTypeIdentifierCompartmentEditPart.VISUAL_ID:
					if (OccurenceTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case TopicTypeIdEditPart.VISUAL_ID:
				case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
				case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
				case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
					if (TopicTypeEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case RoleTypeConstraintsCardMinCardMaxEditPart.VISUAL_ID:
				case RoleTypeConstraintsRoleEditPart.VISUAL_ID:
					if (RoleTypeConstraintsEditPart.VISUAL_ID != TmceleditVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !TmceleditVisualIDRegistry.canCreateNode(containerView,
						visualID)) {
			return null;
		}
		switch (visualID) {
		case NameTypeEditPart.VISUAL_ID:
			return NameTypeViewFactory.class;
		case NameTypeIdEditPart.VISUAL_ID:
			return NameTypeIdViewFactory.class;
		case ScopeTypeEditPart.VISUAL_ID:
			return ScopeTypeViewFactory.class;
		case ScopeTypeIdEditPart.VISUAL_ID:
			return ScopeTypeIdViewFactory.class;
		case RoleTypeEditPart.VISUAL_ID:
			return RoleTypeViewFactory.class;
		case RoleTypeIdEditPart.VISUAL_ID:
			return RoleTypeIdViewFactory.class;
		case AssociationsTypeEditPart.VISUAL_ID:
			return AssociationsTypeViewFactory.class;
		case AssociationsTypeIdEditPart.VISUAL_ID:
			return AssociationsTypeIdViewFactory.class;
		case OccurenceTypeEditPart.VISUAL_ID:
			return OccurenceTypeViewFactory.class;
		case OccurenceTypeIdEditPart.VISUAL_ID:
			return OccurenceTypeIdViewFactory.class;
		case AssociationTypeConstraintEditPart.VISUAL_ID:
			return AssociationTypeConstraintViewFactory.class;
		case TopicTypeEditPart.VISUAL_ID:
			return TopicTypeViewFactory.class;
		case TopicTypeIdEditPart.VISUAL_ID:
			return TopicTypeIdViewFactory.class;
		case NameTypeConstraintEditPart.VISUAL_ID:
			return NameTypeConstraintViewFactory.class;
		case OccurenceTypeConstraintEditPart.VISUAL_ID:
			return OccurenceTypeConstraintViewFactory.class;
		case SubjectIdentifierConstraintEditPart.VISUAL_ID:
			return SubjectIdentifierConstraintViewFactory.class;
		case SubjectLocatorConstraintEditPart.VISUAL_ID:
			return SubjectLocatorConstraintViewFactory.class;
		case SubjectLocatorConstraint2EditPart.VISUAL_ID:
			return SubjectLocatorConstraint2ViewFactory.class;
		case OccurenceTypeConstraint2EditPart.VISUAL_ID:
			return OccurenceTypeConstraint2ViewFactory.class;
		case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
			return SubjectIdentifierConstraint2ViewFactory.class;
		case SubjectLocatorConstraint3EditPart.VISUAL_ID:
			return SubjectLocatorConstraint3ViewFactory.class;
		case NameTypeConstraint2EditPart.VISUAL_ID:
			return NameTypeConstraint2ViewFactory.class;
		case OccurenceTypeConstraint3EditPart.VISUAL_ID:
			return OccurenceTypeConstraint3ViewFactory.class;
		case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
			return SubjectIdentifierConstraint3ViewFactory.class;
		case SubjectLocatorConstraint4EditPart.VISUAL_ID:
			return SubjectLocatorConstraint4ViewFactory.class;
		case NameTypeConstraint3EditPart.VISUAL_ID:
			return NameTypeConstraint3ViewFactory.class;
		case OccurenceTypeConstraint4EditPart.VISUAL_ID:
			return OccurenceTypeConstraint4ViewFactory.class;
		case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
			return SubjectIdentifierConstraint4ViewFactory.class;
		case SubjectLocatorConstraint5EditPart.VISUAL_ID:
			return SubjectLocatorConstraint5ViewFactory.class;
		case NameTypeConstraint4EditPart.VISUAL_ID:
			return NameTypeConstraint4ViewFactory.class;
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
			return OccurenceTypeConstraint5ViewFactory.class;
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
			return SubjectIdentifierConstraint5ViewFactory.class;
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			return SubjectLocatorConstraint6ViewFactory.class;
		case NameTypeConstraint5EditPart.VISUAL_ID:
			return NameTypeConstraint5ViewFactory.class;
		case OccurenceTypeConstraint6EditPart.VISUAL_ID:
			return OccurenceTypeConstraint6ViewFactory.class;
		case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
			return SubjectIdentifierConstraint6ViewFactory.class;
		case SubjectLocatorConstraint7EditPart.VISUAL_ID:
			return SubjectLocatorConstraint7ViewFactory.class;
		case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return TopicTypeTopicTypeNameCompartmentViewFactory.class;
		case TopicTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
			return TopicTypeTopicTypOccurenceCompartmentViewFactory.class;
		case TopicTypeIdentifierCompartmentEditPart.VISUAL_ID:
			return TopicTypeIdentifierCompartmentViewFactory.class;
		case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
			return TopicTypeIdentifierCompartment2ViewFactory.class;
		case TopicTypeTopicTypOccurenceCompartment2EditPart.VISUAL_ID:
			return TopicTypeTopicTypOccurenceCompartment2ViewFactory.class;
		case TopicTypeIdentifierCompartment3EditPart.VISUAL_ID:
			return TopicTypeIdentifierCompartment3ViewFactory.class;
		case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return RoleTypeTopicTypeNameCompartmentViewFactory.class;
		case RoleTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
			return RoleTypeTopicTypOccurenceCompartmentViewFactory.class;
		case RoleTypeIdentifierCompartmentEditPart.VISUAL_ID:
			return RoleTypeIdentifierCompartmentViewFactory.class;
		case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return AssociationsTypeTopicTypeNameCompartmentViewFactory.class;
		case AssociationsTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
			return AssociationsTypeTopicTypOccurenceCompartmentViewFactory.class;
		case AssociationsTypeIdentifierCompartmentEditPart.VISUAL_ID:
			return AssociationsTypeIdentifierCompartmentViewFactory.class;
		case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
			return OccurenceTypeTopicTypeNameCompartmentViewFactory.class;
		case OccurenceTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
			return OccurenceTypeTopicTypOccurenceCompartmentViewFactory.class;
		case OccurenceTypeIdentifierCompartmentEditPart.VISUAL_ID:
			return OccurenceTypeIdentifierCompartmentViewFactory.class;
		case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
			return TopicTypeTopicTypeNameCompartment2ViewFactory.class;
		case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
			return TopicTypeTopicTypOccurenceCompartment3ViewFactory.class;
		case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
			return TopicTypeIdentifierCompartment4ViewFactory.class;
		case RoleTypeConstraintsCardMinCardMaxEditPart.VISUAL_ID:
			return RoleTypeConstraintsCardMinCardMaxViewFactory.class;
		case RoleTypeConstraintsRoleEditPart.VISUAL_ID:
			return RoleTypeConstraintsRoleViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!TmceleditElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = TmceleditVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != TmceleditVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			return RoleTypeConstraintsViewFactory.class;
		case TopicTypeAkoEditPart.VISUAL_ID:
			return TopicTypeAkoViewFactory.class;
		case TopicTypeIsaEditPart.VISUAL_ID:
			return TopicTypeIsaViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
}
