/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.extensions.views.ModelView;

/**
 * @author Hannes Niederhausen
 *
 */
public class CloseAction extends Action {

	private ModelView modelView;
	
	public CloseAction(ModelView modelView) {
		this.modelView = modelView;
	}
	
	@Override
	public String getText() {
		return "Close fsd";
	}
	
	@Override
	public int getAccelerator() {
		return SWT.CTRL | 'w';
	}
	
	@Override
	public String getId() {
		return ActionFactory.CLOSE.getId();
	}
	
	@Override
	public void run() {
		modelView.close();
	}
	
}
