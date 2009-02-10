/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Hannes Niederhausen
 *
 */
public class ScopedFigureLayoutManager extends AbstractLayout {

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Dimension calculatePreferredSize(IFigure container, int hint,
			int hint2) {
		
		int maxNameWidth = 0;
		int maxTypeWidth = 0;
		int height = 10;
		
		Iterator<IFigure> it = container.getChildren().iterator();
		while (it.hasNext()) {
			IFigure child = it.next();
			height += child.getPreferredSize().height;
			IFigure f = (IFigure) child.getChildren().get(0);
			int w = f.getPreferredSize().width;
			if (maxNameWidth<w)
				maxNameWidth = w;
			
			f = (IFigure) child.getChildren().get(1);
			w = f.getPreferredSize().width;
			if (maxTypeWidth<w)
				maxTypeWidth = w;
		}
		
		int width = maxNameWidth+maxTypeWidth+10;
		return new Dimension(width, height);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void layout(IFigure container) {
		int maxNameWidth = 0;
		int height = 0;
		Iterator<IFigure> it = container.getChildren().iterator();
		while (it.hasNext()) {
			IFigure child = it.next();
			height += child.getPreferredSize().height;
			IFigure f = (IFigure) child.getChildren().get(0);
			int w = f.getPreferredSize().width;
			if (maxNameWidth<w)
				maxNameWidth = w;
			
		}
		it = container.getChildren().iterator();
		int posY = container.getBounds().y+5;
		int posX = container.getBounds().x+5;
		while (it.hasNext()) {
			IFigure child = it.next();
			height += child.getPreferredSize().height;
			IFigure f = (IFigure) child.getChildren().get(0);
			Rectangle rec = f.getBounds();
			Dimension dim = f.getPreferredSize();
			rec.setSize(dim);
			rec.width = maxNameWidth;
			f.setBounds(rec);
			System.out.println("Name: --"+rec);
			
			f = (IFigure) child.getChildren().get(1);
			rec = f.getBounds();
			dim = f.getPreferredSize();
			rec.setSize(dim);
			rec.x = posX+maxNameWidth;
			f.setBounds(rec);
			System.out.println("Type: --"+rec);
			
			
			rec = child.getBounds();
			rec.y = posY;
			rec.x = posX;
			rec.setSize(child.getPreferredSize());
			rec.width = calculatePreferredSize(container, -1, -1).width-10;
			posY += rec.height;
			child.setBounds(rec);
			System.out.println("Child: --"+rec);
		}
		System.out.println("--------------------------");
	}

}
