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
package de.topicmapslab.onotoa.wordlisteditor.editor;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Abstract Sorter for TableViewerColumns
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractColumnViewerSorter extends ViewerSorter {
	private static final int ASC = 1;

	private static final int NONE = 0;

	private static final int DESC = -1;

	private int direction = 0;

	private final TableViewerColumn column;

	private final ColumnViewer viewer;

	/**
	 * Constructor
	 * 
	 * @param viewer
	 *            the viewer which uses the sorter
	 * @param column
	 *            the column which will be sorted
	 */
	public AbstractColumnViewerSorter(ColumnViewer viewer, TableViewerColumn column) {
		this.column = column;
		this.viewer = viewer;
		this.column.getColumn().addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (AbstractColumnViewerSorter.this.viewer.getComparator() != null) {
					if (AbstractColumnViewerSorter.this.viewer.getComparator() == AbstractColumnViewerSorter.this) {
						int tdirection = AbstractColumnViewerSorter.this.direction;

						if (tdirection == ASC) {
							setSorter(AbstractColumnViewerSorter.this, DESC);
						} else if (tdirection == DESC) {
							setSorter(AbstractColumnViewerSorter.this, NONE);
						}
					} else {
						setSorter(AbstractColumnViewerSorter.this, ASC);
					}
				} else {
					setSorter(AbstractColumnViewerSorter.this, ASC);
				}
			}
		});
	}

	/**
	 * Sets the sorter and the direction
	 * 
	 * @param sorter the sorter for the viewer
	 * @param direction the sort direction
	 */
	private void setSorter(AbstractColumnViewerSorter sorter, int direction) {
		if (direction == NONE) {
			column.getColumn().getParent().setSortColumn(null);
			column.getColumn().getParent().setSortDirection(SWT.NONE);
			viewer.setComparator(null);
		} else {
			column.getColumn().getParent().setSortColumn(column.getColumn());
			sorter.direction = direction;

			if (direction == ASC) {
				column.getColumn().getParent().setSortDirection(SWT.DOWN);
			} else {
				column.getColumn().getParent().setSortDirection(SWT.UP);
			}

			if (viewer.getComparator() == sorter) {
				viewer.refresh();
			} else {
				viewer.setComparator(sorter);
			}

		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		return direction * doCompare(e1, e2);
	}

	/**
	 * Compares the given objects
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 */
	protected int doCompare(Object e1, Object e2) {
		String s1 = getText(e1);
		String s2 = getText(e2);
		return s1.toLowerCase().compareTo(s2.toLowerCase());
	}

	/**
	 * Returns the text for the column 
	 * @param element the element in the row
	 * @return
	 */
	abstract public String getText(Object element);
}
