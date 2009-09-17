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
public class ModifyAnnotationKeyCommand extends AbstractCommand {

	private final Annotation annotation;

	private final String newKey;
	private final String oldKey;

	public ModifyAnnotationKeyCommand(Annotation annotation, String newKey) {
		super();
		setLabel("Modify Annotation");
		this.annotation = annotation;
		this.newKey = newKey;
		this.oldKey = annotation.getKey();
	}

	public void execute() {
		annotation.setKey(newKey);
	}

	@Override
	public void undo() {
		annotation.setKey(oldKey);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (newKey.equals(oldKey))
			return false;
		
		return true;
	}

}
