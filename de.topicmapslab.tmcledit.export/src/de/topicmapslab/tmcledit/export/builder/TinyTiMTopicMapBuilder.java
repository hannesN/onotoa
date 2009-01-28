package de.topicmapslab.tmcledit.export.builder;


import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypeConstraint;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TinyTiMTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;
	
	private boolean createConstraintInfos;
	
	private Topic scopeType;
	private Topic nameType;
	private Topic occurenceType;
	private Topic associationType;
	private Topic roleType;
	private Topic topicType;
	private Topic superTypeRole;
	private Topic subTypeRole;
	private Topic supertype_subType_assTopic;
	
	private Topic infoScope;
	
	public TinyTiMTopicMapBuilder(TopicMapSchema topicMapSchema) {
		this.topicMapSchema = topicMapSchema;
	}

	public TopicMap getTopicMap() throws Exception{
		return getTopicMap(false);
	}
	
	public TopicMap getTopicMap(boolean createConstraintInfo) throws Exception{
		TopicMap tm = null;
		this.createConstraintInfos = createConstraintInfo;
		
		TopicMapSystemFactory fac = TopicMapSystemFactory.newInstance();
		
		TopicMapSystem system = fac.newTopicMapSystem();
		
		tm = system.createTopicMap("http://psi.topicmapslab.de/tmclschema");
		
		if (createConstraintInfos) {
			Locator l = tm.createLocator("#constraint_info_scope");
			scopeType = tm.createTopicByItemIdentifier(l);
		}
		
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

		if (createConstraintInfos) {
			createAssociationConstraintInfos(tm);
		}
		
		return tm;
	}

	private void createAssociationConstraintInfos(TopicMap tm) {
		Locator l;
		for (AssociationTypeConstraint asc : topicMapSchema.getAssociationTypeConstraints()) {
			l = tm.createLocator("#topic"+asc.getAssociationType().hashCode());
			Topic topic = tm.createTopicByItemIdentifier(l); // topic should already exists therefor no new creation
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("Associations of this type should have the following role types: ");
			for (RoleTypeConstraints rtc : asc.getRoleTypeConstraints()) {
				buffer.append(rtc.getTopicType().getName());
				buffer.append("[");
				buffer.append(rtc.getCardMin());
				buffer.append("..");
				buffer.append(rtc.getCardMax());
				buffer.append("], ");
			}
			buffer.setLength(buffer.length()-2);
			if (asc.getScope().size()>0)
				buffer.append(" @ scope: ");
			for (ScopeConstraint sc : asc.getScope()) {
				buffer.append(sc.getType().getName());
				buffer.append("[");
				buffer.append(sc.getCardMin());
				buffer.append("..");
				buffer.append(sc.getCardMax());
				buffer.append("]");
			}
			
			topic.createOccurrence(getConstraintInfoTopic(asc, tm), buffer.toString(), scopeType);
		}
	}
	
	private Topic getConstraintInfoTopic(AbstractConstraint constraint, TopicMap tm) {
		Locator l = null;
		if (constraint instanceof AssociationTypeConstraint) {
			l = tm.createLocator("#association_constraint_info");
		} else if (constraint instanceof OccurenceTypeConstraint) {
			l = tm.createLocator("#occurence_constraint_info");
		} else if (constraint instanceof NameTypeConstraint) {
			l = tm.createLocator("#name_constraint_info");
		} else if (constraint  instanceof SubjectIdentifierConstraint) {
			l = tm.createLocator("#subject_identifier_constraint_info");
		} else if (constraint instanceof SubjectLocatorConstraint) {
			l = tm.createLocator("#subject_identifier_constraint_info");
		} else if (constraint instanceof RoleTypeConstraints) {
			l = tm.createLocator("#role_constraint_info");
		}
		
		return tm.createTopicByItemIdentifier(l);
	}
	
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
		
		if (createConstraintInfos) {
			for (NameTypeConstraint ntc : topicType.getNameContraints()) {
				createConstraints(ntc, t);
			}
			
			for (OccurenceTypeConstraint otc : topicType.getOccurenceConstraints()) {
				createConstraints(otc, t);
			}
		}

		return t;
	}

	private void createConstraints(AbstractTypeConstraint tc, Topic t) {
		Topic cit = getConstraintInfoTopic(tc, t.getTopicMap());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("type: ");
		buffer.append(tc.getType().getName());
		buffer.append(" [");
		buffer.append(tc.getCardMin());
		buffer.append("..");
		buffer.append("] ");
		
		if (tc instanceof OccurenceTypeConstraint) {
			OccurenceTypeConstraint otc = (OccurenceTypeConstraint) tc;
			buffer.append(" datatype: ");
			buffer.append(otc.getDataType());
			if (otc.isUnique())
				buffer.append(" is unique ");
		}
		
		if (tc.getScope().size()>0) {
			buffer.append("@scope: ");
			for (ScopeConstraint sc : tc.getScope()) {
				buffer.append(sc.getType().getName());
				buffer.append("[");
				buffer.append(sc.getCardMin());
				buffer.append("..");
				buffer.append(sc.getCardMax());
				buffer.append("], ");
			}
			buffer.setLength(buffer.length()-2);
		}
		
		t.createOccurrence(cit, buffer.toString(), infoScope);
	}


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
