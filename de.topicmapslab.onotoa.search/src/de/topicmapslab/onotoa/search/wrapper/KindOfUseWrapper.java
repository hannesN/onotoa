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
package de.topicmapslab.onotoa.search.wrapper;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.util.UseType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;

/**
 * This wrapper represents the children of the UseWrapper. He consists of an
 * icon and his according label that describes the relation of the parent node
 * to an other TopicType
 * 
 * @author Sebastian Lippert
 * 
 */

@TreeNode(imageMethod = "getImagePath")
public class KindOfUseWrapper {

	private UseType type;

	/**
	 * Constructor
	 * 
	 * @param type
	 *            Kind
	 */

	public KindOfUseWrapper(UseType type) {
		this.type = type;
	}

	/**
	 * 
	 * Getter for name of the child depending on its use type
	 * 
	 * @return Name
	 */
	@Text
	public String getText() {

		switch (type) {
		 case AssociationType:
		 return "Type of..";
		case Nametype:
			return "Name Constraint";
		case OccurrenceType:
			return "Occurrence Constraint";
		case Supertype:
			return "A kind of..";
		case Type:
			return "Instance of..";
		case Role:
			return "Role";
		case Player:
			return "Player";
		default:
			return "Other";
		}
	}

	/**
	 * Get image depending of the kind of use and returns image path
	 * 
	 * @return Path to specific image
	 */

	public String getImagePath() {

		switch (type) {
		 case AssociationType:
		 return ImageConstants.ASSOCIATIONCONSTRAINT_SM;
		case Nametype:
			return ImageConstants.NAMECONSTRAINT;
		case OccurrenceType:
			return ImageConstants.OCCURRENCECONSTRAINT;
		case Supertype:
			return ImageConstants.KINDOF_SM;
		case Type:
			return ImageConstants.ISA;
		case Role:
			return ImageConstants.ASSOCIATIONCONSTRAINT_SM;
		case Player:
			return ImageConstants.TOPICROLE_SM;
		default:
			return null;
		}

	}

}
