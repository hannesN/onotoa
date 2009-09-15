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

import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveAnnotationCommand extends AbstractCommand {

	private final TMCLConstruct construct;

	private final String key;
	private final String value;

	public RemoveAnnotationCommand(TMCLConstruct construct, String key, String value) {
		super();
		this.construct = construct;
		this.key = key;
		this.value = value;
	}

	public void execute() {
		construct.getExtension().removeKey(key);
	}

	@Override
	public void undo() {
		construct.getExtension().put(key, value);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (value.equals(construct.getExtension().get(key)))
			return true;

		return false;
	}

}
