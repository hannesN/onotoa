/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;
import de.topicmapslab.tmcledit.diagram.util.SWTPattern;

/**
 * @author niederhausen
 * 
 */
public class TypeFigure extends Figure {
	public TypeFigure() {
		setBorder(new RoundBorder());
	}
	
	@Override
	public void paintFigure(Graphics graphics) {
		ColorScheme scheme = DiagramActivator.getCurrentColorScheme();

		graphics.pushState();

		Color bg = scheme.getTopicColor().createColor(null);
		graphics.setBackgroundColor(bg);
		ColorDefinition tsc = scheme.getTopicSecondaryColor();
		Rectangle rec = getBounds();
		if (tsc != null) {

			Rectangle r = rec.getCopy();
			r.scale(graphics.getAbsoluteScale());
			graphics.setBackgroundPattern(new SWTPattern(null, r.x + r.width
					/ 2, r.y, r.x + r.width / 2, r.y + r.height, bg, tsc
					.createColor(null)));

		}
		Rectangle rec2 = new Rectangle(rec.x, rec.y, rec.width, rec.height);
		
		graphics.fillRoundRectangle(rec2, 10, 10);

		graphics.setForegroundColor(scheme.getTopicFontColor()
				.createColor(null));
		graphics.pushState();
		paintClientArea(graphics);
		graphics.popState();
		graphics.popState();
	}

	private class RoundBorder extends AbstractBorder {

		private final Insets insets = new Insets(5);

		public Insets getInsets(IFigure figure) {
			return insets;
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			Rectangle rec = figure.getBounds().getCopy();
			rec.width--;
			rec.height--;
			
			graphics.pushState();
			graphics.drawRoundRectangle(rec, 10, 10);
			graphics.popState();
			
		}

	}

}
