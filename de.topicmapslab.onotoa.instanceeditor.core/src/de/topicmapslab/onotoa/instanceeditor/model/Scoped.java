package de.topicmapslab.onotoa.instanceeditor.model;

import java.util.HashSet;
import java.util.Set;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;
import de.topicmapslab.tmql4j.components.processor.prepared.IPreparedStatement;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;

/**
 * abstract scoped class
 * @author Christian Haß
 */
public abstract class Scoped extends Reifiable {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	public Scoped(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	/**
	 * adds a theme to the scope of a construct
	 * @param theme - the theme
	 */
	public void addTheme(Topic theme){
		
		IPreparedStatement stmt = getStatementProvider().getAddThemeStatement();
		stmt.set(0, theme.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
	}
		
	/**
	 * removes a theme from the constructs scope
	 * @param theme - the theme
	 */
	public void removeTheme(Topic theme){
		
		IPreparedStatement stmt = getStatementProvider().getRemoveThemeStatement();
		stmt.set(0, theme.getId());
		stmt.set(1, this.getId());
		
		executePreparedStatement(stmt);
	}
	
	/**
	 * @return the themes or empty set
	 */
	public Set<Topic> getThemes(){
		
		IPreparedStatement stmt = getStatementProvider().getGetThemesStatement();
		stmt.set(0, this.getId());
		
		Set<Topic> result = new HashSet<Topic>();
		
		for(IResult r:executePreparedStatement(stmt)){
			
			String id = r.get(0).toString();
			Topic theme = new Topic(id, getStatementProvider());
			result.add(theme);
		}
		
		return result;
	}
	
}
