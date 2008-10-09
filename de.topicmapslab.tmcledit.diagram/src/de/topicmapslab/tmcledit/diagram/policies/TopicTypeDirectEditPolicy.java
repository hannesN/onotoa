/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.diagram.command.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 *
 */
public class TopicTypeDirectEditPolicy extends DirectEditPolicy {

	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNode tn = (TypeNode) ((TypeNodeEditPart)getHost()).getModel();
			
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			RenameTopicTypeCommand cmd = new RenameTopicTypeCommand(tn.getTopicType(), newName);
		
			return cmd;
		
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
