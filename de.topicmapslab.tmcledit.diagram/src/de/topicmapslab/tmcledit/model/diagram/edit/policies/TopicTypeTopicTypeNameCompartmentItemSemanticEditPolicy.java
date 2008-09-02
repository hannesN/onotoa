package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.NameTypeConstraintCreateCommand;
import de.topicmapslab.tmcledit.model.diagram.providers.TmceleditElementTypes;

/**
 * @generated
 */
public class TopicTypeTopicTypeNameCompartmentItemSemanticEditPolicy extends
		TmceleditBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TmceleditElementTypes.NameTypeConstraint_2001 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(TMPackage.eINSTANCE
						.getTopicType_NameContraints());
			}
			return getGEFWrapper(new NameTypeConstraintCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
