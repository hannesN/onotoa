package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.NameConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class NameTypeConstraintEditPart extends AbstractScopedLabeledEditPart {
	
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
		if (ntc.getType()==null)
			getNameLabel().setText("No Type");
		else
			getNameLabel().setText(ntc.getType().getName());
		StringBuffer buffer = new StringBuffer(50);
		buffer.append(" [");
		buffer.append(ntc.getRegexp());
		buffer.append("] ");
		buffer.append(" ");
		buffer.append(ntc.getCardMin());
		buffer.append("..");
		buffer.append(ntc.getCardMax());
		addScopeText(buffer);
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void activate() {
		super.activate();
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().add(this);
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
		
		if (notification.getNotifier()==getModel()) { 
			if (notification.getFeatureID(TopicType.class)==ModelPackage.NAME_TYPE_CONSTRAINT__TYPE) {
				TopicType tmp = (TopicType) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(this);
				tmp = (TopicType) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(this);
			}
		}
		
		refreshVisuals();
		
	}

	
}
