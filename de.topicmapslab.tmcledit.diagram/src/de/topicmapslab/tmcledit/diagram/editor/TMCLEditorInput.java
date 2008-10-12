/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
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
	private final EditingDomain editingDomain;
	
	private final UndoAction undoAction;
	private final RedoAction redoAction;
	
	
	
	public TMCLEditorInput(Diagram diagram, EditingDomain editingDomain,
			UndoAction undoAction, RedoAction redoAction, 
			boolean exists) {
		super();
		this.diagram = diagram;
		this.editingDomain = editingDomain;
		this.redoAction = redoAction;
		this.undoAction = undoAction;
		this.exists = exists;
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
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Diagram getDiagram() {
		return diagram;
	}
	
	public RedoAction getRedoAction() {
		return redoAction;
	}
	
	public UndoAction getUndoAction() {
		return undoAction;
	}
}
