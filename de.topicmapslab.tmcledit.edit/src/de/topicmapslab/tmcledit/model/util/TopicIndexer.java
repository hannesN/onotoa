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

	private static  TopicIndexer INSTANCE;
	
	private final TopicMapSchema topicMapSchema;
	
	private Notifier target;
	
	public TopicIndexer(TopicMapSchema topicMapSchema) {
		super();
		this.topicMapSchema = topicMapSchema;
		topicMapSchema.eAdapters().add(this);
	}
	
	protected void init() {
		// start indexing, hashtable any one?
	}
	
	public TopicType getTopicType(String id) {
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			if (tt.getId().equals(id))
				return tt;
		}
		return null;
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
		topicMapSchema.eAdapters().remove(this);
	}
	
	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}
	
	public static TopicIndexer getInstance(TopicMapSchema schema) {
		if (INSTANCE==null) {
			INSTANCE = new TopicIndexer(schema);
		} else {
			if (!INSTANCE.topicMapSchema.equals(schema))
				INSTANCE.dispose();
				INSTANCE = new TopicIndexer(schema);
		}
		
		return INSTANCE;
	}
	
	public static TopicIndexer getInstance() {
		return INSTANCE;
	}
}
