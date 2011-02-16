package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;

/**
 * association class
 * @author Christian Haß
 *
 */
public class Association extends Scoped {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	protected Association(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}

	/**
	 * @return all roles of the association
	 */
	public Set<Role> getRoles(){

		Set<Role> result = new HashSet<Role>();
		
		IPreparedStatement stmt = getStatementProvider().getGetAssociationRolesStatement();
		stmt.set(0, this.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Role role = new Role(r.get(0).toString(), getStatementProvider());
			result.add(role);
		}
		
		return result;
	}
	
	/**
	 * selects all roles which have a specific type
	 * @param roleType - the role type
	 * @return roles of this type or empty set
	 */
	public Set<Role> getRoles(Topic roleType){
		
		Set<Role> result = new HashSet<Role>();
		
		IPreparedStatement stmt = getStatementProvider().getGetAssociationRolesByTypeStatement();
		stmt.set(0, this.getId());
		stmt.set(1, roleType.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Role role = new Role(r.get(0).toString(), getStatementProvider());
			result.add(role);
		}
		
		return result;
	}
	
	/**
	 * selects all counter roles of a specific role
	 * @param role - the role
	 * @return set of roles or empty set
	 * NOTE: if the role does not belong to the association, the method will return all roles
	 */
	public Set<Role> getOtherRoles(Role role){

		Set<Role> result = new HashSet<Role>();
		
		IPreparedStatement stmt = getStatementProvider().getGetAssociationCounterRolesStatement();
		stmt.set(0, this.getId());
		stmt.set(1, role.getId());
		
		for(IResult r:executePreparedStatement(stmt)){
			Role counterrole = new Role(r.get(0).toString(), getStatementProvider());
			result.add(counterrole);
		}
		
		return result;
	}
	
}
