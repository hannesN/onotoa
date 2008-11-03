package de.topicmapslab.tmcledit.model.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TopicIndexer implements Adapter{

	private int lastDefaultNumber;
	
	private TopicMapSchema topicMapSchema;
	
	private Notifier target;
	
	
	public TopicType getTopicType(String id) {
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			if (tt.getId().equals(id))
				return tt;
		}
		return null;
	}
	
	public List<TopicType> getTopicTypes() {
		return topicMapSchema.getTopicTypes();
	}
	
	public List<TopicType> getRoleTypes() {
		List<TopicType> result = new ArrayList<TopicType>();
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			if (tt.getKind()==KindOfTopicType.ROLE_TYPE)
				result.add(tt);
		}
		
		return result;
	}
	
	public TopicType createTopicType(String id) {
		TopicType tt = getTopicType(id);
		if (tt==null) {
			tt = ModelFactory.eINSTANCE.createTopicType();
			tt.setId(id);
		}
		return tt;
	}
	
	public TopicType createTopicType() {
		TopicType tt = ModelFactory.eINSTANCE.createTopicType();
		String tmp = "foo:default";
		
		while (getTopicType(tmp+lastDefaultNumber)!=null) {
			lastDefaultNumber++;
		}
		tt.setId(tmp+lastDefaultNumber);
		
		return tt;
	}
		
	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void notifyChanged(Notification notification) {
		// TODO index new Topic or remove it, if indexing is really done ;)
	}

	public void dispose() {
		if (topicMapSchema==null)
			return;
		topicMapSchema.eAdapters().remove(this);
		topicMapSchema = null;
	}
	
	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}
	
	public void init(TopicMapSchema schema) {
		assert(schema!=null);
		if (!schema.equals(topicMapSchema))
			dispose();
		
		this.topicMapSchema = schema;
		topicMapSchema.eAdapters().add(this);
		lastDefaultNumber = 0;
	}

}
