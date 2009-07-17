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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.index;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * The Indexer is a singleton created for one {@link File} which contains different Indexer for
 * {@link Diagram} elements and {@link TopicMapSchema} elements
 * 
 * @author Hannes Niederhausen
 *
 */
public class ModelIndexer {
	private static ModelIndexer instance;
	
	private AssociationIndexer associationIndexer;
	private TopicIndexer topicIndexer;
	private TopicTypeNodeIndexer nodeIndexer;
	
	private final File file;
	
	private ModelIndexer(File file) {
		this.file = file;
	}

	public static void createInstance(File file) {
		if (instance!=null)
			instance.dispose();
		
		instance = new ModelIndexer(file);
		
		getInstance().topicIndexer = new TopicIndexer();
		getInstance().topicIndexer.init(file.getTopicMapSchema());
		
		getInstance().nodeIndexer = new TopicTypeNodeIndexer();
		getInstance().nodeIndexer.init(file);
		
		getInstance().associationIndexer = new AssociationIndexer();
		getInstance().associationIndexer.init(file.getTopicMapSchema());
		
	}
	
	private void dispose() {
		if (topicIndexer!=null)
			topicIndexer.dispose();
	
		if (nodeIndexer!=null)
			nodeIndexer.dispose();
	}

	public static ModelIndexer getInstance() {
		return instance;
	}

	public TopicMapSchema getTopicMapSchema() {
		return file.getTopicMapSchema();
	}

	public TopicType createTopicType(KindOfTopicType kind) {
		return topicIndexer.createTopicType(kind);
	}

	public TopicType createTopicType(String id) {
		return topicIndexer.createTopicType(id);
	}

	public List<TopicType> getRoleTypes() {
		return topicIndexer.getRoleTypes();
	}

	public List<TopicType> getScopeTypes() {
		return topicIndexer.getScopeTypes();
	}

	public TopicType getTopicType(String id) {
		return topicIndexer.getTopicType(id);
	}

	public List<TopicType> getTopicTypes() {
		return topicIndexer.getTopicTypes();
	}

	public Node getNodeFor(TopicType topicType, Diagram diagram) {
		return nodeIndexer.getNodeFor(topicType, diagram);
	}

	public Node getNodeFor(AssociationTypeConstraint assConstraint,
			Diagram diagram) {
		return nodeIndexer.getNodeFor(assConstraint, diagram);
	}

	public List<Edge> getEdges(Diagram d, EdgeType type) {
		return nodeIndexer.getEdges(d, type);
	}
	
	public EList<Diagram> getDiagrams() {
		return file.getDiagrams();
	}

	public List<Edge> getEdgesUsingTopicType(TopicType type) {
		return nodeIndexer.getEdgesUsingTopicType(type);
	}

	public List<TopicType> getInstanceTypes(TopicType topicType) {
		return topicIndexer.getInstanceTypes(topicType);
	}

	public List<TopicType> getSubTypes(TopicType topicType) {
		return topicIndexer.getSubTypes(topicType);
	}
		
	public List<AssociationType> getAssociationTypes() {
		return topicIndexer.getAssociationTypes();
	}
	
	public List<ScopedTopicType> getScopedTopicTypes() {
		return topicIndexer.getScopedTopicTypes();
	}

	public List<RolePlayerConstraint> getRolePlayerConstraintsFor(
			TopicType topicType) {
		return associationIndexer.getRolePlayerConstraintsFor(topicType);
	}

	public List<TopicType> getUsedAsAko(TopicType topicType) {
		return topicIndexer.getUsedAsAko(topicType);
	}

	public List<TopicType> getUsedAsIsa(TopicType topicType) {
		return topicIndexer.getUsedAsIsa(topicType);
	}
	
	
	
}
