package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.NameConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;

public class NameTypeConstraintEditPart extends AbstractLabelEditPart {
	
	private NameTypeConstraint getCastedModel() {
		return (NameTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new NameConstraintDirectEditPolicy());

	}
	
	@Override
	protected void refreshVisuals() {
		NameTypeConstraint ntc = getCastedModel();
		getNameLabel().setText(ntc.getType().getId());
		StringBuffer buffer = new StringBuffer(50);
		buffer.append(" [");
		buffer.append(ntc.getRegexp());
		buffer.append("] ");
		buffer.append(" ");
		buffer.append(ntc.getCardMin());
		buffer.append("..");
		buffer.append(ntc.getCardMax());
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

	
}
