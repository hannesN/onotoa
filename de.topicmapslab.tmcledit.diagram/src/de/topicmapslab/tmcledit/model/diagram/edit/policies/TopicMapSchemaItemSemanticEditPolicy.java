package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.AssociationTypeConstraintCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.AssociationsTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.NameTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.OccurenceTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.RoleTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.ScopeTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.TopicTypeCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.providers.TmcleditElementTypes;

/**
 * @generated
 */
public class TopicMapSchemaItemSemanticEditPolicy extends
		TmcleditBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TmcleditElementTypes.NameType_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new NameTypeCreateCommand(req));
		}
		if (TmcleditElementTypes.ScopeType_1002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new ScopeTypeCreateCommand(req));
		}
		if (TmcleditElementTypes.RoleType_1003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new RoleTypeCreateCommand(req));
		}
		if (TmcleditElementTypes.AssociationsType_1004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new AssociationsTypeCreateCommand(req));
		}
		if (TmcleditElementTypes.OccurenceType_1005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new OccurenceTypeCreateCommand(req));
		}
		if (TmcleditElementTypes.AssociationTypeConstraint_1006 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_AssociationTypeConstraints());
			}
			return getGEFWrapper(new AssociationTypeConstraintCreateCommand(req));
		}
		if (TmcleditElementTypes.TopicType_1007 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicMapSchema_TopicTypes());
			}
			return getGEFWrapper(new TopicTypeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
