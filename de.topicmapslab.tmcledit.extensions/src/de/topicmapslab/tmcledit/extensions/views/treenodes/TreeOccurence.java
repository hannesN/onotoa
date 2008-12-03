package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class TreeOccurence extends TreeObject{

	public TreeOccurence(ModelView modelView, OccurenceTypeConstraint otc) {
		this(modelView, otc, null);
	}

	public TreeOccurence(ModelView modelView, OccurenceTypeConstraint otc, String name) {
		super(modelView, name);
		setModel(otc);
	}

	private OccurenceTypeConstraint getOccurenceTypeConstraint() {
		return (OccurenceTypeConstraint) getModel();
	}
	
	@Override
	public String getName() {
		return getOccurenceTypeConstraint().getType().getName();
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.OCCURENCECONSTRAINT);
		
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(getOccurenceTypeConstraint())) {
			if (notification.getNewValue() instanceof TopicType) {
				if (notification.getOldValue()!=null)
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				((TopicType)notification.getNewValue()).eAdapters().add(this);
			}
			return;
		}
		
		if (notification.getNotifier().equals(getOccurenceTypeConstraint().getType())) {
			getModelView().getViewer().refresh(this);
		}
	}
}
