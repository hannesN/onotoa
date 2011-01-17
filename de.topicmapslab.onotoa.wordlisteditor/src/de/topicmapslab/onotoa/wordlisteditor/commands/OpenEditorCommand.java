/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import de.topicmapslab.onotoa.wordlisteditor.Activator;
import de.topicmapslab.onotoa.wordlisteditor.editor.WordListEditor;

/**
 * Command Handler which opens the {@link WordListEditor}
 * 
 * @author Hannes Niederhausen
 *
 */
public class OpenEditorCommand extends AbstractHandler implements IHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Activator.getDefault().openEditor();
		
		return null;
	}

}
