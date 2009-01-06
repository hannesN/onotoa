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
		StringBuffer buffer = new StringBuffer();
		
		getNameLabel().setText(otc.getType().getName());
		
		buffer.append(" : ");
		buffer.append(otc.getDataType());
		buffer.append(" ");
		buffer.append(otc.getCardMin());
		buffer.append("..");
		buffer.append(otc.getCardMax());	
		
		for (TopicType tt : getCastedModel().getScope()) {
			buffer.append("\n@");
			buffer.append(tt.getName());
		}
		
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void activate() {
		getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				((TopicType) notification.getNewValue()).eAdapters().add(this);
			
			}
			refreshVisuals();
		}
		
	}

}
