package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

public class DiagramAdapter implements Adapter {
	private Notifier target;
	
	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void notifyChanged(Notification notification) {
		System.out.println("Diagram notification: "+notification.getNewValue().toString());
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

}
