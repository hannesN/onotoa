package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public abstract class TopicTypeAction extends ValidationAction {

	protected KindOfTopicType kindOfType;

	public TopicTypeAction(KindOfTopicType type) {
		super();
		this.kindOfType = type;
	}

	public TopicTypeAction(EObject model) {
		super(model);
	}

	public KindOfTopicType getKindOfType() {
		return kindOfType;
	}

	protected abstract AbstractCommand getCommand(TopicType topicType);

}