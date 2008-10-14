package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class TreeName extends TreeObject{

	private final NameTypeConstraint ntc;
	
	public TreeName(ModelView modelView, NameTypeConstraint ntc) {
		super(modelView);
		this.ntc = ntc;
		ntc.eAdapters().add(this);
	}

	public TreeName(ModelView modelView, NameTypeConstraint ntc, String name) {
		super(modelView, name);
		this.ntc = ntc;
		ntc.eAdapters().add(this);
	}

	@Override
	public void dispose() {
		ntc.eAdapters().remove(this);
		super.dispose();
	}
	
	@Override
	public String getName() {
		return ntc.getName();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if ( (notification.getEventType()==Notification.SET) && (notification.getFeatureID(String.class)==ModelPackage.NAME_TYPE_CONSTRAINT__NAME)){
			getModelView().getViewer().refresh(this);
		}
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.NAMECONSTRAINT);
	}
	
	@Override
	public Object getModel() {
		return ntc; 
	}
}
