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

import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.views.ModelView;
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
	
	/**
	 * Returns the nodes for the given parent node which are added by the implementing extension.
	 * @param modelView the modelview to extend
	 * @param parentNode the parent node
	 * @return a list of nodes added to the parent node
	 */
	public List<AbstractModelViewNode> getChildNodes(ModelView modelView, AbstractModelViewNode parentNode);

	public boolean hasPageFor(IModelExtension extension);
	
	public IModelPage getPageFor(IModelExtension extension);
	
	/**
	 * Returns the list of actions provided for the context menu of the ModelView
	 * @return a list of actions. May be emtpy but must not be <code>null</code>
	 */
	public List<UpdateAction> getActions();
	
	/**
	 * This method should create the actions. It is called during the initialization of the modelview.
	 * 
	 * Actions which listen to selection changed event in the model view should be created in this method 
	 * and register to the given {@link ModelView}.
	 * @param modelView the model view instance which calles this method. 
	 */
	public void createActions(ModelView modelView);

	/**
	 * This method is called when the model is closed. 
	 * It should be sued to clean up some cached data.
	 */
	void close();
	
}
