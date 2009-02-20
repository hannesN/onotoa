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
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;

import de.topicmapslab.tmcledit.extensions.util.IModelProvider;


public abstract class AbstractModelPage extends Page implements Adapter, IModelProvider {

	private EObject model;

	private Notifier target;
	
	private Control control;
	
	private CommandStack commandStack;
	
	private String ID;

	
	
	public AbstractModelPage(String id) {
		super();
		ID = id;
	}

	public void setModel(Object model) {
		if (this.model != null)
			this.model.eAdapters().remove(this);
		
		if (model==null) {
			getControl().setEnabled(false);
		} else {
			getControl().setEnabled(true);
		}
			
		this.model = (EObject) model;
		if (model!=null)
			this.model.eAdapters().add(this);
		updateUI();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public Control getControl() {
		return control;
	}
	
	public void setControl(Control control) {
		this.control = control;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

	public CommandStack getCommandStack() {
		return commandStack;
	}
	
	public void setCommandStack(CommandStack commandStack) {
		this.commandStack = commandStack;
	}
	
	public void aboutToHide() {
		if (this.getModel()!=null)
			this.getModel().eAdapters().remove(this);
	}
	
	public abstract void updateUI();

	public EObject getModel() {
		return model;
	}
	
	public String getID() {
		return ID;
	}
}