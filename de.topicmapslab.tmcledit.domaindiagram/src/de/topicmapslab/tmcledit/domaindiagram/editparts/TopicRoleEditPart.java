/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.diagram.action.DeleteFromModelAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetRoleAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetRoleData;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TopicRoleEditPart extends AbstractLabelEditPart implements
		IContextMenuProvider {

	@Override
	protected void createEditPolicies() {
	}

	public void notifyChanged(Notification msg) {
		if (msg.getNotifier() instanceof RolePlayerConstraint) {
			if (msg.getEventType()==Notification.SET) {
				if (msg.getFeatureID(RoleConstraint.class)==ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE) {
					if (msg.getNewValue()!=null)
						addRoleConstraintAdapters((RoleConstraint) msg.getNewValue());
					if (msg.getOldValue()!=null)
						removeRoleConstraintAdapters((RoleConstraint) msg.getOldValue());
				}
			}
		}
		refreshVisuals();
	}
	
	private void addRoleConstraintAdapters(RoleConstraint rc) {
		rc.eAdapters().add(this);
		if (rc.getType()!=null)
			rc.getType().eAdapters().add(this);
	}
	
	private void removeRoleConstraintAdapters(RoleConstraint rc) {
		rc.eAdapters().remove(this);
		if (rc.getType()!=null)
			rc.getType().eAdapters().remove(this);
	}

	@Override
	protected void refreshVisuals() {
		getNameLabel().setText(getCastedModel().getPlayer().getName() + " isa ");
		RoleConstraint role = getCastedModel().getRole();
		getSecondaryLabel().setText("???");
		if (role != null) {
			if (role.getType() != null)
				getSecondaryLabel().setText(role.getType().getName());
		}
	}
	
	@Override
	public void setModel(Object model) {
		if (getModel()!=null) {
			if (getCastedModel().getPlayer()!=null)
				getCastedModel().getPlayer().eAdapters().remove(this);
		if (getCastedModel().getRole()!=null)
			removeRoleConstraintAdapters(getCastedModel().getRole());
		}
		
		super.setModel(model);
		
		if (getModel()==null) 
			return;
		
		if (getCastedModel().getPlayer()!=null)
			getCastedModel().getPlayer().eAdapters().add(this);
		if (getCastedModel().getRole()!=null)
			addRoleConstraintAdapters(getCastedModel().getRole());
		
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
				getEMFCommendStack());
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
}
