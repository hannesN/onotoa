package de.topicmapslab.tmcledit.export.builder;


import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TinyTiMTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;
	private Topic scopeType;
	private Topic nameType;
	private Topic occurenceType;
	private Topic associationType;
	private Topic roleType;
	private Topic topicType;
	private Topic superTypeRole;
	private Topic subTypeRole;
	private Topic supertype_subType_assTopic;

	public TinyTiMTopicMapBuilder(TopicMapSchema topicMapSchema) {
		this.topicMapSchema = topicMapSchema;
	}

	public TopicMap getTopicMap() throws Exception{
		return getTopicMap(false);
	}
	
	public TopicMap getTopicMap(boolean createConstraintInfo) throws Exception{
		TopicMap tm = null;

		TopicMapSystemFactory fac = TopicMapSystemFactory.newInstance();
		
		TopicMapSystem system = fac.newTopicMapSystem();
		
		tm = system.createTopicMap("http://psi.topicmapslab.de/tmclschema");
		
		//topicsIndex = (TopicsIndex) tm.getHelperObject(org.tmapi.index.core.TopicsIndex.class);

		// creating type topics
		Locator l = tm.createLocator("http://psi.topicmaps.org/tmcl/topictype");
		topicType = tm.createTopicBySubjectIdentifier(l);
		topicType.addType(topicType);
		topicType.createName("topic type");
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/roletype");
		roleType = tm.createTopicBySubjectIdentifier(l);
		roleType.addType(topicType);
		roleType.createName("role type");
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/associationtype");
		associationType = tm.createTopicBySubjectIdentifier(l);
		associationType.addType(topicType);
		associationType.createName("association type");
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/occurencetype");
		occurenceType = tm.createTopicBySubjectIdentifier(l);
		occurenceType.addType(topicType);
		occurenceType.createName("occurence type");
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/nametype");
		nameType = tm.createTopicBySubjectIdentifier(l);
		nameType.addType(topicType);
		nameType.createName("name type");
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/scopetype");
		scopeType = tm.createTopicBySubjectIdentifier(l);
		scopeType.addType(topicType);
		scopeType.createName("scope type");

		l = tm.createLocator("http://psi.topicmaps.org/iso13250/model/supertype");
		superTypeRole = tm.createTopicBySubjectIdentifier(l);
		
		l = tm.createLocator("http://psi.topicmaps.org/iso13250/model/subtype");
		subTypeRole = tm.createTopicBySubjectIdentifier(l);
		
		l = tm.createLocator("http://psi.topicmaps.org/iso13250/model/supertype-subtype");
		supertype_subType_assTopic = tm.createTopicBySubjectIdentifier(l);
		
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			createTopic(tt, tm);
		}

		
		return tm;
	}
/*
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
*/
	
	private Topic createTopic(TopicType topicType, TopicMap tm)
			throws Exception {
		
		Topic t = null;

		Locator itemId = tm.createLocator("#topic" + topicType.hashCode());
		t = tm.createTopicByItemIdentifier(itemId);
		for (String s : topicType.getIdentifiers()) {
			t.addSubjectIdentifier(parseId(s, tm));
		}
		
		for (String s : topicType.getLocators()) {
			t.addSubjectLocator(parseId(s, tm));
		}
		

		for (TopicType tt : topicType.getIsa()) {
			Topic topic = createTopic(tt, tm);
			t.addType(topic);
		}
		
		for (TopicType tt : topicType.getAko()) {
			Topic topic = createTopic(tt, tm);
			Association association = tm.createAssociation(supertype_subType_assTopic);
			association.createRole(superTypeRole, topic);
			association.createRole(subTypeRole, t);
		}
		
		t.createName(topicType.getName());
		switch (topicType.getKind()) {
		case ASSOCIATION_TYPE:
			t.addType(associationType);
			break;
		case OCCURENCE_TYPE:
			t.addType(occurenceType);
			break;
		case NAME_TYPE:
			t.addType(nameType);
			break;
		case ROLE_TYPE:
			t.addType(roleType);
			break;
		case SCOPE_TYPE:
			t.addType(scopeType);
			break;
		case TOPIC_TYPE:
			t.addType(this.topicType);
			break;
		}
		/*
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			createBaseName(ntc, t);
		}
		
		for (OccurenceTypeConstraint otc : topicType.getOccurenceConstraints()) {
			createOccurence(otc, t);
		}
		*/

		return t;
	}

	/*
	private TopicName createBaseName(NameTypeConstraint ntc, Topic topic)
			throws Exception {
		String id = ntc.getType().getName();
		TopicName name = topic.createTopicName(id, null);
		Topic type = getTopic(ntc.getType(), topic.getTopicMap());
		name.setValue("");
		name.setType(type);
		
		return name;
	}
	*/
/*
	private Occurrence createOccurence(OccurenceTypeConstraint otc, Topic topic)
			throws Exception {
		
		Occurrence occ = topic.createOccurrence

		return occ;
	}
	*/
	private Locator parseId(String id, TopicMap map) throws Exception {
		
		if (id.startsWith("http://"))
			return map.createLocator(id);
		
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
