package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeRole extends TreeParent {

	private final AssociationTypeConstraint assocConstraint;

	public TreeRole(ModelView modelView, RoleConstraint rc, AssociationTypeConstraint assocConstraint) {
		super(modelView, "");
		assert (rc != null);
		assert (assocConstraint != null);
		this.assocConstraint = assocConstraint;
		setModel(rc);

	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getNotifier().equals(assocConstraint)) {
			if (notification.getFeatureID(EList.class) == ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS)
				updatePlayerConstraints(notification);
		}

		refresh();
	}

	private void updatePlayerConstraints(Notification notification) {
		if (notification.getEventType() == Notification.ADD) {
			addRolePlayerConstraint((RolePlayerConstraint) notification.getNewValue());
		} else if (notification.getEventType()==Notification.REMOVE) {
			removeRolePlayerConstraint((RolePlayerConstraint) notification.getOldValue());
		}
	}

	@Override
	public String getName() {
		return (getRoletType() == null) ? "not type set" : getRoletType().getName();
	}

	@Override
	public void setModel(EObject model) {
		super.setModel(model);
		getAssocTypeConstraint().eAdapters().add(this);
		createChildren();
	}

	private void createChildren() {
		clearChildren();
		for (RolePlayerConstraint rpc : getAssocTypeConstraint().getPlayerConstraints()) {
			addRolePlayerConstraint(rpc);
		}
		refresh();
	}

	private void addRolePlayerConstraint(RolePlayerConstraint rpc) {
	    if (getModel().equals(rpc.getRole())) {
	    	TreeRolePlayer trp = new TreeRolePlayer(getModelView(), rpc);
	    	addChild(trp);
	    }
    }
	
	private void removeRolePlayerConstraint(RolePlayerConstraint rpc) {
		TreeObject child = findChildPerModel(rpc);
		if (child!=null) {
			removeChild(child);
			child.dispose();
		}
	}

	@Override
	public void dispose() {
		getAssocTypeConstraint().eAdapters().remove(this);
		super.dispose();
	}

	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.ROLETYPE_SM);
	}

	private RoleType getRoletType() {
		return (RoleType) getCastedModel().getType();
	}

	private RoleConstraint getCastedModel() {
		return (RoleConstraint) getModel();
	}

	private AssociationTypeConstraint getAssocTypeConstraint() {
		return assocConstraint;
	}
}
