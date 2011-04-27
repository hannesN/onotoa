package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;

/**
 * abstract reifier class
 * @author Christian Haß
 *
 */
public abstract class Reifiable extends Typed {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	public Reifiable(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * sets the reifier
	 * @param reifier - the reifier
	 */
	public void setReifier(Topic reifier){
		
		IPreparedStatement stmt = getStatementProvider().getSetReifierStatement();
		stmt.set(0, reifier.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
		
	}
	
	/**
	 * @return the reifier or <code>null</code>
	 */
	public Topic getReifier(){
	
		IPreparedStatement stmt = getStatementProvider().getGetReifierStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		if(resultSet.isEmpty())
			return null;
		
		String id = resultSet.get(0).get(0).toString();
		Topic reifier = new Topic(id, getStatementProvider());
		
		return reifier;
	}
	
	
	
}
