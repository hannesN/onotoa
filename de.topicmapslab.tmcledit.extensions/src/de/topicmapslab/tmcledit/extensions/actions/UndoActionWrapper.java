package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.ui.actions.ActionFactory;


/**
 * This class is just here to have the UpdateAction implemented
 * @author Hannes Niederhausen
 *
 */
public class UndoActionWrapper extends UndoAction implements UpdateAction {

	public UndoActionWrapper() {
		setId(ActionFactory.UNDO.getId());
	}
	
}
