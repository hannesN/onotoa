package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;

public abstract class Scoped extends Reifiable {

	public Scoped(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
}
