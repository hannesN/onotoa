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

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Border for the comment figure
 * @author Hannes Niederhausen
 *
 */
public class CommentFigureBorder extends AbstractBorder {

	static final Insets zeroInsets = new Insets(0, 0, 0, 0); 
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	public Insets getInsets(IFigure figure) {
		return new Insets(5,5,5,5);
		
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle rec = getPaintRectangle(figure, getInsets(figure));
		//rec.y -= 10;
		PointList p = new PointList();
		int x0 = rec.x;
		int x1 = x0+rec.width-1;
		int y0 = rec.y;
		int y1 = y0+rec.height-1;
		p.addPoint(x0, y0);
		p.addPoint(x0+rec.width-10, y0);
		p.addPoint(x1, y0+10);
		p.addPoint(x1, y1);
		p.addPoint(x0, y1);
		p.addPoint(x0, y0);
		graphics.drawPolygon(p);

	}

}
