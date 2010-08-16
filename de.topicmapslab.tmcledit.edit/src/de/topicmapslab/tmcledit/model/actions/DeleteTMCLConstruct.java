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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
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
public class DeleteTMCLConstruct extends Action implements ISelectionChangedListener {

	private List<TMCLConstruct> constructList;
	private ModelView modelView;
	
	

	public DeleteTMCLConstruct(ModelView modelView) {
		this.modelView = modelView;
		this.modelView.addSelectionChangedListener(this);
		setText("Delete...");
	}

	public List<TMCLConstruct> getConstructList() {
		if (constructList==null)
			constructList = new ArrayList<TMCLConstruct>();
		
		return constructList;
    }

	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?", "Do you really want to delete the selected construct?")) {

			CompoundCommand cmd = new CompoundCommand();

			for (TMCLConstruct construct : getConstructList()) {
			if (construct instanceof TopicType)
				cmd.append(new DeleteTopicTypeCommand((TopicType) construct));

			if (construct instanceof NameTypeConstraint)
				cmd.append(new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS));

			if (construct instanceof OccurrenceTypeConstraint)
				cmd.append(new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS));

			if (construct instanceof ItemIdentifierConstraint)
				cmd.append(new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__ITEM_IDENTIFIER_CONSTRAINTS));

			
			if (construct instanceof SubjectIdentifierConstraint)
				cmd.append(new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS));

			if (construct instanceof SubjectLocatorConstraint)
				cmd.append(new DeleteTopicTypeConstraintItemCommand((TopicType) construct.eContainer(),
				        (AbstractConstraint) construct, ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS));
			
			if (construct instanceof AssociationTypeConstraint)
				cmd.append(new DeleteAssociationConstraintCommand((AssociationTypeConstraint) construct));

			}
			if (cmd != null)
				modelView.getEditingDomain().getCommandStack().execute(cmd);
		}

	}

	@SuppressWarnings("unchecked")
    public void selectionChanged(SelectionChangedEvent event) {
		getConstructList().clear();
		if ((!event.getSelection().isEmpty()) && (((event.getSelection() instanceof IStructuredSelection))) ) {
			IStructuredSelection sel = (IStructuredSelection) event.getSelection();

		
			Object obj = sel.getFirstElement();
			Iterator<Object> it = sel.iterator();
			while (it.hasNext()) {
				obj = it.next();
				if ( (obj instanceof TMCLConstruct) 
				   &&(!(obj instanceof TopicMapSchema))
				   &&(!(obj instanceof File)) ) {
						getConstructList().add((TMCLConstruct) obj);
				}
			}
		}
		setEnabled(!getConstructList().isEmpty());
	}
}
