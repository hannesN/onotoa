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
package de.topicmapslab.tmcledit.diagram.figures;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class SelectionFigure extends Figure {

	private boolean selected;

	public SelectionFigure() {
		super();
	}

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getBounds().getCopy();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	/**
	 * paints figure differently depends on the whether the figure has focus or
	 * is selected
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
		if (selected) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
		}
		super.paintFigure(graphics);
	}

	/**
	 * Sets the selection state of this SimpleActivityLabel
	 * 
	 * @param b
	 *            true will cause the label to appear selected
	 */
	@SuppressWarnings("unchecked")
	public void setSelected(boolean b) {
		selected = b;
		Color c = (b) ? ColorConstants.white : ColorConstants.black;
		List children = getChildren();
		for (Iterator it = children.iterator(); it.hasNext();) {
			IFigure f = (IFigure) it.next();
			if (f instanceof Label)
				((Label) f).setForegroundColor(c);
		}
		repaint();
	}
	
}
