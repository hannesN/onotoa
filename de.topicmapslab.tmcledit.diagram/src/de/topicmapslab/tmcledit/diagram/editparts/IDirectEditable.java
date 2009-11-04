package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.gef.Request;
import org.eclipse.gef.tools.DirectEditManager;

public interface IDirectEditable {

	public abstract void performRequest(Request req);

	public abstract DirectEditManager getManager();

	public abstract void revertNameChange();

	public abstract void handleNameChange(String value);

}