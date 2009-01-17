/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.Node;
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

	public TopicType createTopicType() {
		return topicIndexer.createTopicType();
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
	
	public boolean isFilterActivated(KindOfTopicType kindOfType) {
		TopicMapSchema topicMapSchema = getInstance().getTopicMapSchema();
		switch (kindOfType) {
		case ASSOCIATION_TYPE: 
				return topicMapSchema.isActiveAssociationTypeConstraint(); 
		case NAME_TYPE:
				return topicMapSchema.isActiveNameTypeConstraint();
		case OCCURENCE_TYPE:
				return topicMapSchema.isActiveOccurenceTypeConstraint();
		case ROLE_TYPE:
				return topicMapSchema.isActiveRoleTypeConstraint();
		case SCOPE_TYPE:
				return topicMapSchema.isActiveScopeTypeConstraint();
		case TOPIC_TYPE:
				return topicMapSchema.isActiveTopicTypeConstraint();
		}
		
		return false;
	}
}
