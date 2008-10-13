package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.OccurenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurenceTypeConstraintEditPart extends AbstractLabelEditPart {
	private OccurenceTypeConstraint getCastedModel() {
		return (OccurenceTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new OccurenceConstraintDirectEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		OccurenceTypeConstraint otc = getCastedModel();
		StringBuffer text = new StringBuffer();
		
		getNameLabel().setText(otc.getName());
		
		TopicType type = otc.getType();
		if (type!=null)
			text.append(":"+otc.getType().getId());
		
		text.append("\t"+otc.getCardMin()+".."+otc.getCardMax());	
		
		getTypeLabel().setText(text.toString());
	}

	
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

}
