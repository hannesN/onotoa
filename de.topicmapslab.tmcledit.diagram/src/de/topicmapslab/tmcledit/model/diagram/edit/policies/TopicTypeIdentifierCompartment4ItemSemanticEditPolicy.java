package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.SubjectIdentifierConstraint6CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.SubjectLocatorConstraint7CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.providers.TmceleditElementTypes;

/**
 * @generated
 */
public class TopicTypeIdentifierCompartment4ItemSemanticEditPolicy extends
		TmceleditBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TmceleditElementTypes.SubjectIdentifierConstraint_2023 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_SubjectIdentifierConstraints());
			}
			return getGEFWrapper(new SubjectIdentifierConstraint6CreateCommand(
					req));
		}
		if (TmceleditElementTypes.SubjectLocatorConstraint_2024 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_SubjectLocatorConstraint());
			}
			return getGEFWrapper(new SubjectLocatorConstraint7CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
