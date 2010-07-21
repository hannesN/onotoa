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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeConstraintItemCommand;

/**
 * A generic command to remove an item from a topic type.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class DeleteTopicTypeConstraintItemCommandTest {

	private DeleteTopicTypeConstraintItemCommand command;
	private int featureID;
	private AbstractConstraint constraint0;
	private AbstractConstraint constraint1;
	private TopicType topicType;
	private List<NameTypeConstraint> nameTypeConstraintsList;
	private List<OccurrenceTypeConstraint> occurrenceTypeConstraintsList;
	private List<SubjectIdentifierConstraint> subjectIdentifierConstraintsList;
	private List<ItemIdentifierConstraint> itemIdentifierConstraintsList;
	private List<SubjectLocatorConstraint> subjectLocatorConstraintsList;
	private int nameTypeSize;
	private int occurrenceTypeSize;
	private int subjectIdentifierSize;
	private int itemIdentifierSize;
	private int subjectLocatorSize;

	@Before
	public void prepare() {

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

	}

	@After
	public void shutdown() {

		constraint0 = null;
		constraint1 = null;
		nameTypeConstraintsList = null;
		occurrenceTypeConstraintsList = null;
		subjectIdentifierConstraintsList = null;
		subjectLocatorConstraintsList = null;
		topicType = null;
		command = null;

	}

	@Test
	public void canExecuteTestNameType() {

		constraint0 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestOccurrenceType() {

		constraint0 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestSubjectIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

	}
	
	@Test
	public void canExecuteTestItemIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestSubjectLocator() {

		constraint0 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestNameType() {

		constraint0 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		nameTypeSize = topicType.getNameConstraints().size();
		nameTypeConstraintsList = new ArrayList<NameTypeConstraint>(topicType
				.getNameConstraints());
		nameTypeConstraintsList.remove(constraint0);

		command.execute();

		Assert.assertTrue((nameTypeSize - 1) == topicType.getNameConstraints()
				.size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(
				nameTypeConstraintsList, topicType.getNameConstraints()));

	}

	@Test
	public void executeTestOccurrenceType() {

		constraint0 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		occurrenceTypeSize = topicType.getOccurrenceConstraints().size();
		occurrenceTypeConstraintsList = new ArrayList<OccurrenceTypeConstraint>(
				topicType.getOccurrenceConstraints());
		occurrenceTypeConstraintsList.remove(constraint0);

		command.execute();

		Assert.assertTrue((occurrenceTypeSize - 1) == topicType
				.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(
				occurrenceTypeConstraintsList, topicType
						.getOccurrenceConstraints()));

	}

	@Test
	public void executeTestSubjectIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		subjectIdentifierSize = topicType.getSubjectIdentifierConstraints()
				.size();
		subjectIdentifierConstraintsList = new ArrayList<SubjectIdentifierConstraint>(
				topicType.getSubjectIdentifierConstraints());
		subjectIdentifierConstraintsList.remove(constraint0);

		command.execute();

		Assert.assertTrue((subjectIdentifierSize - 1) == topicType
				.getSubjectIdentifierConstraints().size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(
				subjectIdentifierConstraintsList, topicType
						.getSubjectIdentifierConstraints()));

	}
	
	@Test
	public void executeTestItemIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__ITEM_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		itemIdentifierSize = topicType.getItemIdentifierConstraints()
				.size();
		itemIdentifierConstraintsList = new ArrayList<ItemIdentifierConstraint>(
				topicType.getItemIdentifierConstraints());
		itemIdentifierConstraintsList.remove(constraint0);

		command.execute();

		Assert.assertTrue((itemIdentifierSize - 1) == topicType
				.getItemIdentifierConstraints().size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(
				itemIdentifierConstraintsList, topicType
						.getItemIdentifierConstraints()));

	}

	@Test
	public void executeTestSubjectLocator() {

		constraint0 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		subjectLocatorSize = topicType.getSubjectLocatorConstraints().size();
		subjectLocatorConstraintsList = new ArrayList<SubjectLocatorConstraint>(
				topicType.getSubjectLocatorConstraints());
		subjectLocatorConstraintsList.remove(constraint0);

		command.execute();

		Assert.assertTrue((subjectLocatorSize - 1) == topicType
				.getSubjectLocatorConstraints().size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(
				subjectLocatorConstraintsList, topicType
						.getSubjectLocatorConstraints()));

	}

	@Test
	public void canUndoTestNameType() {

		constraint0 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestOccurrenceType() {

		constraint0 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestSubjectIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}
	
	@Test
	public void canUndoTestItemIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__ITEM_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestSubjectLocator() {

		constraint0 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestNameType() {

		constraint0 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		nameTypeSize = topicType.getNameConstraints().size();
		nameTypeConstraintsList = new ArrayList<NameTypeConstraint>(topicType
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert
				.assertTrue(nameTypeSize == topicType.getNameConstraints()
						.size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(
				nameTypeConstraintsList, topicType.getNameConstraints()));

	}

	@Test
	public void undoTestOccurrenceType() {

		constraint0 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		occurrenceTypeSize = topicType.getOccurrenceConstraints().size();
		occurrenceTypeConstraintsList = new ArrayList<OccurrenceTypeConstraint>(
				topicType.getOccurrenceConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(occurrenceTypeSize == topicType
				.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(
				occurrenceTypeConstraintsList, topicType
						.getOccurrenceConstraints()));

	}

	@Test
	public void undoTestSubjectIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		subjectIdentifierSize = topicType.getSubjectIdentifierConstraints()
				.size();
		subjectIdentifierConstraintsList = new ArrayList<SubjectIdentifierConstraint>(
				topicType.getSubjectIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(subjectIdentifierSize == topicType
				.getSubjectIdentifierConstraints().size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(
				subjectIdentifierConstraintsList, topicType
						.getSubjectIdentifierConstraints()));

	}
	
	@Test
	public void undoTestItemIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__ITEM_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		itemIdentifierSize = topicType.getItemIdentifierConstraints()
				.size();
		itemIdentifierConstraintsList = new ArrayList<ItemIdentifierConstraint>(
				topicType.getItemIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(itemIdentifierSize == topicType
				.getItemIdentifierConstraints().size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(
				itemIdentifierConstraintsList, topicType
						.getItemIdentifierConstraints()));

	}

	@Test
	public void undoTestSubjectLocator() {

		constraint0 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());

		subjectLocatorSize = topicType.getSubjectLocatorConstraints().size();
		subjectLocatorConstraintsList = new ArrayList<SubjectLocatorConstraint>(
				topicType.getSubjectLocatorConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(subjectLocatorSize == topicType
				.getSubjectLocatorConstraints().size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(
				subjectLocatorConstraintsList, topicType
						.getSubjectLocatorConstraints()));

	}

	@Test
	public void redoTestNameType() {

		constraint0 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType.getNameConstraints().add((NameTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__NAME_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();

		nameTypeSize = topicType.getNameConstraints().size();
		nameTypeConstraintsList = new ArrayList<NameTypeConstraint>(topicType
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert
				.assertTrue(nameTypeSize == topicType.getNameConstraints()
						.size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(
				nameTypeConstraintsList, topicType.getNameConstraints()));

	}

	@Test
	public void redoTestOccurrenceType() {

		constraint0 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		topicType.getOccurrenceConstraints().add(
				(OccurrenceTypeConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();

		occurrenceTypeSize = topicType.getOccurrenceConstraints().size();
		occurrenceTypeConstraintsList = new ArrayList<OccurrenceTypeConstraint>(
				topicType.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(occurrenceTypeSize == topicType
				.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(
				occurrenceTypeConstraintsList, topicType
						.getOccurrenceConstraints()));

	}

	@Test
	public void redoTestSubjectIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		topicType.getSubjectIdentifierConstraints().add(
				(SubjectIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();

		subjectIdentifierSize = topicType.getSubjectIdentifierConstraints()
				.size();
		subjectIdentifierConstraintsList = new ArrayList<SubjectIdentifierConstraint>(
				topicType.getSubjectIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(subjectIdentifierSize == topicType
				.getSubjectIdentifierConstraints().size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(
				subjectIdentifierConstraintsList, topicType
						.getSubjectIdentifierConstraints()));

	}
	
	
	@Test
	public void redoTestItemIdentifier() {

		constraint0 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE
				.createItemIdentifierConstraint();
		topicType.getItemIdentifierConstraints().add(
				(ItemIdentifierConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__ITEM_IDENTIFIER_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();

		itemIdentifierSize = topicType.getItemIdentifierConstraints()
				.size();
		itemIdentifierConstraintsList = new ArrayList<ItemIdentifierConstraint>(
				topicType.getItemIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(itemIdentifierSize == topicType
				.getItemIdentifierConstraints().size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(
				itemIdentifierConstraintsList, topicType
						.getItemIdentifierConstraints()));

	}

	@Test
	public void redoTestSubjectLocator() {

		constraint0 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint0);
		constraint1 = ModelFactory.eINSTANCE.createSubjectLocatorConstraint();
		topicType.getSubjectLocatorConstraints().add(
				(SubjectLocatorConstraint) constraint1);
		featureID = ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS;

		command = new DeleteTopicTypeConstraintItemCommand(topicType,
				constraint0, featureID);
		Assert.assertTrue(command.canExecute());
		command.execute();

		subjectLocatorSize = topicType.getSubjectLocatorConstraints().size();
		subjectLocatorConstraintsList = new ArrayList<SubjectLocatorConstraint>(
				topicType.getSubjectLocatorConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(subjectLocatorSize == topicType
				.getSubjectLocatorConstraints().size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(
				subjectLocatorConstraintsList, topicType
						.getSubjectLocatorConstraints()));

	}

}