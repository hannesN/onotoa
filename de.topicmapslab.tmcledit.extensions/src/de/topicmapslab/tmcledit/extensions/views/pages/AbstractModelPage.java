package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.part.Page;

import de.topicmapslab.tmcledit.model.TopicMapSchema;

public abstract class AbstractModelPage extends Page implements Adapter {

	private EObject model;

	private TopicMapSchema topicMapSchema;

	private EditingDomain editingDomain;

	private Diagram diagram;

	private Notifier target;

	public AbstractModelPage() {
		super();
	}

	public void setModel(EObject model) {

		if (this.model != null)
			this.model.eAdapters().remove(this);
		this.model = model;
		this.model.eAdapters().add(this);
		updateUI();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

	public abstract void updateUI();

	public EObject getModel() {
		return model;
	}

	protected TopicMapSchema getTopicMapSchema() {
		return topicMapSchema;
	}

	public void setTopicMapSchema(TopicMapSchema topicMapSchema) {
		this.topicMapSchema = topicMapSchema;
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	protected EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

}