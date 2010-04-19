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
package de.topicmapslab.tmcledit.model.command.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddTopicRoleCommand;
import de.topicmapslab.tmcledit.model.compare.TopicTypeComparator;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddTopicRoleCommandTest {

	private AddTopicRoleCommand command;
	private RolePlayerConstraint rpc;
	private AssociationTypeConstraint atc;
	private TopicType player;
	private int size;
	private TopicMapSchema schema;
	private File file;

	TopicTypeComparator comp;

	@Before
	public void prepare() {

		if (file == null)
			file = ModelFactory.eINSTANCE.createFile();

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			file.setTopicMapSchema(schema);

		}

		if (rpc == null)
			rpc = ModelFactory.eINSTANCE.createRolePlayerConstraint();

		if (atc == null) {

			atc = ModelFactory.eINSTANCE.createAssociationTypeConstraint();
			schema.getAssociationTypeConstraints().add(atc);

		}

		if (player == null)
			player = ModelFactory.eINSTANCE.createTopicType();

		if (command == null)
			command = new AddTopicRoleCommand(rpc, atc, player);

	}

	@After
	public void shutdown() {

		rpc = null;
		atc = null;
		player = null;
		file = null;
		schema = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		comp = new TopicTypeComparator();
		size = atc.getPlayerConstraints().size();

		Assert.assertFalse(atc.getPlayerConstraints().contains(rpc));
		command.execute();
		Assert.assertTrue((size + 1) == atc.getPlayerConstraints().size());
		Assert.assertTrue(atc.getPlayerConstraints().contains(rpc));
		Assert.assertTrue(comp.equals(rpc.getPlayer(), player));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		player = rpc.getPlayer();

		command.execute();

		comp = new TopicTypeComparator();
		size = atc.getPlayerConstraints().size();

		Assert.assertTrue(atc.getPlayerConstraints().contains(rpc));
		command.undo();
		Assert.assertTrue((size - 1) == atc.getPlayerConstraints().size());
		Assert.assertFalse(atc.getPlayerConstraints().contains(rpc));
		Assert.assertTrue(comp.equals(rpc.getPlayer(), player));

	}

	@Test
	public void redoTest() {

		command.execute();
		command.undo();

		comp = new TopicTypeComparator();
		size = atc.getPlayerConstraints().size();

		Assert.assertFalse(atc.getPlayerConstraints().contains(rpc));
		command.redo();
		Assert.assertTrue((size + 1) == atc.getPlayerConstraints().size());
		Assert.assertTrue(atc.getPlayerConstraints().contains(rpc));
		Assert.assertTrue(comp.equals(rpc.getPlayer(), player));

	}

	// private final RolePlayerConstraint rpc;
	// private final AssociationTypeConstraint atc;
	//	
	// private List<EdgeWrapper> list = new ArrayList<EdgeWrapper>();
	// private TopicType player;
	//	
	// public AddTopicRoleCommandTest(RolePlayerConstraint rpc,
	// AssociationTypeConstraint atc, TopicType player) {
	// super();
	// this.rpc = rpc;
	// this.atc = atc;
	// this.player = player;
	// }
	//
	// public void execute() {
	// rpc.setPlayer(player);
	// atc.getPlayerConstraints().add(rpc);
	// for (EdgeWrapper ew : list) {
	// ew.diagram.getEdges().add(ew.edge);
	// }
	// }
	//
	// public void redo() {
	// execute();
	// }
	//	
	// @Override
	// protected boolean prepare() {
	// if (atc.eContainer()==null)
	// return false;
	//		
	// TopicMapSchema schema = (TopicMapSchema) atc.eContainer();
	// if (schema==null)
	// return false;
	//	    
	// File file = (File) schema.eContainer();
	// if (file==null)
	// return false;
	//	    
	// for (Diagram d : file.getDiagrams()) {
	// Node source = ModelIndexer.getNodeIndexer().getNodeFor(atc, d);
	// if (source!=null) {
	// Node target = ModelIndexer.getNodeIndexer().getNodeFor(player, d);
	// if (target!=null) {
	// Edge e = ModelFactory.eINSTANCE.createEdge();
	// e.setType(EdgeType.ROLE_CONSTRAINT_TYPE);
	// e.setSource(source);
	// e.setTarget(target);
	// e.setRoleConstraint(rpc);
	// list.add(new EdgeWrapper(d, e));
	// }
	// }
	//			
	// }
	//		
	// return true;
	// }
	//	
	// @Override
	// public void undo() {
	// for (EdgeWrapper ew : list) {
	// ew.diagram.getEdges().remove(ew.edge);
	// }
	// atc.getPlayerConstraints().remove(rpc);
	// }

}
