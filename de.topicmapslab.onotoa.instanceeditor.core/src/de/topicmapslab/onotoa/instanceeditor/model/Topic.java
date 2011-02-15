package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;
import de.topicmapslab.tmql4j.components.processor.results.IResultSet;

/**
 * topic class 
 * @author Christian Haß
 *
 */
public class Topic extends Construct {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	protected Topic(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * @return the best label of the topic
	 */
	public String getBestLabel(){
		
		IPreparedStatement stmt = getStatementProvider().getGetBestLableStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		return resultSet.get(0).get(0);
	}
	
	/**
	 * @return the types of the topic
	 */
	public Set<Topic> getTypes(){
		
		Set<Topic> result = new HashSet<Topic>();
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicTypesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Topic type = new Topic(r.get(0).toString(), getStatementProvider());
			result.add(type);
		}
		
		return result;
	}
	
	/**
	 * adds a type to the topic
	 * @param type - the type
	 */
	public void addType(Topic type){
		
		IPreparedStatement stmt = getStatementProvider().getGetTopicTypesStatement();
		stmt.set(0, type.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
	}
	
	/**
	 * @return the names of the topic
	 */
	public Set<Name> getNames(){
		
		Set<Name> result = new HashSet<Name>();
		
		IPreparedStatement stmt = getStatementProvider().getGetNamesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Name name = new Name(r.get(0).toString(), getStatementProvider());
			result.add(name);
		}
		
		return result;
	}
	
	/**
	 * creates a new name with value 'empty string'
	 * @return the name object
	 */
	public Name createName(){
		
		IPreparedStatement stmt = getStatementProvider().getCreateNameStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		String id = resultSet.get(0).get("names");
		
		Name name = new Name(id, getStatementProvider());
		
		return name;
	}
	

	
	/**
	 * @return the occurrences of the topic
	 */
	public Set<Occurrence> getOccurrences(){
		
		Set<Occurrence> result = new HashSet<Occurrence>();
		
		IPreparedStatement stmt = getStatementProvider().getGetOccurrencesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Occurrence occurrence = new Occurrence(r.get(0).toString(), getStatementProvider());
			result.add(occurrence);
		}
		
		return result;
	}
	
	/**
	 * creates a new occurrence with value 'empty string' and type 'http://onotoa.topicmapslab.de/default-occurrence'
	 * @return the name object
	 */
	public Occurrence createOccurrence(){
		
		IPreparedStatement stmt = getStatementProvider().getCreateNameStatement();
		stmt.set(0, this.getId());
		
		IResultSet<IResult> resultSet = executePreparedStatement(stmt);
		
		String id = resultSet.get(0).get("occurrences");
		
		Occurrence occurrence = new Occurrence(id, getStatementProvider());
		
		return occurrence;
	}
	


	/**
	 * adds a supertype to the topic
	 * @param supertype - the supertype
	 */
	public void addSuperType(Topic supertype){
		
		IPreparedStatement stmt = getStatementProvider().getAddSupertypeStatement();
		stmt.set(0, supertype.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
		
	}

	/**
	 * @return the supertypes of the topic
	 */
	public Set<Topic> getSupertypes(){
		
		Set<Topic> result = new HashSet<Topic>();
		
		IPreparedStatement stmt = getStatementProvider().getGetSupertypesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Topic type = new Topic(r.get(0).toString(), getStatementProvider());
			result.add(type);
		}
		
		return result;
	}
	
	/**
	 * @return the subtypes of the topic
	 */
	public Set<Topic> getSubtypes(){
		
		Set<Topic> result = new HashSet<Topic>();
		
		IPreparedStatement stmt = getStatementProvider().getGetSubtypesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Topic type = new Topic(r.get(0).toString(), getStatementProvider());
			result.add(type);
		}
		
		return result;
	}
	
	
// TODO:
//	removeType()
//	removeName()
//	removeOccurrence()
//	getRoles()
//	removeSuperType()
	
}
