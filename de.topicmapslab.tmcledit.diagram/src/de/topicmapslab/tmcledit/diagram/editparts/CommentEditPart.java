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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;

import de.topicmapslab.tmcledit.diagram.directedit.CommentDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.CommentFigure;
import de.topicmapslab.tmcledit.diagram.policies.CommentDirectEditPolicy;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CommentEditPart extends AdapterGraphicalEditPart implements IDirectEditable {

	private DirectEditManager manager = null;

	@Override
	protected IFigure createFigure() {
		CommentFigure figure = new CommentFigure();

		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new CommentDirectEditPolicy());
	}

	public void notifyChanged(Notification notification) {
		if ((notification.getFeatureID(Integer.class) == ModelPackage.COMMENT__HEIGHT)
				|| (notification.getFeatureID(Integer.class) == ModelPackage.COMMENT__WIDTH)
				|| (notification.getFeatureID(Integer.class) == ModelPackage.COMMENT__POS_X)
				|| (notification.getFeatureID(Integer.class) == ModelPackage.COMMENT__POS_Y)) {
			updateBounds();
			return;
		}
		if ((notification.getFeatureID(String.class) == ModelPackage.COMMENT__CONTENT)) {
			updateContent();
			return;
		}
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if ((req instanceof DirectEditRequest)) {
					performDirectEdit();
			}
		}
		super.performRequest(req);
	}

	private void performDirectEdit() {
		getManager().show();
	}

	

	private Comment getCastedModel() {
		return (Comment) getModel();
	}

	@Override
	protected void refreshVisuals() {
		updateContent();
		updateBounds();
	}

	private void updateContent() {
		Comment comment = getCastedModel();
		CommentFigure figure = (CommentFigure) getFigure();
		if (comment.getContent() == null)
			figure.setText("");
		else {
			if (!comment.getContent().equals(figure.getText()))
				figure.setText(comment.getContent());
		}
	}

	private void updateBounds() {
		if (getParent() == null)
			return;
		Comment comment = getCastedModel();
		Rectangle r = new Rectangle(comment.getPosX(), comment.getPosY(),
				comment.getWidth(), comment.getHeight());

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);
		((CommentFigure) figure).setSize(comment.getWidth(), comment
				.getHeight());
	}

	public DirectEditManager getManager() {
		if (manager == null) {
			CommentFigure figure = (CommentFigure) getFigure();
			manager = new CommentDirectEditManager(this, figure);
		}
		return manager;
	}

	public void revertNameChange() {
		figure.setVisible(true);
		refreshVisuals();
	}
	
	public void handleNameChange(String value) {
		CommentFigure figure = (CommentFigure) getFigure();
		figure.setText(value);
		figure.setVisible(false);
		refreshVisuals();
	}
}
