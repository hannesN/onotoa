package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import de.topicmapslab.onotoa.instanceeditor.service.ITopicMapProvider;
import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * topic class
 * @author Christian
 *
 */
public class TopicMap extends Construct {
	
	private Random random;
	private final ITopicMapProvider topicMapProvider;
	
	/**
	 * constructor
	 * @param topicMapProvider - the topic map provider
	 */
	public TopicMap(ITopicMapProvider topicMapProvider) {
		super(null, new StatementProvider(topicMapProvider));
		this.topicMapProvider = topicMapProvider;
		this.random = new Random();
	}
	
	/**
	 * creates a new topic
	 * @return the topic
	 */
	public Topic createTopic(){
		
		IPreparedStatement stmt = getStatementProvider().getCreateTopicByItemIdentifierStatement();
		stmt.set(0, createItemIdentifier());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		String id = resultSet.get(0).get("topics").toString();
		Topic topic = new Topic(id, getStatementProvider());
		
		return topic;
	}

	/**
	 * returns all topics
	 * @return set of topics
	 */
	public Set<Topic> getTopics(){
		
		Set<Topic> result = new HashSet<Topic>();
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicsStatement();
		
		for(IResult r:executePreparedStatement(stmt)){
			Topic topic = new Topic(r.get(0).toString(), getStatementProvider());
			result.add(topic);
		}
		
		return result;
	}
	
	/**
	 * removes the given topic
	 * @param topic - the topic
	 */
	public void removeTopic(Topic topic){
		
		IPreparedStatement stmt = getStatementProvider().getRemoveConstructByIdStatement();
		stmt.set(0, topic.getId());
		
		executePreparedStatement(stmt);
		
	}
	
	/**
	 * selects a topic by its subject identifier
	 * @param iri - the subject identifier iri
	 * @return the topic or <code>null</code> if not found
	 */
	public Topic getTopicBySubjectIdentifier(String iri){
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicBySiStatement();
		stmt.setString(0, iri);
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		if(resultSet.isEmpty())
			return null;
		
		String id = resultSet.get(0).get(0).toString();
		
		Topic topic = new Topic(id, getStatementProvider());
		
		return topic;
	}
	
	/**
	 * selects a topic by its subject locator
	 * @param iri - the subject locator iri
	 * @return the topic or <code>null</code> if not found
	 */
	public Topic getTopicBySubjectLocator(String iri){
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicBySlStatement();
		stmt.setString(0, iri);
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		if(resultSet.isEmpty())
			return null;
		
		String id = resultSet.get(0).get(0).toString();
		
		Topic topic = new Topic(id, getStatementProvider());
		
		return topic;
	}

	/**
	 * selects a topic by its item identifier
	 * @param iri - the item identifier iri
	 * @return the topic or <code>null</code> if not found
	 */
	public Topic getTopicByItemIdentifier(String iri){
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicByIiStatement();
		stmt.setString(0, iri);
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		if(resultSet.isEmpty())
			return null;
		
		String id = resultSet.get(0).get(0).toString();
		
		Topic topic = new Topic(id, getStatementProvider());
		
		return topic;
	}
		
	/**
	 * @return a randomly created item identifier as string
	 */
	private String createItemIdentifier(){
		return this.topicMapProvider.getTopicMapBaseLocator() + Long.toString(this.random.nextLong());
	}
	
	
	
	
	
	
}
