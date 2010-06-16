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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveScopeConstraintsCommand extends AbstractCommand {

	private final ScopedTopicType scopedTopicType;
	private final List<ScopeConstraint> scope;
	private final List<ScopeConstraint> oldScope;
	
	public RemoveScopeConstraintsCommand(ScopedTopicType scopedTopicType,
			 ScopeConstraint scope) {
		this(scopedTopicType, new ArrayList<ScopeConstraint>());
		this.scope.add(scope);
	}
	
	public RemoveScopeConstraintsCommand(ScopedTopicType scopedTopicType,
			List<ScopeConstraint> scope) {
		super("Remove Scope");
		this.scopedTopicType = scopedTopicType;
		this.scope = scope;
		this.oldScope = new ArrayList<ScopeConstraint>(this.scopedTopicType.getScope());
	}

	public void execute() {
		scopedTopicType.getScope().removeAll(scope);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		scopedTopicType.getScope().clear();
		scopedTopicType.getScope().addAll(oldScope);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
