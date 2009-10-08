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

import java.util.Iterator;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleFigure extends Figure {
	
	
	private Insets insets;

	public CircleFigure() {
		setBorder(new EllipseBorder());
		setOpaque(false);
		setLayoutManager(new CenterlayoutManager());
	}
	
	private class EllipseBorder implements Border {

				
		public Insets getInsets(IFigure figure) {
			if (insets==null)
				return NO_INSETS;
			return NO_INSETS;
		}

		public Dimension getPreferredSize(IFigure figure) {
			return getBounds(figure).getSize().getCopy();
		}

		public boolean isOpaque() {
			return false;
		}

		public void paint(IFigure figure, Graphics graphics, Insets insets) {
			Rectangle rec = getBounds(figure);
			
			graphics.drawOval(rec.x+1, rec.y+1, rec.width-3, rec.height-2);
		}

		private Rectangle getBounds(IFigure figure) {
			Rectangle rec = figure.getBounds().getCopy();
			return rec;
		}
	}
	
	private class CenterlayoutManager extends AbstractLayout {

		@Override
		protected Dimension calculatePreferredSize(IFigure container,
				int wHint, int hHint) {
			
			Figure f = (Figure) container.getChildren().get(0);
			
			Dimension dim = f.getPreferredSize(wHint, hHint).getCopy();
			
			dim.width *= 1.3;
			dim.height *= 1.9;
			return dim;
		}

		@SuppressWarnings("unchecked")
		public void layout(IFigure container) {
			Iterator it = container.getChildren().iterator();
			
			while (it.hasNext()) {
				Figure f = (Figure) it.next();
				
				Dimension size = container.getPreferredSize();
				int x = (int) (size.width*0.3/2); 
				int y = (int) (size.height*0.22);
				size = f.getPreferredSize();
				Rectangle rec = container.getBounds().getCopy();
				rec.translate(x, y);
				rec.setSize(size);
				
				f.setBounds(rec);
			}
			
		}
		
	}
}