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

import de.topicmapslab.onotoa.search.dialogs.NonSubjectIdentifierDialog;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author niederhausen
 *
 */
public class ListNonSubjectIdentifierTopicsHandler extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		List<TopicType> result = new ArrayList<TopicType>();
		for (TopicType tt : ModelIndexer.getTopicIndexer().getTopicTypes()) {
			if ((tt.getIdentifiers().size()==0) && (tt.getLocators().size()==0))
				result.add(tt);
		}
		
		Shell shell = HandlerUtil.getActiveShell(event);
		NonSubjectIdentifierDialog dlg = new NonSubjectIdentifierDialog(shell, result);
		
		dlg.open();
		
		return null;
	}

}
