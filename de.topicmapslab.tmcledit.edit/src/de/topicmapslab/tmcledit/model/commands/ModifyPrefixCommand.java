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


import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.MappingElement;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModifyPrefixCommand extends AbstractCommand {

	private final MappingElement me;
	private final String oldKey;
	private final String newKey;
	private final String oldValue;
	private final String newValue;

	
	
	public ModifyPrefixCommand(MappingElement me, String newKey, String newValue) {
		super();
		this.me = me;
		this.oldKey = me.getKey();
		this.oldValue = me.getValue();
		this.newKey = newKey;
		this.newValue = newValue;
	}

	public void execute() {
		me.eSetDeliver(false);
		me.setKey(newKey);
		me.eSetDeliver(true);
		me.setValue(newValue);
	}
	
	@Override
	public void undo() {
		me.eSetDeliver(false);
		me.setKey(oldKey);
		me.eSetDeliver(true);
		me.setValue(oldValue);
	}

	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Update Prefix Mapping";
	}
	
	@Override
	protected boolean prepare() {
		return checkValue() || checkKey();
	}

	private boolean checkValue() {
		if (newValue == null) {
	        if (oldValue == null)
		        return false;
        } else {
	        if (newValue.equals(oldValue))
		        return false;
        }
		return true;
	}
	
	private boolean checkKey() {
	    if (oldKey == null) {
	        if (newKey == null)
		        return false;
        } else {
	        if (oldKey.equals(newKey))
		        return false;
        }
	    return true;
    }
}
