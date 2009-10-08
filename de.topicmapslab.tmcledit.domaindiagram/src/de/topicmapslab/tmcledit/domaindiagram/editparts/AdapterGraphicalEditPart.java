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
/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class AdapterGraphicalEditPart extends AbstractGraphicalEditPart implements Adapter{

	private Notifier target;
	
	public Notifier getTarget() {
		return target;
	}

	public boolean isAdapterForType(Object type) {
		return true;
	}

	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}
	
	@Override
	public void activate() {
		super.activate();
		if (getModel() instanceof EObject) {
			EObject obj = (EObject) getModel();
			obj.eAdapters().add(this);
		
		}
	}
	
	@Override
	public void deactivate() {
		if (getModel() instanceof EObject) {
			EObject obj = (EObject) getModel();
			obj.eAdapters().remove(this);
		
		}
		super.deactivate();
	}
	
}
