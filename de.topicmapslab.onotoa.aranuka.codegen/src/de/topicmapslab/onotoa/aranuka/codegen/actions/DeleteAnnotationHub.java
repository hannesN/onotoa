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
package de.topicmapslab.onotoa.aranuka.codegen.actions;

import de.topicmapslab.onotoa.aranuka.codegen.commands.RemoveGeneratorDataCommand;
import de.topicmapslab.onotoa.aranuka.codegen.modelview.GeneratorDataNode;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteAnnotationHub extends AbstractSelectionAction {

	/**
	 * @param modelView
	 */
	public DeleteAnnotationHub(ModelView modelView) {
		super(modelView);
		setText("Delete Code Generator Meta Data");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		AbstractModelViewNode parent = (AbstractModelViewNode) sel.getFirstElement();

		RemoveGeneratorDataCommand cmd = new RemoveGeneratorDataCommand(parent);

		modelView.getCommandStack().execute(cmd);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (sel.size() == 1) {
			Object obj = sel.getFirstElement();
			if (obj instanceof GeneratorDataNode) {
				setEnabled(true);
				return;
			}
		}
		setEnabled(false);
	}

}
