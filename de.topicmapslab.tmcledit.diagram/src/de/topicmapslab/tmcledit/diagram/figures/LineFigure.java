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
package de.topicmapslab.tmcledit.diagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Hannes Niederhausen
 *
 */
public class LineFigure extends Figure {

	@Override
	public Dimension getPreferredSize(int hint, int hint2) {
		return super.getPreferredSize(hint, 10);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Rectangle rec = getBounds();
		int x1 = rec.x;
		int x2 = x1+rec.width;
		int y = rec.y+rec.height/2;
		graphics.drawLine(x1, y, x2, y);
	}
}
