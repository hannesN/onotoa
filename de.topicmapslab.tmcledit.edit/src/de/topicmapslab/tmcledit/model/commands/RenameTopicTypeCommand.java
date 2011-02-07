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

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class RenameTopicTypeCommand extends AbstractCommand {

	private final TopicType tt;
	private final String oldName;
	private final String newName;
	private SetTopicTypeIdentifiersCommand idCmd;

	
	public RenameTopicTypeCommand(TopicType tt, String newName) {
		super();
		this.tt = tt;
		this.oldName = tt.getName();
		this.newName = newName;
	}

	@Override
	public boolean canExecute() {
		if (ModelIndexer.getTopicIndexer().getTopicTypeByName(newName)!=null)
			return false;
		if (newName.length()==0)
			return false;
		
		if (!super.canExecute())
			return false;
		
		if ((idCmd != null) && (!idCmd.canExecute()))
			return false;
		
	    return true;
	}
	
	public void execute() {
		tt.setName(newName);
		if (idCmd!=null)
			idCmd.execute();
	}
	
	@Override
	public void undo() { 
		tt.setName(oldName);
		if (idCmd!=null)
			idCmd.undo();
	}

	public void redo() {
		tt.setName(newName);
		if (idCmd!=null)
			idCmd.redo();
	}
	
	private boolean isSyncAllowed() {
		TopicMapSchema schema = (TopicMapSchema) tt.eContainer();
		String baseLocator = schema.getBaseLocator();
		if ( (baseLocator==null) 
			|| (tt.getIdentifiers().size()>1)	
			|| (baseLocator.length()==0) ) {
			return false;
		}
		if (tt.getIdentifiers().size()==1) {
			// todo check if si was changed manually
			String id = tt.getIdentifiers().get(0);
			if (!id.startsWith(baseLocator))
				return false;
			else {
				id = id.replace(baseLocator, "");
				if (!id.equals(tt.getName().toLowerCase()))
					return false;
			}
		}
		
		return true;
    }
	
	@Override
	protected boolean prepare() {
		if (isSyncAllowed()) {
			List<String> newIds = new ArrayList<String>(1);
    		
    		TopicMapSchema schema = (TopicMapSchema) tt.eContainer();
    		String baseLocator = schema.getBaseLocator();
    		
    		if ( (!baseLocator.endsWith("/"))
    		   && (!baseLocator.endsWith(":")) )
    			baseLocator += "/";
    		
    		String newId = baseLocator + newName.replaceAll(" ", "_").toLowerCase();
    		newIds.add(newId);
    		idCmd = (new SetTopicTypeIdentifiersCommand(newIds, tt));
		}
		return true;
	}
}
