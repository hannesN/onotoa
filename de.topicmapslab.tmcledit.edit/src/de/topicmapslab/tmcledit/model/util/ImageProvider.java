package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.provider.TmcleditEditPlugin;

public class ImageProvider {

	
	
	private static ExtendedImageRegistry imageRegistry;
	
	public static Image getImage(String key) {
		return getExtendedImageRegistry().getImage(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	public static ImageDescriptor getImageDescriptor(String key) {
		return getExtendedImageRegistry().getImageDescriptor(TmcleditEditPlugin.INSTANCE.getImage(key));
	}
	
	public static Image getTopicTypeImage(TopicType topicType) {
		switch(topicType.getKind())
		{
			case ASSOCIATION_TYPE:
				return ImageProvider.getImage(ImageConstants.ASSOCIATIONTYPE);
			case NAME_TYPE:
				return ImageProvider.getImage(ImageConstants.NAMETYPE);
			case OCCURENCE_TYPE:
				return ImageProvider.getImage(ImageConstants.OCCURENCETYPE);
			case ROLE_TYPE:
				return ImageProvider.getImage(ImageConstants.ROLETYPE);
			case SCOPE_TYPE:
				return ImageProvider.getImage(ImageConstants.SCOPETYPE);
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
