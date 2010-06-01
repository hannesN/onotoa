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

import static de.topicmapslab.tmcledit.export.voc.ITMCLURIs.*;

import java.util.ArrayList;
import java.util.List;

import org.tinytim.voc.Namespace;
import org.tinytim.voc.TMCL;
import org.tinytim.voc.TMDM;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;

import de.topicmapslab.ctm.writer.core.CTMTopicMapWriter;
import de.topicmapslab.ctm.writer.templates.Template;
import de.topicmapslab.ctm.writer.templates.TemplateFactory;
import de.topicmapslab.ctm.writer.templates.entry.AssociationEntry;
import de.topicmapslab.ctm.writer.templates.entry.EntryFactory;
import de.topicmapslab.ctm.writer.templates.entry.IsInstanceOfEntry;
import de.topicmapslab.ctm.writer.templates.entry.RoleEntry;
import de.topicmapslab.ctm.writer.templates.entry.TopicEntry;
import de.topicmapslab.ctm.writer.templates.entry.param.IEntryParam;
import de.topicmapslab.ctm.writer.templates.entry.param.TopicTypeParam;
import de.topicmapslab.ctm.writer.templates.entry.param.VariableParam;
import de.topicmapslab.ctm.writer.templates.entry.param.WildcardParam;

/**
 * A class which creates {@link Template}s for the CTM Serializer.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class TMCLTemplateDefinitions {

	private List<Template> templates;
	private TopicMap topicMap;
	private final TemplateFactory templateFactory;

	private EntryFactory entryFactory;
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
	private Topic otherConstrainedRole;
	private Topic otherConstrainedTopicType;
	private Topic overlaps;
	private Topic belongsTo;

	// -- roles
	private Topic constraint;
	private Topic constrained;
	private Topic allows;
	private Topic allowed;

	// -- constrains
	// private Topic constraint; // -- not needed is super type
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

	public TMCLTemplateDefinitions(CTMTopicMapWriter writer, TopicMap topicMap) {
		this.topicMap = topicMap;
		this.templateFactory = new TemplateFactory(writer);
		this.entryFactory = this.templateFactory.getEntryFactory();
	}

	public List<Template> getTemplates() {
		if (templates == null)
	        try {
	            init();
            } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }

		return templates;
	}

	private void init() throws Exception {
		initTypes();
		
		templates = new ArrayList<Template>();

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
		topicType = createTopic(TOPIC_TYPE);
		nameType = createTopic(NAME_TYPE);
		associationType = createTopic(ASSOCIATION_TYPE);
		roleType = createTopic(ROLE_TYPE);
		occurrenceType = createTopic(OCCURRENCE_TYPE);

		cardMin = createTopic(CARD_MIN);
		cardMax = createTopic(CARD_MAX);
		regExp = createTopic(REGEXP);
		datatype = createTopic(DATATYPE);
		comment = createTopic(COMMENT);
		description = createTopic(DESCRIPTION);
		seeAlso = createTopic(SEE_ALSO);

		// roles
		constraint = createTopic(CONSTRAINT);
		constrained = createTopic(CONSTRAINED);
		allowed = createTopic(ALLOWED);
		allows = createTopic(ALLOWS);

		// associations
		constraintStatement = createTopic(CONSTRAINED_STATEMENT);
		constrainedTopicType = createTopic(CONSTRAINED_TOPIC_TYPE);
		constrainedRole = createTopic(CONSTRAINED_ROLE);
		otherConstrainedTopicType = createTopic(OTHER_CONSTRAINED_TOPIC_TYPE);
		otherConstrainedRole = createTopic(OTHER_CONSTRAINED_ROLE);
		allowedReifier = createTopic(ALLOWED_REIFIER);
		constrainedRole = createTopic(CONSTRAINED_ROLE);
		otherConstrainedRole = createTopic(OTHER_CONSTRAINED_ROLE);
		otherConstrainedTopicType = createTopic(OTHER_CONSTRAINED_TOPIC_TYPE);
		overlaps = createTopic(OVERLAP_DECLARATION);

		// constraint = createTopic(CONSTRAINT);
		abstractConstraint = createTopic(ABSTRACT_CONSTRAINT);
		subjectIdentifierConstraint = createTopic(SUBJECT_IDENTIFIER_CONSTRAINT);
		subjectLocatorConstraint = createTopic(SUBJECT_LOCATOR_CONSTRAINT);
		topicNameConstraint = createTopic(TOPIC_NAME_CONSTRAINT);
		topicOccurrenceConstraint = createTopic(TOPIC_OCCURRENCE_CONSTRAINT);
		topicRoleConstraint = createTopic(TOPIC_ROLE_CONSTRAINT);
		scopeConstraint = createTopic(SCOPE_CONSTRAINT);
		reifierConstraint = createTopic(REIFIER_CONSTRAINT);
		topicReifiesConstraint = createTopic(TOPIC_REIFIES_CONSTRAINT);
		associationRoleConstraint = createTopic(ASSOCIATION_ROLE_CONSTRAINT);
		roleCombinationConstraint = createTopic(ROLE_COMBINATION_CONSTRAINT);
		occurrenceDatatypeConstraint = createTopic(OCCURRENCE_DATATYPE_CONSTRAINT);
		uniqueValueConstraint = createTopic(UNIQUE_VALUE_CONSTRAINT);
		regularExpressionConstraint = createTopic(REGULAR_EXPRESSION_CONSTRAINT);
		overlapDeclaration = createTopic(OVERLAP_DECLARATION);
	}

	private void addOverlaps() throws Exception {
		Template t = templateFactory.newTemplate("overlaps");
		
		IEntryParam constrParam = entryFactory.newWildcardParam("c");
		TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
		
		t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(overlapDeclaration)));
		t.add(t1);
		
		RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
		RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt1"));
		AssociationEntry a1 = entryFactory.newAssociationEntry(overlaps, r1, r2);
		t.add(a1);
		
		r1 = entryFactory.newRoleEntry(constraint, constrParam);
		r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt2"));
		AssociationEntry a2 = entryFactory.newAssociationEntry(overlaps, r1, r2);
		t.add(a2);

		t.setSerialize(false);
		templates.add(t);
		
	}

	private void addHasSubjectIdentifier() {
		// TODO Auto-generated method stub

	}

	private void addHasSubjectLocator() {
		// TODO Auto-generated method stub

	}

	private void addHasName() {
		// TODO Auto-generated method stub

	}

	private void addHasOccurrence() {
		// TODO Auto-generated method stub

	}

	private void addPlaysRole() {
		// TODO Auto-generated method stub

	}

	private void addHasScope() {
		// TODO Auto-generated method stub

	}

	private void addMustHaveReifier() {
		// TODO Auto-generated method stub

	}

	private void addCannotHaveReifier() {
		// TODO Auto-generated method stub

	}

	private void addMayHaveReifier() {
		// TODO Auto-generated method stub

	}

	private void addMustReify() {
		// TODO Auto-generated method stub

	}

	private void addCannotReify() {
		// TODO Auto-generated method stub

	}

	private void addMayReify() {
		// TODO Auto-generated method stub

	}

	private void addHasRole() {
		// TODO Auto-generated method stub

	}

	private void addRoleCombination() {
		// TODO Auto-generated method stub

	}

	private void addHasDatatype() {
		// TODO Auto-generated method stub

	}

	private void addIsUnique() {
		// TODO Auto-generated method stub

	}

	private void addMatchesRegExp() {
		// TODO Auto-generated method stub

	}

	private void addBelongsTo() {
		// TODO Auto-generated method stub

	}

	private Topic createTopic(String si) {
		return topicMap.createTopicBySubjectIdentifier(topicMap.createLocator(si));
	}
}
