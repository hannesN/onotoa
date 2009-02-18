package de.topicmapslab.tmcledit.model.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteRolePlayerConstraintCommand extends AbstractCommand {

	private final AssociationTypeConstraint associationTypeConstraint;
	private final RolePlayerConstraint RolePlayerConstraint;
	
	private Map<Diagram, Edge>  edgeMap = Collections.emptyMap();
	
	public DeleteRolePlayerConstraintCommand(
			AssociationTypeConstraint associationTypeConstraint,
			RolePlayerConstraint RolePlayerConstraint) {
		super();
		this.associationTypeConstraint = associationTypeConstraint;
		this.RolePlayerConstraint = RolePlayerConstraint;
	}

	@Override
	public void execute() {
		for (Diagram d : edgeMap.keySet()) {
			d.getEdges().remove(edgeMap.get(d));
		}
		associationTypeConstraint.getPlayerConstraints().remove(RolePlayerConstraint);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationTypeConstraint.getPlayerConstraints().add(RolePlayerConstraint);
		for (Diagram d : edgeMap.keySet()) {
			d.getEdges().add(edgeMap.get(d));
		}
	}
	
	@Override
	protected boolean prepare() {
		for (Diagram d : ModelIndexer.getInstance().getDiagrams()) {
			AssociationNode node = (AssociationNode) ModelIndexer.getInstance()
					.getNodeFor(associationTypeConstraint, d);
			
			if (node!=null) {
				for (Edge e : ModelIndexer.getInstance().getEdges(d, EdgeType.ROLE_CONSTRAINT_TYPE)) {
					if (e.getRoleConstraint().equals(RolePlayerConstraint))
						addEdge(d, e);
				}
			}
		}
		return true;
	}

	private void addEdge(Diagram d, Edge e) {
		if (edgeMap==Collections.EMPTY_MAP) {
			edgeMap = new HashMap<Diagram, Edge>();
		}
		edgeMap.put(d, e);
	}
}
