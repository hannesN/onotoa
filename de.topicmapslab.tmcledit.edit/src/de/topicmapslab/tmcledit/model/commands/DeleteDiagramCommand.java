/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;

/**
 * @author Hannes Niederhausen
 *
 */
public class DeleteDiagramCommand extends AbstractCommand {

	private final File file;
	private final Diagram diagram;
	private final int index;
	
	
	public DeleteDiagramCommand(Diagram diagram) {
		super();
		this.diagram = diagram;
		this.file = (File) diagram.eContainer();
		this.index = file.getDiagrams().indexOf(diagram);
	}

	@Override
	public void execute() {
		file.getDiagrams().remove(diagram);
	}

	@Override
	public void undo() {
		file.getDiagrams().add(index, diagram);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
