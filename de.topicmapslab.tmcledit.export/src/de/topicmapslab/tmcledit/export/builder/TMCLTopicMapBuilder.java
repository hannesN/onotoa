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

import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.BENDPOINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.CONNECTOR;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.CONNECTS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.CONTAINS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.CONTENT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.DIAGRAM;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.EDGE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.HEIGHT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.ID;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.NODE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.POSX;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.POSY;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.REFEREE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.REFERER;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.REFERS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.SOURCE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.TARGET;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa.WIDTH;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ABSTRACT_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWED;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWED_REIFIER;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ALLOWS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ASSOCIATION_ROLE_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ASSOCIATION_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.BELONGS_TO;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CARD_MAX;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CARD_MIN;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_CONSTRUCT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_ROLE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.CONSTRAINED_SCOPE;
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
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OVERLAPS;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.OVERLAP_DECLARATION;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REGEXP;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REGULAR_EXPRESSION_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.REIFIER_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ROLE_COMBINATION_CONSTRAINT;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.ROLE_TYPE;
import static de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL.SCHEMA;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tmapi.core.Association;
import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;
import org.tmapi.index.LiteralIndex;
import org.tmapi.index.ScopedIndex;
import org.tmapi.index.TypeInstanceIndex;

import de.topicmapslab.majortom.core.TopicMapSystemFactoryImpl;
import de.topicmapslab.majortom.inmemory.store.InMemoryTopicMapStore;
import de.topicmapslab.majortom.store.TopicMapStoreProperty;
import de.topicmapslab.tmcledit.export.voc.Namespaces;
import de.topicmapslab.tmcledit.export.voc.Namespaces.Onotoa;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMCL;
import de.topicmapslab.tmcledit.export.voc.Namespaces.TMDM;
import de.topicmapslab.tmcledit.export.voc.Namespaces.XSD;
import de.topicmapslab.tmcledit.model.AbstractCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * The class create a TMCL topic map based on a Onotoa {@link TopicMapSchema}
 * 
 * @author Hannes Niederhausen
 * 
 */
public class TMCLTopicMapBuilder {
	private static final String ANNOTATION_PREFIX = "http://onotoa.topicmapslab.de/annotation";

	private final TopicMapSchema topicMapSchema;

	// set to store the created roleconstraints and roleplayerconstraint
	private final Set<ConstraintWrapper> constraintSet = new HashSet<ConstraintWrapper>();

	// private final Set<OccurrenceType> occTypes = new
	// HashSet<OccurrenceType>();

	private Map<TopicType, Topic> topicTypeMap;
	private Map<Node, Topic> nodeMap;
	private Map<String, String> prefixMap;
	
	private Map<String, Topic> annotationMap;

	private TopicMap topicMap;
	private Locator baseLocator;

	private boolean exportSchema = true;
	private boolean exportAnnotations = true;
	private boolean exportTopicTypesOnly = false;
	private Topic schema;

	private Map<TopicType, TopicType> overlapMap;

	private int topicCounter = 0;

	private boolean createDiagramNodes;

	// topic for the annotation type
	private Topic annotationType;
	
	// topics for the diagram informations
	
	// topics for the classes
	private Topic diagramTopic;
	private Topic nodeTopic;
	private Topic edgeTopic;
	private Topic bendpointTopic;

	private Topic commentTopic;

	private Topic idType;

	private Topic typeType;

	private Topic posxType;

	private Topic posyType;

	private Topic widthType;

	private Topic heightType;

	private Topic content;

	private Topic sourceTopic;

	private Topic targetTopic;

	private Topic connectorTopic;

	private Topic containerTopic;

	private Topic containeeTopic;

	private Topic refererTopic;

	private Topic refereeTopic;

	private Topic refersTopic;

	private Topic connectsTopic;

	private Topic containsTopic;

	private TopicMapSystem topicMapSystem;

	/**
	 * Constructor
	 * @param topicMapSchema the topic map schema
	 * @param exportSchema export schema information flag
	 */
	public TMCLTopicMapBuilder(TopicMapSchema topicMapSchema, boolean exportSchema) {
		this(topicMapSchema, exportSchema, false);
	}

	/**
	 * Constructor
	 * @param topicMapSchema the topic map schema
	 * @param exportSchema export schema information flag
	 * @param createDiagramNodes create diagram nodes flag
	 */
	public TMCLTopicMapBuilder(TopicMapSchema topicMapSchema, boolean exportSchema, boolean createDiagramNodes) {
		super();
		this.topicMapSchema = topicMapSchema;
		this.exportSchema = exportSchema;
		this.createDiagramNodes = createDiagramNodes;
	}

	/**
	 * Sets the flag whether export the types only 
	 * 
	 * @param exportTopicTypesOnly 
	 */
	public void setExportTopicTypesOnly(boolean exportTopicTypesOnly) {
		this.exportTopicTypesOnly = exportTopicTypesOnly;
	}

	/**
	 * Creates the topic map based on the {@link TopicMapSchema}
	 * @return the cretaed {@link TopicMap}
	 */
	public TopicMap createTopicMap() {
		try {
			// init prefixMap
			prefixMap = new HashMap<String, String>(topicMapSchema.getMappings().size());
			for (MappingElement me : topicMapSchema.getMappings()) {
				prefixMap.put(me.getKey(), me.getValue());
			}
			String baseLoc = topicMapSchema.getBaseLocator();
			if ((baseLoc == null) || (baseLoc.length() == 0))
				baseLoc = "http://onotoa.topicmapslab.de";

			if (baseLoc.endsWith(":")) {
				String key = baseLoc.substring(0, baseLoc.length() - 1);
				baseLoc = prefixMap.get(key);
				if (baseLoc == null)
					baseLoc = "http://onotoa.topicmapslab.de";
			}

			// using fixed majortom
			TopicMapSystemFactoryImpl tmSystemFac = new TopicMapSystemFactoryImpl();
			tmSystemFac.setProperty(TopicMapStoreProperty.TOPICMAPSTORE_CLASS, InMemoryTopicMapStore.class.getName());

			topicMapSystem = tmSystemFac.newTopicMapSystem();
			topicMap = topicMapSystem.createTopicMap(baseLoc);
			baseLocator = topicMap.createLocator(baseLoc);

			topicTypeMap = new HashMap<TopicType, Topic>(topicMapSchema.getTopicTypes().size());

			overlapMap = new HashMap<TopicType, TopicType>(10);

			
			
			if (exportAnnotations) {
				annotationType = topicMap.createTopicBySubjectIdentifier(topicMap.createLocator(ANNOTATION_PREFIX));
				annotationType.createName("Onotoa Annotation");
				annotationType.addType(getTopicType(KindOfTopicType.TOPIC_TYPE));
				
				// create reifier for topic map for schema annotations
				Topic reifier = topicMap.createTopicBySubjectIdentifier(topicMap.createLocator("http://onotoa.topicmapslab.de/schemareifier"));
				topicMap.setReifier(reifier);
				for (Annotation a : topicMapSchema.getAnnotations()) {
					addAnnotation(a, reifier);
				}
				
			}

			if (exportSchema) {
				
				String schemaII = "http://onotoa.topicmapslab.de/schema/";
				if (topicMapSchema.getVersion()!=null)
					schemaII+=topicMapSchema.getVersion();
				
				schema = topicMap.createTopicByItemIdentifier(topicMap.createLocator(schemaII));
				addDocumentationOccurrences(schema, topicMapSchema);
				
				String tmp = topicMapSchema.getSchemaResource();
				if ((tmp!=null) && (tmp.length()>0)) {
					schema.createOccurrence(createTopic(TMCL.SCHEMA_RESOURCE), tmp);
				}
				
				tmp = topicMapSchema.getVersion();
				if ((tmp!=null) && (tmp.length()>0)) {
					schema.createOccurrence(createTopic(TMCL.VERSION), tmp);
				}
				schema.addType(createTopic(SCHEMA));
				
				if ((topicMapSchema.getName()!=null) && (topicMapSchema.getName().length()>0)) {
					schema.createName(topicMapSchema.getName());
				}
			}
			
			createTopicTypes();
			if (!exportTopicTypesOnly) {
				createAssociationConstraints();
			}

			// for(OccurrenceType ot : occTypes) {
			// setOccurrenceDatatype(ot);
			// }

			// cleaning up the topics, removing item identifier where there not
			// needed
			for (Topic t : topicMap.getTopics()) {
				if ((t.getSubjectIdentifiers().size() > 0) || (t.getSubjectLocators().size() > 0)) {
					Set<Locator> itemIds = new HashSet<Locator>(t.getItemIdentifiers());
					for (Locator l : itemIds) {
						t.removeItemIdentifier(l);
					}
				}
			}

			if (createDiagramNodes) {
				nodeMap = new HashMap<Node, Topic>();
				createDiagramNodes();
			}

			// open indexes because nobody else does it...
			topicMap.getIndex(TypeInstanceIndex.class).open();
			topicMap.getIndex(ScopedIndex.class).open();
			topicMap.getIndex(LiteralIndex.class).open();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return topicMap;
	}
	
	/**
	 * 
	 * @return the used {@link TopicMapSystem}
	 */
	public TopicMapSystem getTopicMapSystem() {
	    return topicMapSystem;
    }
	
	/**
	 * Sets the flag whether to export the annotations
	 * @param exportAnnotations
	 */
	public void setExportAnnotations(boolean exportAnnotations) {
	    this.exportAnnotations = exportAnnotations;
    }
	/**
	 * Sets the flag whether to export the schema and create association for every element
	 * 
	 * @param exportSchema
	 */
	public void setExportSchema(boolean exportSchema) {
	    this.exportSchema = exportSchema;
    }

	private void createDiagramNodes() {
		// init types
		diagramTopic = createTopic(DIAGRAM);
		edgeTopic = createTopic(EDGE);
		nodeTopic = createTopic(NODE);
		commentTopic = createTopic(Onotoa.COMMENT);
		bendpointTopic = createTopic(BENDPOINT);

		// occurrences
		idType = createTopic(ID);
		typeType = createTopic(TYPE);
		posxType = createTopic(POSX);
		posyType = createTopic(POSY);
		widthType = createTopic(WIDTH);
		heightType = createTopic(HEIGHT);
		content = createTopic(CONTENT);

		// roles
		sourceTopic = createTopic(SOURCE);
		targetTopic = createTopic(TARGET);
		connectorTopic = createTopic(CONNECTOR);
		containerTopic = createTopic(Onotoa.CONTAINER);
		containeeTopic = createTopic(Onotoa.CONTAINEE);
		refererTopic = createTopic(REFERER);
		refereeTopic = createTopic(REFEREE);

		// associations
		refersTopic = createTopic(REFERS);
		connectsTopic = createTopic(CONNECTS);
		containsTopic = createTopic(CONTAINS);

		File file = (File) topicMapSchema.eContainer();

		for (Diagram d : file.getDiagrams()) {
			createDiagram(d);
		}

	}

	private void createDiagram(Diagram d) {
		Locator id = topicMap.createLocator(Onotoa.PREFIX + "/diagrams/" + d.getName().toLowerCase());
		Topic diagram = createTopic(id);
		diagram.addType(diagramTopic);

		diagram.createName(d.getName());

		for (Comment c : d.getComments()) {
			createComment(diagram, c);
		}

		for (Node n : d.getNodes()) {
			createNode(diagram, n);
		}

		for (Edge e : d.getEdges()) {
			createEdge(diagram, e);
		}

	}

	private void createEdge(Topic diagram, Edge e) {
		Locator l = topicMap.createLocator(EDGE + "/" + e.getId());
		Topic edge = createTopic(l);
		edge.addType(edgeTopic);

		edge.createOccurrence(typeType, e.getType().getLiteral());

		Association assoc = topicMap.createAssociation(connectsTopic);
		assoc.createRole(connectorTopic, edge);
		assoc.createRole(sourceTopic, nodeMap.get(e.getSource()));
		assoc.createRole(targetTopic, nodeMap.get(e.getTarget()));

		for (Bendpoint bp : e.getBendpoints()) {
			createBendPoints(bp, edge);
		}
		// TODO role constraint - contraint which represents this thing
	}

	private void createBendPoints(Bendpoint bp, Topic edge) {
		Locator l = topicMap.createLocator(BENDPOINT + "/" + bp.getId());
		Topic bendpoint = createTopic(l);
		bendpoint.addType(bendpointTopic);

		bendpoint.createOccurrence(posyType, Integer.toString(bp.getPosY()));
		bendpoint.createOccurrence(posxType, Integer.toString(bp.getPosX()));

		Association assoc = topicMap.createAssociation(containsTopic);
		assoc.createRole(containerTopic, edge);
		assoc.createRole(containeeTopic, bendpoint);

	}

	private void createNode(Topic diagram, Node n) {
		Locator l = topicMap.createLocator(NODE + "/" + n.getId());
		Topic node = createTopic(l);
		node.addType(typeType);

		node.createOccurrence(posyType, Integer.toString(n.getPosY()));
		node.createOccurrence(posxType, Integer.toString(n.getPosX()));

		if (n instanceof TypeNode) {
			Topic t = topicTypeMap.get(((TypeNode) n).getTopicType());
			Association assoc = topicMap.createAssociation(refersTopic);
			assoc.createRole(refereeTopic, t);
			assoc.createRole(refersTopic, node);
		} else {
			// TODO assoc nodes
		}

		Association assoc = topicMap.createAssociation(containsTopic);
		assoc.createRole(containerTopic, diagram);
		assoc.createRole(containeeTopic, node);
		nodeMap.put(n, node);
	}

	private void createComment(Topic diagram, Comment c) {
		Locator l = topicMap.createLocator(TMCL.COMMENT + "/" + c.getId());
		Topic comment = createTopic(l);
		comment.addType(commentTopic);

		comment.createOccurrence(content, c.getContent());
		comment.createOccurrence(posyType, Integer.toString(c.getPosY()));
		comment.createOccurrence(posxType, Integer.toString(c.getPosX()));
		comment.createOccurrence(widthType, Integer.toString(c.getWidth()));
		comment.createOccurrence(heightType, Integer.toString(c.getHeight()));

		Association assoc = topicMap.createAssociation(containsTopic);
		assoc.createRole(containerTopic, diagram);
		assoc.createRole(containeeTopic, comment);
	}

	private void createTopicTypes() {
		for (TopicType tt : topicMapSchema.getTopicTypes()) {
			createTopic(tt);
		}
	}

	private void createAssociationConstraints() {
		for (AssociationTypeConstraint atc : topicMapSchema.getAssociationTypeConstraints()) {
			if (atc.getType() == null)
				continue;
			AssociationType at = (AssociationType) atc.getType();
			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				ConstraintWrapper w = new ConstraintWrapper(rpc, at);
				if (constraintSet.contains(w))
					continue;

				setRolePlayerConstraint(atc.getType(), rpc);
				constraintSet.add(w);
			}

			for (RoleConstraint rc : at.getRoles()) {
				ConstraintWrapper w = new ConstraintWrapper(rc, at);
				if (constraintSet.contains(w))
					continue;

				setRoleConstraint(atc, rc);
				constraintSet.add(w);
			}

			// setScopeConstraints(at);
		}
	}

	private void setRoleCombinationConstraint(AssociationType type, RoleCombinationConstraint rcc) {
		Topic constr = createConstraint(ROLE_COMBINATION_CONSTRAINT);
		createConstrainedStatement(type, constr);
		createConstrainedRole(rcc.getRole(), constr);
		createConstrainedTopicType(createTopic(rcc.getPlayer()), constr);
		createOtherConstrainedRole(rcc.getOtherRole(), constr);
		createOtherConstrainedTopicType(createTopic(rcc.getOtherPlayer()), constr);

		setSchema(constr);
	}

	private void createOtherConstrainedTopicType(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(OTHER_CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), t);
	}

	private void createOtherConstrainedRole(TopicType rt, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(OTHER_CONSTRAINED_ROLE));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), createTopic(rt));
	}

	private void setRoleConstraint(AssociationTypeConstraint atc, RoleConstraint rc) {
		Topic constr = createConstraint(ASSOCIATION_ROLE_CONSTRAINT);
		addCardinalityOccurrences(constr, rc.getCardMin(), rc.getCardMax());

		createConstrainedRole(rc.getType(), constr);
		createConstrainedStatement(atc.getType(), constr);

		setSchema(constr);
	}

	private void setRolePlayerConstraint(TopicType type, RolePlayerConstraint rpc) {
		if ((rpc.getPlayer() == null) || (rpc.getRole() == null))
			return;
		Topic constr = createConstraint(TOPIC_ROLE_CONSTRAINT);
		addCardinalityOccurrences(constr, rpc.getCardMin(), rpc.getCardMax());
		addDocumentationOccurrences(constr, rpc);
		createConstrainedStatement(type, constr);
		createConstrainedTopicType(createTopic(rpc.getPlayer()), constr);
		createConstrainedRole(rpc.getRole().getType(), constr);

		setSchema(constr);
	}

	private void createConstrainedRole(TopicType rt, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_ROLE));

		Topic rtTopic = createTopic(rt);
		// check if type is a topic type, if so, add the role-type to isa list
		if (rt.getKind() == KindOfTopicType.TOPIC_TYPE) {
			rtTopic.addType(getTopicType(KindOfTopicType.ROLE_TYPE));
		}

		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), createTopic(rt));
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

	private Topic createTopic(String uri) {
		Locator loc = topicMap.createLocator(uri);
		return createTopic(loc);
	}

	private Topic createTopic(TopicType type) {
		Topic t = null;
		String url = (type.getName() + (topicCounter)).replaceAll(" ", "-");
		topicCounter++;
		try {
			url = URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		Locator itemId = baseLocator.resolve("#" + url);
		t = topicTypeMap.get(type);
		if ((t != null) && (t.getParent() != null))
			return t;

		t = topicMap.createTopicByItemIdentifier(itemId);
		topicTypeMap.put(type, t);
		t.createName(type.getName());
		setSchema(t);

		// setting identifiers
		for (String id : type.getIdentifiers()) {
			if (id.indexOf(':') != -1) { // TODO make it more robust
				String iri = resolveIRI(id);
				if (iri != null) {
					Locator loc = topicMap.createLocator(iri);
					Topic exTopix = topicMap.getTopicBySubjectIdentifier(loc);
					if (exTopix != null)
						t.mergeIn(exTopix);
					else
						t.addSubjectIdentifier(loc);
				}
			} else {
				Locator loc = topicMap.createLocator(id);
				Topic exTopix = topicMap.getTopicBySubjectIdentifier(loc);
				if (exTopix != null)
					t.mergeIn(exTopix);
				else
					t.addSubjectIdentifier(loc);
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
		Topic typeTopic = getTopicType(type.getKind());
		if (typeTopic != null)
			t.addType(typeTopic);

		for (TopicType tt : type.getIsa()) {
			t.addType(createTopic(tt));
		}
		for (TopicType tt : type.getAko()) {
			setSuperType(t, createTopic(tt));
		}

		// don't start with constraints if not wanted
		if (exportTopicTypesOnly)
			return t;

		// creating doc occs
		addDocumentationOccurrences(t, type);

		if (type.isAbstract())
			setAbstract(t);

		for (ItemIdentifierConstraint iic : type.getItemIdentifierConstraints()) {
			setItemIdentifierConstraint(t, iic);
		}
		
		for (SubjectIdentifierConstraint sic : type.getSubjectIdentifierConstraints()) {
			setSubjectIdentifierConstraint(t, sic);
		}

		for (SubjectLocatorConstraint slc : type.getSubjectLocatorConstraints()) {
			setSubjectLocatorConstraint(t, slc);
		}

		for (NameTypeConstraint ntc : type.getNameConstraints()) {
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

		if (type instanceof ScopedTopicType) {
			setScopeConstraints((ScopedTopicType) type);
		}

		if (type instanceof AbstractUniqueValueTopicType) {
			setUnique((AbstractUniqueValueTopicType) type);
		}

		if (type instanceof AbstractRegExpTopicType) {
			setRegExpConstraint((AbstractRegExpTopicType) type);
		}

		if (type instanceof OccurrenceType) {
			setOccurrenceDatatype((OccurrenceType) type);
		}
		
		if (type instanceof AssociationType) {
			for (RoleCombinationConstraint rcc : ((AssociationType) type).getRoleCombinations()) {
				setRoleCombinationConstraint((AssociationType) type, rcc);
			}
		}

		setTopicReifiesConstraint(type);

		return t;
	}

	private Topic createConstraint(String type) {
		return createConstraint(topicMap.createLocator(type));
	}

	private Topic createConstraint(Locator type) {
		Topic constr = topicMap.createTopic();

		constr.addType(createTopic(type));

		return constr;
	}

	private void createConstrainedStatement(TopicType tt, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), createTopic(tt));
	}

	private void createConstrainedStatement(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), t);
	}

	private void createConstrainedTopicType(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), t);
	}

	private void createConstrainedScope(TopicType tt, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_SCOPE));
		ass.createRole(createTopic(CONSTRAINT), constr);
		Topic t = null;
		if (tt == null)
			t = createTopic(TMDM.SUBJECT);
		else
			t = createTopic(tt);
		ass.createRole(createTopic(CONSTRAINED), t);
	}

	private void createConstrainedConstruct(Topic t, Topic constr) {
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_CONSTRUCT));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), t);
        
    }

	private void createOverlaps(TopicType type, Topic constraint) {
		Association ass = topicMap.createAssociation(createTopic(OVERLAPS));
		ass.createRole(createTopic(ALLOWS), constraint);
		ass.createRole(createTopic(ALLOWED), createTopic(type));
	}

	private void addDocumentationOccurrences(Topic topic, TMCLConstruct construct) {
		String tmp = construct.getComment();
		if ((tmp != null) && (tmp.length() > 0))
			topic.createOccurrence(createTopic(TMCL.COMMENT), tmp);

		tmp = construct.getSee_also();
		if ((tmp != null) && (tmp.length() > 0))
			topic.createOccurrence(createTopic(SEE_ALSO), tmp);

		tmp = construct.getDescription();
		if ((tmp != null) && (tmp.length() > 0))
			topic.createOccurrence(createTopic(DESCRIPTION), tmp);

		addAnnotationOccurrences(topic, construct);
	}

	private void addAnnotationOccurrences(Topic topic, TMCLConstruct construct) {
		if (!exportAnnotations)
			return;
		
		for (Annotation a : construct.getAnnotations()) {
			addAnnotation(a, topic);
		}
	    
    }

	private void addAnnotation(Annotation a, Topic topic) {
	    if (a.getValue().length()==0)
	    	return;
		Topic type = getAnnotationType(a);
	    
	    topic.createOccurrence(type, a.getValue());
    }

	private void addCardinalityOccurrences(Topic constr, String cardMin, String cardMax) {
		constr.createOccurrence(createTopic(CARD_MIN), cardMin, topicMap.createLocator(XSD.INTEGER));
		constr.createOccurrence(createTopic(CARD_MAX), cardMax, topicMap.createLocator(TMCL.INTEGER));
	}

	private void setOverlapConstraint(TopicType type, TopicType othertype) {
		if (type.equals(overlapMap.get(othertype)))
			return;

		overlapMap.put(type, othertype);

		Topic constr = createConstraint(OVERLAP_DECLARATION);

		createOverlaps(othertype, constr);
		createOverlaps(type, constr);

		setSchema(constr);
	}

	private void setNameTypeConstraint(Topic t, NameTypeConstraint ntc) {
		NameType nt = (NameType) ntc.getType();
		Topic nameTopic = null;
		if (nt == null)
			nameTopic = createTopic(TMDM.TOPIC_NAME);
		else {
			nameTopic = createTopic(nt);
		}

		Topic constr = createConstraint(TOPIC_NAME_CONSTRAINT);
		addDocumentationOccurrences(constr, ntc);
		addCardinalityOccurrences(constr, ntc.getCardMin(), ntc.getCardMax());

		createConstrainedTopicType(t, constr);
		createConstrainedStatement(nameTopic, constr);

		// setScopeConstraints(nt);

		setSchema(constr);
	}

	private void setOccurrenceConstraint(Topic t, OccurrenceTypeConstraint otc) {
		OccurrenceType otype = (OccurrenceType) otc.getType();
		Topic occType = null;
		if (otype != null) {
			occType = createTopic(otype);
		} else {
			occType = createTopic(TMDM.SUBJECT);
		}

		Topic constr = createConstraint(TOPIC_OCCURRENCE_CONSTRAINT);
		addDocumentationOccurrences(constr, otc);
		addCardinalityOccurrences(constr, otc.getCardMin(), otc.getCardMax());
		setSchema(constr);

		createConstrainedTopicType(t, constr);
		createConstrainedStatement(occType, constr);

		// setScopeConstraints(otype);
	}

	private void setScopeConstraints(ScopedTopicType type) {
		if (type == null)
			return;
		for (ScopeConstraint sc : type.getScope()) {
			Topic constr = createConstraint(SCOPE_CONSTRAINT);
			addCardinalityOccurrences(constr, sc.getCardMin(), sc.getCardMax());
			addDocumentationOccurrences(constr, sc);
			createConstrainedStatement(type, constr);
			createConstrainedScope(sc.getType(), constr);

			setSchema(constr);
		}

	}

	private void setReifierConstraint(ReifiableTopicType rft) {
		ReifierConstraint constraint = rft.getReifierConstraint();
		if (constraint == null)
			return;
		Topic constr = createConstraint(REIFIER_CONSTRAINT);
		addDocumentationOccurrences(constr, constraint);
		constr.createOccurrence(createTopic(CARD_MIN), constraint.getCardMin(), topicMap.createLocator(XSD.INTEGER));
		constr.createOccurrence(createTopic(CARD_MAX), constraint.getCardMax(), topicMap.createLocator(XSD.INTEGER));
		createConstrainedStatement(rft, constr);

		Association ass = topicMap.createAssociation(createTopic(ALLOWED_REIFIER));
		ass.createRole(createTopic(ALLOWS), constr);
		Topic t = null;
		if ((constraint.getCardMax().equals("0")) || (constraint.getType() == null))
			t = createTopic(TMDM.SUBJECT);
		else
			t = createTopic(constraint.getType());
		ass.createRole(createTopic(ALLOWED), t);

		setSchema(constr);

	}

	private void setTopicReifiesConstraint(TopicType tt) {
		for (TopicReifiesConstraint trc : tt.getTopicReifiesConstraints()) {
			Topic constr = createConstraint(TOPIC_REIFIES_CONSTRAINT);
			addDocumentationOccurrences(constr, trc);
			constr.createOccurrence(createTopic(CARD_MIN), trc.getCardMin(), topicMap.createLocator(XSD.INTEGER));
			constr.createOccurrence(createTopic(CARD_MAX), trc.getCardMax(), topicMap.createLocator(XSD.INTEGER));
			createConstrainedTopicType(createTopic(tt), constr);

			TopicType type = trc.getType();
			if (type != null)
				createConstrainedStatement(createTopic(type), constr);

			setSchema(constr);
		}

	}

	private void setRegExpConstraint(AbstractRegExpTopicType type) {
		Topic constr = createConstraint(REGULAR_EXPRESSION_CONSTRAINT);
		constr.createOccurrence(createTopic(REGEXP), type.getRegExp());
		createConstrainedStatement(type, constr);

		setSchema(constr);
	}

	private void setUnique(AbstractUniqueValueTopicType ut) {
		if (!ut.isUnique())
			return;
		Topic constr = createConstraint(UNIQUE_VALUE_CONSTRAINT);
		Topic type = createTopic(ut);
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_STATEMENT));
		ass.createRole(createTopic(CONSTRAINT), constr);
		ass.createRole(createTopic(CONSTRAINED), type);

		setSchema(constr);
	}

	private void setOccurrenceDatatype(OccurrenceType ot) {
		Topic constr = createConstraint(OCCURRENCE_DATATYPE_CONSTRAINT);

		String dataType = ot.getDataType();
		if (dataType.startsWith("xsd:")) {
			dataType = dataType.replace("xsd:", Namespaces.XSD.PREFIX);
		}

		constr.createOccurrence(createTopic(DATATYPE), dataType, topicMap.createLocator(XSD.ANYURI));

		createConstrainedStatement(ot, constr);

		setSchema(constr);
	}

	private void setSchema(Topic construct) {
		if (!exportSchema)
			return;
		Association ass = topicMap.createAssociation(createTopic(BELONGS_TO));
		ass.createRole(createTopic(TMCL.CONTAINER), schema);
		ass.createRole(createTopic(TMCL.CONTAINEE), construct);
	}

	private void setAbstract(Topic t) {
		Topic abstrConst = createConstraint(ABSTRACT_CONSTRAINT);
		Association ass = topicMap.createAssociation(createTopic(CONSTRAINED_TOPIC_TYPE));
		ass.createRole(createTopic(CONSTRAINT), abstrConst);
		ass.createRole(createTopic(CONSTRAINED), t);

		setSchema(abstrConst);
	}

	private void setItemIdentifierConstraint(Topic t, ItemIdentifierConstraint constraint) {
		Topic constr = createConstraint(ITEM_IDENTIFIER_CONSTRAINT);

		addDocumentationOccurrences(constr, constraint);
		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());

		if (constraint.getRegexp() != null) 
			constr.createOccurrence(createTopic(REGEXP), constraint.getRegexp());
		else
			constr.createOccurrence(createTopic(REGEXP), ".*");

		createConstrainedConstruct(t, constr);

		setSchema(constr);
	}

	private void setSubjectIdentifierConstraint(Topic t, SubjectIdentifierConstraint constraint) {
		Topic constr = createConstraint(SUBJECT_IDENTIFIER_CONSTRAINT);

		addDocumentationOccurrences(constr, constraint);
		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());

		if (constraint.getRegexp() != null) 
			constr.createOccurrence(createTopic(REGEXP), constraint.getRegexp());
		else
			constr.createOccurrence(createTopic(REGEXP), ".*");

		createConstrainedTopicType(t, constr);

		setSchema(constr);
	}

	private void setSubjectLocatorConstraint(Topic t, SubjectLocatorConstraint constraint) {
		Topic constr = createConstraint(SUBJECT_LOCATOR_CONSTRAINT);
		addDocumentationOccurrences(constr, constraint);
		addCardinalityOccurrences(constr, constraint.getCardMin(), constraint.getCardMax());

		if (constraint.getRegexp() != null) 
			constr.createOccurrence(createTopic(REGEXP), constraint.getRegexp());
		else
			constr.createOccurrence(createTopic(REGEXP), ".*");

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
			if ((value.endsWith("/")) || (value.endsWith("#")))
				return value + id.substring(index + 1);
			else
				return value + "/" + id.substring(index + 1);
		}

		return id;
	}

	private Topic getTopicType(KindOfTopicType kind) {
		Locator loc = topicMap.createLocator(TOPIC_TYPE);
		switch (kind) {
		case NO_TYPE:
			return null;
		case ASSOCIATION_TYPE:
			loc = topicMap.createLocator(ASSOCIATION_TYPE);
			break;
		case ROLE_TYPE:
			loc = topicMap.createLocator(ROLE_TYPE);
			break;
		case OCCURRENCE_TYPE:
			loc = topicMap.createLocator(OCCURRENCE_TYPE);
			break;
		case NAME_TYPE:
			loc = topicMap.createLocator(NAME_TYPE);
			break;
		}
		return createTopic(loc);
	}

	private Map<String, Topic> getAnnotationMap() {
        if (annotationMap==null)
        	return Collections.emptyMap();
    	return annotationMap;
    }

	private Topic getAnnotationType(Annotation annotation) {
    	Topic type = getAnnotationMap().get(annotation.getKey());
    	if (type==null) {
    		String id = ANNOTATION_PREFIX+"/"+annotation.getKey().replaceAll("\\.", "/"); 
    		Locator loc = topicMap.createLocator(id);
    		type = topicMap.createTopicBySubjectIdentifier(loc);
    		type.addType(annotationType);
    		addAnnotationType(annotation.getKey(), type);
    	}
    	
    	return type;
    }

	private void addAnnotationType(String key, Topic type) {
        if (annotationMap==null) {
        	annotationMap = new HashMap<String, Topic>();
        }
        annotationMap.put(key, type);
    }

	private class ConstraintWrapper {
		private final AbstractConstraint constraint;
		private final AssociationType at;

		public ConstraintWrapper(AbstractConstraint constraint, AssociationType at) {
			super();
			this.constraint = constraint;
			this.at = at;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = at.hashCode();

			if (constraint instanceof RoleConstraint) {
				RoleConstraint rc = (RoleConstraint) constraint;
				if (rc.getType() != null)
					result += prime * result + rc.getType().hashCode();

			} else if (constraint instanceof RolePlayerConstraint) {
				RolePlayerConstraint rpc = (RolePlayerConstraint) constraint;

				if (rpc.getPlayer() != null)
					result += prime * result + rpc.getPlayer().hashCode();
				if (rpc.getRole() != null)
					result += prime * result + rpc.getRole().hashCode();
			}
			if (constraint instanceof AbstractCardinalityConstraint) {
				AbstractCardinalityConstraint acc = (AbstractCardinalityConstraint) constraint;
				result += prime * result + acc.getCardMin().hashCode();
				result += prime * result + acc.getCardMax().hashCode();
			}
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ConstraintWrapper))
				return false;

			AbstractConstraint otherC = ((ConstraintWrapper) obj).constraint;

			if (!(at.equals(((ConstraintWrapper) obj).at)))
				return false;

			if (!constraint.getClass().equals(otherC.getClass()))
				return false;

			if (constraint instanceof RoleConstraint) {
				RoleConstraint rc1 = (RoleConstraint) constraint;
				RoleConstraint rc2 = (RoleConstraint) otherC;

				if (rc1.getType() == null) {
					if (rc2.getType() != null) {
						return false;
					}
				} else if (!rc1.getType().equals(rc2.getType()))
					return false;

				if ((rc1.getCardMin().equals(rc2.getCardMin())) && (rc1.getCardMax().equals(rc2.getCardMax())))
					return true;

			} else if (constraint instanceof RolePlayerConstraint) {
				RolePlayerConstraint rpc1 = (RolePlayerConstraint) constraint;
				RolePlayerConstraint rpc2 = (RolePlayerConstraint) otherC;

				if (rpc1.getPlayer() == null) {
					if (rpc2.getPlayer() != null) {
						return false;
					}
				} else if (!rpc1.getPlayer().equals(rpc2.getPlayer()))
					return false;

				if (rpc1.getRole() == null) {
					if (rpc2.getRole() != null) {
						return false;
					}
				} else if (!rpc1.getRole().equals(rpc2.getRole()))
					return false;

				if ((rpc1.getCardMin().equals(rpc2.getCardMin())) && (rpc1.getCardMax().equals(rpc2.getCardMax())))
					return true;
			}

			return super.equals(obj);
		}
	}
}
