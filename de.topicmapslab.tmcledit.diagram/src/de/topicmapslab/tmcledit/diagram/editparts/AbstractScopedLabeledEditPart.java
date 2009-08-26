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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	public AbstractScopedLabeledEditPart() {
		super();
	}

	@Override
	public void setModel(Object model) {
		if (getModel() != null)
			removeScopeAdapters();
		super.setModel(model);
		updateScopeAdapters();
	}

	private void updateScopeAdapters() {
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType() != null)
				sc.getType().eAdapters().add(this);
			sc.eAdapters().add(this);
		}
	}

	private void removeScopeAdapters() {
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType() != null)
				sc.getType().eAdapters().remove(this);
			sc.eAdapters().remove(this);
		}
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

	private List<ScopeConstraint> getScope() {
		if (getModel() instanceof AbstractTypedConstraint) {
			TopicType type = ((AbstractTypedConstraint) getModel()).getType();
			if (type instanceof ScopedTopicType)
				return ((ScopedTopicType) type).getScope();
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	public void notifyChanged(Notification notification) {
		EObject oldValue = null;
		EObject newValue = null;
		if (notification.getOldValue() instanceof EObject)
			oldValue = (EObject) notification.getOldValue();
		if (notification.getNewValue() instanceof EObject)
			newValue = (EObject) notification.getNewValue();

		if (notification.getFeatureID(List.class) == ModelPackage.SCOPED_TOPIC_TYPE__SCOPE) {
			if (notification.getEventType() == Notification.ADD) {
				ScopeConstraint sc = (ScopeConstraint) newValue;
				sc.eAdapters().add(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().add(this);
			} else if (notification.getEventType() == Notification.ADD_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) newValue) {
					sc.eAdapters().add(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().add(this);
				}
			} else if (notification.getEventType() == Notification.REMOVE) {
				ScopeConstraint sc = (ScopeConstraint) oldValue;
				sc.eAdapters().remove(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().remove(this);
			} else if (notification.getEventType() == Notification.REMOVE_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) oldValue) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			}
			refreshChildren();
		} else if (notification.getFeatureID(ReifierConstraint.class) == ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT) {
			if (oldValue != null)
				oldValue.eAdapters().remove(this);
			if (newValue != null)
				newValue.eAdapters().add(this);

			refreshChildren();
		}

	}
}