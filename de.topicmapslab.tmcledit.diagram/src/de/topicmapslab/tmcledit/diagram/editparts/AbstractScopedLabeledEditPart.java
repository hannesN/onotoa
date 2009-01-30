package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Collections;
import java.util.List;

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	public AbstractScopedLabeledEditPart() {
		super();
	}

	protected void addScopeText(StringBuffer buffer) {
		for (ScopeConstraint sc : getScope()) {
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
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters();
			sc.eAdapters().add(this);
		}
	}
	
	private void removeScopeAdapters() {
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters();
			sc.eAdapters().add(this);
		}
	}

	private List<ScopeConstraint> getScope() {
		if (getModel() instanceof AbstractTypedConstraint) {
			TopicType type = ((AbstractTypedConstraint)getModel()).getType();
			if (type instanceof ScopedTopicType)
				return ((ScopedTopicType)type).getScope();
		}
		return Collections.emptyList();
	}
}