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
	
	@Override
	public void setModel(Object model) {
		if (getModel()!=null)
			removeScopeAdapters();
		super.setModel(model);
		updateScopeAdapters();
	}
	
	private void updateScopeAdapters() {
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters();
			sc.eAdapters().add(this);
		}
	}
	
	private void removeScopeAdapters() {
		for (ScopeConstraint sc : getCastedModel().getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters();
			sc.eAdapters().add(this);
		}
	}

	private ScopedConstraint getCastedModel() {
		return (ScopedConstraint) getModel();
	}

}