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

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;

public class ImageProvider {

	
	
	private static ExtendedImageRegistry imageRegistry;
	
	public static Image getImage(String key) {
		return getExtendedImageRegistry().getImage(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	public static ImageDescriptor getImageDescriptor(String key) {
		return getExtendedImageRegistry().getImageDescriptor(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	public static Image getTopicTypeImage(TopicType topicType) {
		if (topicType==null)
			return null;
		
		switch(topicType.getKind())
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
