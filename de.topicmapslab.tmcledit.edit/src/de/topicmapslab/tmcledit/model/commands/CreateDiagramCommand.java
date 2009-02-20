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
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;

public class CreateDiagramCommand extends AbstractCommand{

	private final String name;
	private final File file;
	
	private Diagram diagram;
	
	
	
	public CreateDiagramCommand(String name, File file) {
		super();
		this.name = name;
		this.file = file;
	}
	
	@Override
	public void execute() {
		file.getDiagrams().add(diagram);
	}
	
	@Override
	public void undo() {
		file.getDiagrams().remove(diagram);
	}
	
	@Override
	protected boolean prepare() {
		diagram = ModelFactory.eINSTANCE.createDiagram();
		diagram.setName(name);
		return true;
	}
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Create new diagram";
	}
	
}
