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
/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.creationfactories;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public final class TypeNodeCreationFactory implements CreationFactory {
	private KindOfTopicType kind;

	private TopicType topicType;
	private boolean cache;	// cache the topic type or not (last for palette necessary)

	public TypeNodeCreationFactory(KindOfTopicType kind, boolean cache) {
		this.kind = kind;
		topicType = null;
		this.cache = cache;
	}
	
	/**
	 * 
	 * @param kind
	 */
	public TypeNodeCreationFactory(KindOfTopicType kind) {
		this(kind, false);
	}
	
	public TypeNodeCreationFactory(boolean cache) {
		this(KindOfTopicType.TOPIC_TYPE, cache);
	}

	public Object getNewObject() {
		TypeNode tn = ModelFactory.eINSTANCE.createTypeNode();

		if (topicType == null) {
			topicType = ModelIndexer.getInstance().createTopicType(kind);
		}
		tn.setTopicType(topicType);

		if (!cache)
			topicType = null;
		
		return tn;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	public Object getObjectType() {
		return TypeNode.class;
	}
}