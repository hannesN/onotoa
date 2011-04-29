/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.diagram.action.AbstractCommandStackAction;
import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.SetImageCommand;

/**
 * Action to remove an image from a node
 * @author Hannes Niederhausen
 *
 */
public class RemoveImageAction extends AbstractCommandStackAction {
	/**
	 * 
	 */
	private TypeNode typeNode;
	
	/**
	 * Constructor
	 * @param commandStack the commandStack for the action
	 * @param typeNode the node with the image to remove
	 */
	public RemoveImageAction(CommandStack commandStack, TypeNode typeNode) {
		super(commandStack);
		this.typeNode = typeNode;
		setText("Remove Image...");
	}

	/**
	 * Sets the type node 
	 * @param typeNode
	 */
	public void setTypeNode(TypeNode typeNode) {
		this.typeNode = typeNode;
	}
	
	@Override
	public void run() {
		try {
			Command command = new SetImageCommand(typeNode.getTopicType(), null);
			getCommandStack().execute(command);
		} catch (Exception e) {
			Activator.getDefault().log(e);
		}
	}
}