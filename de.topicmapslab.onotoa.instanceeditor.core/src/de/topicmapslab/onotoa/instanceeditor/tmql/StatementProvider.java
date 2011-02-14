package de.topicmapslab.onotoa.instanceeditor.tmql;

import java.util.HashMap;
import java.util.Map;

import de.topicmapslab.onotoa.instanceeditor.service.ITopicMapProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;

/**
 * class to hold prepared statements 
 * @author Christian Haß
 *
 */
public class StatementProvider {

	private final ITopicMapProvider topicMapProvider;
	private Map<String, IPreparedStatement> statements;
	
	/**
	 * constructor
	 * @param topicMapProvider - the topic map provider
	 */
	public StatementProvider(ITopicMapProvider topicMapProvider) {
		this.topicMapProvider = topicMapProvider;
		this.statements = new HashMap<String, IPreparedStatement>();
	}
	
	/**
	 * @return the topic map provider
	 */
	public ITopicMapProvider getTopicMapProvider(){
		return this.topicMapProvider;
	}
	
	/**
	 * @return prepared statement to create a topic
	 */
	public IPreparedStatement getCreateTopicByItemIdentifier(){

		final String statementId = "create-topic-by-item-identifier";
		return getStatement(statementId, "update topics add ? << item");
	}
	
	/**
	 * @return prepared statement to get all topic ids
	 */
	public IPreparedStatement getGetTopics(){
		final String statementId = "get-topics";
		return getStatement(statementId, "// tm:subject >> id");
	}
	
	/**
	 * @return prepared statement to remove a topic
	 */
	public IPreparedStatement getRemoveConstructById(){
		final String statementId = "remove-construct-by-id";
		return getStatement(statementId, "delete ? << id");
	}
	
	
	
	
	/**
	 * returns a prepared statement. creates a new one if not available
	 * @param id - the statement id
	 * @param query - the query
	 * @return the prepared statement
	 */
	private IPreparedStatement getStatement(String id, String query){
		
		IPreparedStatement statement = this.statements.get(id);
		
		if(statement == null){
			statement = this.topicMapProvider.createPreparedStatement(query);
			this.statements.put(id, statement);
		}
		
		return statement;
	}
	
}
