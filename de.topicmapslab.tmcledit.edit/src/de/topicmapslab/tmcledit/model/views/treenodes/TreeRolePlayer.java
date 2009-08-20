package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeRolePlayer extends TreeObject {

	public TreeRolePlayer(ModelView modelView, RolePlayerConstraint rc) {
	    super(modelView);
	    assert rc!=null;
	    setModel(rc);
    }

	@Override
	public void notifyChanged(Notification notification) {
	    super.notifyChanged(notification);
	    getModelView().getViewer().refresh(this);
	    
	    Object notifier = notification.getNotifier();
		if (notifier instanceof RoleConstraint) {
			if (notification.getFeatureID(RoleType.class)==ModelPackage.ROLE_CONSTRAINT__TYPE) {
				EObject tmp = (EObject) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(getAdapter());
				tmp = (EObject) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(getAdapter());
			}
		} 
	    
	}
	
	@Override
	public String getName() {
		StringBuilder builder = new StringBuilder(50);
		TopicType player = getCastedModel().getPlayer();
		if (player!=null)
			builder = new StringBuilder(player.getName());
		else
			builder = new StringBuilder("no player");
		
		builder.append("(");
		RoleConstraint role = getCastedModel().getRole();
		if (role==null)
			builder.append("no role");
		else
			builder.append(role.getType().getName());
		builder.append(")");
		
		return builder.toString();
	}
	
	@Override
	public Image getImage() {
	    return ImageProvider.getImage(ImageConstants.TOPICTYPE_SM);
	}
	
	@Override
	public void setModel(EObject model) {
	    super.setModel(model);
	    RoleConstraint role = getCastedModel().getRole();
		if (role!=null) {
			role.eAdapters().add(getAdapter());
			role.getType().eAdapters().add(getAdapter());
		}
		TopicType player = getCastedModel().getPlayer();
		if (player!=null)
			player.eAdapters().add(getAdapter());
	}
	
	
	private RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}
	
}
