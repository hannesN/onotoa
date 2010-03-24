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
package de.topicmapslab.tmcledit.domaindiagram.action;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.action.AbstractSelectionAction;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.DeleteAssociationConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteCommentCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteRolePlayerConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeConstraintItemCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;

public class DeleteFromModelAction extends AbstractSelectionAction {
	public final static String ID = "de.topicmapslab.tmcleditor.removefrommodel";



	public DeleteFromModelAction(CommandStack commandStack) {
		super(commandStack);
		update();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		EditDomain ed = null;

		CompoundCommand cmd = new CompoundCommand();

		Iterator it = getSelections().iterator();
		while (it.hasNext()) {
			EditPart selectedEditPart = (EditPart) it.next();
			if (ed == null)
				ed = (EditDomain) selectedEditPart.getViewer()
						.getEditDomain();

			Object model = selectedEditPart.getModel();
			if (model instanceof AbstractTypedConstraint) {
				continue;
			}

			cmd.append(getCommand(model));
		}
		if (cmd != null) {
			getCommandStack().execute(cmd);
		}
	}

	/**
	 * @param cmd
	 * @param model
	 * @return
	 */
	private AbstractCommand getCommand(Object model) {
		AbstractCommand cmd = null;
		if (model instanceof TypeNode) {
			cmd = new DeleteTopicTypeCommand(((TypeNode) model).getTopicType());
		} else if (model instanceof Edge) {
			cmd = getDeleteEdgeCommand(model);
		} else if (model instanceof AssociationNode) {
			cmd = new DeleteAssociationConstraintCommand(
					((AssociationNode) model).getAssociationConstraint());
		} else if (model instanceof Comment) {
			cmd = new DeleteCommentCommand((Comment) model);
		} else if (model instanceof ReifierConstraint) {
			ReifiableTopicType rtt = (ReifiableTopicType) ((EObject) model).eContainer();
			cmd = new GenericSetCommand(rtt, ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, null);
		} else if (model instanceof ScopeConstraint) {
			ScopeConstraint sc = (ScopeConstraint) model;
			cmd = new RemoveScopeConstraintsCommand((ScopedTopicType) sc.eContainer(), sc);
		} else if (model instanceof AbstractConstraint) {
			cmd = getDeleteTopicTypeConstraintItemCommand(model);
		}
		
		return cmd;
	}

	private AbstractCommand getDeleteEdgeCommand(Object model) {
		Edge edge = (Edge) model;
		RolePlayerConstraint roleConstraint = edge.getRoleConstraint();
		if (roleConstraint != null) {
			return new DeleteRolePlayerConstraintCommand(
					(AssociationTypeConstraint) roleConstraint.eContainer(),
					roleConstraint);
		} else {
			if (edge.getType() != EdgeType.ROLE_CONSTRAINT_TYPE) {
				TopicType source = ((TypeNode) edge.getSource()).getTopicType();
				TopicType target = ((TypeNode) edge.getTarget()).getTopicType();
				
				if (edge.getType() == EdgeType.IS_ATYPE) {
					ArrayList<TopicType> newList = new ArrayList<TopicType>(source.getIsa());
					newList.remove(target);
					
					return new SetIsACommand(newList, source);
				} else {
					ArrayList<TopicType> newList = new ArrayList<TopicType>(source.getAko());
					newList.remove(target);
					
					return new SetAkoCommand(newList, source);
				}
				
			}
		}
		return null;
	}

	private AbstractCommand getDeleteTopicTypeConstraintItemCommand(Object model) {
		AbstractCommand cmd;
		int type = -1;
		if (model instanceof NameTypeConstraint) {
			type = ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS;
		} else if (model instanceof OccurrenceTypeConstraint) {
			type = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;
		} else if (model instanceof SubjectIdentifierConstraint) {
			type = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;
		} else if (model instanceof SubjectLocatorConstraint) {
			type = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;
		}
		AbstractConstraint ac = (AbstractConstraint) model;
		cmd = new DeleteTopicTypeConstraintItemCommand((TopicType) ac
				.eContainer(), ac, type);
		return cmd;
	}

	@Override
	public String getText() {
		return "Delete from Model";
	}

	@Override
	public String getId() {
		return ID;
	}

	@SuppressWarnings("unchecked")
	public void update() {
		if (getSelections().isEmpty()) {
			setEnabled(false);
			return;
		}

		setEnabled(true);

		Iterator it = getSelections().iterator();
		while (it.hasNext()) {
			EditPart selectedEditPart = (EditPart) it.next();
			Object model = selectedEditPart.getModel();
			if (!(selectedEditPart instanceof NodeEditPart)
					&& !isValidEditPartModel(model)) {
				setEnabled(false);
			}
		}
	}

	/**
	 * @param model
	 * @return
	 */
	private boolean isValidEditPartModel(Object model) {
		return  (model instanceof SubjectIdentifierConstraint)
				|| (model instanceof SubjectLocatorConstraint)
				|| (model instanceof Edge)
				|| (model instanceof ScopeConstraint)
				|| (model instanceof ReifierConstraint)
				|| (model instanceof Comment);
	}

}
