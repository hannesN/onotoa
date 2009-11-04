package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;

public interface IOnotoaEditDomain {
	public void setEditingDomain(EditingDomain editingDomain);
	
	public EditingDomain getEditingDomain();
}