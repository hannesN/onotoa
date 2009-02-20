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
import org.eclipse.gef.GraphicalEditPart;

import de.topicmapslab.tmcledit.diagram.figures.CommentFigure;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentEditPart extends AdapterGraphicalEditPart {


	
	@Override
	protected IFigure createFigure() {
		CommentFigure figure = new CommentFigure();
		
		return figure;
	}

	@Override
	protected void createEditPolicies() {
	}

	@Override
	public void notifyChanged(Notification notification) {
		if ( (notification.getFeatureID(Integer.class)==ModelPackage.COMMENT__HEIGHT) ||
			 (notification.getFeatureID(Integer.class)==ModelPackage.COMMENT__WIDTH) ) {
			updateSize();
			return;
		}
		if ( (notification.getFeatureID(Integer.class)==ModelPackage.COMMENT__POS_X) ||
			 (notification.getFeatureID(Integer.class)==ModelPackage.COMMENT__POS_Y) ) {
			updatePosition();
			return;
		}
		if ( (notification.getFeatureID(String.class)==ModelPackage.COMMENT__CONTENT)) {
			updateContent();
			return;
		}
	}

	private void updateSize() {
		Comment comment = getCastedModel();
		((CommentFigure)figure).setSize(comment.getWidth(), comment.getHeight());
		
	}
	
	private Comment getCastedModel() {
		return (Comment) getModel();
	}
	
	@Override
	protected void refreshVisuals() {
		updateContent();
		updatePosition();
		updateSize();
	}

	private void updateContent() {
		Comment comment = getCastedModel();
		CommentFigure figure = (CommentFigure) getFigure();
		if (comment.getContent()==null)
			figure.setText("");
		else {
			if (!comment.getContent().equals(figure.getText()))
				figure.setText(comment.getContent());
		}
	}

	private void updatePosition() {
		if (getParent()==null)
			return;
		Comment comment = getCastedModel();
		Rectangle r = new Rectangle(comment.getPosX(), comment.getPosY(), 
						comment.getWidth(), comment.getHeight());
		
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);
		
	}
}
