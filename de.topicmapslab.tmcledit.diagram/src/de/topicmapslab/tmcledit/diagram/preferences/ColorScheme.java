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
package de.topicmapslab.tmcledit.diagram.preferences;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;


/**
 * Class to safe the color schema for a diagram. Configured and created in the preference page.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ColorScheme implements Cloneable {

	private String name;
	
	private ColorDefinition commentColor;
	
	private ColorDefinition commentSecondaryColor;
	
	private ColorDefinition topicColor;
	
	private ColorDefinition topicSecondaryColor;

	private static ColorScheme defaultSchema;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ColorDefinition getCommentColor() {
		return commentColor;
	}

	public void setCommentColor(ColorDefinition noteColor) {
		this.commentColor = noteColor;
	}

	public ColorDefinition getCommentSecondaryColor() {
		return commentSecondaryColor;
	}

	public void setCommentSecondaryColor(ColorDefinition noteSecondaryColor) {
		this.commentSecondaryColor = noteSecondaryColor;
	}

	public ColorDefinition getTopicColor() {
		return topicColor;
	}

	public void setTopicColor(ColorDefinition topicColor) {
		this.topicColor = topicColor;
	}

	public ColorDefinition getTopicSecondaryColor() {
		return topicSecondaryColor;
	}

	public void setTopicSecondaryColor(ColorDefinition topicSecondaryColor) {
		this.topicSecondaryColor = topicSecondaryColor;
	}
	
	@Override
	protected ColorScheme clone() {
		ColorScheme scheme = new ColorScheme();
		scheme.commentColor = commentColor.clone();
		scheme.topicColor = topicColor.clone();
		
		if (commentSecondaryColor!=null)
			scheme.commentSecondaryColor = commentSecondaryColor.clone();
		if (topicSecondaryColor != null)
			scheme.topicSecondaryColor = topicSecondaryColor.clone();
		
		scheme.name = "copy of "+name;
		
		return scheme;
	}

	public static final ColorScheme getDefault() {
		if (defaultSchema==null) {
			defaultSchema = new ColorScheme();
			
			defaultSchema.setName("Default");
			
			defaultSchema.setCommentColor(new ColorDefinition(255, 196,   0));
			defaultSchema.setCommentSecondaryColor(new ColorDefinition(255, 255, 255));
			
			defaultSchema.setTopicColor(new ColorDefinition(255, 255, 0));
			defaultSchema.setTopicSecondaryColor(new ColorDefinition(255, 255, 255));
			
			
		}
		
		return defaultSchema;
	}
	
	public static class ColorDefinition implements Cloneable {
		public int r;
		public int g;
		public int b;
		private Color color = null;
		
		public ColorDefinition() {
		}
		
		public ColorDefinition(RGB rgb) {
			setRGB(rgb);
		}
		
		public ColorDefinition(int r, int g, int b) {
			super();
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		public RGB getRGB() {
			return new RGB(r,g,b);
		}
		
		public void setRGB(RGB rgb) {
			if (rgb==null)
				return;
			r = rgb.red;
			g = rgb.green;
			b = rgb.blue;
		}
		
		public Color createColor(Device device) {
			if (color==null)
				color = new Color(device, r, g, b);
			return color;
		}
		
		@Override
		protected ColorDefinition clone() {
			return new ColorDefinition(r, g, b);
		}
		
		public void dispose() {
			if ( (color!=null) && (!color.isDisposed()) ){
				color.dispose();
				color = null;
			}
		}
		
	}
}
