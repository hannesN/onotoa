package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.Page;


public abstract class AbstractModelPage extends Page implements Adapter {

	private EObject model;

	private Notifier target;
	
	private Control control;
	
	private CommandStack commandStack;

	public AbstractModelPage() {
		super();
	}

	public void setModel(Object model) {
		if (this.model != null)
			this.model.eAdapters().remove(this);
			
		this.model = (EObject) model;
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
	public Control getControl() {
		return control;
	}
	
	public void setControl(Control control) {
		this.control = control;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

	protected CommandStack getCommandStack() {
		return commandStack;
	}
	
	public void setCommandStack(CommandStack commandStack) {
		this.commandStack = commandStack;
	}
	
	public abstract void updateUI();

	public EObject getModel() {
		return model;
	}
}