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

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

public class CompartmentFigureBorder extends AbstractBorder {

	static final Insets zeroInsets = new Insets(0, 0, 0, 0); 
	
	public Insets getInsets(IFigure figure) {
		return new Insets(5,5,5,5);
		
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		
		graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),
                 tempRect.getTopRight());

	}

}
