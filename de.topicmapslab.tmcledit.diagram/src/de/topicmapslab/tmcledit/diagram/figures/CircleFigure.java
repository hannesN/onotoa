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
		
		bounds.width = rect.width+20;
		bounds.height = rect.height+10;
		
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		
		Rectangle rec = getBounds();
		
		graphics.drawOval(rec.x+5, rec.y+2, rec.width-10, rec.height-4);
	}
	
}