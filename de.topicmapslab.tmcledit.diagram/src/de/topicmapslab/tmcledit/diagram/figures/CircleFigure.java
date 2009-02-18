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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleFigure extends Label {
	public CircleFigure() {
	}

	@Override
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		
		bounds.width = rect.width+30;
		bounds.height = rect.height+25;
		
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		
		Rectangle rec = getBounds();
		
		graphics.drawOval(rec.x+3, rec.y+6, rec.width-8, rec.height-7);
	}
	
}