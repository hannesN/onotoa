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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddScopeConstraintsCommand extends AbstractCommand {

	private final ScopedTopicType scopedTopicType;
	private final List<ScopeConstraint> scopes;
	
	
	
	public AddScopeConstraintsCommand(ScopedTopicType scopedTopicType,
			List<ScopeConstraint> scopes) {
		super("Add Scope Constraints");
		this.scopedTopicType = scopedTopicType;
		this.scopes = scopes;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		scopedTopicType.getScope().addAll(scopes);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		scopedTopicType.getScope().removeAll(scopes);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
