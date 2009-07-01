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

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
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

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return topicMap;
	}

	private void createTopicTypes() {
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			getTopic(tt);
		}
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

	private Topic getTopic(TopicType type) {
		Topic t = null;

		Locator itemId = baseLocator.resolve("#" + type.hashCode());
		t = topicTypeMap.get(itemId);
		if (t != null)
			return t;

		t = topicMap.createTopicByItemIdentifier(itemId);

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
			t.addType(getTopic(tt));
		}

		for (TopicType tt : type.getAko()) {
			setSuperType(t, getTopic(tt));
		}

		if (type.isAbstract())
			setAbstract(t);

		for (SubjectIdentifierConstraint sic : type.getSubjectIdentifierConstraints()) {
			setSubjectIdentifierConstraint(t, sic);
		}
		
		for (SubjectLocatorConstraint slc : type.getSubjectLocatorConstraint()) {
			setSubjectLocatorConstraint(t, slc);
		}
		
		topicTypeMap.put(itemId, t);
		return t;
	}

	private void setAbstract(Topic t) {
		Topic abstrConst = topicMap.createTopicBySubjectIdentifier(TMCL.ABSTRACT_TOPIC_TYPE_CONSTRAINT);

		Association ass = topicMap.createAssociation(topicMap
		        .createTopicBySubjectIdentifier(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), abstrConst);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);
	}

	private void setSubjectIdentifierConstraint(Topic t, SubjectIdentifierConstraint constraint) {
		Topic constr = createTopic(TMCL.SUBJECT_IDENTIFIER_CONSTRAINT);

		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());
		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);

	}
	
	private void setSubjectLocatorConstraint(Topic t, SubjectLocatorConstraint constraint) {
		Topic constr = createTopic(TMCL.SUBJECT_LOCATOR_CONSTRAINT);

		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());
		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);

	}

	private void addCardinalityOccurrences(Topic constr, String cardMin, String cardMax) {
		constr.createOccurrence(createTopic(TMCL.CARD_MIN), cardMin);
		constr.createOccurrence(createTopic(TMCL.CARD_MAX), cardMax);
	}

	private void setSuperType(Topic subtype, Topic supertype) {
		Association ass = topicMap.createAssociation(topicMap.createTopicBySubjectIdentifier(TMDM.SUPERTYPE_SUBTYPE));
		ass.createRole(topicMap.createTopicBySubjectIdentifier(TMDM.SUPERTYPE), supertype);
		ass.createRole(topicMap.createTopicBySubjectIdentifier(TMDM.SUBTYPE), subtype);

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
		return topicMap.createTopicBySubjectIdentifier(loc);
	}

}
