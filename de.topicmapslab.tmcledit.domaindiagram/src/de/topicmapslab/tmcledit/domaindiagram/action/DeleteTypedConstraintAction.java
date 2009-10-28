/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeConstraintItemCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteTypedConstraintAction extends AbstractCommandStackAction {

	private AbstractTypedConstraint atc;

	public DeleteTypedConstraintAction(CommandStack commandStack, AbstractTypedConstraint atc) {
		super(commandStack);
		this.atc = atc;
		setText("Delete from Model");
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}

	@Override
	public void run() {
		if (atc == null) {
			return;
		}
		CompoundCommand cmd = new CompoundCommand();
		if (atc.getType() != null) {
			TopicType tt = atc.getType();
			if (ModelIndexer.getConstraintIndexer().getConstraintsByType(tt)
					.size() == 1)
				cmd.append(new DeleteTopicTypeCommand(tt));
		}
		cmd.append(getDeleteTopicTypeConstraintItemCommand(atc));

		if (cmd.canExecute())
			getCommandStack().execute(cmd);
	}

	private AbstractCommand getDeleteTopicTypeConstraintItemCommand(Object model) {
		AbstractCommand cmd;
		int type = -1;
		if (model instanceof NameTypeConstraint) {
			type = ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS;
		} else if (model instanceof OccurrenceTypeConstraint) {
			type = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;
		}
		AbstractConstraint ac = (AbstractConstraint) model;
		cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) ac
				.eContainer(), ac, type);
		return cmd;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}
}
