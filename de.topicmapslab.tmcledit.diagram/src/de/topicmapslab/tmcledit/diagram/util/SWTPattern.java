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
package de.topicmapslab.tmcledit.diagram.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Pattern;

/**
 * Wrapper for the SWT pattern, to get the colors and points.
 * 
 * @author Hannes Niederhausen
 *
 */
public class SWTPattern extends Pattern {

	private float x1;
	private float y1;
	private float x2;
	private float y2;
	
	private float alpha1;
	private float alpha2;
	
	private Color color1;
	private Color color2;
	
	
	public SWTPattern(Device device, float x1, float y1, float x2, float y2,
			Color color1, Color color2) {
		this(device, x1, y1, x2, y2, color1, 0xFF, color2, 0xFF);
	}

	public SWTPattern(Device device, float x1, float y1, float x2, float y2,
			Color color1, int alpha1, Color color2, int alpha2) {
		super(device, x1, y1, x2, y2, color1, alpha1, color2, alpha2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.alpha1 = alpha1;
		this.alpha2 = alpha2;
		this.color1 = color1;
		this.color2 = color2;
	}

	public float getX1() {
		return x1;
	}

	public float getY1() {
		return y1;
	}

	public float getX2() {
		return x2;
	}

	public float getY2() {
		return y2;
	}

	public float getAlpha1() {
		return alpha1;
	}

	public float getAlpha2() {
		return alpha2;
	}

	public Color getColor1() {
		return color1;
	}

	public Color getColor2() {
		return color2;
	}
	
	

}
