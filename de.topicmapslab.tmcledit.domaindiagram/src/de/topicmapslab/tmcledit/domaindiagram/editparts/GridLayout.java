/*******************************************************************************
 /*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Asim Ullah - Ported for use in draw2d (c.f Bugzilla 71684). [Sep 10, 2004]
 *     Hannes Niederhausen - added child layouting
 *******************************************************************************/
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import de.topicmapslab.tmcledit.domaindiagram.figures.LineFigure;
import de.topicmapslab.tmcledit.domaindiagram.figures.SelectionFigure;

/**
 * @author Hannes Niederhausen
 * 
 */
public class GridLayout extends AbstractLayout {

	/**
	 * numColumns specifies the number of cell columns in the layout.
	 * 
	 * The default value is 1.
	 */
	public int numColumns = 1;
	
	public int hmargin = 5;
	
	public int vmargin = 2;

	protected Map<IFigure, GridData> constraints = new HashMap<IFigure, GridData>();

	private int maxColWidths[];

	private ArrayList<Integer> rowHeight;

	private int counter;

	private int maxHeight;

	private int totalDefaultWidth;

	private int totalDefaultHeight;

	private int currY;

	private int currX;

	private int currRow;
	
	/**
	 * Default Constructor
	 */
	public GridLayout() {
		super();
	}

	/**
	 * Constructs a new instance of this class given the number of columns, and
	 * whether or not the columns should be forced to have the same width.
	 * 
	 * @param numColumns
	 *            the number of columns in the grid
	 */
	public GridLayout(int numColumns) {
		this.numColumns = numColumns;
	}

	/**
	 * @param child
	 * @param wHint
	 * @param hHint
	 * @return the child size.
	 */
	protected Dimension getChildSize(IFigure child, int wHint, int hHint) {
		return child.getPreferredSize(wHint, hHint);
	}

	protected Dimension calculatePreferredSize(IFigure container, int wHint,
			int hHint) {
		Dimension size = layout(container, false, 0, 0, wHint, hHint, /* flushCache */
		true);
		if (wHint != SWT.DEFAULT)
			size.width = wHint;
		if (hHint != SWT.DEFAULT)
			size.height = hHint;

		/*
		 * Adjust for the size of the border
		 */
		size.expand(container.getInsets().getWidth(), container.getInsets()
				.getHeight());
		size.union(getBorderPreferredSize(container));

		return size;
	}

	public void layout(IFigure container) {
		// initChildren( container);
		Rectangle rect = container.getClientArea();
		layout(container, true, rect.x, rect.y, rect.width, rect.height, /* flushCache */
		true);

	}

	@SuppressWarnings("unchecked")
	Dimension layout(IFigure container, boolean move, int x, int y, int width,
			int height, boolean flushCache) {
		totalDefaultWidth = 0;
		totalDefaultHeight = 0;
		currX = 0;
		currY = 0;
		currRow = 0;

		maxColWidths = new int[numColumns];

		rowHeight = new ArrayList<Integer>();
		counter = 0;

		maxHeight = 0;
		Iterator it = container.getChildren().iterator();
		while (it.hasNext()) {
			IFigure f = (IFigure) it.next();
			calculateFigureSize(f);
		}
		// add last maxHeight
		rowHeight.add(maxHeight);
		maxHeight=0;

		for (int cw : maxColWidths) {
			totalDefaultWidth += cw;
		}
		totalDefaultWidth += ((numColumns-1)*hmargin);
		
		Rectangle bounds = container.getBounds();
		totalDefaultWidth = Math.max(bounds.width, totalDefaultWidth);
		
		
		for (int ch : rowHeight) {
			totalDefaultHeight+=ch;
		}
		totalDefaultHeight += ((rowHeight.size()-1)*vmargin);
				
		// setting size and position to figures
		it = container.getChildren().iterator();
		counter=0;
		currRow = 0;
		
		while (it.hasNext()) {
			IFigure f = (IFigure) it.next();
			processFigure(f);
		}

		Dimension dimension = new Dimension(totalDefaultWidth, totalDefaultHeight);
		Point p = bounds.getLocation();
		
		for (IFigure f : constraints.keySet()) {
			GridData gd = getConstraint(f);
			Rectangle rect = new Rectangle(gd.x, gd.y, gd.width, gd.height);
			
			rect = rect.getTranslated(p);
			f.setBounds(rect);
			if ( (f instanceof SelectionFigure) || (f instanceof LineFigure ))
				System.out.println(f+" : "+rect);
		}
		
		for (IFigure f : new ArrayList<IFigure>(container.getChildren())) {
			GridData gd = getConstraint(f);
			Rectangle rect = new Rectangle(gd.x, gd.y, gd.width, gd.height);
			
			rect = rect.getTranslated(p);
			System.out.println(f+" : "+rect);
		}
		
		return dimension;
	}

	@SuppressWarnings("unchecked")
	private void calculateFigureSize(IFigure f) {
		System.out.println(counter+":"+f);
		Dimension dim = f.getPreferredSize(-1, -1).getCopy();
		
		Border border = f.getBorder();
		if (border!=null) {
			Insets insets = border.getInsets(f);
			dim.width+=insets.left+insets.right;
			dim.height+=insets.top+insets.bottom;
		}
		GridData gd = (GridData) getConstraint(f);
		int colNumber = counter % numColumns;
		if (((colNumber % numColumns) == 0) && (counter != 0) && (!gd.layoutChildren) && (maxHeight!=0)) {
			rowHeight.add(maxHeight);
			maxHeight = 0;
		}


		if (gd.fillHorizontal) {
			maxHeight = Math.max(maxHeight, dim.height);
			rowHeight.add(maxHeight);
			maxHeight = 0;
			counter+=4-colNumber;
		} else if (gd.layoutChildren) {
			Iterator it = f.getChildren().iterator();
			while (it.hasNext()) {
				calculateFigureSize((IFigure) it.next());
			}			
		} else {
			maxColWidths[colNumber] = Math.max(dim.width,
					maxColWidths[colNumber]);
			
			maxHeight = Math.max(maxHeight, dim.height);
			counter++;
		} 
	}
	
	@SuppressWarnings("unchecked")
	private void processFigure(IFigure f) {
//		System.out.println(counter+":"+f);
		int colNumber = counter % numColumns;

		GridData gd = (GridData) getConstraint(f);
		if (gd.fillHorizontal) {
			gd.x = currX;
			gd.y = currY;
			gd.width = totalDefaultWidth;
			gd.height = rowHeight.get(currRow);
			System.out.println("line: "+rowHeight.get(currRow));
			counter+=4-colNumber;
			nextPositions();
			System.out.println("after line: "+rowHeight.get(currRow));
		} else if (gd.layoutChildren) {
			gd = getConstraint(f);
			gd.x = currX;
			gd.y = currY;
			gd.width = totalDefaultWidth;
			gd.height = rowHeight.get(currRow);
			Iterator it = f.getChildren().iterator();
			while (it.hasNext()) {
				processFigure((IFigure) it.next());
			}
		} else {
			gd.x = currX;
			gd.y = currY;
			gd.width = maxColWidths[colNumber];
			gd.height = rowHeight.get(currRow);
			counter++;
			nextPositions();
		} 
	}
	
	private void nextPositions() {
		int colNumber = counter % numColumns;
		if (colNumber==0) {
			currY += rowHeight.get(currRow) + vmargin;
			currRow++;
			if (currRow>=rowHeight.size())
				currRow=rowHeight.size()-1;
			currX = 0;
		} else {
			currX = 0;
			for (int i=0; i<colNumber; i++) {
				currX += maxColWidths[i]+hmargin;
			}
		}
		
	}

	@Override
	public GridData getConstraint(IFigure child) {
		GridData gd = constraints.get(child);
		if (gd == null) {
			gd = new GridData();
			setConstraint(child, gd);
		}
		return gd;
	}

	/**
	 * Sets the layout constraint of the given figure. The constraints can only
	 * be of type {@link GridData}.
	 * 
	 * @see LayoutManager#setConstraint(IFigure, Object)
	 */
	public void setConstraint(IFigure figure, GridData newConstraint) {
		super.setConstraint(figure, newConstraint);
		if (newConstraint != null) {
			constraints.put(figure, newConstraint);

		}
	}

}