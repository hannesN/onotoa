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

import de.topicmapslab.tmcledit.model.Diagram;

public class RenameDiagramCommand extends AbstractCommand{

	private final String name;
	private final Diagram diagram;
	
	private String oldName;
	
	
	public RenameDiagramCommand(String name, Diagram diagram) {
		super();
		this.name = name;
		this.diagram = diagram;
	}
	
	@Override
	public void execute() {
		diagram.setName(name);
	}
	
	@Override
	public void undo() {
		diagram.setName(oldName);
	}
	
	@Override
	protected boolean prepare() {
		oldName = diagram.getName();
		return true;
	}
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Rename diagram";
	}
	
}
