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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;

/**
 * @author Hannes Niederhausen
 *
 */
public class GeneralSearchUseCommandHandler extends AbstractUseFinderHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShell(event); 

		
		FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(shell, false);
		if ( (dlg.open()!=Dialog.OK) || (dlg.getResult().length==0)) {
			return null;
		}
		
		TopicType tt = (TopicType) dlg.getResult()[0];

		startSearch(shell, tt);
		
		return null;
	}

}
