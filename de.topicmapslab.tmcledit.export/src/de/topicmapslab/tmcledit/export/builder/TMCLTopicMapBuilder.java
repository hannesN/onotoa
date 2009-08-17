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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.tinytim.voc.Namespace;
import org.tinytim.voc.TMCL;
import org.tinytim.voc.TMDM;
import org.tinytim.voc.XSD;
import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystemFactory;

import com.semagia.mio.rdf.sesame.aduna.collections.ArrayMap;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
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
	
	private boolean exportSchema = true;
	private Topic schema;

	private Map<TopicType,TopicType> overlapMap;

	public TMCLTopicMapBuilder(TopicMapSchema topicMapSchema, boolean exportSchema) {
		super();
		this.topicMapSchema = topicMapSchema;
		this.exportSchema = exportSchema;
	}
		
	public TopicMap createTopicMap() {
		try {

			String baseLoc = topicMapSchema.getBaseLocator();
			if ( (baseLoc==null) || (baseLoc.length()==0))
				baseLoc = "http://onotoa.topicmapslab.de";
							
			topicMap = TopicMapSystemFactory.newInstance().newTopicMapSystem().createTopicMap(
			        baseLoc);
			baseLocator = topicMap.createLocator(baseLoc);

			topicTypeMap = new HashMap<Locator, Topic>(topicMapSchema.getTopicTypes().size());

			// init prefixMap
			prefixMap = new HashMap<String, String>(topicMapSchema.getMappings().size());
			for (MappingElement me : topicMapSchema.getMappings()) {
				prefixMap.put(me.getKey(), me.getValue());
			}
			overlapMap = new ArrayMap<TopicType, TopicType>(10);
			
			if (exportSchema) {
				schema = topicMap.createTopic();
				addDocumentationOccurrences(schema, topicMapSchema);
				schema.addType(topicMap.createTopicBySubjectIdentifier(TMCL.SCHEMA));
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
			}
			
			for (RoleCombinationConstraint rcc : at.getRoleCombinations()) {
				setRoleCombinationConstraint(at, rcc);
			}
						
			setScopeConstraints(at);
		}
	}

	private void setRoleCombinationConstraint(AssociationType type, RoleCombinationConstraint rcc) {
		Topic constr = createConstraint(TMCL.ROLE_COMBINATION_CONSTRAINT);
		createConstrainedStatement(type, constr);
		createConstrainedRole(rcc.getRole(), constr);
		createConstrainedTopicType(createTopic(rcc.getPlayer()), constr);
		createOtherConstrainedRole(rcc.getOtherRole(), constr);
		createOtherConstrainedTopicType(createTopic(rcc.getOtherPlayer()), constr);
		
		setSchema(constr);
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
		addCardinalityOccurrences(constr, rc.getCardMin(), rc.getCardMax());

		createConstrainedRole(rc.getType(), constr);
		createConstrainedStatement(atc.getType(), constr);
		
		setSchema(constr);
	}

	private void setRolePlayerConstraint(TopicType type, RolePlayerConstraint rpc) {
		if (rpc.getPlayer()==null)
			return;
		Topic constr = createConstraint(TMCL.TOPIC_ROLE_CONSTRAINT);
		addCardinalityOccurrences(constr, rpc.getCardMin(), rpc.getCardMax());
		addDocumentationOccurrences(constr, rpc);
		createConstrainedStatement(type, constr);
		createConstrainedTopicType(createTopic(rpc.getPlayer()), constr);
		createConstrainedRole(rpc.getRole().getType(), constr);
		
		setSchema(constr);
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
		String url = (type.getName() + type.hashCode()).replaceAll(" ", "-");
		try {
	        url = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
        }
		Locator itemId = baseLocator.resolve("#"+url);
		t = topicTypeMap.get(itemId);
		if (t != null)
			return t;

		t = topicMap.createTopicByItemIdentifier(itemId);
		topicTypeMap.put(itemId, t);
		t.createName(type.getName());
		setSchema(t);

		// creating doc occs
		addDocumentationOccurrences(t, type);
		
		// setting identifiers
		for (String id : type.getIdentifiers()) {
			if (id.indexOf(':') != -1) { // TODO make it more robust
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

		// add types
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

		for (TopicType tt : type.getOverlap()) {
			setOverlapConstraint(type, tt);
		}
		
		if (type instanceof ReifiableTopicType) {
			setReifierConstraint((ReifiableTopicType) type);
		}

		return t;
	}

	private Topic createConstraint(Locator type) {
    	Topic constr = topicMap.createTopic();
    
    	constr.addType(createTopic(type));
    
    	return constr;
    }

	private void createConstrainedStatement(TopicType tt, Topic constr) {
    	Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_STATEMENT));
    	ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
    	ass.createRole(createTopic(TMCL.CONSTRAINED), createTopic(tt));
    }
	
	private void createConstrainedStatement(Topic t, Topic constr) {
    	Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_STATEMENT));
    	ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
    	ass.createRole(createTopic(TMCL.CONSTRAINED), t);
    }

	private void createConstrainedTopicType(Topic t, Topic constr) {
    	Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_TOPIC_TYPE));
    	ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
    	ass.createRole(createTopic(TMCL.CONSTRAINED), t);
    }

	private void createAllowesScope(TopicType tt, Topic constr) {
    	Association ass = topicMap.createAssociation(createTopic(TMCL.ALLOWED_SCOPE));
    	ass.createRole(createTopic(TMCL.ALLOWS), constr);
    	ass.createRole(createTopic(TMCL.ALLOWED), createTopic(tt));
    }

	private void createOverlaps(TopicType type, Topic constraint) {
    	Association ass = topicMap.createAssociation(createTopic(TMCL.OVERLAPS));
    	ass.createRole(createTopic(TMCL.ALLOWS), constraint);
    	ass.createRole(createTopic(TMCL.ALLOWED), createTopic(type));
    }

	private void addDocumentationOccurrences(Topic topic, TMCLConstruct construct) {
    	String tmp = construct.getComment();
    	if ((tmp!=null) && (tmp.length()>0))
    		topic.createOccurrence(createTopic(TMCL.COMMENT), tmp);
    	
    	tmp = construct.getSee_also();
    	if ((tmp!=null) && (tmp.length()>0))
    		topic.createOccurrence(createTopic(TMCL.SEE_ALSO), tmp);
    	
    	
    	tmp = construct.getDescription();
    	if ((tmp!=null) && (tmp.length()>0))
    		topic.createOccurrence(createTopic(TMCL.DESCRIPTION), tmp);
    	
    }

	private void addCardinalityOccurrences(Topic constr, String cardMin, String cardMax) {
    	// needed for templates
    	if (!cardMax.equals("0"))
    		constr.createOccurrence(createTopic(TMCL.CARD_MIN), cardMin, XSD.INTEGER);
    	if (!cardMax.equals("*"))
    		constr.createOccurrence(createTopic(TMCL.CARD_MAX), cardMax, XSD.INTEGER);
    }

	private void setOverlapConstraint(TopicType type, TopicType othertype) {
		if (type.equals(overlapMap.get(othertype)))
			return;
		
		overlapMap.put(type, othertype);
		
		Topic constr = createConstraint(TMCL.OVERLAP_DECLARATION);

		createOverlaps(othertype, constr);
		createOverlaps(type, constr);
		
		setSchema(constr);
	}

	private void setNameTypeConstraint(Topic t, NameTypeConstraint ntc) {
		NameType nt = (NameType) ntc.getType();
		Topic nameTopic = null;
		if (nt==null)
			nameTopic = createTopic(TMDM.TOPIC_NAME);
		else
			nameTopic = createTopic(nt);
		
		if (!nt.getRegExp().equals(".*"))
			setRegExpConstraint(nt, nt.getRegExp());

		Topic constr = createConstraint(TMCL.TOPIC_NAME_CONSTRAINT);
		addDocumentationOccurrences(constr, ntc);
		addCardinalityOccurrences(constr, ntc.getCardMin(), ntc.getCardMax());

		createConstrainedTopicType(t, constr);
		createConstrainedStatement(nameTopic, constr);

		setScopeConstraints(nt);
		
		setSchema(constr);
	}

	private void setOccurrenceConstraint(Topic t, OccurrenceTypeConstraint otc) {
		OccurrenceType otype = (OccurrenceType) otc.getType();
		Topic occType = null;
		if (otype!=null) {
			setOccurrenceDatatype(otype);
			occType = createTopic(otype);
		} else {
			occType = createTopic(topicMap.createLocator(Namespace.TMDM_MODEL+"subject"));
		}
		
		
		if (otc.isUnique()) {
			setUnique(otype);
		}
		if (!otype.getRegExp().equals(".*"))
			setRegExpConstraint(otype, otype.getRegExp());
		Topic constr = createConstraint(TMCL.TOPIC_OCCURRENCE_CONSTRAINT);
		addDocumentationOccurrences(constr, otc);
		addCardinalityOccurrences(constr, otc.getCardMin(), otc.getCardMax());
		setSchema(constr);
		
		createConstrainedTopicType(t, constr);
		createConstrainedStatement(occType, constr);
		
		setScopeConstraints(otype);
	}

	private void setScopeConstraints(ScopedTopicType type) {
		if (type==null)
			return;
		for (ScopeConstraint sc : type.getScope()) {
			Topic constr = createConstraint(TMCL.SCOPE_CONSTRAINT);
			addCardinalityOccurrences(constr, sc.getCardMin(), sc.getCardMax());
			addDocumentationOccurrences(constr, sc);
			createConstrainedStatement(type, constr);
			createAllowesScope(sc.getType(), constr);
			
			setSchema(constr);
		}
	    
    }

	private void setReifierConstraint(ReifiableTopicType rft) {
		ReifierConstraint constraint = rft.getReifierConstraint();
		if (constraint==null)
			return;
		Topic constr = createConstraint(TMCL.REIFIER_CONSTRAINT);
		addDocumentationOccurrences(constr, constraint);
		constr.createOccurrence(createTopic(TMCL.CARD_MIN), constraint.getCardMin(), XSD.INTEGER);
		constr.createOccurrence(createTopic(TMCL.CARD_MAX), constraint.getCardMax(), XSD.INTEGER);
		createConstrainedStatement(rft, constr);

		Association ass = topicMap.createAssociation(createTopic(TMCL.ALLOWED_REIFIER));
		ass.createRole(createTopic(TMCL.ALLOWS), constr);
		Topic t = null;
		if ((constraint.getCardMax().equals("0")) || (constraint.getType() == null))
			t = createTopic(topicMap.createLocator(Namespace.TMDM_MODEL + "subject"));
		else
			t = createTopic(constraint.getType());
		ass.createRole(createTopic(TMCL.ALLOWED), t);
		
		setSchema(constr);

	}

	private void setRegExpConstraint(TopicType type, String regexp) {
		Topic constr = createConstraint(TMCL.REGULAR_EXPRESSION_CONSTRAINT);
		constr.createOccurrence(createTopic(TMCL.REGEXP), regexp);
		createConstrainedStatement(type, constr);
		
		setSchema(constr);
	}

	private void setUnique(OccurrenceType ot) {
		Topic constr = createConstraint(TMCL.UNIQUE_VALUE_CONSTRAINT);
		Topic type = createTopic(ot);
		Association ass = topicMap.createAssociation(createTopic(TMCL.CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(TMCL.CONSTRAINS), constr);
		ass.createRole(createTopic(TMCL.CONSTRAINED), type);
		
		setSchema(constr);
	}

	private void setOccurrenceDatatype(OccurrenceType ot) {
		Topic constr = createConstraint(TMCL.OCCURRENCE_DATATYPE_CONSTRAINT);
		constr.createOccurrence(createTopic(TMCL.DATATYPE), ot.getDataType());

		createConstrainedStatement(ot, constr);
		
		setSchema(constr);
	}

	private void setSchema(Topic construct) {
    	if (!exportSchema)
    		return;
    	Association ass = topicMap.createAssociation(createTopic(TMCL.BELONGS_TO_SCHEMA));
    	ass.createRole(createTopic(TMCL.CONTAINER), schema);
    	ass.createRole(createTopic(TMCL.CONTAINEE), construct);
    }

	private void setAbstract(Topic t) {
		Topic abstrConst = createConstraint(TMCL.ABSTRACT_CONSTRAINT);
		Association ass = topicMap.createAssociation(topicMap
		        .createTopicBySubjectIdentifier(TMCL.CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(TMCL.CONSTRAINS), abstrConst);
		ass.createRole(createTopic(TMCL.CONSTRAINED), t);
		
		setSchema(abstrConst);
	}

	private void setSubjectIdentifierConstraint(Topic t, SubjectIdentifierConstraint constraint) {
		Topic constr = createConstraint(TMCL.SUBJECT_IDENTIFIER_CONSTRAINT);
		
		addDocumentationOccurrences(constr, constraint);
		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());

		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		createConstrainedTopicType(t, constr);

		setSchema(constr);
	}

	private void setSubjectLocatorConstraint(Topic t, SubjectLocatorConstraint constraint) {
		Topic constr = createConstraint(TMCL.SUBJECT_LOCATOR_CONSTRAINT);
		addDocumentationOccurrences(constr, constraint);
		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());
		
		if ((constraint.getRegexp() != null) && (!".*".equals(constraint.getRegexp())))
			constr.createOccurrence(createTopic(TMCL.REGEXP), constraint.getRegexp());

		createConstrainedTopicType(t, constr);
		
		setSchema(constr);

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
		if (value != null) {
			if (value.endsWith("/"))
				return value + id.substring(index + 1);
			else
				return value + "/" + id.substring(index + 1);
		}

		return id;
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
		}
		return createTopic(loc);
	}

	
	
}
