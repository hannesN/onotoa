package de.topicmapslab.tmcledit.export.builder;


import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Occurrence;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;
import org.tmapi.core.TopicName;
import org.tmapi.index.core.TopicsIndex;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TinyTiMTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;

	private TopicsIndex topicsIndex;
	
	public TinyTiMTopicMapBuilder(TopicMapSchema topicMapSchema) {
		this.topicMapSchema = topicMapSchema;
	}

	public TopicMap getTopicMap() throws Exception{
		TopicMap tm = null;

		TopicMapSystemFactory fac = TopicMapSystemFactory.newInstance();
		
		TopicMapSystem system = fac.newTopicMapSystem();
		
		tm = system.createTopicMap("http://psi.topicmapslab.de/tmclschema");
		
		topicsIndex = (TopicsIndex) tm.getHelperObject(org.tmapi.index.core.TopicsIndex.class);


		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			getTopic(tt, tm);
		}

		for (AssociationTypeConstraint atc : topicMapSchema
				.getAssociationTypeConstraints()) {
			createAssociation(atc, tm);
		}

		
		return tm;
	}

	private Association createAssociation(AssociationTypeConstraint atc,
			TopicMap tm) throws Exception {
		Association association = tm.createAssociation();
		if (atc.getAssociationType()!=null)
			association.setType(getTopic(atc.getAssociationType(), tm));

		for (RoleTypeConstraints rtc : atc.getRoleTypeConstraints()) {
			createMember(rtc.getTopicType(), rtc.getType(),
					association);
		}

		return association;
	}

	private void createMember(TopicType player, TopicType role,
			Association association) throws Exception {
		Topic roleTopic = getTopic(role, association.getTopicMap());
		Topic playerTopic = getTopic(player, association.getTopicMap());
		association.createAssociationRole(playerTopic, roleTopic);
	}

	private Topic getTopic(TopicType topicType, TopicMap tm) throws Exception {
		Locator l = parseId(topicType.getId(), tm);
		Topic r = null;
		if (l !=null)
			r = topicsIndex.getTopicBySubjectIdentifier(l);
		if (r == null) {
			r = createTopic(topicType, tm);
		}
		return r;
	}

	private Topic createTopic(TopicType topicType, TopicMap tm)
			throws Exception {
		Locator l = parseId(topicType.getId(), tm);
		Topic t = null;
		if (l!=null) {
			t = tm.createTopic();
			t.addSubjectIdentifier(l);
		} else {
			throw new RuntimeException("No subject IDentifier given.");
		}

		for (TopicType tt : topicType.getIsa()) {
			Topic topic = getTopic(tt, tm);
			t.addType(topic);
		}
		TopicName name = t.createTopicName(topicType.getId(), null);
		name.setValue(topicType.getId());
		
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			createBaseName(ntc, t);
		}
		
		for (OccurenceTypeConstraint otc : topicType.getOccurenceConstraints()) {
			createOccurence(otc, t);
		}

		return t;
	}

	private TopicName createBaseName(NameTypeConstraint ntc, Topic topic)
			throws Exception {
		String id = ntc.getType().getId();
		TopicName name = topic.createTopicName(id.substring(id.indexOf(':')+1), null);
		Topic type = getTopic(ntc.getType(), topic.getTopicMap());
		name.setValue("");
		name.setType(type);
		
		return name;
	}

	private Occurrence createOccurence(OccurenceTypeConstraint otc, Topic topic)
			throws Exception {
		
		Occurrence occ = topic.createOccurrence("", getTopic(otc.getType(), topic.getTopicMap()), null);

		return occ;
	}
	
	private Locator parseId(String id, TopicMap map) throws Exception {
		int index = id.indexOf(':');
		if (index>-1) {
			String tmp = id.substring(0, index);
			for (MappingElement me : topicMapSchema.getMappings()) {
				if (me.getKey().equals(tmp)) {
					tmp = me.getValue()+"/"+id.substring(index+1);
					return map.createLocator(tmp);
				}
			}
			
		}
		
		return null;
	}
}
