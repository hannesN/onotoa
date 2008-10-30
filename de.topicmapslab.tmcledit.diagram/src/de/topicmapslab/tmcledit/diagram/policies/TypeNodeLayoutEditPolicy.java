package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateNameTypeConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateOccurenceConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectIdentifierConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectLocatorConstraintCommand;

public class TypeNodeLayoutEditPolicy extends LayoutEditPolicy {


	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Node node = (Node) getHost().getModel();
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
		AbstractCommand cmd = null;
		
		if (request.getNewObjectType()==OccurenceTypeConstraint.class) {
			// getting container which is a diagram
			Diagram diagram = (Diagram) node.eContainer();
			if (diagram==null)
				return null;
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			
			cmd = new CreateOccurenceConstraintCommand(tt, (OccurenceTypeConstraint) request.getNewObject());
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
