/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * 
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractCreateConstraintAction extends Action implements ISelectionChangedListener {

	private TopicType topicType;
	private ModelView view;

	/**
	 * 
	 * @param view the {@link ModelView} 
	 */
	public AbstractCreateConstraintAction(ModelView view) {
		this.view = view;
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().addSelectionChangedListener(this);
	}

	/**
	 * Sets the topic type which will be the container of the constraint.
	 * 
	 * @param topicType the container of the constraint
	 */
	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
		setEnabled(topicType != null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		view.getEditingDomain().getCommandStack().execute(getCommand());
	}

	protected TopicType getTopicType() {
		return topicType;
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		TopicType tt = null;
		if ((!event.getSelection().isEmpty()) || ((event.getSelection() instanceof IStructuredSelection))) {

			IStructuredSelection sel = (IStructuredSelection) event.getSelection();

			Object obj = sel.getFirstElement();
			if (obj instanceof TopicType)
				tt = (TopicType) obj;

		}
		setTopicType(tt);
	}

	protected abstract Command getCommand();

}
