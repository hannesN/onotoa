package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
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
	}
	
	@Override
	public String getName() {
		TopicType tt = getCastedModel().getPlayer();
		if (tt!=null)
			return tt.getName();
		else
			return "no player set";
	}
	
	@Override
	public Image getImage() {
	    return ImageProvider.getImage(ImageConstants.TOPICTYPE_SM);
	}
	
	
	
	
	private RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}
	
}
