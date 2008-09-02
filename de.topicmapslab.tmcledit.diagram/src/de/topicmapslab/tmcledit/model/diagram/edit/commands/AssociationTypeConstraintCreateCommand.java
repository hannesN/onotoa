package de.topicmapslab.tmcledit.model.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

/**
 * @generated
 */
public class AssociationTypeConstraintCreateCommand extends
		CreateElementCommand {

	/**
	 * @generated
	 */
	public AssociationTypeConstraintCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		TopicMapSchema container = (TopicMapSchema) getElementToEdit();
		if (container.getAssociationTypeConstraints() != null) {
			return false;
		}
		return true;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return TMPackage.eINSTANCE.getTopicMapSchema();
	}

}
