package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;

public class CreateNewDiagramCommand extends AbstractCommand {

	private final String name;
	private final File file;
	private Diagram diagram;
	
	
	
	public CreateNewDiagramCommand(String name, File file) {
		super();
		this.name = name;
		this.file = file;
	}

	@Override
	public void execute() {
		file.getDiagrams().add(diagram);
	}

	@Override
	public void redo() {
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
}
