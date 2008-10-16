package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.OccurenceConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

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
		
		getNameLabel().setText(otc.getType().getId());
		
		text.append(" : ");
		text.append(otc.getDataType());
		text.append(" ");
		text.append(otc.getCardMin());
		text.append("..");
		text.append(otc.getCardMax());	
		
		getTypeLabel().setText(text.toString());
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
				TypeNode old = (TypeNode) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				((TypeNode) notification.getNewValue()).eAdapters().add(this);
			
			}
			refreshVisuals();
		}
		
	}

}
