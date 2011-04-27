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
package de.topicmapslab.tmcledit.domaindiagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

public class CircleFigure extends Figure {
	public CircleFigure() {
		setOpaque(false);
	}

	@Override
	public void paint(Graphics graphics) {
		Rectangle rec = getBounds();
		int height = rec.height;
		int width = rec.width;
	
		int posX = rec.x + ((width-height)/2);
		
		graphics.pushState();
		graphics.pushState();
		graphics.setBackgroundColor(getBackgroundColor());
		graphics.setForegroundColor(getForegroundColor());
		
		Rectangle r = rec.getCopy();
		r.scale(graphics.getAbsoluteScale());
		graphics.setBackgroundPattern(new Pattern(Display.getCurrent(), posX, r.y, posX, r.y+r.height, getForegroundColor(), getBackgroundColor()));
		
		graphics.fillOval(posX, rec.y, height, height);
		graphics.popState();
		
		graphics.drawOval(posX, rec.y, height-1, height-1);
		
		graphics.popState();
		
		
	}

}