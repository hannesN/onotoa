package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DirectEditManager;

/**
 * Interface for edit parts with dierect editable components
 * @author Hannes Niederhausen
 *
 */
public interface IDirectEditable {

	/**
	 * Performs the modify request
	 * @param req the request with the new value
	 */
	public abstract void performRequest(Request req);

	/**
	 * 
	 * @return a {@link DirectEditManager}
	 */
	public abstract DirectEditManager getManager();

	/**
	 * Reverting the name in the ui
	 */
	public abstract void revertNameChange();

	/**
	 * handles name changes in the ui
	 * @param value the new value
	 */
	public abstract void handleNameChange(String value);

}