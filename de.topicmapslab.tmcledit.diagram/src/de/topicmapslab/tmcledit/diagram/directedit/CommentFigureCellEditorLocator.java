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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.diagram.figures.CommentFigure;

/**
 * This locator sets the cell editor to the position of the given Label.
 * 
 * @author Hannes Niederhausen
 *
 */
public class CommentFigureCellEditorLocator implements CellEditorLocator {

	private final CommentFigure figure;
	
	public CommentFigureCellEditorLocator(CommentFigure figure) {
		this.figure = figure;
	}
	
	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();

		Rectangle rect = figure.getBounds();
		figure.translateToAbsolute(rect);
		text.setBounds(rect.x - 1, rect.y - 1, rect.width, rect.height);

	}

}
