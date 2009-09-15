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
import org.eclipse.emf.common.util.EMap;

import de.topicmapslab.tmcledit.model.TMCLConstruct;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModifyAnnotationKeyCommand extends AbstractCommand {

	private final TMCLConstruct construct;

	private final String newKey;
	private final String oldKey;

	public ModifyAnnotationKeyCommand(TMCLConstruct construct, String newKey, String oldKey) {
		super();
		setLabel("Modify Annotation");
		this.construct = construct;
		this.newKey = newKey;
		this.oldKey = oldKey;
	}

	public void execute() {
		
		
		EMap<String, String> extension = construct.getExtension();
		String value = extension.get(oldKey);
		extension.removeKey(oldKey);
		extension.put(newKey, value);
		
	}

	@Override
	public void undo() {
		EMap<String, String> extension = construct.getExtension();
		String value = extension.get(newKey);
		
		extension.removeKey(newKey);
		extension.put(oldKey, value);
	}

	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		if (newKey.equals(oldKey))
			return false;
		if ((construct.getExtension().get(oldKey)==null))
			return false;
		if ((construct.getExtension().get(newKey)!=null))
			return false;
		
		return true;
	}

}
