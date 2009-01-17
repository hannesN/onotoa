/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.extensions.views.ValidationErrorView;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.validation.ModelValidator;
import de.topicmapslab.tmcledit.model.validation.ValidationResult;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ValidateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		ModelView view = (ModelView) activePage
				.findView(ModelView.ID);
		if (view == null)
			return null;

		if (view.getCurrentTopicMapSchema() != null) {
			File file = (File) view.getCurrentTopicMapSchema().eContainer();

			ModelValidator validator = new ModelValidator(file);

			List<ValidationResult> list = validator.validate();
			
			try {
				ValidationErrorView vew = (ValidationErrorView) activePage.findView(ValidationErrorView.ID);
				
				if (list.size()==0) {
					MessageBox box = new MessageBox(activeWorkbenchWindow.getShell());
					box.setMessage("Everything is fine..");
					box.setText("Validation message");
					box.open();
				} else {
					if (vew == null)
						vew = (ValidationErrorView) activePage.showView(ValidationErrorView.ID);
					else
						activePage.activate(vew);
				}
				if (vew!=null)
					vew.setValidationResults(list);

			} catch (PartInitException e) {
				throw new RuntimeException(e);
			}
			
			
		}
		return null;
	}

}
