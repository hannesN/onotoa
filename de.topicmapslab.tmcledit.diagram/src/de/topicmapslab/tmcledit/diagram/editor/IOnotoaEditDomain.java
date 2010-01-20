package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.CommandStack;

public interface IOnotoaEditDomain {
	public void setEditingDomain(EditingDomain editingDomain);
	
	public EditingDomain getEditingDomain();
	
	public CommandStack getCommandStack();
}