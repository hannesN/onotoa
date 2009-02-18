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