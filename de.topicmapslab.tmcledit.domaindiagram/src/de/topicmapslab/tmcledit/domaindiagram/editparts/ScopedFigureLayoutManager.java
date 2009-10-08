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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import de.topicmapslab.tmcledit.domaindiagram.figures.LineFigure;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ScopedFigureLayoutManager extends AbstractLayout {

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int hint,
			int hint2) {

		Maximum max = calculateMaximums(container);

		int height = max.height + 10;
		int width = max.nameWidth + max.typeWidth + max.regExpWidth + 15;
		return new Dimension(width, height);
	}

	@SuppressWarnings("unchecked")
	private Maximum calculateMaximums(IFigure container) {
		Maximum max = new Maximum();

		Iterator<IFigure> it = container.getChildren().iterator();
		while (it.hasNext()) {
			IFigure child = it.next();
			if (child instanceof LineFigure)
				max.height += 10;
			else {
				IFigure f = (IFigure) child.getChildren().get(0);
				Dimension preferredSize2 = f.getPreferredSize();
				int w = preferredSize2.width;
				int h = preferredSize2.height;

				w += 2;

				if (max.nameWidth < w)
					max.nameWidth = w;

				f = (IFigure) child.getChildren().get(1);
				preferredSize2 = f.getPreferredSize();
				w = preferredSize2.width+2;
				if (max.typeWidth < w)
					max.typeWidth = w;

				if (h < preferredSize2.height)
					h = preferredSize2.height;

				f = (IFigure) child.getChildren().get(2);
				preferredSize2 = f.getPreferredSize();
				w = preferredSize2.width+2;
				if (max.regExpWidth < w)
					max.regExpWidth = w;

				if (h < preferredSize2.height)
					h = preferredSize2.height;

				max.height += h;
				if (child.getChildren().size() > 3) {
					f = (IFigure) child.getChildren().get(3);
					preferredSize2 = f.getPreferredSize();
					w = preferredSize2.width;
					if (max.scopeWidth < w)
						max.scopeWidth = w;

					max.height += preferredSize2.height;
				}
			}
		}
		if (max.scopeWidth>0) {
			int firstLineWidth = max.regExpWidth + max.typeWidth;
			if (max.scopeWidth < firstLineWidth)
				max.scopeWidth = firstLineWidth;
			else if (max.scopeWidth > firstLineWidth) {
				int diff = max.scopeWidth - firstLineWidth;
				max.typeWidth += diff;
			}
		}

		return max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	@SuppressWarnings("unchecked")
	public void layout(IFigure container) {
		Maximum max = calculateMaximums(container);

		Iterator<IFigure> it = container.getChildren().iterator();
		int posY = container.getBounds().y + 3;
		int posX = container.getBounds().x + 10;
		Dimension calculatePreferredSize = calculatePreferredSize(container,
				-1, -1);
		while (it.hasNext()) {

			int maxHeight = 0;
			IFigure child = it.next();

			if (child instanceof LineFigure) {
				Rectangle parentBounds = container.getParent().getBounds();
				Rectangle rec = new Rectangle();
				rec.x = parentBounds.x;
				rec.y = posY;
				rec.width = parentBounds.width;
				rec.height = 10;
				posY += rec.height;
				child.setBounds(rec);
			} else {
				IFigure f = (IFigure) child.getChildren().get(0);
				Rectangle rec = new Rectangle();
				Dimension dim = f.getPreferredSize();
				rec.setSize(dim);
				rec.width = max.nameWidth;
				rec.setLocation(posX, posY);
				f.setBounds(rec);
				maxHeight = rec.height;

				f = (IFigure) child.getChildren().get(1);

				rec = new Rectangle();
				dim = f.getPreferredSize();
				dim.width = max.typeWidth;
				rec.setSize(dim);
				rec.x = posX + max.nameWidth;
				rec.y = posY;
				f.setBounds(rec);
				if (rec.height > maxHeight)
					maxHeight = rec.height;

				f = (IFigure) child.getChildren().get(2);

				rec = new Rectangle();
				dim = f.getPreferredSize();
				dim.width = max.regExpWidth;
				rec.setSize(dim);
				rec.x = posX + max.nameWidth + max.typeWidth;
				rec.y = posY;
				f.setBounds(rec);
				if (rec.height > maxHeight)
					maxHeight = rec.height;

				if (child.getChildren().size() == 4) {
					// setting bound of scope
					f = (IFigure) child.getChildren().get(3);
					rec = new Rectangle();
					dim = f.getPreferredSize();
					dim.width = max.scopeWidth;
					rec.setSize(dim);
					rec.x = posX + max.nameWidth;
					rec.y = posY + maxHeight;
					f.setBounds(rec);

					maxHeight += rec.height;
				}
				// setting child bounds
				rec = child.getBounds();
				rec.y = posY;
				rec.x = posX;
				rec.setSize(child.getPreferredSize());
				rec.width = calculatePreferredSize.width - 10;
				rec.height = maxHeight;
				posY += rec.height;
				child.setBounds(rec);

			}
		}
	}

	private class Maximum {
		int nameWidth = 0;
		int typeWidth = 0;
		int scopeWidth = 0;
		int height = 0;
		int regExpWidth = 0;
	}
}
