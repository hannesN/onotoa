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
package de.topicmapslab.tmcledit.domaindiagram.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.BlockFlow;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Color;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;
import de.topicmapslab.tmcledit.diagram.util.SWTPattern;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentFigure extends Figure {

	public static final int MIN_WIDTH = 50;
	public static final int MIN_HEIGHT = 40;

	private TextFlow textFlow;
	private FlowPage page;
	private BlockFlow blockFlow;
	
	public CommentFigure() {
		setBackgroundColor(ColorConstants.orange);
		setOpaque(false);
		page = new FlowPage();
		page.setBorder(new MarginBorder(0, 5, 0, 5));
		add(page);
		
		blockFlow = new BlockFlow();
		page.add(blockFlow);
		
		textFlow = new TextFlow();
		blockFlow.add(textFlow);
		
		setSize(MIN_WIDTH, MIN_HEIGHT);
	}

	@Override
	public Dimension getPreferredSize(int hint, int hint2) {
		return getSize();
	}
	
	@Override
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		setChildrenSize(rect.width, rect.height);
	}
	
	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);
		setChildrenSize(w, h);
	}

	public void setText(String text) {
		textFlow.setText(text);
	}

	public String getText() {
		return textFlow.getText();
	}
	
	private void setChildrenSize(int w, int h) {
		Dimension dim = new Dimension(w-10, h-10);
		
		page.setSize(dim);
		blockFlow.setSize(dim);
		textFlow.setSize(dim.width, dim.height);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Rectangle rec = getBounds();
		
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
		graphics.pushState();
		try {
			ColorScheme scheme = DiagramActivator.getCurrentColorScheme();
			ColorDefinition cd = scheme.getCommentColor();
			Color bg = cd.createColor(null);
			cd = scheme.getCommentSecondaryColor();
			if (cd!=null) {
				graphics.setBackgroundPattern(new SWTPattern(null, x1/2, y0, x1/2, y1, bg, cd.createColor(null)));
			} else {
				graphics.setBackgroundColor(bg);
			}

			graphics.fillPolygon(p);
			graphics.drawPolygon(p);
			graphics.translate(0, 15);
			graphics.setForegroundColor(scheme.getCommentFontColor().createColor(null));
			paintClientArea(graphics);
		} finally {
			graphics.popState();
		}
		
	}
}
