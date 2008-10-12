/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.ui.IEditorPart;

public class TMCLEditDomain extends DefaultEditDomain {

	private EditingDomain editingDomain;
	
	public TMCLEditDomain(IEditorPart editorPart) {
		super(editorPart);
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
}