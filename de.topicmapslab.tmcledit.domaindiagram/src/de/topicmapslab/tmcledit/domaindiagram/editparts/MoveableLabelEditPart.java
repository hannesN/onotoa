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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DragEditPartsTracker;

import de.topicmapslab.tmcledit.domaindiagram.figures.MovableConnectionLocator;
import de.topicmapslab.tmcledit.model.LabelPos;

/**
 * @author Hannes Niederhausen
 * 
 */
public class MoveableLabelEditPart extends AdapterGraphicalEditPart {

	private Label label;
	private String text;

	private MovableConnectionLocator locator;
	private DragTracker dragTracker;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		label = new Label();
		return label;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	protected void refreshVisuals() {
		label.setText(text);
		LabelPos lp = getCastesModel();

		MovableConnectionLocator locator = getLocator();
		locator.setLabelPos(lp);
		try {
			((GraphicalEditPart) getParent()).setLayoutConstraint(this,
					getFigure(), locator);
		} catch (NullPointerException e) {
			new RuntimeException(e);
		}
	}
	

	private LabelPos getCastesModel() {
		return (LabelPos) getModel();
	}

	@Override
	public DragTracker getDragTracker(Request request) {
		return getDragTracker();
	}

	private DragTracker getDragTracker() {
		if (dragTracker == null) {
			dragTracker = new DragEditPartsTracker(this) {
				@Override
				protected boolean isMove() {
					return true;
				}
			};
		}

		return dragTracker;
	}
	
	public MovableConnectionLocator getLocator() {
		if (locator == null) {
			boolean isEnd = (getParent().getChildren().indexOf(this)!=0);
				
			locator = new MovableConnectionLocator(
					(Connection) ((GraphicalEditPart) getParent()).getFigure(),
					isEnd);
			
			if  ( (getCastesModel().getPosX()==0) &&
				  (getCastesModel().getPosY()==0) ){
				locator.setVDistance(15);			
				locator.setUDistance(15);
				
				if (getParent().getChildren().indexOf(this)==1) {
					locator.setUDistance(35);
				}
			}
			
			locator.setLabelPos(getCastesModel());
		}
		return locator;
	}

	@Override
	protected void createEditPolicies() {
	}

	public void notifyChanged(Notification notification) {
		refreshVisuals();
	}
}
