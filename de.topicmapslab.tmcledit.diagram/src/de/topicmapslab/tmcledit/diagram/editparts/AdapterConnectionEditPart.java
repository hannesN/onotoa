package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import de.topicmapslab.tmcledit.model.Edge;

public abstract class AdapterConnectionEditPart extends AbstractConnectionEditPart {

	protected Adapter adapter = new Adapter() {
		private Notifier target;
		
		@Override
		public Notifier getTarget() {
			return target;
		}

		@Override
		public boolean isAdapterForType(Object type) {
			return AdapterConnectionEditPart.this.isAdapterForType(type);
		}

		@Override
		public void notifyChanged(Notification notification) {
			AdapterConnectionEditPart.this.notifyChanged(notification);
		}

		@Override
		public void setTarget(Notifier newTarget) {
			this.target = newTarget;
		}
		
	};

	public boolean isAdapterForType(Object type) {
		return true;
	}

	public abstract void notifyChanged(Notification notification);

	
	@Override
	public void activate() {
		((Edge)getModel()).eAdapters().add(adapter);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		((Edge)getModel()).eAdapters().remove(adapter);
		super.deactivate();
	}
}
