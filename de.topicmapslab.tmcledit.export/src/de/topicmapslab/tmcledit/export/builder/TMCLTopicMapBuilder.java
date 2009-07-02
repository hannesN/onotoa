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

import java.util.HashMap;
import java.util.Map;

import org.tinytim.voc.TMCL;
import org.tinytim.voc.TMDM;
import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystemFactory;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TMCLTopicMapBuilder {
	private final TopicMapSchema topicMapSchema;

	private Map<Locator, Topic> topicTypeMap;
	private Map<String, String> prefixMap;

	private TopicMap topicMap;
	private Locator baseLocator;

	public TMCLTopicMapBuilder(TopicMapSchema topicMapSchema) {
		super();
		this.topicMapSchema = topicMapSchema;
	}

	public TopicMap createTopicMap() {
		try {

			topicMap = TopicMapSystemFactory.newInstance().newTopicMapSystem().createTopicMap(
			        "http://onotoa.topicmapslab.de");
			baseLocator = topicMap.createLocator("http://onotoa.topicmapslab.de");

			topicTypeMap = new HashMap<Locator, Topic>(topicMapSchema.getTopicTypes().size());

			// init prefixMap
			prefixMap = new HashMap<String, String>(topicMapSchema.getMappings().size());
			for (MappingElement me : topicMapSchema.getMappings()) {
				prefixMap.put(me.getKey(), me.getValue());
			}

			createTopicTypes();
			createAssociationConstraints();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return topicMap;
	}

	private void createTopicTypes() {
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			createTopic(tt);
		}
	}

	private void createAssociationConstraints() {
		for (AssociationTypeConstraint atc : topicMapSchema.getAssociationTypeConstraints()) {
			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				setRolePlayerConstraint(atc.getType(), rpc);
			}
			AssociationType at = (AssociationType) atc.getType();
			for (RoleConstraint rc : at.getRoles()) {
				setRoleConstraint(atc, rc);
				RoleType rt = (RoleType) rc.getType();
				for (OtherRolePlayerConstraint orpc : rt.getOtherRoles()) {
					setOtherRoleConstraint(at, rt, orpc);
				}
			}
			
		}
	}

	private void setOtherRoleConstraint(AssociationType at, RoleType rt, OtherRolePlayerConstraint orpc) {
		Topic constr = createConstraint(TMCL.OTHER_ROLE_CONSTRAINT);
		createConstrainedStatement(at, constr);
		createConstrainedRole(rt, constr);
		createConstrainedTopicType(createTopic(orpc.getPlayer()), constr);
		createOtherConstrainedRole(rt, constr);
		createOtherConstrainedTopicType(createTopic(orpc.getPlayer()), constr);
		
		
    }

	private void createOtherConstrainedTopicType(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(TMCL.OTHER_CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);
    }

	private void createOtherConstrainedRole(RoleType rt, Topic constr) {
	    Association ass = topicMap.createAssociation(createTopic(TMCL.OTHER_CONSTRAINED_ROLE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), createTopic(rt));
    }

	private void setRoleConstraint(AssociationTypeConstraint atc, RoleConstraint rc) {
		Topic constr = createConstraint(TMCL.ASSOCIATION_ROLE_CONSTRAINT);
		addCardinalityOccurrences(constr, atc.getCardMin(), atc.getCardMax());
		
		createConstrainedRole(rc.getType(), constr);
		createConstrainedStatement(atc.getType(), constr);
		
    }

	private void setRolePlayerConstraint(TopicType type, RolePlayerConstraint rpc) {
		Topic constr = createConstraint(TMCL.ROLE_PLAYER_CONSTRAINT);
		addCardinalityOccurrences(constr, rpc.getCardMin(), rpc.getCardMax());

		createConstrainedStatement(type, constr);
		createConstrainedTopicType(createTopic(rpc.getPlayer()), constr);
		createConstrainedRole(rpc.getRole().getType(), constr);
	}
	

	private void createConstrainedRole(TopicType rt, Topic constr) {
	    Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_ROLE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), createTopic(rt));
    }

	/**
	 * Creates a topic with the given subject identifier
	 * 
	 * @param loc
	 * @return
	 */
	private Topic createTopic(Locator loc) {
		return topicMap.createTopicBySubjectIdentifier(loc);
	}

	private Topic createTopic(TopicType type) {
		Topic t = null;

		Locator itemId = baseLocator.resolve("#" + type.hashCode());
		t = topicTypeMap.get(itemId);
		if (t != null)
			return t;

		t = topicMap.createTopicByItemIdentifier(itemId);
		topicTypeMap.put(itemId, t);
		t.createName(type.getName());

		for (String id : type.getIdentifiers()) {
			if (id.indexOf(':') != -1) {
				String iri = resolveIRI(id);
				if (iri != null)
					t.addSubjectIdentifier(topicMap.createLocator(iri));
			} else {
				t.addSubjectIdentifier(topicMap.createLocator(id));
			}
		}

		for (String id : type.getLocators()) {
			if (id.indexOf(':') != -1) {
				String iri = resolveIRI(id);
				if (iri != null)
					t.addSubjectLocator(topicMap.createLocator(iri));
			} else {
				t.addSubjectLocator(topicMap.createLocator(id));
			}
		}

		t.addType(getTopicTypes(type.getKind()));

		for (TopicType tt : type.getIsa()) {
			t.addType(createTopic(tt));
		}

		for (TopicType tt : type.getAko()) {
			setSuperType(t, createTopic(tt));
		}

		if (type.isAbstract())
			setAbstract(t);

		for (SubjectIdentifierConstraint sic : type.getSubjectIdentifierConstraints()) {
			setSubjectIdentifierConstraint(t, sic);
		}

		for (SubjectLocatorConstraint slc : type.getSubjectLocatorConstraint()) {
			setSubjectLocatorConstraint(t, slc);
		}

		for (NameTypeConstraint ntc : type.getNameContraints()) {
			setNameTypeConstraint(t, ntc);
		}

		for (OccurrenceTypeConstraint otc : type.getOccurrenceConstraints()) {
			setOccurrenceConstraint(t, otc);
		}

		return t;
	}

	private void setNameTypeConstraint(Topic t, NameTypeConstraint ntc) {
		NameType nt = (NameType) ntc.getType();

		if (!ntc.getRegexp().equals(".*"))
			setRegExpConstraint(nt, ntc.getRegexp());

		Topic constr = createConstraint(TMCL.TOPIC_NAME_CONSTRAINT);
		addCardinalityOccurrences(constr, ntc.getCardMin(), ntc.getCardMax());

		createConstrainedTopicType(t, constr);
		createConstrainedStatement(nt, constr);
	}

	private void createConstrainedStatement(TopicType tt, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), createTopic(tt));
	}

	private void createConstrainedTopicType(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);
	}

	private void setOccurrenceConstraint(Topic t, OccurrenceTypeConstraint otc) {
		OccurrenceType otype = (OccurrenceType) otc.getType();

		setOccurrenceDatatype(otype);
		if (otc.isUnique()) {
			setUnique(otype);
		}
		if (!otc.getRegexp().equals(".*"))
			setRegExpConstraint(otype, otc.getRegexp());

		Topic constr = createConstraint(TMCL.TOPIC_OCCURRENCE_CONSTRAINT);
		addCardinalityOccurrences(constr, otc.getCardMin(), otc.getCardMax());

		createConstrainedTopicType(t, constr);
		createConstrainedStatement(otype, constr);

	}

	private Topic createConstraint(Locator type) {
		Topic constr = topicMap.createTopic();

		constr.addType(createTopic(type));

		return constr;
	}

	private void setRegExpConstraint(TopicType type, String regexp) {
		Topic constr = createConstraint(TMCL.REGULAR_EXPRESSION_CONSTRAINT);
		constr.createOccurrence(createTopic(TMCL.REGEXP), regexp);

		createConstrainedStatement(type, constr);
	}

	private void setUnique(OccurrenceType ot) {
		Topic constr = createConstraint(TMCL.UNIQUE_VALUE_CONSTRAINT);
		Topic type = createTopic(ot);
		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), type);
	}

	private void setOccurrenceDatatype(OccurrenceType ot) {
		Topic constr = createConstraint(TMCL.OCCURRENCE_DATATYPE_CONSTRAINT);
		constr.createOccurrence(createTopic(TMCL.DATATYPE), ot.getDataType());

		createConstrainedStatement(ot, constr);
	}

	private void setAbstract(Topic t) {
		Topic abstrConst = createTopic(TMCL.ABSTRACT_TOPIC_TYPE_CONSTRAINT);

		Association ass = topicMap.createAssociation(topicMap
		        .createTopicBySubjectIdentifier(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), abstrConst);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);
	}

	private void setSubjectIdentifierConstraint(Topic t, SubjectIdentifierConstraint constraint) {
		Topic constr = createConstraint(TMCL.SUBJECT_IDENTIFIER_CONSTRAINT);

		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());
		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		createConstrainedTopicType(t, constr);

	}

	private void setSubjectLocatorConstraint(Topic t, SubjectLocatorConstraint constraint) {
		Topic constr = createConstraint(TMCL.SUBJECT_LOCATOR_CONSTRAINT);

		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());
		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		createConstrainedTopicType(t, constr);

	}

	private void addCardinalityOccurrences(Topic constr, String cardMin, String cardMax) {
		if (!cardMax.equals("0"))
			constr.createOccurrence(createTopic(TMCL.CARD_MIN), cardMin);
		if (!cardMax.equals("*"))
			constr.createOccurrence(createTopic(TMCL.CARD_MAX), cardMax);
	}

	private void setSuperType(Topic subtype, Topic supertype) {
		Association ass = topicMap.createAssociation(createTopic(TMDM.SUPERTYPE_SUBTYPE));
		ass.createRole(createTopic(TMDM.SUPERTYPE), supertype);
		ass.createRole(createTopic(TMDM.SUBTYPE), subtype);

	}

	private String resolveIRI(String id) {
		int index = id.indexOf(':');
		String prefix = id.substring(0, index);
		String value = prefixMap.get(prefix);
		if (value != null)
			return value + id.substring(index + 1);

		return null;
	}

	private Topic getTopicTypes(KindOfTopicType kind) {
		Locator loc = TMCL.TOPIC_TYPE;
		switch (kind) {
		case NO_TYPE:
			return null;
		case ASSOCIATION_TYPE:
			loc = TMCL.ASSOCIATION_TYPE;
			break;
		case ROLE_TYPE:
			loc = TMCL.ROLE_TYPE;
			break;
		case OCCURRENCE_TYPE:
			loc = TMCL.OCCURRENCE_TYPE;
			break;
		case NAME_TYPE:
			loc = TMCL.NAME_TYPE;
			break;
		case SCOPE_TYPE:
			loc = TMCL.SCOPE_TYPE;
			break;
		}
		return createTopic(loc);
	}

}
