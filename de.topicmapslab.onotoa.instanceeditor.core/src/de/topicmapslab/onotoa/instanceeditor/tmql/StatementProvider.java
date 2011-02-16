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
		return getStatement("DELETE ? << id");
	}
	
	/**
	 * @return prepared statement to get best label
	 */
	public IPreparedStatement getGetBestLableStatement(){
		return getStatement("fn:best-label( ? << id )");
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
		return getStatement("%pragma taxonometry tm:transitive ? << id >> supertypes >> id");
	}
	
	/**
	 * @return prepared statement to get the subtypes of a topic
	 */
	public IPreparedStatement getGetSubtypesStatement(){
		return getStatement("? << id >> subtypes >> id ");
	}
	
	/**
	 * @return prepared statement to get a characteristics value
	 */
	public IPreparedStatement getGetCharacteristicValueStatement(){
		return getStatement("? << id >> atomify");
	}
	
	/**
	 * @return prepared statement to set a name value
	 */
	public IPreparedStatement getSetCharacteristicValueStatement(){
		return getStatement("UPDATE characteristics SET ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get the datatype
	 */
	public IPreparedStatement getGetDatatypeStatement(){
		return getStatement("fn:has-datatype( ? << id )");
	}
	
	/**
	 * @return prepared statement to set the type of a typed construct
	 */
	public IPreparedStatement getSetTypeStatement(){
		return getStatement("UPDATE types SET ? << id WHERE ? << id ");
	}
	
	/**
	 * @return prepared statement to get the type of a typed construct
	 */
	public IPreparedStatement getGetTypeStatement(){
		return getStatement("? << id << typed >> id");
	}
	
	/**
	 * @return prepared statement to add a theme
	 */
	public IPreparedStatement getAddThemeStatement(){
		return getStatement("UPDATE scope ADD ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get the reifier
	 */
	public IPreparedStatement getGetReifierStatement(){
		return getStatement("? << id << reifier >> id");
	}
	
	/**
	 * @return prepared statement to set an reifier
	 */
	public IPreparedStatement getSetReifierStatement(){
		return getStatement("UPDATE reifier SET ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get association roles
	 */
	public IPreparedStatement getGetAssociationRolesStatement(){
		return getStatement("? << id >> roles >> id");
	}
	
	/**
	 * @return prepared statement to get association roles yb type
	 */
	public IPreparedStatement getGetAssociationRolesByTypeStatement(){
		return getStatement("? << id >> roles [ . >> types == ? << id ] >> id ");
	}
	
	/**
	 * @return prepared statement to get association roles execluding one specific role (i.e. the counter roles)
	 */
	public IPreparedStatement getGetAssociationCounterRolesStatement(){
		return getStatement("? << id >> roles >> id MINUS ?");
	}
	
	/**
	 * @return prepared statement to get the association of a specific role
	 */
	public IPreparedStatement getGetRoleAssociationStatement(){
		return getStatement("? << id << roles >> id");
	}
	
	/**
	 * @return prepared statement to get role player
	 */
	public IPreparedStatement getGetRolePlayerStatement(){
		return getStatement("? << id >> playes >> id");
	}
	
	/**
	 * @return prepared statement to remove a theme
	 */
	public IPreparedStatement getRemoveThemeStatement(){
		return getStatement("UPDATE scope REMOVE ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to remove a type
	 */
	public IPreparedStatement getRemoveTypeStatement(){
		return getStatement("UPDATE types REMOVE ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to delete a contruct
	 */
	public IPreparedStatement getDeleteConstructStatement(){
		return getStatement("DELETE ? << id");
	}
	
	/**
	 * @return prepared statement to get roles played
	 */
	public IPreparedStatement getRolesPlayedStatement(){
		return getStatement("? << id >> roles >> id");
	}
	
	/**
	 * @return prepared statement to remove a supertype
	 */
	public IPreparedStatement getRemoveSupertypeStatement(){
		return getStatement("UPDATE supertypes REMOVE ? << id WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to get a topic by an subject identifier
	 */
	public IPreparedStatement getGetTopicBySiStatement(){
		return getStatement("? << indicators >> id");
	}
	
	/**
	 * @return prepared statement to get a topic by an subject locator
	 */
	public IPreparedStatement getGetTopicBySlStatement(){
		return getStatement("? << locators >> id");
	}
	
	/**
	 * @return prepared statement to get a topic by an item identifier
	 */
	public IPreparedStatement getGetTopicByIiStatement(){
		return getStatement("? << item >> id");
	}
	
	/**
	 * @return prepared statement to get the subject identifier of a topic
	 */
	public IPreparedStatement getGetSubjectIdentifierStatement(){
		return getStatement("? << id >> indicators >> atomify");
	}
	
	/**
	 * @return prepared statement to get the subject locator of a topic
	 */
	public IPreparedStatement getGetSubjectLocatorStatement(){
		return getStatement("? << id >> locators >> atomify");
	}
	
	/**
	 * @return prepared statement to get the item identifier of a topic
	 */
	public IPreparedStatement getGetItemIdentifierStatement(){
		return getStatement("? << id >> item >> atomify");
	}
	
	/**
	 * @return prepared statement to add a subject identifier
	 */
	public IPreparedStatement getAddSubjectIdentifierStatement(){
		return getStatement("UPDATE indicators ADD ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to add a subject locator
	 */
	public IPreparedStatement getAddSubjectLocatorStatement(){
		return getStatement("UPDATE locators ADD ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to add a item identifier
	 */
	public IPreparedStatement getAddItemIdentifierStatement(){
		return getStatement("UPDATE item ADD ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to remove an subject identifier
	 */
	public IPreparedStatement getRemoveSubjectIdentifierStatement(){
		return getStatement("UPDATE indicators REMOVE ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to remove an subject locator
	 */
	public IPreparedStatement getRemoveSubjectLocatorStatement(){
		return getStatement("UPDATE locators REMOVE ? WHERE ? << id");
	}
	
	/**
	 * @return prepared statement to remove an item identifier
	 */
	public IPreparedStatement getRemoveItemIdentifierStatement(){
		return getStatement("UPDATE item REMOVE ? WHERE ? << id");
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
