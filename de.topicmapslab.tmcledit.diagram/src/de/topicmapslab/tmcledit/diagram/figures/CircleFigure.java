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
		int max = Math.max(rect.width, rect.height)+5;
		bounds.width = max;
		bounds.height = max;
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		
		Rectangle rec = getBounds();
		
		int max = Math.max(rec.width, rec.height)-1;
		
		
		graphics.drawOval(rec.x, rec.y, max, max);
	}
	
}