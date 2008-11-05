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
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteRoleConstraintCommand extends AbstractCommand {

	private final AssociationTypeConstraint associationTypeConstraint;
	private final RoleTypeConstraints roleTypeConstraints;
	
	private Map<Diagram, Edge>  edgeMap = Collections.emptyMap();
	
	public DeleteRoleConstraintCommand(
			AssociationTypeConstraint associationTypeConstraint,
			RoleTypeConstraints roleTypeConstraints) {
		super();
		this.associationTypeConstraint = associationTypeConstraint;
		this.roleTypeConstraints = roleTypeConstraints;
	}

	@Override
	public void execute() {
		for (Diagram d : edgeMap.keySet()) {
			d.getEdges().remove(edgeMap.get(d));
		}
		associationTypeConstraint.getRoleTypeConstraints().remove(roleTypeConstraints);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationTypeConstraint.getRoleTypeConstraints().add(roleTypeConstraints);
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
					if (e.getRoleConstraint().equals(roleTypeConstraints))
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
