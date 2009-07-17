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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.EditableLabel;
import de.topicmapslab.tmcledit.diagram.figures.SelectionFigure;

public abstract class AbstractLabelEditPart extends AdapterGraphicalEditPart {

	private DirectEditManager manager;
	private EditableLabel nameLabel;
	private Label typeLabel;
	private Label regExpLabel;
	private boolean editable = true;

	public AbstractLabelEditPart() {
		super();
	}

	@Override
	protected IFigure createFigure() {
	
		figure = new SelectionFigure();
		
		nameLabel = new EditableLabel("");
		nameLabel.setLabelAlignment(PositionConstants.LEFT);
		figure.add(nameLabel);
		
		typeLabel = new Label();
		typeLabel.setLabelAlignment(PositionConstants.LEFT);
		figure.add(typeLabel);
		
		regExpLabel = new Label();
		regExpLabel.setLabelAlignment(PositionConstants.LEFT);
		figure.add(regExpLabel);
		
		
		return figure;
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

	protected boolean isEditable() {
		return editable;
	}
	
	protected void setEditable(boolean editable) {
		this.editable = editable;
	}
	
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

	public Label getTypeLabel() {
		return typeLabel;
	}
	
	public Label getRegExpLabel() {
		return regExpLabel;
	}
	
	@SuppressWarnings("unchecked")
	public void clearScopeLables() {
		int counter = 0;
		Iterator it = getFigure().getChildren().iterator();
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