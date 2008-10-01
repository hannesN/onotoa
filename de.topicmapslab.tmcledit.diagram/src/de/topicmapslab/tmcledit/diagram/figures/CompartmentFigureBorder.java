package de.topicmapslab.tmcledit.diagram.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

public class CompartmentFigureBorder extends AbstractBorder {

	static final Insets zeroInsets = new Insets(0, 0, 0, 0); 
	
	@Override
	public Insets getInsets(IFigure figure) {
		return new Insets(5,5,5,5);
		
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		
		graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(),
                 tempRect.getTopRight());

	}

}
