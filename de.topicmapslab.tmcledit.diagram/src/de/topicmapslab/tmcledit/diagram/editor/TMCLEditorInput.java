/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.topicmapslab.tmcledit.model.Diagram;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditorInput implements IEditorInput {

	private final Diagram diagram;
	private final boolean exists;
	
	
	public TMCLEditorInput(Diagram diagram, boolean doesExists) {
		this.diagram = diagram;
		this.exists = doesExists;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return diagram.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return diagram.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	public Diagram getDiagram() {
		return diagram;
	}
}
