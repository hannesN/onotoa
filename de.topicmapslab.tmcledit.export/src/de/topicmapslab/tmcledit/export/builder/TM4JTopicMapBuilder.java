package de.topicmapslab.tmcledit.export.builder;

import org.tm4j.net.Locator;
import org.tm4j.topicmap.Association;
import org.tm4j.topicmap.BaseName;
import org.tm4j.topicmap.Member;
import org.tm4j.topicmap.Occurrence;
import org.tm4j.topicmap.Topic;
import org.tm4j.topicmap.TopicMap;
import org.tm4j.topicmap.TopicMapProvider;
import org.tm4j.topicmap.TopicMapProviderFactory;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TM4JTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;

	public TM4JTopicMapBuilder(TopicMapSchema topicMapSchema) {
		this.topicMapSchema = topicMapSchema;
	}

	public TopicMap getTopicMap() throws Exception{
		TopicMap tm = null;

		TopicMapProvider tmPrv = TopicMapProviderFactory.newInstance()
				.newTopicMapProvider(System.getProperties());

		tm = tmPrv.createTopicMap(tmPrv.getLocatorFactory().createLocator(
				"URI", "http://psi.topicmapslab.de/tmclschema"));

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
		Association association = tm.createAssociation(null);
		if (atc.getAssociationType()!=null)
			association.setType(getTopic(atc.getAssociationType(), tm));

		for (RoleTypeConstraints rtc : atc.getRoleTypeConstraints()) {
			Member member = createMember(rtc.getTopicType(), rtc.getType(),
					association);
			association.addMember(member);
		}

		return association;
	}

	private Member createMember(TopicType player, TopicType role,
			Association association) throws Exception {
		Member member = association.createMember(null);
		member.setRoleSpec(getTopic(role, association.getTopicMap()));
		member.setPlayers(new Topic[] { getTopic(player, association
				.getTopicMap()) });

		return member;
	}

	private Topic getTopic(TopicType topicType, TopicMap tm) throws Exception {
		Locator l = parseId(topicType.getId(), tm);
		Topic r = null;
		if (l ==null)
			r = tm.getTopicByID(topicType.getId());
		else {
			r = tm.getTopicBySubjectIndicator(l);
		}
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
			t = tm.createTopic(null);
			t.addSubjectIndicator(l);
		} else {
			t = tm.createTopic(topicType.getId());
		}

		for (TopicType tt : topicType.getIsa()) {
			Topic topic = getTopic(tt, tm);
			t.addType(topic);
		}
		BaseName name = t.createName(null);
		name.setData(topicType.getId());
		t.addName(name);
		
		for (NameTypeConstraint ntc : topicType.getNameContraints()) {
			t.addName(createBaseName(ntc, t));
		}
		
		for (OccurenceTypeConstraint otc : topicType.getOccurenceConstraints()) {
			t.addOccurrence(createOccurence(otc, t));
		}

		return t;
	}

	private BaseName createBaseName(NameTypeConstraint ntc, Topic topic)
			throws Exception {
		String id = ntc.getType().getId();
		BaseName name = topic.createName(id.substring(id.indexOf(':')+1));
		name.setData("");
		
		return name;
	}

	private Occurrence createOccurence(OccurenceTypeConstraint otc, Topic topic)
			throws Exception {
		Locator l = parseId(otc.getType().getId(), topic.getTopicMap());
		Occurrence occ = null;
		if (l==null)
			occ = topic.createOccurrence(otc.getType().getId());
		else {
			occ = topic.createOccurrence(null);
		}

		return occ;
	}
	
	private Locator parseId(String id, TopicMap map) throws Exception {
		int index = id.indexOf(':');
		if (index>-1) {
			String tmp = id.substring(0, index);
			for (MappingElement me : topicMapSchema.getMappings()) {
				if (me.getKey().equals(tmp)) {
					tmp = me.getValue()+"/"+id.substring(index+1);
					return map.getLocatorFactory().createLocator("URI", tmp);
				}
			}
			
		}
		
		return null;
	}
}
