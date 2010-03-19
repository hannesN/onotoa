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

import static org.tinytim.voc.TMCL.ABSTRACT_CONSTRAINT;
import static org.tinytim.voc.TMCL.ALLOWED;
import static org.tinytim.voc.TMCL.ALLOWED_REIFIER;
import static org.tinytim.voc.TMCL.ALLOWED_SCOPE;
import static org.tinytim.voc.TMCL.ALLOWS;
import static org.tinytim.voc.TMCL.ASSOCIATION_ROLE_CONSTRAINT;
import static org.tinytim.voc.TMCL.ASSOCIATION_TYPE;
import static org.tinytim.voc.TMCL.CARD_MAX;
import static org.tinytim.voc.TMCL.CARD_MIN;
import static org.tinytim.voc.TMCL.COMMENT;
import static org.tinytim.voc.TMCL.CONSTRAINED;
import static org.tinytim.voc.TMCL.CONSTRAINED_ROLE;
import static org.tinytim.voc.TMCL.CONSTRAINED_STATEMENT;
import static org.tinytim.voc.TMCL.CONSTRAINED_TOPIC_TYPE;
import static org.tinytim.voc.TMCL.CONSTRAINS;
import static org.tinytim.voc.TMCL.DATATYPE;
import static org.tinytim.voc.TMCL.DESCRIPTION;
import static org.tinytim.voc.TMCL.NAME_TYPE;
import static org.tinytim.voc.TMCL.OCCURRENCE_DATATYPE_CONSTRAINT;
import static org.tinytim.voc.TMCL.OCCURRENCE_TYPE;
import static org.tinytim.voc.TMCL.OTHER_CONSTRAINED_ROLE;
import static org.tinytim.voc.TMCL.OTHER_CONSTRAINED_TOPIC_TYPE;
import static org.tinytim.voc.TMCL.OVERLAPS;
import static org.tinytim.voc.TMCL.OVERLAP_DECLARATION;
import static org.tinytim.voc.TMCL.REGEXP;
import static org.tinytim.voc.TMCL.REGULAR_EXPRESSION_CONSTRAINT;
import static org.tinytim.voc.TMCL.REIFIER_CONSTRAINT;
import static org.tinytim.voc.TMCL.ROLE_COMBINATION_CONSTRAINT;
import static org.tinytim.voc.TMCL.ROLE_TYPE;
import static org.tinytim.voc.TMCL.SCOPE_CONSTRAINT;
import static org.tinytim.voc.TMCL.SCOPE_TYPE;
import static org.tinytim.voc.TMCL.SEE_ALSO;
import static org.tinytim.voc.TMCL.SUBJECT_IDENTIFIER_CONSTRAINT;
import static org.tinytim.voc.TMCL.SUBJECT_LOCATOR_CONSTRAINT;
import static org.tinytim.voc.TMCL.TOPIC_NAME_CONSTRAINT;
import static org.tinytim.voc.TMCL.TOPIC_OCCURRENCE_CONSTRAINT;
import static org.tinytim.voc.TMCL.TOPIC_REIFIES_CONSTRAINT;
import static org.tinytim.voc.TMCL.TOPIC_ROLE_CONSTRAINT;
import static org.tinytim.voc.TMCL.TOPIC_TYPE;
import static org.tinytim.voc.TMCL.UNIQUE_VALUE_CONSTRAINT;

import java.util.List;

import org.tinytim.voc.Namespace;
import org.tinytim.voc.TMCL;
import org.tinytim.voc.TMDM;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;

import de.topicmapslab.ctm.writer.templates.Template;
import de.topicmapslab.ctm.writer.templates.entry.IsInstanceOfEntry;

/**
 * A class which creates {@link Template}s for the CTM Serializer.
 * 
 * @author Hannes Niederhausen
 *
 */
public class TMCLTemplateDefinitions {

	private  List<Template> templates;
	private TopicMap topicMap;
	
	// -- topic types
	private Topic topicType;
	private Topic associationType;
	private Topic roleType;
	private Topic occurrenceType;
	private Topic nameType;
	private Topic scopeType;
	
	private Topic topicMapSchema;

	// -- associations
	private Topic constraintStatement;
	private Topic constrainedTopicType;
	private Topic constrainedRole;
	private Topic allowedReifier;
	private Topic allowedScope;
	private Topic otherConstrainedRole;
	private Topic otherConstrainedTopicType;
	private Topic overlaps;
	private Topic belongsTo;

	// -- roles
	private Topic constrains;
	private Topic constrained;
	private Topic allows;
	private Topic allowed;
	

	// -- constrains
	//private Topic constraint; // -- not needed is super type
	private Topic abstractConstraint;
	private Topic subjectIdentifierConstraint;
	private Topic subjectLocatorConstraint;
	private Topic topicNameConstraint;
	private Topic topicOccurrenceConstraint;
	private Topic topicRoleConstraint;
	private Topic scopeConstraint;
	private Topic reifierConstraint;
	private Topic topicReifiesConstraint;
	private Topic associationRoleConstraint;
	private Topic roleCombinationConstraint;
	private Topic occurrenceDatatypeConstraint;
	private Topic uniqueValueConstraint;
	private Topic regularExpressionConstraint;
	private Topic overlapDeclaration;
	
	// -- occurrence types
	private Topic datatype;
	private Topic cardMin;
	private Topic cardMax;
	private Topic regExp;
	private Topic comment;
	private Topic seeAlso;
	private Topic description;

	
	// -- tmdm
	private Topic topicName;
	private Topic subject;
	
	public TMCLTemplateDefinitions(TopicMap topicMap) {
		this.topicMap = topicMap;
    }
	
	public  List<Template> getTemplates() {
		if (templates==null)
			init();
		
		return templates;
    }

	private  void init() {
		initTypes();
		
		addOverlaps();
		addHasSubjectIdentifier();
		addHasSubjectLocator();
		addHasName();
		addHasOccurrence();
		addPlaysRole();
		addHasScope();
		addMustHaveReifier();
		addCannotHaveReifier();
		addMayHaveReifier();
		addMustReify();
		addCannotReify();
		addMayReify();
		addHasRole();
		addRoleCombination();
		addHasDatatype();
		addIsUnique();
		addMatchesRegExp();
		addBelongsTo();
	}
	
	private void initTypes() {

		// removing TMDM types from cache
		topicMap.createTopicBySubjectIdentifier(TMDM.TYPE);
		topicMap.createTopicBySubjectIdentifier(TMDM.INSTANCE);
		topicMap.createTopicBySubjectIdentifier(TMDM.TYPE_INSTANCE);
		topicMap.createTopicBySubjectIdentifier(TMDM.SUPERTYPE);
		topicMap.createTopicBySubjectIdentifier(TMDM.SUBTYPE);
		topicMap.createTopicBySubjectIdentifier(TMDM.SUPERTYPE_SUBTYPE);
		// removing TMCL roles from cache
		topicMap.createTopicBySubjectIdentifier(TMCL.CONTAINEE);
		topicMap.createTopicBySubjectIdentifier(TMCL.CONTAINER);
		
		// initialize tmdm topics
		topicName = topicMap.createTopicBySubjectIdentifier(TMDM.TOPIC_NAME);
		subject = topicMap.createTopicBySubjectIdentifier(topicMap.createLocator(Namespace.TMDM_MODEL + "subject"));

		topicMapSchema = topicMap.createTopicBySubjectIdentifier(TMCL.SCHEMA);
		belongsTo = topicMap.createTopicBySubjectIdentifier(TMCL.BELONGS_TO_SCHEMA);

		// init topic types
		topicType = topicMap.createTopicBySubjectIdentifier(TOPIC_TYPE);
		nameType = topicMap.createTopicBySubjectIdentifier(NAME_TYPE);
		associationType = topicMap.createTopicBySubjectIdentifier(ASSOCIATION_TYPE);
		roleType = topicMap.createTopicBySubjectIdentifier(ROLE_TYPE);
		occurrenceType = topicMap.createTopicBySubjectIdentifier(OCCURRENCE_TYPE);

		cardMin = topicMap.createTopicBySubjectIdentifier(CARD_MIN);
		cardMax = topicMap.createTopicBySubjectIdentifier(CARD_MAX);
		regExp = topicMap.createTopicBySubjectIdentifier(REGEXP);
		datatype = topicMap.createTopicBySubjectIdentifier(DATATYPE);
		comment = topicMap.createTopicBySubjectIdentifier(COMMENT);
		description = topicMap.createTopicBySubjectIdentifier(DESCRIPTION);
		seeAlso = topicMap.createTopicBySubjectIdentifier(SEE_ALSO);

		// roles
		constrains = topicMap.createTopicBySubjectIdentifier(CONSTRAINS);
		constrained = topicMap.createTopicBySubjectIdentifier(CONSTRAINED);
		allowed = topicMap.createTopicBySubjectIdentifier(ALLOWED);
		allows = topicMap.createTopicBySubjectIdentifier(ALLOWS);

		// associations
		constraintStatement = topicMap.createTopicBySubjectIdentifier(CONSTRAINED_STATEMENT);
		constrainedTopicType = topicMap.createTopicBySubjectIdentifier(CONSTRAINED_TOPIC_TYPE);
		constrainedRole = topicMap.createTopicBySubjectIdentifier(CONSTRAINED_ROLE);
		otherConstrainedTopicType = topicMap.createTopicBySubjectIdentifier(OTHER_CONSTRAINED_TOPIC_TYPE);
		otherConstrainedRole = topicMap.createTopicBySubjectIdentifier(OTHER_CONSTRAINED_ROLE);
		allowedReifier = topicMap.createTopicBySubjectIdentifier(ALLOWED_REIFIER);
		allowedScope = topicMap.createTopicBySubjectIdentifier(ALLOWED_SCOPE);
		constrainedRole = topicMap.createTopicBySubjectIdentifier(CONSTRAINED_ROLE);
		otherConstrainedRole = topicMap.createTopicBySubjectIdentifier(OTHER_CONSTRAINED_ROLE);
		otherConstrainedTopicType = topicMap.createTopicBySubjectIdentifier(OTHER_CONSTRAINED_TOPIC_TYPE);
		overlaps = topicMap.createTopicBySubjectIdentifier(OVERLAP_DECLARATION);

//		constraint = topicMap.createTopicBySubjectIdentifier(CONSTRAINT);
		abstractConstraint = topicMap.createTopicBySubjectIdentifier(ABSTRACT_CONSTRAINT);
		subjectIdentifierConstraint = topicMap.createTopicBySubjectIdentifier(SUBJECT_IDENTIFIER_CONSTRAINT);
		subjectLocatorConstraint = topicMap.createTopicBySubjectIdentifier(SUBJECT_LOCATOR_CONSTRAINT);
		topicNameConstraint = topicMap.createTopicBySubjectIdentifier(TOPIC_NAME_CONSTRAINT);
		topicOccurrenceConstraint = topicMap.createTopicBySubjectIdentifier(TOPIC_OCCURRENCE_CONSTRAINT);
		topicRoleConstraint = topicMap.createTopicBySubjectIdentifier(TOPIC_ROLE_CONSTRAINT);
		scopeConstraint = topicMap.createTopicBySubjectIdentifier(SCOPE_CONSTRAINT);
		reifierConstraint = topicMap.createTopicBySubjectIdentifier(REIFIER_CONSTRAINT);
		topicReifiesConstraint = topicMap.createTopicBySubjectIdentifier(TOPIC_REIFIES_CONSTRAINT);
		associationRoleConstraint = topicMap.createTopicBySubjectIdentifier(ASSOCIATION_ROLE_CONSTRAINT);
		roleCombinationConstraint = topicMap.createTopicBySubjectIdentifier(ROLE_COMBINATION_CONSTRAINT);
		occurrenceDatatypeConstraint = topicMap.createTopicBySubjectIdentifier(OCCURRENCE_DATATYPE_CONSTRAINT);
		uniqueValueConstraint = topicMap.createTopicBySubjectIdentifier(UNIQUE_VALUE_CONSTRAINT);
		regularExpressionConstraint = topicMap.createTopicBySubjectIdentifier(REGULAR_EXPRESSION_CONSTRAINT);
		overlapDeclaration = topicMap.createTopicBySubjectIdentifier(OVERLAP_DECLARATION);
	}

	private  void addOverlaps() {
		Template t = new Template("overlaps");
		
		
//		IsInstanceOfEntry e = new IsInstanceOfEntry(properties, ctmIdentity, type)
		
		
//		?c isa tmcl:overlap-declaration.
//		tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt1)
//		tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt2)
    }

	private  void addHasSubjectIdentifier() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasSubjectLocator() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasName() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasOccurrence() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addPlaysRole() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasScope() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addMustHaveReifier() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addCannotHaveReifier() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addMayHaveReifier() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addMustReify() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addCannotReify() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addMayReify() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasRole() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addRoleCombination() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addHasDatatype() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addIsUnique() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addMatchesRegExp() {
	    // TODO Auto-generated method stub
	    
    }

	private  void addBelongsTo() {
	    // TODO Auto-generated method stub
	    
    }
	
	
	
	
}
