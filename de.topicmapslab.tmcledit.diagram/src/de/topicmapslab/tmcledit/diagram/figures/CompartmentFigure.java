package de.topicmapslab.tmcledit.diagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;

public class CompartmentFigure extends Figure {

	public CompartmentFigure() {
		setBorder(new CompartmentFigureBorder());
		setLayoutManager(new ToolbarLayout(false));
		setMinimumSize(new Dimension(10, 20));
	}
}
