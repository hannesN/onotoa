package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * abstract class for typed constructs
 * @author Christian Haß
 *
 */
public abstract class Typed extends Construct {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	public Typed(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * sets the type of a construct
	 * @param type - the type
	 */
	public void setType(Topic type){
		
		IPreparedStatement stmt = getStatementProvider().getSetTypeStatement();
		stmt.set(0, type.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
		
	}
	
	/**
	 * @return the type of a construct
	 */
	public Topic getType(){
		
		IPreparedStatement stmt = getStatementProvider().getGetTypeStatement();
		stmt.set(0, this.getId());

		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		String id = resultSet.get(0).get(0).toString();
		Topic type = new Topic(id, getStatementProvider());
		
		return type;
		
	}
	
}
