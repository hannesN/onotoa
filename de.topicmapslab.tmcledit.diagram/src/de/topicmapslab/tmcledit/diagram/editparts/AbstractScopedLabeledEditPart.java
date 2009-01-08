package de.topicmapslab.tmcledit.diagram.editparts;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedConstraint;

public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	public AbstractScopedLabeledEditPart() {
		super();
	}

	protected void addScopeText(StringBuffer buffer) {
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			buffer.append("\n@");
			buffer.append(sc.getType().getName());
			buffer.append("  [");
			buffer.append(sc.getCardMin());
			buffer.append("..");
			buffer.append(sc.getCardMax());
			buffer.append("]");
		}
	}
	
	private ScopedConstraint getCastedModel() {
		return (ScopedConstraint) getModel();
	}

}