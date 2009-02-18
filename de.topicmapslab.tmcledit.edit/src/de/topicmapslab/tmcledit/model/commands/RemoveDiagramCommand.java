package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;

public class RemoveDiagramCommand extends AbstractCommand{

	private final File file;
	private final Diagram diagram;
	
	
	
	public RemoveDiagramCommand(Diagram diagram, File file) {
		super();
		this.diagram = diagram;
		this.file = file;
	}
	
	@Override
	public void execute() {
		file.getDiagrams().remove(diagram);
	}
	
	@Override
	public void undo() {
		file.getDiagrams().add(diagram);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Remove diagram";
	}
	
}
