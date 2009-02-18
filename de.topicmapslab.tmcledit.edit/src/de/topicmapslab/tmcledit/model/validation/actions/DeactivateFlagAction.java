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
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class DeactivateFlagAction extends ValidationAction {

	private int featureId;
	private TopicMapSchema schema;
	private Boolean value;
	

	public DeactivateFlagAction(CommandStack cmdStack, TopicMapSchema schema, int featureId, boolean value) {
		super(cmdStack);
		this.schema = schema;
		this.featureId = featureId;
		this.value = value;
		
		setText("Change "+schema.eClass().getEStructuralFeature(featureId).getName());
	}


	

	@Override
	public void run() {
		
		
		AbstractCommand cmd = new GenericSetCommand(schema, featureId, value);
		getCommandStack().execute(cmd);
	}

	
}
