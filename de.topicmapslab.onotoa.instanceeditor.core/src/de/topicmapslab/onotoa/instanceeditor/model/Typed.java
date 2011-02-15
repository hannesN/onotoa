package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;

/**
 * abstract class for typed constructs
 * @author Christian Haß
 *
 */
public abstract class Typed extends Construct {

	/**
	 * constructor
	 * @param id - the construct id
	 * @param statementProvider - the statement provider
	 */
	public Typed(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
	
	
	
}
