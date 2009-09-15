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
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModifyAnnotationValueCommand extends AbstractCommand {

	private final TMCLConstruct construct;

	private final String key;
	private final String newValue;
	private final String oldValue;

	public ModifyAnnotationValueCommand(TMCLConstruct construct, String key, String newValue) {
		super();
		this.construct = construct;
		this.newValue = newValue;
		this.oldValue = construct.getExtension().get(key);
		this.key = key;
	}

	public void execute() {
		construct.getExtension().put(key, newValue);
		notifyChanged();
	}

	@Override
	public void undo() {
		construct.getExtension().put(key, oldValue);
		notifyChanged();
	}

	public void redo() {
		execute();
	}

	private void notifyChanged() {
		ENotificationImpl n = new ENotificationImpl((InternalEObject) construct, Notification.SET, null, oldValue, newValue);
	
		for (Adapter a : construct.eAdapters()) {
			a.notifyChanged(n);
		}

    }
	
	@Override
	protected boolean prepare() {
		String oldValue = construct.getExtension().get(key);
		if (oldValue==null)
			return false;
		if (oldValue.equals(newValue))
			return false;

		return true;
	}

}
