/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.TreeViewer;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class TreeTopic extends TreeParent {

	private final TopicType topic;
	
	public TreeTopic(TreeViewer viewer, TopicType topic) {
		super(viewer, null);
		this.topic = topic;
		topic.eAdapters().add(this);
	}

	public TopicType getTopic() {
		return topic;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if ( (notification.getEventType()==Notification.SET) && (notification.getFeatureID(String.class)==ModelPackage.TOPIC_TYPE__ID)){
			viewer.refresh(this);
		}
	}
	
	@Override
	public String getName() {
		return topic.getId();
	}
	
	
	@Override
	public void dispose() {
		topic.eAdapters().remove(this);
		super.dispose();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class key) {
		if (topic==null)
			return null;
		
				
		
		return null;
	}
}
