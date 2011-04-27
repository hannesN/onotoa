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
package de.topicmapslab.tmcledit.domaindiagram.policies;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.domaindiagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateNameTypeConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectIdentifierConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectLocatorConstraintCommand;

/**
 * Layout policy for type nodes which creates commands for new occurrences and names. 
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeNodeLayoutEditPolicy extends LayoutEditPolicy {


	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Node node = (Node) getHost().getModel();
		DomainEditDomain ed = (DomainEditDomain) getHost().getViewer().getEditDomain();
		AbstractCommand cmd = null;
		
		if (request.getNewObjectType()==OccurrenceTypeConstraint.class) {
			// getting container which is a diagram
			Diagram diagram = (Diagram) node.eContainer();
			if (diagram==null)
				return null;
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			
			cmd = new CreateOccurrenceConstraintCommand(tt, (OccurrenceTypeConstraint) request.getNewObject());
		} else if (request.getNewObjectType()==NameTypeConstraint.class) {
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			cmd = new CreateNameTypeConstraintCommand(tt, (NameTypeConstraint) request.getNewObject());
		} else if (request.getNewObjectType()==SubjectIdentifierConstraint.class) {
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			cmd = new CreateSubjectIdentifierConstraintCommand(tt, (SubjectIdentifierConstraint) request.getNewObject());
		} else if (request.getNewObjectType()==SubjectLocatorConstraint.class) {
				TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
				cmd = new CreateSubjectLocatorConstraintCommand(tt, (SubjectLocatorConstraint) request.getNewObject());
		}
		
		if (cmd!=null)
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		
		return null;
	}
	

	

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	@Override
	protected Command getMoveChildrenCommand(Request request) {
		return null;
	}
	
}
