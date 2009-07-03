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
		bounds.width = (int) (rect.width*1.4);
		bounds.height = (int) (rect.height*1.9);
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		
		Rectangle rec = getBounds();
		graphics.drawOval(rec.x+1, rec.y+1, rec.width-3, rec.height-2);
	}
	
}