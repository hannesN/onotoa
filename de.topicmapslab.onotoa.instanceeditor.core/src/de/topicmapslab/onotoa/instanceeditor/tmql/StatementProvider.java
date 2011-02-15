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

	private final static String DEFAULT_OCCURRENCE_TYPE = "http://onotoa.topicmapslab.de/default-occurrence";
	
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
	public IPreparedStatement getCreateTopicByItemIdentifierStatement(){
		return getStatement("update topics add ? << item");
	}
	
	/**
	 * @return prepared statement to get all topic ids
	 */
	public IPreparedStatement getGetTopicsStatement(){
		return getStatement("// tm:subject >> id");
	}
	
	/**
	 * @return prepared statement to remove a topic
	 */
	public IPreparedStatement getRemoveConstructByIdStatement(){
		return getStatement("delete ? << id");
	}
	
	/**
	 * @return prepared statement to get best label
	 */
	public IPreparedStatement getGetBestLableStatement(){
		return getStatement("fn:best-lebel( ? << id )");
	}
	
	/**
	 * @return prepared statement to get types of a topic
	 */
	public IPreparedStatement getGetTopicTypesStatement(){
		return getStatement("? << id << instances >> id");
	}
	
	/**
	 * @return prepared statement to add a type to the topic
	 */
	public IPreparedStatement getAddTopicTypeStatement(){
		return getStatement("UPDATE types ADD ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get topic names
	 */
	public IPreparedStatement getGetNamesStatement(){
		return getStatement("? << id >> characteristics tm:name >> id");
	}
	
	/**
	 * @return prepared statement to create a new name
	 */
	public IPreparedStatement getCreateNameStatement(){
		return getStatement("UPDATE names ADD \"\" WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get topic occurrences
	 */
	public IPreparedStatement getGetOccurrencesStatement(){
		return getStatement("? << id >> characteristics tm:occurrence >> id");
	}
	
	/**
	 * @return prepared statement to create a new occurrence
	 */
	public IPreparedStatement getCreateOccurrenceStatement(){
		return getStatement("UPDATE occurrences " + DEFAULT_OCCURRENCE_TYPE + " ADD \"\" WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to add a supertype to a topic
	 */
	public IPreparedStatement getAddSupertypeStatement(){
		return getStatement("UPDATE supertypes ADD ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get the supertypes of a topic
	 */
	public IPreparedStatement getGetSupertypesStatement(){
		return getStatement("? << id >> supertypes >> id");
	}
	
	/**
	 * @return prepared statement to get the subtypes of a topic
	 */
	public IPreparedStatement getGetSubtypesStatement(){
		return getStatement("? << id >> subtypes >> id ");
	}
	
	
	/**
	 * returns a prepared statement. creates a new one if not available
	 * @param query - the query
	 * @return the prepared statement
	 */
	private IPreparedStatement getStatement(String query){
		
		IPreparedStatement statement = this.statements.get(query);
		
		if(statement == null){
			statement = this.topicMapProvider.createPreparedStatement(query);
			this.statements.put(query, statement);
		}
		
		return statement;
	}
	
}
