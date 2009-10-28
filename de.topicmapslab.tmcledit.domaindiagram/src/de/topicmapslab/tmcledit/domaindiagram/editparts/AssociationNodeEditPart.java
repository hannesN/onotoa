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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
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
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;
import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.domaindiagram.figures.CircleFigure;
import de.topicmapslab.tmcledit.domaindiagram.policies.AbstractDirectEditPolicy;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AssociationNodeEditPart extends NodeEditPart implements IDirectEditable {

	private Label typeLabel;
	private Figure compartement;
	private CircleFigure circle;
	private DirectEditManager manager;

	@Override
	protected IFigure createFigure() {
		Figure figure = new Figure();

		GridLayout layout = new GridLayout();
		figure.setLayoutManager(layout);

		typeLabel = new Label();
		typeLabel.setText("foo:association");
		figure.add(typeLabel);
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.CENTER;
		layout.setConstraint(typeLabel, gd);

		compartement = new Figure();
		compartement.setLayoutManager(new ToolbarLayout(false));
		figure.add(compartement);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.CENTER;
		layout.setConstraint(compartement, gd);

		compartement.setBackgroundColor(ColorConstants.blue);
		compartement.setOpaque(false);

		int size = 25;
		circle = new CircleFigure();
		circle.setBackgroundColor(ColorConstants.blue);
		circle.setForegroundColor(ColorConstants.white);
		circle.setOpaque(true);
		circle.setSize(size, size);
		figure.add(circle);

		gd = new GridData();
		gd.grabExcessHorizontalSpace = false;
		gd.grabExcessVerticalSpace = false;
		gd.horizontalAlignment = GridData.CENTER;
		gd.widthHint = size;
		gd.heightHint = size;
		layout.setConstraint(circle, gd);

		return figure;
	}

	@Override
	public IFigure getContentPane() {
		return compartement;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new EllipseAnchor(circle);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(circle);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(circle);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(circle);
	}

	private AssociationNode getCastedModel() {
		return (AssociationNode) getModel();
	}

	@Override
	protected void refreshVisuals() {
		AssociationNode node = getCastedModel();
		if (getParent() == null)
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
		List<Object> children = new ArrayList<Object>();
		children.addAll(getCastedModel().getAssociationConstraint()
				.getPlayerConstraints());

		if (children.isEmpty())
			return Collections.emptyList();

		return children;
	}

	@Override
	public void setModel(Object model) {
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			tt.eAdapters().remove(this);
		}
		super.setModel(model);
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			if (tt == null)
				return;
			tt.eAdapters().add(this);
		}
	}

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
			}
		}

		refreshVisuals();
		refreshChildren();

	}

	private void processAdapter(Notification notification) {
		if (notification.getOldValue() != null)
			((EObject) notification.getOldValue()).eAdapters().remove(this);

		if (notification.getNewValue() != null)
			((EObject) notification.getNewValue()).eAdapters().add(this);
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutEditPolicy() {

			@Override
			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			@Override
			protected Command getCreateCommand(CreateRequest request) {
				org.eclipse.emf.common.command.Command cmd = null;
				AssociationType at = (AssociationType) getCastedModel()
						.getAssociationConstraint().getType();
				if (at == null)
					return null;

				if (cmd == null)
					return null;

				DomainEditDomain ed = (DomainEditDomain) getHost().getViewer()
						.getEditDomain();
				CommandStack cmdStack = ed.getEditingDomain().getCommandStack();
				return new CommandAdapter(cmdStack, cmd);
			}

			@Override
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return null;
			}
		});

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new AbstractDirectEditPolicy() {
					
					@Override
					public org.eclipse.emf.common.command.Command getRenameCommand(
							Object model, DirectEditRequest request) {
						if (model instanceof AssociationNode) {
							TopicType tt = ((AssociationNode)model).getAssociationConstraint().getType();
							if (tt==null)
								return null;
							
							return new RenameTopicTypeCommand(tt, getNewString(request));
						}
						return null;
					}
				});

	}

	private boolean isEditable() {
		return (getCastedModel().getAssociationConstraint().getType() != null);
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if ((req instanceof DirectEditRequest) && (isEditable())) {
				Label label = directEditHitTest(((DirectEditRequest) req)
						.getLocation().getCopy());
				if (label != null) {
					performDirectEdit(label);
				}
			}
		}
		super.performRequest(req);
	}

	private void performDirectEdit(Label label) {
		if (manager == null) {
			manager = new TMCLDirectEditManager(this, TextCellEditor.class,
					label);
		}
		manager.show();
	}

	protected Label directEditHitTest(Point requestLoc) {

		typeLabel.translateToRelative(requestLoc);
		if (typeLabel.containsPoint(requestLoc))
			return typeLabel;

		return null;
	}

	public DirectEditManager getManager() {
		return manager;
	}

	public void revertNameChange() {
		figure.setVisible(true);
		refreshVisuals();
	}
	
	public void handleNameChange(String value) {
		typeLabel.setText(value);
		figure.setVisible(false);
		refreshVisuals();
	}

	@Override
	public List<IContributionItem> getItems() {
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Association");
		SetTypeData data = new SetTypeData();
		data.typedConstraint = getCastedModel().getAssociationConstraint();
		data.editDomain = getEditDomain();
		data.schema = getTopicMapSchema();
		data.featureId = ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE;
		
//		subMenu.add(new SetAssociationAction(data));
		AssociationType at = (AssociationType) getCastedModel().getAssociationConstraint().getType();
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if ( (tt instanceof AssociationType) && (!(tt.equals(at)))) {
				SetTypeData d = data.clone();
				d.type = tt;
				subMenu.add(new SetTypeAction(d));
			}
		}
		result.add(subMenu);

		return result;
	}

	private TopicMapSchema getTopicMapSchema() {
		return (TopicMapSchema) getCastedModel().getAssociationConstraint().eContainer();
	}
}
