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
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.dialogs.MessageDialog;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.commands.UpdatePrefixCommand;
import de.topicmapslab.tmcledit.model.util.PrefixKeyMatcher;

public class PrefixMappingElementEditPolicy extends AbstractDirectEditPolicy {

	public static final String EXT_EDITED = "edited";
	
	public static final String EXT_EDITED_KEY = "key";
	public static final String EXT_EDITED_VALUE = "value";
	
	@Override
	public Command getRenameCommand(Object model, DirectEditRequest request) {
		if (model instanceof MappingElement) {
			MappingElement element = (MappingElement) model;
			String newKey = element.getKey();
			String newValue = element.getValue();
			
			String data = (String) request.getExtendedData().get(EXT_EDITED_VALUE);
			if (data == EXT_EDITED_KEY)
				newKey = getNewString(request);
			else
				newValue = getNewString(request);
			 
			if (PrefixKeyMatcher.isValidKey(newValue))
				return new UpdatePrefixCommand((MappingElement) model, newKey, newValue);
			else
				MessageDialog
				.openError(request.getCellEditor().getControl().getShell(), "invalid key",
						"You've entered an invalid key!");
		}
		
		return null;
	}

}
