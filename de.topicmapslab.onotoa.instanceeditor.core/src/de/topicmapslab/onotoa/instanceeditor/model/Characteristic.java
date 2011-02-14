package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;

public abstract class Characteristic extends Scoped {

	public Characteristic(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
}
