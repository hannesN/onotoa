package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class TreeOccurence extends TreeObject{

	private final OccurenceTypeConstraint otc;
	
	public TreeOccurence(ModelView modelView, OccurenceTypeConstraint otc) {
		super(modelView);
		this.otc = otc;
		otc.eAdapters().add(this);
	}

	public TreeOccurence(ModelView modelView, OccurenceTypeConstraint otc, String name) {
		super(modelView, name);
		this.otc = otc;
		otc.eAdapters().add(this);
	}

	@Override
	public void dispose() {
		otc.eAdapters().remove(this);
		super.dispose();
	}
	
	@Override
	public String getName() {
		return otc.getType().getId();
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.OCCURENCECONSTRAINT);
		
	}
	
	@Override
	public Object getModel() {
		return otc;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(this.otc)) {
			if (notification.getNewValue() instanceof TopicType) {
				if (notification.getOldValue()!=null)
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				((TopicType)notification.getNewValue()).eAdapters().add(this);
			}
			return;
		}
		
		if (notification.getNotifier().equals(otc.getType())) {
			getModelView().getViewer().refresh(this);
		}
	}
}
