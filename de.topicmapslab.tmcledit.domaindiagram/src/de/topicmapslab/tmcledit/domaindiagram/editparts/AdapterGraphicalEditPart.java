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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;

import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class AdapterGraphicalEditPart extends AbstractGraphicalEditPart implements Adapter, IContextMenuProvider {

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
	
	public List<IContributionItem> getItems() {
		return Collections.emptyList();
	}
	
	public List<IAction> getActions() {
		return Collections.emptyList();
	}
	
	protected org.eclipse.gef.commands.CommandStack getGEFCommendStack() {
		return getEditDomain().getCommandStack();
	}
	
	protected CommandStack getEMFCommendStack() {
		return getEditDomain().getEditingDomain().getCommandStack();
	}
	
	protected DomainEditDomain getEditDomain() {
		return ((DomainEditDomain) getViewer().getEditDomain());
	}
}
