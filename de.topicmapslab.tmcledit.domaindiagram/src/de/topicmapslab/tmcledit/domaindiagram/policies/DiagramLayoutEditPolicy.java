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

import java.util.List;

import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.graphics.Point;

import de.topicmapslab.tmcledit.domaindiagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.domaindiagram.editor.DomainEditDomain;
import de.topicmapslab.tmcledit.domaindiagram.editparts.CommentEditPart;
import de.topicmapslab.tmcledit.domaindiagram.editparts.PrefixMappingEditPart;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateCommentCommand;
import de.topicmapslab.tmcledit.model.commands.CreateNodeCommand;
import de.topicmapslab.tmcledit.model.commands.MoveNodeCommand;
import de.topicmapslab.tmcledit.model.commands.ResizeCommentCommand;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		DomainEditDomain ed = (DomainEditDomain) getHost().getViewer().getEditDomain();
		if (constraint instanceof Rectangle) {
			if (child instanceof PrefixMappingEditPart)
				return null;
			AbstractCommand cmd = null;
			Rectangle rec = (Rectangle) constraint;
			Node node = (Node) child.getModel();
			
			// check if resize command
			if (node instanceof Comment) {
				cmd = new ResizeCommentCommand((Comment) node, rec.x, rec.y, rec.width, rec.height);
			} else {
				cmd = new MoveNodeCommand((Node) child.getModel(), rec.x, rec.y);
			}
			if (cmd!=null)
				return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		DomainEditDomain ed = (DomainEditDomain) getHost().getViewer().getEditDomain();
		CommandStack commandStack = ed.getEditingDomain().getCommandStack();
		Diagram diagram = (Diagram) getHost().getModel();
		AbstractCommand cmd = null;
		org.eclipse.draw2d.geometry.Point p = request.getLocation().getCopy();

		ZoomManager manager = (ZoomManager) getHost().getViewer().getProperty(ZoomManager.class.toString());
	//	manager.getViewport().translateToAbsolute(request.getLocation().getCopy());
		
		Viewport viewport = manager.getViewport();
		p.translate(viewport.getViewLocation());
		
		Point p2 = new Point(p.x, p.y);
		if ( (request.getNewObjectType()==TypeNode.class) ||
			 (request.getNewObjectType()==AssociationNode.class) ){

			
			cmd = new CreateNodeCommand(diagram, p2, (Node) request.getNewObject());
			
		} else if (request.getNewObjectType() == Comment.class) {
			cmd = new CreateCommentCommand(diagram,
					(Comment) request.getNewObject(), p2);
		} else if (request.getNewObjectType() == List.class) {
			cmd = new CompoundCommand();
			for (Node node : (List<Node>) request.getNewObject() ) {
				Point tmp = new Point(p2.x, p2.y);
				((CompoundCommand)cmd).append(new CreateNodeCommand(diagram, tmp,node));
				p2.x+=40;
				p2.y+=40;
			}
			
		}
		if (cmd!=null)
			return new CommandAdapter(commandStack, cmd);

		return null;
	}
	
	

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof CommentEditPart)
			return new ResizableEditPolicy();
		
		return new NonResizableEditPolicy();
	}
	
}
