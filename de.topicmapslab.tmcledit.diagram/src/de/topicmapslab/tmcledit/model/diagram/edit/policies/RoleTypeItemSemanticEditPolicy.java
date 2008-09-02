package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.OccurenceTypeConstraint3CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.RoleTypeConstraintsCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.RoleTypeConstraintsReorientCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.SubjectIdentifierConstraint3CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.SubjectLocatorConstraint4CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.TopicTypeAkoCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.TopicTypeAkoReorientCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.TopicTypeIsaCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.TopicTypeIsaReorientCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeConstraintsEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeIdentifierCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeTopicTypOccurenceCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeTopicTypeNameCompartmentEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeAkoEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIsaEditPart;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditVisualIDRegistry;
import de.topicmapslab.tmcledit.model.diagram.providers.TmceleditElementTypes;

/**
 * @generated
 */
public class RoleTypeItemSemanticEditPolicy extends
		TmceleditBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TmceleditElementTypes.OccurenceTypeConstraint_2010 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_OccurenceConstraints());
			}
			return getGEFWrapper(new OccurenceTypeConstraint3CreateCommand(req));
		}
		if (TmceleditElementTypes.SubjectIdentifierConstraint_2011 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_SubjectIdentifierConstraints());
			}
			return getGEFWrapper(new SubjectIdentifierConstraint3CreateCommand(
					req));
		}
		if (TmceleditElementTypes.SubjectLocatorConstraint_2012 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_SubjectLocatorConstraint());
			}
			return getGEFWrapper(new SubjectLocatorConstraint4CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
		View view = (View) getHost().getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (TmceleditVisualIDRegistry.getVisualID(node)) {
			case OccurenceTypeConstraint3EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case SubjectLocatorConstraint4EditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node));
				break;
			case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (TmceleditVisualIDRegistry.getVisualID(cnode)) {
					case NameTypeConstraint2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case RoleTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (TmceleditVisualIDRegistry.getVisualID(cnode)) {
					}
				}
				break;
			case RoleTypeIdentifierCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (TmceleditVisualIDRegistry.getVisualID(cnode)) {
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (TmceleditElementTypes.RoleTypeConstraints_3001 == req
				.getElementType()) {
			return null;
		}
		if (TmceleditElementTypes.TopicTypeAko_3002 == req.getElementType()) {
			return getGEFWrapper(new TopicTypeAkoCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (TmceleditElementTypes.TopicTypeIsa_3003 == req.getElementType()) {
			return getGEFWrapper(new TopicTypeIsaCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (TmceleditElementTypes.RoleTypeConstraints_3001 == req
				.getElementType()) {
			return getGEFWrapper(new RoleTypeConstraintsCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (TmceleditElementTypes.TopicTypeAko_3002 == req.getElementType()) {
			return getGEFWrapper(new TopicTypeAkoCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (TmceleditElementTypes.TopicTypeIsa_3003 == req.getElementType()) {
			return getGEFWrapper(new TopicTypeIsaCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case RoleTypeConstraintsEditPart.VISUAL_ID:
			return getGEFWrapper(new RoleTypeConstraintsReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case TopicTypeAkoEditPart.VISUAL_ID:
			return getGEFWrapper(new TopicTypeAkoReorientCommand(req));
		case TopicTypeIsaEditPart.VISUAL_ID:
			return getGEFWrapper(new TopicTypeIsaReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
