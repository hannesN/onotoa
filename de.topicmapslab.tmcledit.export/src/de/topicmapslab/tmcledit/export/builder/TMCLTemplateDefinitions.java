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

import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ABSTRACT_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWED;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWED_REIFIER;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ASSOCIATION_ROLE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ASSOCIATION_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.BELONGS_TO;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CARD_MAX;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CARD_MIN;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.COMMENT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_ROLE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_STATEMENT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_TOPIC_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.DATATYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.DESCRIPTION;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ITEM_IDENTIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.NAME_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OCCURRENCE_DATATYPE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OCCURRENCE_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OTHER_CONSTRAINED_ROLE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OTHER_CONSTRAINED_TOPIC_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OVERLAP_DECLARATION;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REGEXP;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REGULAR_EXPRESSION_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ROLE_COMBINATION_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ROLE_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.SCOPE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.SEE_ALSO;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.SUBJECT_IDENTIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.SUBJECT_LOCATOR_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.TOPIC_NAME_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.TOPIC_OCCURRENCE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.TOPIC_REIFIES_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.TOPIC_ROLE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.TOPIC_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.UNIQUE_VALUE_CONSTRAINT;

import java.util.ArrayList;
import java.util.List;

import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;

import de.topicmapslab.ctm.writer.core.CTMTopicMapWriter;
import de.topicmapslab.ctm.writer.templates.Template;
import de.topicmapslab.ctm.writer.templates.TemplateFactory;
import de.topicmapslab.ctm.writer.templates.entry.AssociationEntry;
import de.topicmapslab.ctm.writer.templates.entry.EntryFactory;
import de.topicmapslab.ctm.writer.templates.entry.OccurrenceEntry;
import de.topicmapslab.ctm.writer.templates.entry.RoleEntry;
import de.topicmapslab.ctm.writer.templates.entry.TopicEntry;
import de.topicmapslab.ctm.writer.templates.entry.param.IEntryParam;
import de.topicmapslab.tmcledit.export.builder.scanner.CannotReifyScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasItemIdentifierScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasNameScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasOccurrenceScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasRoleScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasScopeScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasSubjectIdentifierScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.HasSubjectLocatorScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.IsAbstractScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.IsUniqueScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.MayHaveReifierScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.MayReifyScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.MustHaveReifierScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.MustReifyScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.OccurrenceDatatypeScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.OverlapScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.PlaysRoleScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.RegExpConstraintScanner;
import de.topicmapslab.tmcledit.export.builder.scanner.RoleCombinationScanner;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMDM;

/**
 * A class which creates {@link Template}s for the CTM Serializer.
 * 
 * @author Lorenz Bühmann
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
    private Topic constrainedScope;
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
    private Topic container;
    private Topic containee;

    // -- constrains
    // private Topic constraint; // -- not needed is super type
    private Topic abstractConstraint;
    private Topic subjectIdentifierConstraint;
    private Topic itemIdentifierConstraint;
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
                addHasItemIdentifier();
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
                addIsAbstract();
                addMatchesRegExp();
                addBelongsTo();
        }

        private void initTypes() {

            // removing TMDM types from cache
            createTopic(TMDM.TYPE);
            createTopic(TMDM.INSTANCE);
            createTopic(TMDM.TYPE_INSTANCE);
            createTopic(TMDM.SUPERTYPE);
            createTopic(TMDM.SUBTYPE);
            createTopic(TMDM.SUPERTYPE_SUBTYPE);
            // removing TMCL roles from cache
            createTopic(TMCL.CONTAINEE);
            createTopic(TMCL.CONTAINER);

            // initialize tmdm topics
            topicName = createTopic(TMDM.TOPIC_NAME);
            subject = createTopic(TMDM.SUBJECT + "subject");

            topicMapSchema = createTopic(TMCL.SCHEMA);
            belongsTo = createTopic(BELONGS_TO);


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
            container = createTopic(TMCL.CONTAINER);
            containee = createTopic(TMCL.CONTAINEE);

            // associations
            constraintStatement = createTopic(CONSTRAINED_STATEMENT);
            constrainedTopicType = createTopic(CONSTRAINED_TOPIC_TYPE);
            constrainedRole = createTopic(CONSTRAINED_ROLE);
            constrainedScope = createTopic(TMCL.CONSTRAINED_SCOPE);
            otherConstrainedTopicType = createTopic(OTHER_CONSTRAINED_TOPIC_TYPE);
            otherConstrainedRole = createTopic(OTHER_CONSTRAINED_ROLE);
            allowedReifier = createTopic(ALLOWED_REIFIER);
            constrainedRole = createTopic(CONSTRAINED_ROLE);
            otherConstrainedRole = createTopic(OTHER_CONSTRAINED_ROLE);
            otherConstrainedTopicType = createTopic(OTHER_CONSTRAINED_TOPIC_TYPE);
            overlaps = createTopic(OVERLAP_DECLARATION);

            // constraint = createTopic(CONSTRAINT);
            abstractConstraint = createTopic(ABSTRACT_CONSTRAINT);
            itemIdentifierConstraint = createTopic(ITEM_IDENTIFIER_CONSTRAINT);
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
        
        private Topic createTopic(String si) {
            return topicMap.createTopicBySubjectIdentifier(topicMap.createLocator(si));
        }



        /*
         	def overlaps($tt1, $tt2)
  				?c isa tmcl:overlap-declaration.
  				tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt1)
  				tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt2)
			end

         */
        private void addOverlaps() throws Exception {
                Template t = templateFactory.newTemplate("overlaps");
               
                IEntryParam constrParam = entryFactory.newWildcardParam("c");
                TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
               
                t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(overlapDeclaration)));
                t.add(t1);
               
                RoleEntry r1 = entryFactory.newRoleEntry(allows, constrParam);
                RoleEntry r2 = entryFactory.newRoleEntry(allowed, entryFactory.newVariableParam("tt1"));
                AssociationEntry a1 = entryFactory.newAssociationEntry(overlaps, r1, r2);
                t.add(a1);
               
                r1 = entryFactory.newRoleEntry(allows, constrParam);
                r2 = entryFactory.newRoleEntry(allowed, entryFactory.newVariableParam("tt2"));
                AssociationEntry a2 = entryFactory.newAssociationEntry(overlaps, r1, r2);
                t.add(a2);

                t.setScanner(new OverlapScanner());
                templates.add(t);
               
        }

        /*
         * def has-subject-identifier($tt, $min, $max, $regexp)
			  ?c isa tmcl:subject-identifier-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max;
			    tmcl:regexp: $regexp.
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)
			end

         */
        private void addHasSubjectIdentifier() {
        	Template t = templateFactory.newTemplate("has-subject-identifier");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(subjectIdentifierConstraint)));
            
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"),
            		entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"),
            		entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            OccurrenceEntry o3 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("regexp"),
            		entryFactory.newTopicTypeParam(regExp));
            t1.add(o3);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            t.setScanner(new HasSubjectIdentifierScanner());
            templates.add(t);
        }
        
        /*
         * def has-item-identifier($tt, $min, $max, $regexp)
			  ?c isa tmcl:item-identifier-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max;
			    tmcl:regexp: $regexp.
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)
			end

         */
        private void addHasItemIdentifier() {
        	Template t = templateFactory.newTemplate("has-item-identifier");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(itemIdentifierConstraint)));
            
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"),
            		entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"),
            		entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            OccurrenceEntry o3 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("regexp"),
            		entryFactory.newTopicTypeParam(regExp));
            t1.add(o3);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            t.setScanner(new HasItemIdentifierScanner());
            templates.add(t);
        }

        /*
          	def has-subject-locator($tt, $min, $max, $regexp)
			  ?c isa tmcl:subject-locator-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max;
			    tmcl:regexp: $regexp.
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)
			end

         */
        private void addHasSubjectLocator() {
        	Template t = templateFactory.newTemplate("has-subject-locator");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(subjectLocatorConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"),
            		entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"),
            		entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            OccurrenceEntry o3 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("regexp"),
            		entryFactory.newTopicTypeParam(regExp));
            t1.add(o3);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            t.setScanner(new HasSubjectLocatorScanner());
            
            templates.add(t);

        }

        /*
          def has-name($tt, $nt, $min, $max)
			  ?c isa tmcl:topic-name-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max.
			  
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)  
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $nt)
			end

         */
        private void addHasName() {
        	Template t = templateFactory.newTemplate("has-name");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(subjectLocatorConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("nt"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a2);
            t.setScanner(new HasNameScanner());
            templates.add(t);
            

        }

        /*
         * # Topic Occurrence Constraint
			def has-occurrence($tt, $ot, $min, $max)
			  ?c isa tmcl:topic-occurrence-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max.
			  
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)  
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $ot)
			end

         */
        private void addHasOccurrence() {
        	Template t = templateFactory.newTemplate("has-occurence");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(topicOccurrenceConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("ot"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a2);
            
            t.setScanner(new HasOccurrenceScanner());
            templates.add(t);

        }

        /*
         * # Topic Role Constraint
			def plays-role($tt, $rt, $at, $min, $max)
			  ?c isa tmcl:topic-role-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max.
			  
			  tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)  
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $at)
			  tmcl:constrained-role(tmcl:constraint : ?c, tmcl:constrained : $rt)
			end

         */
        private void addPlaysRole() {
        	Template t = templateFactory.newTemplate("plays-role");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(topicRoleConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("at"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a2);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("rt"));
            AssociationEntry a3 = entryFactory.newAssociationEntry(constrainedRole, r1, r2);
            t.add(a3);
            t.setScanner(new PlaysRoleScanner());
            templates.add(t);

        }

        /*
         * # Scope Constraint
			def has-scope($st, $tt, $min, $max)
			  ?c isa tmcl:scope-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max.
			  
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $st)
			  tmcl:constrained-scope(tmcl:constraint : ?c, tmcl:constrained : $tt)
			end
         */
        private void addHasScope() {
        	Template t = templateFactory.newTemplate("has-scope");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(scopeConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("at"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constrainedScope, r1, r2);
            t.add(a2);
            t.setScanner(new HasScopeScanner());
            
            templates.add(t);

        }

        /*
         * # Reifier Constraint
			def must-have-reifier($st, $tt)
			  ?c isa tmcl:reifier-constraint;
			    tmcl:card-min: 1;
			    tmcl:card-max: 1.
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			  tmcl:allowed-reifier(tmcl:allows: ?c, tmcl:allowed: $tt)
			end
         */
        private void addMustHaveReifier() {
        	Template t = templateFactory.newTemplate("must-have-reifier");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(reifierConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(allows, constrParam);
            r2 = entryFactory.newRoleEntry(allowed, entryFactory.newVariableParam("tt"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(allowedReifier, r1, r2);
            t.add(a2);
            t.setScanner(new MustHaveReifierScanner());
            templates.add(t);

        }

        /*
         * def cannot-have-reifier($st)
			  ?c isa tmcl:reifier-constraint;
			    tmcl:card-min: 0;
			    tmcl:card-max: 0.
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			  tmcl:allowed-reifier(tmcl:allows: ?c, tmcl:allowed: tmdm:subject)
			end
         */
        private void addCannotHaveReifier() {
        	Template t = templateFactory.newTemplate("cannot-have-reifier");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(reifierConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(allows, constrParam);
            r2 = entryFactory.newRoleEntry(allowed, entryFactory.newTopicTypeParam(subject));
            AssociationEntry a2 = entryFactory.newAssociationEntry(allowedReifier, r1, r2);
            t.add(a2);
            
            templates.add(t);

        }

        /*
         * def may-have-reifier($st, $tt)
			  ?c isa tmcl:reifier-constraint;
			    tmcl:card-min: 0;
			    tmcl:card-max: 1.
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			  tmcl:allowed-reifier(tmcl:allows: ?c, tmcl:allowed: $tt)
			end
         */
        private void addMayHaveReifier() {
        	Template t = templateFactory.newTemplate("may-have-reifier");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(reifierConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(allows, constrParam);
            r2 = entryFactory.newRoleEntry(allowed, entryFactory.newVariableParam("tt"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(allowedReifier, r1, r2);
            t.add(a2);
            
            t.setScanner(new MayHaveReifierScanner());
            templates.add(t);

        }

        /*
         * # Topic Reifies Constraint
			def must-reify($tt, $st)
			  ?c isa tmcl:topic-reifies-constraint;
			    tmcl:card-min: 1;
			    tmcl:card-max: 1.
			  tmcl:constrained-topic-type(tmcl:constraint: ?c, tmcl:constrained: $tt)
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			end
         */
        private void addMustReify() {
        	Template t = templateFactory.newTemplate("must-reify");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(topicReifiesConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a2);
            t.setScanner(new MustReifyScanner());
            templates.add(t);

        }
        
        /*
         * def cannot-reify($tt)
			  ?c isa tmcl:topic-reifies-constraint;
			    tmcl:card-min: 0;
			    tmcl:card-max: 0.
			  tmcl:constrained-topic-type(tmcl:constraint: ?c, tmcl:constrained: $tt)
			end
         */
        private void addCannotReify() {
        	Template t = templateFactory.newTemplate("cannot-reify");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(topicReifiesConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            t.setScanner(new CannotReifyScanner());
            templates.add(t);

        }

        /*
         * def may-reify($tt, $st)
			  ?c isa tmcl:topic-reifies-constraint;
			    tmcl:card-min: 0;
			    tmcl:card-max: 1.
			  tmcl:constrained-topic-type(tmcl:constraint: ?c, tmcl:constrained: $tt)
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			end
         */
        private void addMayReify() {
        	Template t = templateFactory.newTemplate("may-reify");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(topicReifiesConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("0"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newValueParam("1"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a2);
            
            t.setScanner(new MayReifyScanner());
            templates.add(t);

        }

        /*
         * # Association Role Constraint
			def has-role($at, $rt, $min, $max)
			  ?c isa tmcl:association-role-constraint;
			    tmcl:card-min: $min;
			    tmcl:card-max: $max.
			  
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $at)
			  tmcl:constrained-role(tmcl:constraint : ?c, tmcl:constrained : $rt)
			end
         */
        private void addHasRole() {
        	Template t = templateFactory.newTemplate("has-role");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(associationRoleConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("min"), entryFactory.newTopicTypeParam(cardMin));
            t1.add(o1);
            
            OccurrenceEntry o2 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("max"), entryFactory.newTopicTypeParam(cardMax));
            t1.add(o2);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("at"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("rt"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constrainedRole, r1, r2);
            t.add(a2);
            t.setScanner(new HasRoleScanner());
            
            templates.add(t);

        }

        /*
         * # Role Combination Constraint
			def role-combination($at, $rt, $tt, $ort, $ott)
			  ?c isa tmcl:role-combination-constraint.
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $at)
			  tmcl:constrained-role(tmcl:constraint: ?c, tmcl:constrained: $rt)
			  tmcl:constrained-topic-type(tmcl:constraint: ?c, tmcl:constrained: $tt)
			  tmcl:other-constrained-role(tmcl:constraint: ?c, tmcl:constrained: $ort)
			  tmcl:other-constrained-topic-type(tmcl:constraint: ?c, tmcl:constrained: $ott)
			end
         */
        private void addRoleCombination() {
        	Template t = templateFactory.newTemplate("role-combination");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(roleCombinationConstraint)));
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("at"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("rt"));
            AssociationEntry a2 = entryFactory.newAssociationEntry(constrainedRole, r1, r2);
            t.add(a2);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a3 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a3);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("ort"));
            AssociationEntry a4 = entryFactory.newAssociationEntry(otherConstrainedRole, r1, r2);
            t.add(a4);
            
            r1 = entryFactory.newRoleEntry(constraint, constrParam);
            r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("ott"));
            AssociationEntry a5 = entryFactory.newAssociationEntry(otherConstrainedTopicType, r1, r2);
            t.add(a5);
            t.setScanner(new RoleCombinationScanner());
            
            templates.add(t);

        }

        /*
         * # Occurrence Data Type Constraint
			def has-datatype($ot, $dt)
			  ?c isa tmcl:occurrence-datatype-constraint;
			    tmcl:datatype: $dt.
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $ot)
			end
         */
        private void addHasDatatype() {
        	Template t = templateFactory.newTemplate("has-datatype");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(occurrenceDatatypeConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("dt"), entryFactory.newTopicTypeParam(datatype));
            t1.add(o1);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("ot"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            
            t.setScanner(new OccurrenceDatatypeScanner());
            templates.add(t);

        }

        /*
         * # Unique Value Constraint
			def has-unique-value($st)
			  ?c isa tmcl:unique-value-constraint.
			  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $st)
			end
         */
        private void addIsUnique() {
        	Template t = templateFactory.newTemplate("has-unique-value");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(uniqueValueConstraint)));
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            t.setScanner(new IsUniqueScanner());
            
            templates.add(t);

        }
        
        /*
         * # Abstract Constraint
			def is-abstract($tt)
  				?c isa tmcl:abstract-constraint.
  				tmcl:constrained-topic-type(tmcl:constraint : ?c, tmcl:constrained : $tt)
			end
         */
        private void addIsAbstract() {
        	Template t = templateFactory.newTemplate("is-abstract");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(abstractConstraint)));
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("tt"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constrainedTopicType, r1, r2);
            t.add(a1);
            t.setScanner(new IsAbstractScanner());
            templates.add(t);

        }

        /*
         * # Regular Expression Constraint
			def matches-regexp($st, $regexp)
			  ?c isa tmcl:regular-expression-constraint;
			    tmcl:regexp: $regexp.
			  
			  tmcl:constrained-statement(tmcl:constraint: ?c, tmcl:constrained: $st)
			end
         */
        private void addMatchesRegExp() {
        	Template t = templateFactory.newTemplate("matches-regexp");
        	
        	IEntryParam constrParam = entryFactory.newWildcardParam("c");
        	TopicEntry t1 = entryFactory.newTopicEntry(constrParam);
        	
        	t1.add(entryFactory.newIsInstanceOfEntry(entryFactory.newTopicTypeParam(regularExpressionConstraint)));
        	
            OccurrenceEntry o1 = entryFactory.newOccurrenceEntry(entryFactory.newVariableParam("regexp"), entryFactory.newTopicTypeParam(regExp));
            t1.add(o1);
            
            t.add(t1);
            
            RoleEntry r1 = entryFactory.newRoleEntry(constraint, constrParam);
            RoleEntry r2 = entryFactory.newRoleEntry(constrained, entryFactory.newVariableParam("st"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(constraintStatement, r1, r2);
            t.add(a1);
            t.setScanner(new RegExpConstraintScanner());
            
            templates.add(t);

        }

        /*
         * # The Schema Topic
			def belongs-to($construct, $schema)
			  tmcl:belongs-to-schema(tmcl:container: $schema, tmcl:containee: $construct)
			end
         */
        private void addBelongsTo() {
        	Template t = templateFactory.newTemplate("belongs-to");
        	
            RoleEntry r1 = entryFactory.newRoleEntry(container, entryFactory.newVariableParam("schema"));
            RoleEntry r2 = entryFactory.newRoleEntry(containee, entryFactory.newVariableParam("construct"));
            AssociationEntry a1 = entryFactory.newAssociationEntry(belongsTo, r1, r2);
            t.add(a1);
            
            templates.add(t);

        }
        
}
