package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteAssociationConstraintCommand extends AbstractCommand {
	
	private final AssociationTypeConstraint constraint;
	
	private List<DeleteRoleConstraintCommand> cmds = Collections.emptyList();
	
	private Map<Diagram, Node> nodeMap = Collections.emptyMap();
	
	public DeleteAssociationConstraintCommand(
			AssociationTypeConstraint constraint) {
		super();
		this.constraint = constraint;
	}
	
	
	@Override
	public void execute() {
		ModelIndexer indexer = ModelIndexer.getInstance();
		
		
		for (DeleteRoleConstraintCommand cmd : cmds) {
			cmd.execute();
		}
		

		for (Diagram d : nodeMap.keySet()) {
			d.getNodes().remove(nodeMap.get(d));
		}
		
		indexer.getTopicMapSchema().getAssociationTypeConstraints().remove(constraint);
		
	}

	@Override
	public void redo() {
		ModelIndexer indexer = ModelIndexer.getInstance();
		for (DeleteRoleConstraintCommand cmd : cmds) {
			cmd.redo();
		}
		
		for (Diagram d : nodeMap.keySet()) {
			d.getNodes().remove(nodeMap.get(d));
		}
		
		indexer.getTopicMapSchema().getAssociationTypeConstraints().remove(constraint);
	}
	
	@Override
	public void undo() {
		ModelIndexer indexer = ModelIndexer.getInstance();
		
		indexer.getTopicMapSchema().getAssociationTypeConstraints().add(constraint);
		
		
		for (Diagram d : nodeMap.keySet()) {
			d.getNodes().add(nodeMap.get(d));
		}
		
		for (DeleteRoleConstraintCommand cmd : cmds) {
			cmd.undo();
		}
	}
	
	@Override
	protected boolean prepare() {
		if (constraint.getRoleTypeConstraints().size()>0) {
			cmds = new ArrayList<DeleteRoleConstraintCommand>(constraint.getRoleTypeConstraints().size());
		}
		
		for (RoleTypeConstraints rtc : constraint.getRoleTypeConstraints()) {
			DeleteRoleConstraintCommand cmd = new DeleteRoleConstraintCommand(constraint, rtc);
			if (cmd.canExecute())
				cmds.add(cmd);
		}
		
		for (Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getInstance()
					.getNodeFor(constraint, d);
			if (node!=null) {
				addNode(d, node);
			}
		}
		
		return true;
	}


	private void addNode(Diagram d, AssociationNode node) {
		if (nodeMap==Collections.EMPTY_MAP) {
			nodeMap = new HashMap<Diagram, Node>();
		}
		nodeMap.put(d, node);
	}	



}
