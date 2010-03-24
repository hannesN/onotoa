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
package de.topicmapslab.onotoa.search.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.onotoa.search.dialogs.SubjectIdentifierDialog;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author niederhausen
 *
 */
public class ListSubjectIdentifierHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		List<String> result = new ArrayList<String>();
		for (TopicType tt : ModelIndexer.getTopicIndexer().getTopicTypes()) {
			result.addAll(tt.getIdentifiers());
		}
		
		Shell shell = HandlerUtil.getActiveShell(event);
		SubjectIdentifierDialog dlg = new SubjectIdentifierDialog(shell, result);
		
		dlg.open();
		
		return null;
	}

}
