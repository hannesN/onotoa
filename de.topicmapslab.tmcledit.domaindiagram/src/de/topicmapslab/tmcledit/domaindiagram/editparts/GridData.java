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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

/**
 * @author Hannes Niederhausen
 *
 */
public class GridData {
	
	public boolean fillHorizontal;
	
	public boolean spanRow;
	
	public boolean layoutChildren;
	
	int width;
	
	int height;
	
	int x;
	
	int y;
	
	boolean cached;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GridData [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append("]");
		return builder.toString();
	}

	void flush() {
		x = -1;
		y = -1;
		width = -1;
		height = -1;
		cached = false;
	}

}
