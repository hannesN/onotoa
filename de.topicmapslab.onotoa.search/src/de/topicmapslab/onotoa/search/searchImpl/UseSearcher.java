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
package de.topicmapslab.onotoa.search.searchImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.topicmapslab.onotoa.search.container.AbstractContainer;
import de.topicmapslab.onotoa.search.container.BasicTopicTypeContainer;
import de.topicmapslab.onotoa.search.util.UseType;
import de.topicmapslab.onotoa.search.wrapper.AssociationTypeConstraintWrapper;
import de.topicmapslab.onotoa.search.wrapper.KindOfUseWrapper;
import de.topicmapslab.onotoa.search.wrapper.UseWrapper;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * This class searches all Topic Types which are related to given TopicType. All
 * results are put into a container.
 * 
 * @author Sebastian Lippert
 * 
 */
public class UseSearcher implements ISearcher {

	private TopicType topicType;
	private BasicTopicTypeContainer con;
	private TopicMapSchema schema;

	/**
	 * Constructor
	 * 
	 * @param topicType
	 *            The type that should investigate
	 * @param schema
	 *            The schema that should used
	 */

	public UseSearcher(TopicType topicType, TopicMapSchema schema) {

		this.topicType = topicType;
		this.schema = schema;
		con = new BasicTopicTypeContainer("Search use for: " + this.topicType.getName(), this);

	}

	/**
	 * {@inheritDoc}
	 */
	public void fetchResult() {

		/*
		 * iterate over all Topic Types of the schema. If two types aren't equal
		 * the method createUseWrapper() tries to detect in which kind of
		 * relation these types are linked
		 */

		if (topicType instanceof AssociationType) {

			for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
				if (atc.getType().equals(topicType))
					con.addListElement(new AssociationTypeConstraintWrapper(atc));

			}

		}

		for (TopicType tt : schema.getTopicTypes()) {
			if (tt.equals(topicType))
				continue;
			createUseWrapper(topicType, tt);
		}
	}

	/**
	 * Detect the relation between two Topic Types. A UseWrapper will be created
	 * (if necessary) and relations will be added as KindOfUseWrapper.
	 * 
	 * @param type
	 *            Topic Type that is perhaps related to the inspected Topic Type
	 * @param tt
	 *            The inspected Topic Type
	 */

	private void createUseWrapper(TopicType type, TopicType tt) {

		// list for KindOfUseWrappers
		List<Object> kindList = new ArrayList<Object>();

		// check isa
		for (TopicType isa : tt.getIsa()) {
			if (isa.equals(type))
				kindList.add(new KindOfUseWrapper(UseType.Type));
		}

		// check ako
		for (TopicType ako : tt.getAko()) {
			if (ako.equals(type))
				kindList.add(new KindOfUseWrapper(UseType.Supertype));
		}

		// check name constraints
		for (NameTypeConstraint ntc : tt.getNameConstraints()) {
			if (type.equals(ntc.getType())) {
				kindList.add(new KindOfUseWrapper(UseType.Nametype));
			}
		}

		// check occurrence constraints
		for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
			if (type.equals(otc.getType())) {
				kindList.add(new KindOfUseWrapper(UseType.OccurrenceType));
			}
		}

		// check if inspected type is a role or player of an Association Type
		if (tt instanceof AssociationType) {

			// I really have no idea why the player search doesn't work!!! Thats
			// why I use the way you can see below

			// for (RolePlayerConstraint rpc :
			// ModelIndexer.getAssociationIndexer().getRolePlayerConstraintsFor(tt))
			// {
			// if (rpc.getPlayer() == type)
			// kindList.add(new KindOfUseWrapper(TreeNodeType.Player));
			// }

			for (RoleConstraint rc : ((AssociationType) tt).getRoles()) {
				if (rc.getType().equals(type)) {
					kindList.add(new KindOfUseWrapper(UseType.Role));
				}
			}
		}

		// inspect if topicType plays a role. Only in use because of the not
		// working way above
		for (AbstractTypedConstraint constraint : ModelIndexer.getConstraintIndexer().getConstraintsByType(tt)) {

			if (constraint instanceof AssociationTypeConstraint)
				for (RolePlayerConstraint playerConstraint : ((AssociationTypeConstraint) constraint)
				        .getPlayerConstraints())

					if (playerConstraint.getPlayer().equals(topicType)) {
						kindList.add(new KindOfUseWrapper(UseType.Player));
					}
		}

		// check if any relation exists and when true: create wrapper
		if (kindList.size() > 0) {
			con.addListElement(new UseWrapper(tt, kindList));
		}

	}

	/**
	 * {@inheritDoc}
	 */

	public AbstractContainer getResult() {
		// TODO Auto-generated method stub

		Collections.sort((List<? extends Comparable>) con.getContentList());
		return con;
	}

	/**
	 * {@inheritDoc}
	 */

	public void refresh() {
		con.removeAllElements();
		fetchResult();
	}

	/**
	 * Setter for Topic Type that should inspected
	 * 
	 * @param tt
	 *            TopicType that should be inspected
	 */

	public void setTopicType(TopicType tt) {
		topicType = tt;
	}
}
