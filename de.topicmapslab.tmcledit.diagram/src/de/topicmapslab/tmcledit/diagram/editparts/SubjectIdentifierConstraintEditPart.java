package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;


public class SubjectIdentifierConstraintEditPart extends AbstractLabelEditPart {
	
	private SubjectIdentifierConstraint getCastedModel() {
		return (SubjectIdentifierConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		SubjectIdentifierConstraint sic = getCastedModel();
		getNameLabel().setText("Subject Identifier");
		StringBuffer buffer = new StringBuffer(50);
		buffer.append(" [");
		buffer.append(sic.getRegexp());
		buffer.append("] ");
		buffer.append(" ");
		buffer.append(sic.getCardMin());
		buffer.append("..");
		buffer.append(sic.getCardMax());
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

	
}
