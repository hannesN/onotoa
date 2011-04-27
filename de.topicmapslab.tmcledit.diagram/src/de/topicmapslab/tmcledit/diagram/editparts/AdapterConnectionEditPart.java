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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;

import de.topicmapslab.tmcledit.diagram.editor.IOnotoaEditDomain;
import de.topicmapslab.tmcledit.model.Edge;

public abstract class AdapterConnectionEditPart extends AbstractConnectionEditPart implements IContextMenuProvider {

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
	
	public List<IAction> getActions() {
		return Collections.emptyList();
	}
	
	public List<IContributionItem> getItems() {
		return Collections.emptyList();
	}
	
	protected org.eclipse.gef.commands.CommandStack getGEFCommendStack() {
		return getEditDomain().getCommandStack();
	}
	
	protected CommandStack getEMFCommendStack() {
		return getEditDomain().getEditingDomain().getCommandStack();
	}
	
	protected IOnotoaEditDomain getEditDomain() {
		return ((IOnotoaEditDomain) getViewer().getEditDomain());
	}
}
