/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;

import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.domaindiagram.action.AbstractCommandStackAction;
import de.topicmapslab.tmcledit.domaindiagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;

/**
 * @author niederhausen
 * 
 */
public class TopicRoleEditPart extends AbstractLabelEditPart implements
		IContextMenuProvider {

	@Override
	protected void createEditPolicies() {
	}

	public void notifyChanged(Notification notification) {
		refreshVisuals();
	}

	@Override
	protected void refreshVisuals() {
		getNameLabel()
				.setText(getCastedModel().getPlayer().getName() + " isa ");
		RoleConstraint role = getCastedModel().getRole();
		getSecondaryLabel().setText("???");
		if (role != null) {
			if (role.getType() != null)
				getSecondaryLabel().setText(role.getType().getName());
		}

	}
	
	@Override
	protected boolean isEditable() {
		return false;
	}

	private RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}

	public List<IAction> getActions() {
		List<IAction> result = new ArrayList<IAction>();

		DeleteFromModelAction model = new DeleteFromModelAction(
				getGEFCommendStack());
		result.add(model);

		return result;
	}

	private TopicMapSchema getTopicMapSchema() {
		return (TopicMapSchema) getCastedModel().getPlayer().eContainer();
	}

	@Override
	public List<IContributionItem> getItems() {
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Role");
		SetRoleData data = new SetRoleData();
		data.rpc = getCastedModel();
		data.editDomain = getEditDomain();
		data.role = null;
		data.schema = getTopicMapSchema();

		subMenu.add(new SetRoleAction(data));
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if (tt instanceof RoleType) {
				SetRoleData d = data.clone();
				d.role = (RoleType) tt;
				subMenu.add(new SetRoleAction(d));
			}
		}
		result.add(subMenu);

		return result;
	}

	private class SetRoleAction extends AbstractCommandStackAction {

		private SetRoleData data = new SetRoleData();

		public SetRoleAction(SetRoleData data) {
			super(data.editDomain.getCommandStack());
			this.data = data;
			init();
		}

		private void init() {
			String text = (data.role == null) ? "New Role..." : data.role
					.getName();
			setText(text);
		}

		@Override
		public void run() {
			CompoundCommand cmd = new CompoundCommand();
			if (data.role == null) {
				Command c = createRoleCommand();
				if (c != null)
					cmd.append(c);
			}

			Command c = createRoleConstraintCommand();
			if (c != null)
				cmd.append(c);

			c = new SetRoleConstraintCommand(data.rpc, data.rc);
			cmd.append(c);

			CommandStack cmdStack = data.editDomain.getEditingDomain()
					.getCommandStack();
			getCommandStack().execute(new CommandAdapter(cmdStack, cmd));
		}

		private Command createRoleConstraintCommand() {
			AssociationType at = (AssociationType) ((AssociationTypeConstraint) data.rpc
					.eContainer()).getType();
			for (RoleConstraint rc : at.getRoles()) {
				if (rc.getType().equals(data.role)) {
					data.rc = rc;
					return null;
				}
			}
			RoleConstraint rc = ModelFactory.eINSTANCE.createRoleConstraint();
			rc.setType(data.role);
			data.rc = rc;
			AddRoleConstraintCommand c = new AddRoleConstraintCommand(at, rc);

			return c;
		}

		private Command createRoleCommand() {
			NewTopicTypeWizard tt = new NewTopicTypeWizard(
					KindOfTopicType.ROLE_TYPE);
			WizardDialog dlg = new WizardDialog(Activator.getCurrentShell(), tt);
			if (dlg.open() != Dialog.OK) {
				return null;
			}

			CreateTopicTypeCommand cmd = new CreateTopicTypeCommand(
					data.schema, tt.getNewTopicType());

			data.role = (RoleType) tt.getNewTopicType();
			return cmd;
		}

	}
}
