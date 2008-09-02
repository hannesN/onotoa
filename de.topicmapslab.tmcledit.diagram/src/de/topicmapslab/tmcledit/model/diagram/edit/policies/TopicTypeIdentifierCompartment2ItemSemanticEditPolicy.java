package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.commands.SubjectLocatorConstraint2CreateCommand;
import de.topicmapslab.tmcledit.model.diagram.providers.TmcleditElementTypes;

/**
 * @generated
 */
public class TopicTypeIdentifierCompartment2ItemSemanticEditPolicy extends
		TmcleditBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (TmcleditElementTypes.NameTypeConstraint_2005 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getTopicType_NameContraints());
			}
			return getGEFWrapper(new SubjectLocatorConstraint2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
