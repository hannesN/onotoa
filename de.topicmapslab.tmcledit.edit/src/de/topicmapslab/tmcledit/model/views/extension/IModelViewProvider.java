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
package de.topicmapslab.tmcledit.model.views.extension;

import java.util.List;

import org.eclipse.jface.action.IAction;

import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * 
 * 
 * @author Hannes Niederhausen
 * @version 1.1.2
 */
public interface IModelViewProvider {

	/**
	 * Serializes the model extension which is stored in an onotoa annotation.
	 * 
	 * @param modelEx the model to serialize
	 * @return the serialized model
	 */
	public String serialize(IModelExtension modelEx );
	
	
	public IModelExtension deserialize(String model);
		
	public String getAnnotationKey(IModelExtension modelExtension);
	
	public List<AbstractModelViewNode> getChildrenNodes(AbstractModelViewNode parentModel);

	public boolean hasPageFor(IModelExtension extension);
	
	public IModelPage getPageFor(IModelExtension extension);
	
	public List<IAction> getActionsFor(AbstractModelViewNode node);
}
