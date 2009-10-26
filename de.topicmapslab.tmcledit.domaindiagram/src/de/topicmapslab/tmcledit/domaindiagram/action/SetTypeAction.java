/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

public class SetTypeAction extends AbstractCommandStackAction {

	protected SetTypeData data = new SetTypeData();

	public SetTypeAction(SetTypeData data) {
		super(data.editDomain.getEditingDomain().getCommandStack());
		this.data = data;
		init();
	}

	private void init() {
		String text = (data.type == null) ? "New Association..." : data.type
				.getName();
		setText(text);
	}

	@Override
	public void run() {
		CompoundCommand cmd = new CompoundCommand();

//		AbstractCommand c = null;
//		if (data.type == null) {
//			c = createAssociationCommand();
//			if (c != null)
//				cmd.append(c);
//		}
		cmd.append(getCommand());

		getCommandStack().execute(cmd);
	}
	
	protected AbstractCommand getCommand() {
		return new GenericSetCommand(data.typedConstraint, data.featureId, data.type);
	}

}