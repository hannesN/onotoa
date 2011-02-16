package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * abstract class for characteristics
 * @author Christian Haß
 *
 */
public abstract class Characteristic extends Scoped {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	public Characteristic(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}

	/**
	 * sets the value of the characteristic
	 * @param value - the value
	 */
	public void setValue(String value){
		
		if(value == null)
			return;
		
		IPreparedStatement stmt = getStatementProvider().getSetCharacteristicValueStatement();
		
		stmt.setString(0, value);
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
	}
	
	/**
	 * @return the value of the characteristic
	 */
	public String getValue(){
		
		IPreparedStatement stmt = getStatementProvider().getGetCharacteristicValueStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		return resultSet.get(0).get(0).toString();
	}

}



