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

public class RemoveDiagramCommand extends AbstractCommand{

	private final File file;
	private final Diagram diagram;
	private final int index;
	
	
	public RemoveDiagramCommand(Diagram diagram, File file) {
		super();
		this.diagram = diagram;
		this.file = file;
		this.index = this.file.getDiagrams().indexOf(this.diagram);
	}
	
	public void execute() {
		file.getDiagrams().remove(diagram);
	}
	
	@Override
	public void undo() {
		file.getDiagrams().add(index, diagram);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Remove diagram";
	}
	
}
