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
import de.topicmapslab.tmcledit.diagram.editparts.NodeEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class TopicTypeDirectEditPolicy extends DirectEditPolicy {

	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNode tn = (TypeNode) ((NodeEditPart)getHost()).getModel();
			
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			RenameTopicTypeCommand cmd = new RenameTopicTypeCommand(tn.getTopicType(), newName);
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		}
		return null;
	}

	
	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNodeEditPart tnep = ((TypeNodeEditPart)getHost());
			
			tnep.revertNameChange();
		}
	}
	
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		TypeNodeEditPart ep = (TypeNodeEditPart) getHost();
		ep.handleNameChange(value);
	}

}
