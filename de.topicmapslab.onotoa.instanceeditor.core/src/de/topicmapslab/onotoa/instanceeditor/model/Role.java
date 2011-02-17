package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;

/**
 * role class
 * @author Christian Haß
 *
 */
public class Role extends Reifiable {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	protected Role(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * @return the parent of the role
	 */
	public Association getAssociation(){
		
		IPreparedStatement stmt = getStatementProvider().getGetRoleAssociationStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		String id = resultSet.get(0).get(0).toString();
		Association association = new Association(id, getStatementProvider());
		
		return association;
	}
		
	/**
	 * @return the role player
	 */
	public Topic getPlayer(){
		
		IPreparedStatement stmt = getStatementProvider().getGetRolePlayerStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		String id = resultSet.get(0).get(0).toString();
		Topic player = new Topic(id, getStatementProvider());
		
		return player;
	}
	
}
