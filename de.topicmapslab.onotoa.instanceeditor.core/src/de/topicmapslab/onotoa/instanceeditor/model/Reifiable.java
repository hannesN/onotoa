package de.topicmapslab.onotoa.instanceeditor.model;

import de.topicmapslab.onotoa.instanceeditor.tmql.StatementProvider;

public abstract class Reifiable extends Typed {

	public Reifiable(String id, StatementProvider statementProvider) {
		super(id, statementProvider);
	}
}
