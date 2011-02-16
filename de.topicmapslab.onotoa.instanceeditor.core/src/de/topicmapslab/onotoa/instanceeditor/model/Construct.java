package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * abstract construct
 * @author Christian Haß
 *
 */
public abstract class Construct {

	private final String id;
	private final StatementProvider statementProvider;
	
	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	protected Construct(String id, StatementProvider statementProvider) {
		this.id = id;
		this.statementProvider = statementProvider;
	}

	/**
	 * executes an prepared tmql statement
	 * @param statement - the statement
	 * @return the result set
	 */
	public IResultSet<IResult> executePreparedStatement(IPreparedStatement statement){
		return this.statementProvider.getTopicMapProvider().executePrepatedStement(statement);
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the statementProvider
	 */
	public StatementProvider getStatementProvider() {
		return this.statementProvider;
	}
	
	
	
}
