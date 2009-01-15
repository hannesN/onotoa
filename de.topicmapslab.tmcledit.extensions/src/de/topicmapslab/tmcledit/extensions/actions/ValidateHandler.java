/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.validation.ModelValidator;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ValidateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		ModelView view = (ModelView) activeWorkbenchWindow.getActivePage()
				.findView(ModelView.ID);
		if (view == null)
			return null;

		if (view.getCurrentTopicMapSchema() != null) {
			File file = (File) view.getCurrentTopicMapSchema().eContainer();

			ModelValidator validator = new ModelValidator(file);

			validator.validate();
		}
		return null;
	}

}
