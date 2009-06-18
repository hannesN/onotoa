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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public final class TypeNodeCreationFactory implements CreationFactory {
	private KindOfTopicType kind;

	private List<TopicType> topicTypes = Collections.emptyList();
	private boolean cache; // cache the topic type or not (last for palette
							// necessary)

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

	public TypeNodeCreationFactory(boolean cache) {
		this(KindOfTopicType.TOPIC_TYPE, cache);
	}

	public Object getNewObject() {
		if (topicTypes.size() < 2) {
			TypeNode tn = ModelFactory.eINSTANCE.createTypeNode();
			TopicType topicType = null;
			switch (topicTypes.size()) {
			case 0:
				topicType = ModelIndexer.getInstance().createTopicType(kind);
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