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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddTopicRoleCommand extends AbstractCommand {

	private final RolePlayerConstraint rpc;
	private final AssociationTypeConstraint atc;
	
	private List<EdgeWrapper> list = new ArrayList<EdgeWrapper>();
	private TopicType player;
	
	public AddTopicRoleCommand(RolePlayerConstraint rpc, AssociationTypeConstraint atc, TopicType player) {
	    super();
	    this.rpc = rpc;
	    this.atc = atc;
	    this.player = player;
    }

	public void execute() {
		rpc.setPlayer(player);
		atc.getPlayerConstraints().add(rpc);
		for (EdgeWrapper ew : list) {
			ew.diagram.getEdges().add(ew.edge);
		}
	}

	public void redo() {
		execute();		
	}
	
	@Override
	protected boolean prepare() {
		if (atc.eContainer()==null)
			return false;
		
	    TopicMapSchema schema = (TopicMapSchema) atc.eContainer();
	    if (schema==null)
	    	return false;
	    
	    File file = (File) schema.eContainer();
	    if (file==null)
	    	return false;
	    
		for (Diagram d : file.getDiagrams()) {
			Node source = ModelIndexer.getNodeIndexer().getNodeFor(atc, d);
			if (source!=null) {
				Node target = ModelIndexer.getNodeIndexer().getNodeFor(player, d);
				if (target!=null) {
					Edge e = ModelFactory.eINSTANCE.createEdge();
					e.setType(EdgeType.ROLE_CONSTRAINT_TYPE);
					e.setSource(source);
					e.setTarget(target);
					e.setRoleConstraint(rpc);
					list.add(new EdgeWrapper(d, e));
				}
			}
			
		}
		
		return true;
	}
	
	@Override
	public void undo() {
		for (EdgeWrapper ew : list) {
			ew.diagram.getEdges().remove(ew.edge);
		}
		atc.getPlayerConstraints().remove(rpc);
	}

}
