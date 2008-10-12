/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameOccurenceConstraintCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceConstraintDirectEditPolicy extends DirectEditPolicy {

	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof OccurenceTypeConstraintEditPart) {
			OccurenceTypeConstraint otc = (OccurenceTypeConstraint) ((OccurenceTypeConstraintEditPart) getHost())
					.getModel();
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			RenameOccurenceConstraintCommand cmd = new RenameOccurenceConstraintCommand(otc, newName);
		
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		}
		return null;
	}

	
	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		if (getHost() instanceof OccurenceTypeConstraintEditPart) {
			OccurenceTypeConstraintEditPart otcep = ((OccurenceTypeConstraintEditPart)getHost());
			
			otcep.revertNameChange();
		}
	}
	
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		OccurenceTypeConstraintEditPart ep = (OccurenceTypeConstraintEditPart) getHost();
		ep.handleNameChange(value);
	}

}
