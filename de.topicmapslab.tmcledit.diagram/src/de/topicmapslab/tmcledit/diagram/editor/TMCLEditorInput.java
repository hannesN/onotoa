/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditorInput implements IEditorInput {

	private String path;
	private String name;
	private boolean doesExists;
	
	public TMCLEditorInput(String path, String name, boolean doesExists) {
		this.path = path;
		this.name = name;
		this.doesExists = doesExists;
	}
	
	@Override
	public boolean exists() {
		return doesExists;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return path;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
