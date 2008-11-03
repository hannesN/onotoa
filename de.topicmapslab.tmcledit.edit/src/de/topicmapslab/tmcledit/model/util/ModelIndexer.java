/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import java.util.List;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

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
	
	private ModelIndexer(File file) {
		
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


	public TopicType createTopicType() {
		return topicIndexer.createTopicType();
	}


	public TopicType createTopicType(String id) {
		return topicIndexer.createTopicType(id);
	}


	public List<TopicType> getRoleTypes() {
		return topicIndexer.getRoleTypes();
	}


	public TopicType getTopicType(String id) {
		return topicIndexer.getTopicType(id);
	}


	public List<TopicType> getTopicTypes() {
		return topicIndexer.getTopicTypes();
	}

	public TypeNode getNodeFor(TopicType topicType, Diagram diagram) {
		return nodeIndexer.getNodeFor(topicType, diagram);
	}


	public List<Edge> getEdges(Diagram d, EdgeType type) {
		return nodeIndexer.getEdges(d, type);
	}
}
