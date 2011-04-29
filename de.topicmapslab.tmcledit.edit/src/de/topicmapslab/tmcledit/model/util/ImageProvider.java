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
package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;

/**
 * Util class to get image objects from an image registry.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ImageProvider {
	private static ExtendedImageRegistry imageRegistry;
	
	/**
	 * Returns an image for the given key or <code>null</code>
	 * 
	 * @param key the key should be a constant from {@link ImageConstants}
	 * @return an image for the given key or <code>null</code>
	 */
	public static Image getImage(String key) {
		return getExtendedImageRegistry().getImage(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	/**
	 * Returns an image descriptor for the given key or <code>null</code>
	 * 
	 * @param key the key should be a constant from {@link ImageConstants}
	 * @return an image descriptor for the given key or <code>null</code>
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		return getExtendedImageRegistry().getImageDescriptor(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	/**
	 * Returns an image for the given type. For each kind of topic type animage exists  
	 * @param topicType the topic type which image should be returned
	 * @return the image for the kind of topic type of the given type
	 */
	public static Image getTopicTypeImage(TopicType topicType) {
		if (topicType==null)
			return null;
		
		return getImageOfKindOfTopic(topicType.getKind());
	}

	/**
	 * Returns an image based on the given kind of topic
	 * 
	 * @param kind the kind of topic
	 * @return the image for the kind of topic
	 */
	public static Image getImageOfKindOfTopic(KindOfTopicType kind) {
	    switch(kind)
		{
			case NO_TYPE:
				return ImageProvider.getImage(ImageConstants.TOPIC);
			case ASSOCIATION_TYPE:
				return ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE);
			case NAME_TYPE:
				return ImageProvider.getImage(ImageConstants.NAMETYPE);
			case OCCURRENCE_TYPE:
				return ImageProvider.getImage(ImageConstants.OCCURRENCETYPE);
			case ROLE_TYPE:
				return ImageProvider.getImage(ImageConstants.ROLETYPE);
			default:
				return ImageProvider.getImage(ImageConstants.TOPICTYPE);
		
		}
    }
	
	private static ExtendedImageRegistry getExtendedImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ExtendedImageRegistry();
			
		}

		return imageRegistry;
	}
}
