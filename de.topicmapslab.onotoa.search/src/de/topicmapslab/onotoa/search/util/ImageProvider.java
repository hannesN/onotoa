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
package de.topicmapslab.onotoa.search.util;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.topicmapslab.onotoa.search.Activator;
/**
 * @author sip
 *
 */
public class ImageProvider {
	
	public static Image getImage(String path) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		
		
		Image img = imageRegistry.get(path);
		
		if (img==null) {
			try {
	            URL url = Activator.getDefault().getBundle().getEntry(path);
	            img = new Image(Display.getCurrent(), url.openStream());
	            imageRegistry.put(path, img);
            } catch (IOException e) {
            	Activator.logException(e);
            }
		}
		
		return img;
		
	}

}
