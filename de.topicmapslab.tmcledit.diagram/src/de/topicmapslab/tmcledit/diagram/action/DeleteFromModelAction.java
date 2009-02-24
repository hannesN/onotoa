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
package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.DeleteAssociationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteCommentCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteRolePlayerConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeConstraintItemCommand;

public class DeleteFromModelAction extends Action implements UpdateAction{
public final static String ID = "de.topicmapslab.tmcleditor.removefrommodel";
	
	private EditPart selectedEditPart;
	
	private final CommandStack commandStack;
	
	
	
	public DeleteFromModelAction(CommandStack commandStack) {
		super();
		this.commandStack = commandStack;
		update();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}
	
	@Override
	public void run() {
		TMCLEditDomain ed = (TMCLEditDomain) selectedEditPart.getViewer().getEditDomain();
		AbstractCommand cmd = null;
		Object model = selectedEditPart.getModel();
		
		if (model instanceof TypeNode) {
			cmd = new DeleteTopicTypeCommand(((TypeNode) model).getTopicType());
		} else if (model instanceof AbstractConstraint) {
			int type = -1;
			if (model instanceof NameTypeConstraint) {
				type = ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS;
			} else if (model instanceof OccurrenceTypeConstraint) {
				type = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;
			} else if (model instanceof SubjectIdentifierConstraint) {
				type = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;
			} else if (model instanceof SubjectIdentifierConstraint) {
				type = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;
			}
			AbstractConstraint ac = (AbstractConstraint) model;
			cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) ac
					.eContainer(), ac, type);
		} else if (model instanceof Edge) {
			Edge edge = (Edge) model;
			RolePlayerConstraint roleConstraint = edge.getRoleConstraint();
			if (roleConstraint != null)
				cmd = new DeleteRolePlayerConstraintCommand(
						(AssociationTypeConstraint) roleConstraint.eContainer(),
						roleConstraint);
		} else if (model instanceof AssociationNode) {
			cmd = new DeleteAssociationConstraintCommand(
					((AssociationNode) model).getAssociationConstraint());
		} else if (model instanceof Comment) {
			cmd = new DeleteCommentCommand((Comment) model);
		}

		
		if (cmd!=null) {
			commandStack.execute(new CommandAdapter(ed.getEditingDomain()
					.getCommandStack(), cmd));
		}
	}
	
	@Override
	public String getText() {
		return "Delete from Model";
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public void setSelectedEditPart(EditPart selectedEditPart) {
		this.selectedEditPart = selectedEditPart;
		update();
	}
	
	@Override
	public void update() {
		Object model = null;
		if (selectedEditPart != null)
			model = selectedEditPart.getModel();
		if ( (selectedEditPart instanceof NodeEditPart)
			|| (model instanceof OccurrenceTypeConstraint)
			|| (model instanceof NameTypeConstraint)
			|| (model instanceof SubjectIdentifierConstraint)
			|| (model instanceof SubjectLocatorConstraint)
			|| ( (model instanceof Edge) && ((Edge)model).getType()==EdgeType.ROLE_CONSTRAINT_TYPE)
			|| (model instanceof Comment)
		 ) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

}
