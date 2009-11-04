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
package de.topicmapslab.tmcledit.diagram.directedit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.diagram.figures.CommentFigure;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentDirectEditManager extends DirectEditManager {

	private String originalValue;
	private CommentFigure figure;
	private Font figureFont;

	public CommentDirectEditManager(GraphicalEditPart source, 
			CommentFigure figure) {
		super(source, TextCellEditor.class, new CommentFigureCellEditorLocator(figure));
		this.figure = figure;
	}

	@Override
	protected void initCellEditor() {
		Text text = (Text) getCellEditor().getControl();

		
		//set the initial value of the
		originalValue = this.figure.getText();
		getCellEditor().setValue(originalValue);

		//calculate the font size of the underlying
		IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
		figureFont = figure.getFont();
		FontData data = figureFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, data.getHeight());

		//set the font to be used
		this.figure.translateToAbsolute(fontSize);
		data.setHeight(fontSize.height);
		figureFont = new Font(null, data);

		text.setFont(figureFont);
		text.selectAll();
	}

	protected CellEditor createCellEditorOn(Composite composite) {
		try {
			return new TextCellEditor(composite, SWT.MULTI);
		} catch (Exception e) {
			return null;
		}
	}
	
}
