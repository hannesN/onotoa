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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteAssociationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeConstraintItemCommand;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteTopicTypeAction extends Action implements ISelectionChangedListener {

	private TMCLConstruct construct;
	private ModelView modelView;

	public DeleteTopicTypeAction(ModelView modelView) {
		setText("Delete TopicTyype");
		this.modelView = modelView;
		this.modelView.addSelectionChangedListener(this);
		setType(null);
	}

	public void setType(TMCLConstruct type) {
		this.construct = type;
		if (this.construct instanceof TopicType) {
			setText("Delete...");
		} 
		setEnabled(type!=null);
	}

	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?", "Do you really want to delete the selected construct?")) {

			Command cmd = null;

			if (construct instanceof TopicType)
				cmd = new DeleteTopicTypeCommand((TopicType) construct);

			if (construct instanceof NameTypeConstraint)
				cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS);

			if (construct instanceof OccurrenceTypeConstraint)
				cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS);

			if (construct instanceof SubjectIdentifierConstraint)
				cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS);

			if (construct instanceof SubjectLocatorConstraint)
				cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS);
			
			if (construct instanceof AssociationTypeConstraint)
				cmd = new DeleteAssociationConstraintCommand((AssociationTypeConstraint) construct);

			if (cmd != null)
				modelView.getEditingDomain().getCommandStack().execute(cmd);
		}

	}

	public void selectionChanged(SelectionChangedEvent event) {
		TMCLConstruct c = null;
		if ((!event.getSelection().isEmpty()) || ((event.getSelection() instanceof IStructuredSelection))) {

			IStructuredSelection sel = (IStructuredSelection) event.getSelection();

			Object obj = sel.getFirstElement();
			if ( (obj instanceof TMCLConstruct) 
			   &&(!(obj instanceof TopicMapSchema))
			   &&(!(obj instanceof File)) )
				c = (TMCLConstruct) obj;

		}
		setType(c);

	}
}
