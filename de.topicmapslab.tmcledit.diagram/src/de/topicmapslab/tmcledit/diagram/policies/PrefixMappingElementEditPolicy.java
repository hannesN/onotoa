package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.commands.UpdatePrefixCommand;

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
			 
			return new UpdatePrefixCommand((MappingElement) model, newKey, newValue);
		}
		
		return null;
	}

}
