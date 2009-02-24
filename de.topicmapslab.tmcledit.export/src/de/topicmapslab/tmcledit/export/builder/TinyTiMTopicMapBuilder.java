/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.export.builder;


import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TinyTiMTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;
	
	private boolean createConstraintInfos;
	
	private Topic scopeType;
	private Topic nameType;
	private Topic occurrenceType;
	private Topic associationType;
	private Topic roleType;
	private Topic topicType;
	private Topic superTypeRole;
	private Topic subTypeRole;
	private Topic supertype_subType_assTopic;
	
	private Topic infoScope;
	private Topic occurrenceDatatype;
	private Topic scopeConstraintType;
	private Topic roleConstraintType;
	private Topic rolePlayerConstraintType;
	private Topic nameConstraintType;
	private Topic occurrenceConstraintType;
	private Topic locatorConstraintType;
	private Topic identifierConstraintType;
	
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
			Locator l = tm.createLocator("#info_scope");
			infoScope = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_occurrence_datatype_info");
			occurrenceDatatype = tm.createTopicByItemIdentifier(l);
		
			l = tm.createLocator("#constraint_scope_info");
			scopeConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_role_info");
			roleConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_role_player_info");
			roleConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_name_info");
			nameConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_occurrence_info");
			occurrenceConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_subject_locator_info");
			locatorConstraintType = tm.createTopicByItemIdentifier(l);
			
			l = tm.createLocator("#constraint_subject_identifier_info");
			identifierConstraintType = tm.createTopicByItemIdentifier(l);
			
		}
		
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
		
		l = tm.createLocator("http://psi.topicmaps.org/tmcl/occurrencetype");
		occurrenceType = tm.createTopicBySubjectIdentifier(l);
		occurrenceType.addType(topicType);
		occurrenceType.createName("occurrence type");
		
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
			AssociationType type = (AssociationType) asc.getType();
			l = tm.createLocator("#topic"+type.hashCode());
			Topic topic = tm.createTopicByItemIdentifier(l); // topic should already exists therefor no new creation
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("Associations of this type have the following players: ");
			
			for (RolePlayerConstraint rpc : asc.getPlayerConstraints()) {
				RoleConstraint rc = rpc.getRole();
				buffer.setLength(0);
				buffer.append("Player: ");
				buffer.append(rpc.getPlayer().getName());
				buffer.append(" Role:");
				buffer.append(rc.getType().getName());
				buffer.append(" [");
				buffer.append(rc.getCardMin());
				buffer.append("..");
				buffer.append(rc.getCardMax());
				buffer.append("]");
				
				topic.createOccurrence(rolePlayerConstraintType, buffer.toString(), infoScope);
			}
						
			
		}
	}
	/*
	private Topic getConstraintInfoTopic(Object constraint, TopicMap tm) {
		Locator l = null;
		if (constraint instanceof AssociationTypeConstraint) {
			l = tm.createLocator("#association_constraint_info");
		} else if (constraint instanceof occurrenceTypeConstraint) {
			l = tm.createLocator("#occurrence_constraint_info");
		} else if (constraint instanceof NameTypeConstraint) {
			l = tm.createLocator("#name_constraint_info");
		} else if (constraint  instanceof SubjectIdentifierConstraint) {
			l = tm.createLocator("#subject_identifier_constraint_info");
		} else if (constraint instanceof SubjectLocatorConstraint) {
			l = tm.createLocator("#subject_identifier_constraint_info");
		} else if (constraint instanceof RoleConstraint) {
			l = tm.createLocator("#role_constraint_info");
		}
		
		return tm.createTopicByItemIdentifier(l);
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
			createScopeInfo(t, (AssociationType) topicType);
			addAssociationInfos(t, (AssociationType) topicType);
			break;
		case OCCURRENCE_TYPE:
			t.addType(occurrenceType);
			addoccurrenceInfos(t, topicType);
			break;
		case NAME_TYPE:
			t.addType(nameType);
			createScopeInfo(t, (NameType) topicType);
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
			
			for (OccurrenceTypeConstraint otc : topicType.getOccurrenceConstraints()) {
				createConstraints(otc, t);
			}
			
			for (SubjectLocatorConstraint slc : topicType.getSubjectLocatorConstraint()) {
				createLocatorConstraint(slc, t);
			}
			
			for (SubjectIdentifierConstraint sic : topicType.getSubjectIdentifierConstraints()) {
				createIdentifierConstraint(sic, t);
			}
		}

		return t;
	}

	private void createLocatorConstraint(SubjectLocatorConstraint slc,
			Topic t) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\"");
		buffer.append(slc.getRegexp());
		buffer.append("\" [");
		buffer.append(slc.getCardMin());
		buffer.append("..");
		buffer.append(slc.getCardMax());
		buffer.append("]");
		 
		t.createOccurrence(locatorConstraintType, buffer.toString(), infoScope);
	}
	
	private void createIdentifierConstraint(SubjectIdentifierConstraint sic,
			Topic t) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\"");
		buffer.append(sic.getRegexp());
		buffer.append("\" [");
		buffer.append(sic.getCardMin());
		buffer.append("..");
		buffer.append(sic.getCardMax());
		buffer.append("]");
		 
		t.createOccurrence(identifierConstraintType, buffer.toString(), infoScope);
	}

	private void addAssociationInfos(Topic t, AssociationType topicType) {
		StringBuffer buffer = new StringBuffer();
		for (RoleConstraint rc : topicType.getRoles()) {
			buffer.setLength(0);
			buffer.append(rc.getType().getName());
			buffer.append(" [");
			buffer.append(rc.getCardMin());
			buffer.append("..");
			buffer.append(rc.getCardMax());
			buffer.append("]");
			t.createOccurrence(roleConstraintType, buffer.toString(), infoScope);
		}
	}

	private void addoccurrenceInfos(Topic t, TopicType topicType) {
		if (!createConstraintInfos)
			return;
		
		OccurrenceType ot = (OccurrenceType) topicType;
		
		t.createOccurrence(occurrenceDatatype, ot.getDataType(), infoScope);
		createScopeInfo(t, ot);
	}

	private void createScopeInfo(Topic t, ScopedTopicType st) {
		for (ScopeConstraint sc : st.getScope()) {
			addScopeInfo(t, sc);
		}
	}

	private void addScopeInfo(Topic t, ScopeConstraint sc) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(sc.getType().getName());
		buffer.append(" [");
		buffer.append(sc.getCardMin());
		buffer.append("..");
		buffer.append(sc.getCardMax());
		buffer.append("]");
		
		t.createOccurrence(scopeConstraintType, buffer.toString(), infoScope);
	}

	private void createConstraints(AbstractTypedCardinalityConstraint tc, Topic t) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("type: ");
		buffer.append(tc.getType().getName());
		buffer.append(" [");
		buffer.append(tc.getCardMin());
		buffer.append("..");
		buffer.append("] ");

		if (tc instanceof NameTypeConstraint)
			t.createOccurrence(nameConstraintType, buffer.toString(), infoScope);
		else if (tc instanceof OccurrenceTypeConstraint)
			t.createOccurrence(occurrenceConstraintType, buffer.toString(), infoScope);
		
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
