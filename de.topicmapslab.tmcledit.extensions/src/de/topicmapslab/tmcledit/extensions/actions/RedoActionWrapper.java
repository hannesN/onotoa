package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.ui.actions.ActionFactory;


/**
 * This class is just here to have the UpdateAction implemented
 * @author Hannes Niederhausen
 *
 */
public class RedoActionWrapper extends RedoAction implements UpdateAction {

	public RedoActionWrapper() {
		setId(ActionFactory.REDO.getId());
	}
}
