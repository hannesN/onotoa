package de.topicmapslab.tmcledit.diagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ToolbarLayout;

public class CompartmentFigure extends Figure {

	public CompartmentFigure() {
		setBorder(new CompartmentFigureBorder());
		
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setStretchMinorAxis(true);
		setLayoutManager(layout);
		//setMinimumSize(new Dimension(10, 20));
		
	}
}
