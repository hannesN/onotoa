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
package de.topicmapslab.onotoa.aranuka.codegen.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;
import de.topicmapslab.onotoa.aranuka.codegen.modelview.GeneratorDataNode;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateGeneratorDataCommand extends AbstractCommand {

	private final AbstractModelViewNode parent;
	private GeneratorDataNode node;
	private int idx;

	/**
	 * @param parent
	 */
	public CreateGeneratorDataCommand(AbstractModelViewNode parent) {
		super();
		this.parent = parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		parent.addChild(idx, node);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		idx = node.getChildrenList().indexOf(node);
		parent.removeChild(node);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		for (AbstractModelViewNode n : parent.getChildrenList()) {
			if (n instanceof GeneratorDataNode)
				return false;
		}
		return super.canExecute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
		node = new GeneratorDataNode((ModelView) parent.getAdapter(ModelView.class));
		node.setModel(new GeneratorData((TMCLConstruct) parent.getModel()));
		idx = -1;
		return true;
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void redo() {
	    execute();
    }

	

}
