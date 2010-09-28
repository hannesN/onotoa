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
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import org.eclipse.swt.graphics.Image;

import de.topicmapslab.onotoa.aranuka.codegen.util.ImageConstants;
import de.topicmapslab.onotoa.aranuka.codegen.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * Node for the generator data.
 * 
 * @author Hannes Niederhausen
 *
 */
public class GeneratorDataNode extends AbstractModelViewNode {

	/**
     * @param modelView
     */
    public GeneratorDataNode(ModelView modelView) {
	    super(modelView);
	    setName("Code Generation Data");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
    	return ImageProvider.getImage(ImageConstants.IMG_GENERATE_CODE);
    }
}
