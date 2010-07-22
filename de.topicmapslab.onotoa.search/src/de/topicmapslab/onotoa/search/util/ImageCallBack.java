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

import org.eclipse.swt.graphics.Image;

import de.topicmapslab.kuria.swtgenerator.util.IImageCallback;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * Implementation of image Callback for Kuria annotated wrapper.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ImageCallBack implements IImageCallback {

	public Image loadImage(String path) {
		return ImageProvider.getImage(path);
	}

}
