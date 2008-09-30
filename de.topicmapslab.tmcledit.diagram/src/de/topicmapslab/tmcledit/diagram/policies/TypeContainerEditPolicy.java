package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeContainerEditPolicy extends ContainerEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected Command getAddCommand(GroupRequest request) {
		// TODO Auto-generated method stub
		return super.getAddCommand(request);
	}
	
	

}
