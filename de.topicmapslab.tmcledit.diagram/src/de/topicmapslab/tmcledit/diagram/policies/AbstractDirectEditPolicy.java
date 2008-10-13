package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.AbstractLabelEditPart;

public abstract class AbstractDirectEditPolicy extends DirectEditPolicy {

	public AbstractDirectEditPolicy() {
		super();
	}

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof AbstractLabelEditPart) {
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			org.eclipse.emf.common.command.Command cmd = getRenameCommand(getHost().getModel(), newName);
			if (cmd!=null)
				return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		}
		return null;
	}

	public abstract org.eclipse.emf.common.command.Command getRenameCommand(Object model, String name);
		
	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		if (getHost() instanceof AbstractLabelEditPart) {
			AbstractLabelEditPart otcep = ((AbstractLabelEditPart)getHost());
			
			otcep.revertNameChange();
		}
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		AbstractLabelEditPart ep = (AbstractLabelEditPart) getHost();
		ep.handleNameChange(value);
	}

}