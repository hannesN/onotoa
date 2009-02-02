package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.OccurenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurenceTypeConstraintEditPart extends AbstractScopedLabeledEditPart {
	OccurenceTypeConstraint getCastedModel() {
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
		
		if (otc.getType()!=null)
			getNameLabel().setText(otc.getType().getName());
		else
			getNameLabel().setText("No Type Set");
		
		buffer.append(" : ");
		if (otc.getType() instanceof OccurenceType)
			buffer.append(((OccurenceType) otc.getType()).getDataType());
		else
			buffer.append("xsd:anyType");
		buffer.append(" ");
		buffer.append(otc.getCardMin());
		buffer.append("..");
		buffer.append(otc.getCardMax());	
		
		addScopeText(buffer);
		
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void activate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();

	}
	
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getEventType()==Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				if (notification.getNewValue()!=null)
					((TopicType) notification.getNewValue()).eAdapters().add(this);
			
			}
			refreshVisuals();
		}
		
	}

}
