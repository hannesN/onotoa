package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.IResult;

public class Association extends Scoped {

	protected Association(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/// TODO reguires roles axis from tmql 3.1
	
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
	
	public Set<Role> getRoles(Topic roleType){
		return null;
	}
	
	public Set<Role> getOtherRoles(Role role){
		return null;
	}
	
}
