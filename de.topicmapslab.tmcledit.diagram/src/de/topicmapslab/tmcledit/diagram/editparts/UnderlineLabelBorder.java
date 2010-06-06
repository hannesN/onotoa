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

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

class UnderlineLabelBorder extends AbstractBorder {

	public Insets getInsets(IFigure figure) {
		return new Insets(0, 0, 1, 0);
	}

	@Override
	public Dimension getPreferredSize(IFigure figure) {
		Dimension preferredSize = figure.getPreferredSize();
		return new Dimension(preferredSize.width, preferredSize.height+1);
	}

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle rec = figure.getBounds();
		int y = rec.y+rec.height-1;
		graphics.pushState();
		graphics.setForegroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		graphics.drawLine(rec.x, y, rec.x+rec.width, y);
		graphics.popState();
	}
}