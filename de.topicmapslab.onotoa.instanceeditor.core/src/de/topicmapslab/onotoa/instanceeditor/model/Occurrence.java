package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;

/**
 * occurrence class
 * @author Christian Haß
 *
 */
public class Occurrence extends Characteristic {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	protected Occurrence(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * @return the datatype of the occurrence as string
	 */
	public String getDatatype(){
		
		IPreparedStatement stmt = getStatementProvider().getGetDatatypeStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		return resultSet.get(0).get(0).toString();
	}
	
	/**
	 * sets the datatype of the occurrence 
	 * @param datatypeIri - the datatype iri
	 */
	public void setDatatype(String datatypeIri){
		
		String value = getValue();
		value+="^^"+datatypeIri;
		setValue(value);
	}
	
}
