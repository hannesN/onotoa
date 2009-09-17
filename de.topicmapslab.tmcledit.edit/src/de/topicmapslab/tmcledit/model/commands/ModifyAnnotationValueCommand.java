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

import de.topicmapslab.tmcledit.model.Annotation;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModifyAnnotationValueCommand extends AbstractCommand {

	private final Annotation annotation;
	private final String newValue;
	private final String oldValue;

	public ModifyAnnotationValueCommand(Annotation annotation, String newValue) {
		super();
		this.newValue = newValue;
		this.oldValue = annotation.getValue();
		this.annotation = annotation;
	}

	public void execute() {
		annotation.setValue(newValue);
	}

	@Override
	public void undo() {
		annotation.setValue(oldValue);
	}

	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		if (oldValue==null)
			return false;
		if (oldValue.equals(newValue))
			return false;

		return true;
	}

}
