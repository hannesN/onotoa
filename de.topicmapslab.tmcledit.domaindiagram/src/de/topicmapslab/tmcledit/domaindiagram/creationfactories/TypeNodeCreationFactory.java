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
package de.topicmapslab.tmcledit.domaindiagram.creationfactories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Creation factory to create a type node
 * @author Hannes Niederhausen
 *
 */
public final class TypeNodeCreationFactory implements CreationFactory {
	private KindOfTopicType kind;

	private List<TopicType> topicTypes = Collections.emptyList();
	private boolean cache; // cache the topic type or not (last for palette
							// necessary)

	/**
	 * Constructor
	 * @param kind kind of topic type to create
	 * @param cache the created topic type
	 */
	public TypeNodeCreationFactory(KindOfTopicType kind, boolean cache) {
		this.kind = kind;
		this.cache = cache;
	}

	/**
	 * 
	 * @param kind
	 */
	public TypeNodeCreationFactory(KindOfTopicType kind) {
		this(kind, false);
	}

	/**
	 * Constructor
	 * @param cache flag whether to cache the topic type
	 */
	public TypeNodeCreationFactory(boolean cache) {
		this(KindOfTopicType.TOPIC_TYPE, cache);
	}

	public Object getNewObject() {
		if (topicTypes.size() < 2) {
			TypeNode tn = ModelFactory.eINSTANCE.createTypeNode();
			TopicType topicType = null;
			switch (topicTypes.size()) {
			case 0:
				topicType = ModelIndexer.getTopicIndexer().createTopicType(kind);
				break;
			case 1:
				topicType = topicTypes.get(0);
			}

			tn.setTopicType(topicType);

			if (!cache)
				topicType = null;

			return tn;
		} else {
			ArrayList<TypeNode> nodeList = new ArrayList<TypeNode>(topicTypes.size());
			for (TopicType tt : topicTypes) {
				TypeNode tn = ModelFactory.eINSTANCE.createTypeNode();
				tn.setTopicType(tt);
				nodeList.add(tn);
			}
			return nodeList;
		}
	}

	/**
	 * Sets a list of topic types which need a node in a diagram
	 * @param topicTypes
	 */
	public void setTopicTypes(List<TopicType> topicTypes) {
		this.topicTypes = new ArrayList<TopicType>(topicTypes);
	}

	public Object getObjectType() {
		if (topicTypes.size() < 2)
			return TypeNode.class;
		else
			return List.class;
	}
}