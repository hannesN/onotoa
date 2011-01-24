/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import de.topicmapslab.tmcledit.model.Edge;

/**
 * Abstract class for connection {@link EditPart}s with a {@link Adapter} to listen
 * to model changes.
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AdapterConnectionEditPart extends AbstractConnectionEditPart {

	protected Adapter adapter = new Adapter() {
		private Notifier target;
		
		public Notifier getTarget() {
			return target;
		}

		public boolean isAdapterForType(Object type) {
			return AdapterConnectionEditPart.this.isAdapterForType(type);
		}

		public void notifyChanged(Notification notification) {
			AdapterConnectionEditPart.this.notifyChanged(notification);
		}

		public void setTarget(Notifier newTarget) {
			this.target = newTarget;
		}
		
	};

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean isAdapterForType(Object type) {
		return true;
	}

	/**
	 * {inheritDoc}
	 * 
	 * @param notification the event notification
	 */
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
