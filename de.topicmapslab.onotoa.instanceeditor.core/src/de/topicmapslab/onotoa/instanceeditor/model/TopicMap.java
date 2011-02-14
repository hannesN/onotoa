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
		
		IPreparedStatement stmt = getStatementProvider().getCreateTopicByItemIdentifier();
		stmt.set(0, createItemIdentifier()); // XXX check for uniqueness
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		String id = resultSet.get(0).get(0);
		Topic topic = new Topic(id, getStatementProvider());
		
		return topic;
	}

	/**
	 * returns all topics
	 * @return set of topics
	 */
	public Set<Topic> getTopics(){
		
		Set<Topic> result = new HashSet<Topic>();
		
		IPreparedStatement stmt = getStatementProvider().getGetTopics();
		
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
		
		IPreparedStatement stmt = getStatementProvider().getRemoveConstructById();
		stmt.set(0, topic.getId());
		
		executePreparedStatement(stmt); // TODO check return value! (TODO: find out what will be returned)
		
	}
	
	private String createItemIdentifier(){
		return this.topicMapProvider.getTopicMapBaseLocator() + Long.toString(this.random.nextLong());
	}
	
}
