/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

public class RemoveImageAction extends AbstractCommandStackAction {
	/**
	 * 
	 */
	private TypeNode typeNode;
	

	public RemoveImageAction(CommandStack commandStack, TypeNode typeNode) {
		super(commandStack);
		this.typeNode = typeNode;
		setText("Remove Image...");
	}

	public void setTypeNode(TypeNode typeNode) {
		this.typeNode = typeNode;
	}
	
	@Override
	public void run() {
		try {
			GenericSetCommand command = new GenericSetCommand(typeNode, ModelPackage.TYPE_NODE__IMAGE, null);
			getCommandStack().execute(command);
		} catch (Exception e) {
			Activator.getDefault().log(e);
		}
	}
}