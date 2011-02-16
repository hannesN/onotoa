package de.topicmapslab.onotoa.instanceeditor.service;

import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * topic map provider interface
 * @author Christian Haß
 *
 */
public interface ITopicMapProvider {

	/**
	 * @return the topic map base locator
	 */
	public String getTopicMapBaseLocator();
	
	/**
	 * creates a prepared statement
	 * @param statement - the parameterized query
	 * @return the statement
	 */
	public IPreparedStatement createPreparedStatement(String statement);
	
	/**
	 * executes a prepared statement
	 * @param statement - the statement
	 * @return the result of the execution
	 */
	public IResultSet<IResult> executePrepatedStement(IPreparedStatement statement);
	
}
