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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.figures.CircleFigure;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AssociationNodeEditPart extends NodeEditPart {

	private Label typeLabel;
	private Figure compartement;

	@Override
	protected IFigure createFigure() {
		Figure figure = new CircleFigure();
		
		compartement = new Figure();
		compartement.setLayoutManager(new ToolbarLayout(false));
		figure.add(compartement);
		
		compartement.setBackgroundColor(ColorConstants.blue);
		compartement.setOpaque(false);
		
		typeLabel = new Label();
		typeLabel.setText("foo:association");
		compartement.add(typeLabel);
		
		
		return figure;
	}


	@Override
	public IFigure getContentPane() {
		return compartement;
	}

	private AssociationNode getCastedModel() {
		return (AssociationNode) getModel();
	}

	@Override
	protected void refreshVisuals() {
		AssociationNode node = getCastedModel();
		if (getParent()==null)
			return;
		Rectangle r = new Rectangle(node.getPosX(), node.getPosY(), -1, -1);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);

		TopicType associationType = node.getAssociationConstraint().getType();
		StringBuffer buffer = new StringBuffer();
		if (associationType != null) {
			buffer.append(associationType.getName());
		} else {
			buffer.append("No type set");
		}

		typeLabel.setText(buffer.toString());

		super.refreshVisuals();
	}

	@Override
	public void activate() {
		super.activate();
		AssociationTypeConstraint associationConstraint = getCastedModel()
				.getAssociationConstraint();
		associationConstraint.eAdapters().add(this);
		if (associationConstraint.getType() != null)
			associationConstraint.getType().eAdapters().add(this);

	}

	@Override
	public void deactivate() {
		AssociationTypeConstraint associationConstraint = getCastedModel()
				.getAssociationConstraint();
		associationConstraint.eAdapters().remove(this);
		if (associationConstraint.getType() != null)
			associationConstraint.getType().eAdapters().remove(this);
		super.deactivate();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		
		if (getCastedModel()==null)
			return Collections.emptyList();
		TopicType type = getCastedModel().getAssociationConstraint().getType();
		if (type==null)
			return Collections.emptyList();
		
		List<Object> children = new ArrayList<Object>();
		children.addAll(((ScopedTopicType) type).getScope());
		
		if (type instanceof ReifiableTopicType) {
			ReifierConstraint rc = ((ReifiableTopicType)type).getReifierConstraint();
			if (rc!=null) {
				children.add(0, rc);
			}
		}
		if (children.isEmpty())
			return Collections.emptyList();
		
		return children;
	}
	
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (index!=-1)
			index++;
		super.addChildVisual(childEditPart, index);
	}
	
	@Override
	public void setModel(Object model) {
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			tt.eAdapters().remove(this);
			if ((tt instanceof AssociationType)) {
				for (ScopeConstraint sc : ((AssociationType) tt).getScope()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
				ReifierConstraint reifierConstraint = ((AssociationType) tt).getReifierConstraint();
				if (reifierConstraint!=null) {
					reifierConstraint.eAdapters().remove(this);
					if (reifierConstraint.getType()!=null)
						reifierConstraint.getType().eAdapters().remove(this);
				}
			}
		}
		super.setModel(model);
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			if (tt==null)
				return;
			tt.eAdapters().add(this);
			if ((tt instanceof AssociationType)) {
				for (ScopeConstraint sc : ((AssociationType) tt).getScope()) {
					sc.eAdapters().add(this);
					TopicType type = sc.getType();
					if (type!=null)
						type.eAdapters().add(this);
				}
				ReifierConstraint reifierConstraint = ((AssociationType) tt).getReifierConstraint();
				if (reifierConstraint!=null) {
					reifierConstraint.eAdapters().add(this);
					if (reifierConstraint.getType()!=null)
						reifierConstraint.getType().eAdapters().add(this);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void notifyChanged(Notification notification) {
		int eventType = notification.getEventType();
		if (eventType == Notification.REMOVING_ADAPTER) {
			return;
		}
		if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__EDGES)
			refreshSourceConnections();
		
		Object notifier = notification.getNotifier();
		if (notifier == getModel())
			refreshVisuals();
		
		if (eventType == Notification.SET) {
			if (notifier.equals(getCastedModel().getAssociationConstraint())) {
				if (notification.getFeatureID(TopicType.class) == ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE) {
					processAdapter(notification);
				}
			} else if (notifier instanceof ReifierConstraint) {
				if (notification.getFeatureID(TopicType.class) == ModelPackage.REIFIER_CONSTRAINT__TYPE) {
					processAdapter(notification);
				}
			}
			
			
		} else if (notification.getFeatureID(List.class) == ModelPackage.ASSOCIATION_TYPE__SCOPE) {
			if (eventType == Notification.ADD) {
				ScopeConstraint sc = (ScopeConstraint) notification
						.getNewValue();
				sc.eAdapters().add(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().add(this);
			} else if (eventType == Notification.ADD_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification
						.getNewValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			} else if (eventType == Notification.REMOVE) {
				ScopeConstraint sc = (ScopeConstraint) notification
						.getOldValue();
				sc.eAdapters().remove(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().remove(this);
			} else if (eventType == Notification.REMOVE_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification
						.getOldValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			}
		} 
		
		refreshVisuals();
		refreshChildren();

	}

	private void processAdapter(Notification notification) {
		if (notification.getOldValue() != null)
			((EObject) notification.getOldValue()).eAdapters()
					.remove(this);

		if (notification.getNewValue() != null)
			((EObject) notification.getNewValue()).eAdapters().add(
					this);
	}

	@Override
	protected void createEditPolicies() {
		 installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutEditPolicy() {

				@Override
				protected Command getMoveChildrenCommand(Request request) {
					return null;
				}

				@Override
				protected Command getCreateCommand(CreateRequest request) {
					org.eclipse.emf.common.command.Command cmd = null;
					AssociationType at = (AssociationType) getCastedModel().getAssociationConstraint().getType();
					if (at==null)
						return null;
					
					if (request.getNewObjectType() == ScopeConstraint.class) {
						cmd = new AddScopeConstraintsCommand(at, (ScopeConstraint) request.getNewObject());
					} else if (request.getNewObjectType() == ReifierConstraint.class) {
						if (at instanceof ReifiableTopicType) {
							if ((at != null)
									&& (at.getReifierConstraint() == null)) {
								cmd = new GenericSetCommand(
										at,
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

}
