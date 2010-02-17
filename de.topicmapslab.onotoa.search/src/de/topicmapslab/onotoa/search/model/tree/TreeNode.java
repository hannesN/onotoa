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
package de.topicmapslab.onotoa.search.model.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * @author Hannes Niederhausen
 *
 */
public class TreeNode {

	private TreeNode parent;
	private List<TreeNode> children;
	
	private final Object object;
	private final TreeNodeType type;
	
	/**
	 * 
	 */
	public TreeNode(Object object, TreeNodeType type) {
		this.object = object;
		this.type = type;
	}
	
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		if (children==null)
			return Collections.emptyList();
		return children;
	}
	
	public void addChild(TreeNode child) {
		if (children==null)
			children = new ArrayList<TreeNode>();
		children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(TreeNode child) {
		if (getChildren().contains(child)) {
			children.remove(child);
			child.setParent(null);	
		}
	}
	
	public String getText() {
		
		switch (type) {
		case Association:
			break;
		case Nametype:
		case OccurrenceType:
			return ((AbstractTypedConstraint)object).getType().getName();
		case Supertype:
			return "A kind of..";
		case Type:
			return "Instance of..";
		case User:
			return ((TopicType) object).getName();
		
		}
		return object.toString();
	}
	
	public Image getImage() {
		switch(type){
		case Association:
			break;
		case Nametype:
			return ImageProvider.getImage(ImageConstants.NAMECONSTRAINT);
		case OccurrenceType:
			return ImageProvider.getImage(ImageConstants.OCCURRENCECONSTRAINT);
		case Supertype:
//			return ImageProvider.getImage(ImageConstants.);
		case Type:
			break;
		case User:
			return ImageProvider.getTopicTypeImage((TopicType) object);
			
		}
		return null;
	}
	
	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}
}
