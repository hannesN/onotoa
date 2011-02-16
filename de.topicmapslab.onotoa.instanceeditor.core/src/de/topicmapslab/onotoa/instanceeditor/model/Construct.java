package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Set;

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

	/**
	 * @return the item identifier of the topic or an empty set
	 */
	public Set<String> getItemIdentifier(){
		
		Set<String> result = new HashSet<String>();
		
		IPreparedStatement stmt = getStatementProvider().getGetItemIdentifierStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			result.add(r.get(0).toString());
		}
		
		return result;
	}
	
	/**
	 * adds an item identifier to the construct
	 * @param iri - the item identifier as string
	 */
	public void addItemIdentifier(String iri){
		
		IPreparedStatement stmt = getStatementProvider().getAddItemIdentifierStatement();
		stmt.setString(0, iri);
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Construct other = (Construct) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
