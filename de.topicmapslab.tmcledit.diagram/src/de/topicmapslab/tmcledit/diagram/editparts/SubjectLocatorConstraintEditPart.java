package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;


public class SubjectLocatorConstraintEditPart extends AbstractLabelEditPart {
	
	public SubjectLocatorConstraintEditPart() {
		setEditable(false);
	}
	
	private SubjectLocatorConstraint getCastedModel() {
		return (SubjectLocatorConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
	}
	
	@Override
	protected void refreshVisuals() {
		SubjectLocatorConstraint slc = getCastedModel();
		getNameLabel().setText("Subject Locator");
		StringBuffer buffer = new StringBuffer(50);
		buffer.append(" [");
		buffer.append(slc.getRegexp());
		buffer.append("] ");
		buffer.append(" ");
		buffer.append(slc.getCardMin());
		buffer.append("..");
		buffer.append(slc.getCardMax());
		getTypeLabel().setText(buffer.toString());
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET)
			refreshVisuals();
		
	}

	
}
