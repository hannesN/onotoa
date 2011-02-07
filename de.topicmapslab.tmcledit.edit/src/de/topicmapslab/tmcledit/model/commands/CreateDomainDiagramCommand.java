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
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;

public class CreateDomainDiagramCommand extends AbstractCommand{

	private final String name;
	private final File file;
	
	private DomainDiagram diagram;
	
	
	
	public CreateDomainDiagramCommand(String name, File file) {
		super();
		this.name = name;
		this.file = file;
	}
	
	public void execute() {
		file.getDiagrams().add(diagram);
	}
	
	@Override
	public void undo() {
		file.getDiagrams().remove(diagram);
	}
	
	@Override
	protected boolean prepare() {
		diagram = ModelFactory.eINSTANCE.createDomainDiagram();
		diagram.setName(name);
		return true;
	}
	
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Create new diagram";
	}
	
	public Diagram getDiagram() {
		return diagram;
	}
}
