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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * Edit part which renders a label containing scope,
 * 
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	@Override
	public Command getCommand(Request request) {
		Command cmd = super.getCommand(request);
		if (cmd==null)
			cmd = getParent().getCommand(request);
		return cmd;
	}
	
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutEditPolicy() {

			@Override
			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				org.eclipse.emf.common.command.Command cmd = null;
				if (request.getNewObjectType() == ScopeConstraint.class) {
					ScopedTopicType stt = (ScopedTopicType) (getCastedModel()
							.getType());
					if (stt != null) {
						cmd = new AddScopeConstraintsCommand(stt,
								(ScopeConstraint) request.getNewObject());
					}
				} else if (request.getNewObjectType() == ReifierConstraint.class) {
					if (getCastedModel().getType() instanceof ReifiableTopicType) {
						ReifiableTopicType rtt = (ReifiableTopicType) getCastedModel()
								.getType();
						if ((rtt != null)
								&& (rtt.getReifierConstraint() == null)) {
							cmd = new GenericSetCommand(
									rtt,
									ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT,
									(ReifierConstraint) request.getNewObject());
						}
					}
				}
				if (cmd == null)
					return null;

				TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer()
						.getEditDomain();
				CommandStack cmdStack = ed.getEditingDomain().getCommandStack();
				return new CommandAdapter(cmdStack, cmd);
			}

			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return null;
			}
		});
	}

	private AbstractTypedConstraint getCastedModel() {
		return (AbstractTypedConstraint) getModel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		if (getCastedModel().getType() != null) {
			List result = new ArrayList();
			if (getCastedModel().getType() instanceof ReifiableTopicType) {
				ReifiableTopicType rtt = (ReifiableTopicType) getCastedModel()
						.getType();
				if (rtt.getReifierConstraint() != null)
					result.add(rtt.getReifierConstraint());
			}

			result.addAll(((ScopedTopicType) getCastedModel().getType())
					.getScope());
			return result;
		}

		return Collections.emptyList();
	}

	
	public void notifyChanged(Notification notification) {
		if ( (notification.getFeatureID(List.class) == ModelPackage.SCOPED_TOPIC_TYPE__SCOPE) || 
		   (notification.getFeatureID(ReifierConstraint.class) == ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT) ){
			refreshChildren();
		}

	}
}