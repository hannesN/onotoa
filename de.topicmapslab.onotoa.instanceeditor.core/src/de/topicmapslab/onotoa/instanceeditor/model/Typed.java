package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;

public abstract class Typed extends Construct {

	public Typed(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
}
