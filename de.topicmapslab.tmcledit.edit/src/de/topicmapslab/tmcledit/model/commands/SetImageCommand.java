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
package de.topicmapslab.tmcledit.model.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetImageCommand extends AbstractCommand {

	private final String newValue;
	private final TopicType type;
	private String oldValue;
	
	private Set<TypeNode> nodes;
	
	public SetImageCommand(TopicType type, String newValue) {
	    super();
	    this.newValue = newValue;
	    this.type = type;
    }

	public void execute() {
		setValue(newValue);
	}

	public void redo() {
		setValue(newValue);
	}
	
	@Override
	public void undo() {
		setValue(oldValue);
	}
	
	private void setValue(String val) {
		for (TypeNode n : nodes) {
			n.setImage(val);
		}
	}
	
	@Override
	protected boolean prepare() {
		if (type==null)
			return false;
		
		Set<TypeNode> result = new HashSet<TypeNode>();
		File file = (File) type.eContainer().eContainer();
		for (Diagram d : file.getDiagrams()) {
			Node n = ModelIndexer.getNodeIndexer().getNodeFor(type, d);
			if (n!=null)
				result.add((TypeNode) n);
		}
		
		if (result.isEmpty())
			return false;
		
		this.nodes = result;
		this.oldValue = nodes.iterator().next().getImage();
	    return true;
	}

	public Set<TypeNode> getNodes() {
		if (nodes==null)
			return Collections.emptySet();
	    return nodes;
    }
}
