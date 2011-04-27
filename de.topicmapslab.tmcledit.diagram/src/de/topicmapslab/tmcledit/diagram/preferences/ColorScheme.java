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
	
	private ColorDefinition topicFontColor;
	
	private ColorDefinition commentFontColor;

	private static ColorScheme defaultSchema;
	
	
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the commentColor
	 */
	public ColorDefinition getCommentColor() {
		return commentColor;
	}

	/**
	 * @param commentColor the commentColor to set
	 */
	public void setCommentColor(ColorDefinition commentColor) {
		this.commentColor = commentColor;
	}

	/**
	 * @return the commentSecondaryColor
	 */
	public ColorDefinition getCommentSecondaryColor() {
		return commentSecondaryColor;
	}

	/**
	 * @param commentSecondaryColor the commentSecondaryColor to set
	 */
	public void setCommentSecondaryColor(ColorDefinition commentSecondaryColor) {
		this.commentSecondaryColor = commentSecondaryColor;
	}

	/**
	 * @return the topicColor
	 */
	public ColorDefinition getTopicColor() {
		return topicColor;
	}

	/**
	 * @param topicColor the topicColor to set
	 */
	public void setTopicColor(ColorDefinition topicColor) {
		this.topicColor = topicColor;
	}

	/**
	 * @return the topicSecondaryColor
	 */
	public ColorDefinition getTopicSecondaryColor() {
		return topicSecondaryColor;
	}

	/**
	 * @param topicSecondaryColor the topicSecondaryColor to set
	 */
	public void setTopicSecondaryColor(ColorDefinition topicSecondaryColor) {
		this.topicSecondaryColor = topicSecondaryColor;
	}

	/**
	 * @return the topicFontColor
	 */
	public ColorDefinition getTopicFontColor() {
		return topicFontColor;
	}

	/**
	 * @param topicFontColor the topicFontColor to set
	 */
	public void setTopicFontColor(ColorDefinition topicFontColor) {
		this.topicFontColor = topicFontColor;
	}

	/**
	 * @return the commentFontColor
	 */
	public ColorDefinition getCommentFontColor() {
		return commentFontColor;
	}

	/**
	 * @param commentFontColor the commentFontColor to set
	 */
	public void setCommentFontColor(ColorDefinition commentFontColor) {
		this.commentFontColor = commentFontColor;
	}

	/**
	 * @return the defaultSchema
	 */
	public static ColorScheme getDefaultSchema() {
		return defaultSchema;
	}

	/**
	 * @param defaultSchema the defaultSchema to set
	 */
	public static void setDefaultSchema(ColorScheme defaultSchema) {
		ColorScheme.defaultSchema = defaultSchema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorScheme other = (ColorScheme) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	protected ColorScheme clone() {
		ColorScheme scheme = new ColorScheme();
		scheme.commentColor = commentColor.clone();
		scheme.topicColor = topicColor.clone();
		
		scheme.topicFontColor = topicFontColor.clone();
		scheme.commentFontColor = commentFontColor.clone();
		
		if (commentSecondaryColor!=null)
			scheme.commentSecondaryColor = commentSecondaryColor.clone();
		if (topicSecondaryColor != null)
			scheme.topicSecondaryColor = topicSecondaryColor.clone();
		
		scheme.name = "copy of "+name;
		
		return scheme;
	}

	/**
	 * 
	 * @return a default schema
	 */
	public static final ColorScheme getDefault() {
		if (defaultSchema==null) {
			defaultSchema = new ColorScheme();
			
			defaultSchema.setName("Default");
			
			defaultSchema.setCommentColor(new ColorDefinition(255, 196,   0));
			defaultSchema.setCommentSecondaryColor(new ColorDefinition(255, 255, 255));
			
			defaultSchema.setTopicColor(new ColorDefinition(255, 255, 0));
			defaultSchema.setTopicSecondaryColor(new ColorDefinition(255, 255, 255));
			
			defaultSchema.setCommentFontColor(new ColorDefinition(0, 0, 0));
			defaultSchema.setTopicFontColor(new ColorDefinition(0, 0, 0));
		}
		
		return defaultSchema;
	}
	
	/**
	 * Helper class for storing colors
	 * 
	 * @author Hannes Niederhausen
	 *
	 */
	public static class ColorDefinition implements Cloneable {
		/**  the red value */
		public int r;
		/**  the green value */
		public int g;
		/**  the blue value */
		public int b;
		private Color color = null;
		
		/**
		 * Constructor
		 */
		public ColorDefinition() {
		}
		
		/**
		 * Constructor
		 * @param rgb the RGB values for the color definition
		 */
		public ColorDefinition(RGB rgb) {
			setRGB(rgb);
		}
		
		/**
		 * Constructor
		 * @param r the red value for the color definition
		 * @param g the green value for the color definition
		 * @param b the blue value for the color definition
		 */
		public ColorDefinition(int r, int g, int b) {
			super();
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		/**
		 * 
		 * @return the RGB of the colors values
		 */
		public RGB getRGB() {
			return new RGB(r,g,b);
		}
		
		/**
		 * Sets the rgb values for the color definition
		 * @param rgb
		 */
		public void setRGB(RGB rgb) {
			if (rgb==null)
				return;
			r = rgb.red;
			g = rgb.green;
			b = rgb.blue;
		}
		
		/**
		 * Returns the color object for the given rgb values.
		 * If it is not created yet it will be using the given device 
		 * @param device the device used to create the color
		 * @return a {@link Color} using the deifnitions RGB values
		 */
		public Color createColor(Device device) {
			if (color==null)
				color = new Color(device, r, g, b);
			return color;
		}
		
		/**
		 * 
		 * {@inheritDoc}
		 */
		@Override
		protected ColorDefinition clone() {
			return new ColorDefinition(r, g, b);
		}
		
		/**
		 * Disposes the created color.
		 */
		public void dispose() {
			if ( (color!=null) && (!color.isDisposed()) ){
				color.dispose();
				color = null;
			}
		}
		
	}
}
