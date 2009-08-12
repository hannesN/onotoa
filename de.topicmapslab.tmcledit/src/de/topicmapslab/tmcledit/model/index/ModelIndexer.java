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
package de.topicmapslab.tmcledit.model.index;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * The Indexer is a singleton created for one {@link File} which contains different Indexer for
 * {@link Diagram} elements and {@link TopicMapSchema} elements
 * 
 * @author Hannes Niederhausen
 *
 */
public class ModelIndexer {
	private static ModelIndexer instance;
	
	private AssociationIndexer associationIndexer;
	private TopicIndexer topicIndexer;
	private TopicTypeNodeIndexer nodeIndexer;
	
	private final File file;
	
	private ModelIndexer(File file) {
		this.file = file;
	}

	public static void createInstance(File file) {
		if (instance!=null)
			instance.dispose();
		
		instance = new ModelIndexer(file);
		
		getInstance().topicIndexer = new TopicIndexer();
		getInstance().topicIndexer.init(file.getTopicMapSchema());
		
		getInstance().nodeIndexer = new TopicTypeNodeIndexer();
		getInstance().nodeIndexer.init(file);
		
		getInstance().associationIndexer = new AssociationIndexer();
		getInstance().associationIndexer.init(file.getTopicMapSchema());
	}
	
	private void dispose() {
		if (topicIndexer!=null)
			topicIndexer.dispose();
	
		if (nodeIndexer!=null)
			nodeIndexer.dispose();
	}

	public static ModelIndexer getInstance() {
		return instance;
	}

	public static TopicIndexer getTopicIndexer() {
	    return getInstance().topicIndexer;
    }
	
	public TopicMapSchema getTopicMapSchema() {
		return file.getTopicMapSchema();
	}

	public List<RolePlayerConstraint> getRolePlayerConstraintsFor(
			TopicType topicType) {
		return associationIndexer.getRolePlayerConstraintsFor(topicType);
	}
	
	public static TopicTypeNodeIndexer getNodeIndexer() {
	    return getInstance().nodeIndexer;
    }
	
	public Collection<Diagram> getDiagrams() {
		return Collections.unmodifiableCollection(file.getDiagrams());
	}
	
	public static AssociationIndexer getAssociationIndexer() {
	    return getInstance().associationIndexer;
    }
}
