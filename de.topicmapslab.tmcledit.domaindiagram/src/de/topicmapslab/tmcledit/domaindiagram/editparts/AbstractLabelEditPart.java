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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;

import de.topicmapslab.tmcledit.domaindiagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.domaindiagram.figures.EditableLabel;
import de.topicmapslab.tmcledit.domaindiagram.figures.SelectionFigure;

public abstract class AbstractLabelEditPart extends AdapterGraphicalEditPart {

	private DirectEditManager manager;
	private EditableLabel nameLabel;
	private Label secondaryLabel;
	private Figure compartement;

	public AbstractLabelEditPart() {
		super();
	}

	@Override
	protected IFigure createFigure() {
	
		figure = new SelectionFigure();
		ToolbarLayout lm = new ToolbarLayout(false);
		figure.setLayoutManager(lm);
		
		
		Figure constraint = new Figure();
		figure.add(constraint);
		lm = new ToolbarLayout(true);
		lm.setSpacing(10);
		constraint.setLayoutManager(lm);
		
		nameLabel = new EditableLabel("");
		nameLabel.setLabelAlignment(PositionConstants.LEFT);
		constraint.add(nameLabel);
		
		secondaryLabel = new Label();
		secondaryLabel.setLabelAlignment(PositionConstants.LEFT);
		constraint.add(secondaryLabel);
		
		return figure;
	}
	
	@Override
	protected void addChildVisual(EditPart childEditPart, int index) {
		super.addChildVisual(childEditPart, index);
		IFigure f = ((GraphicalEditPart) childEditPart).getFigure();
		GridData gd = new GridData();
		gd.horizontalIndent = 20;
		gd.heightHint = SWT.DEFAULT;
		getContentPane().getLayoutManager().setConstraint(f, gd);
	}

	@Override
	public IFigure getContentPane() {
		if (compartement==null) {
			compartement = new Figure();
			getFigure().add(compartement);
			org.eclipse.draw2d.GridLayout gl = new GridLayout(1, false);
			gl.marginHeight = 0;
			gl.verticalSpacing = 1;
			compartement.setLayoutManager(gl);
		}
		return compartement;
	}
	
	public Label getSecondaryLabel() {
		return secondaryLabel;
	}
	
	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		
		SelectionFigure f = (SelectionFigure) getFigure();
		if (value==EditPart.SELECTED_NONE) {
			f.setSelected(false);
		} else {
			f.setSelected(true);
		}
	}
	
	@Override
	protected void removeChildVisual(EditPart childEditPart) {
		super.removeChildVisual(childEditPart);
		if (getContentPane().getChildren().size()==0) {
			getFigure().remove(getContentPane());
			compartement=null;
			getFigure().revalidate();
		}
		
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if ( (req instanceof DirectEditRequest) && (isEditable()) ){
				Label label = directEditHitTest(((DirectEditRequest) req).getLocation().getCopy());
				if (label!=null) {
					fillExtendedData(req.getExtendedData());
					performDirectEdit(label);
					
				}
			}
		}
		super.performRequest(req);
	}

	protected abstract boolean isEditable();
	
	@SuppressWarnings("unchecked")
	protected void fillExtendedData(Map extendedData) {		
	}

	private void performDirectEdit(Label label) {
		if (manager == null) {
			manager = new TMCLDirectEditManager(this, TextCellEditor.class, label);					
		}
		manager.show();
	}

	protected Label directEditHitTest(Point requestLoc) {
		
		getNameLabel().translateToRelative(requestLoc);
		if (getNameLabel().containsPoint(requestLoc))
			return getNameLabel();
		
		return null;
	}

	public DirectEditManager getManager() {
		return manager;
	}

	public EditableLabel getNameLabel() {
		return nameLabel;
	}

	@SuppressWarnings("unchecked")
	public void clearScopeLables() {
		int counter = 0;
		Iterator it = getFigure().getChildren().iterator();
		// 1. name label, 2nd: type/card label, 3rd: 
		while (it.hasNext()) {
			it.next();
			if (counter>2) {
				it.remove();
			}
			counter++;
		}
	}
	
	public void addScopeLabel(String text) {
		Label scopeLabel = new Label();
		scopeLabel.setLabelAlignment(PositionConstants.LEFT);
		getFigure().add(scopeLabel);
		scopeLabel.setText(text);
	}
	
	public void revertNameChange() {
		figure.setVisible(true);
		refreshVisuals();
	}
	
	public void handleNameChange(String value) {
		getNameLabel().setText(value);
		figure.setVisible(false);
		refreshVisuals();
	}

}