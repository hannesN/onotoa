/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import de.topicmapslab.tmcledit.diagram.command.RenameCommand;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author mai00ckx
 *
 */
public class TopicTypeDirectEditPolicy extends DirectEditPolicy {

	private String oldValue;
	
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		if (getHost() instanceof TypeNodeEditPart) {
			TypeNode tn = (TypeNode) ((TypeNodeEditPart)getHost()).getModel();
			
			
			
			CellEditor cellEditor = request.getCellEditor();
			String newName = (String) cellEditor.getValue();
			RenameCommand cmd = new RenameCommand(tn.getType(), newName);
		
			return cmd;
		
		}
		return null;
	}

	@Override
	protected void storeOldEditValue(DirectEditRequest request) {
		oldValue = (String) request.getCellEditor().getValue();
	}
	
	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		TypeNodeEditPart ep = (TypeNodeEditPart) getHost();
		
	}

}
