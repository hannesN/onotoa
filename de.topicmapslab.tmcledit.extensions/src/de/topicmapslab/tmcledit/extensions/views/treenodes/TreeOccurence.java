package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;

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
		return otc.getName();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if ( (notification.getEventType()==Notification.SET) && (notification.getFeatureID(String.class)==ModelPackage.OCCURENCE_TYPE_CONSTRAINT__NAME)){
			getModelView().getViewer().refresh(this);
		}
	}
}
