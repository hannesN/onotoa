/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.commands.CreateDiagramCommand;

public class CreateDiagramAction extends Action {
	/**
	 * 
	 */
	private final ModelView modelView;

	/**
	 * @param modelView
	 */
	public CreateDiagramAction(ModelView modelView) {
		this.modelView = modelView;
	}

	@Override
	public String getText() {
		return "Create Diagram";
	}

	@Override
	public void run() {
		final File file = (File) modelView.getCurrentTopicMapSchema().eContainer();
		InputDialog dlg = new InputDialog(this.modelView.getSite().getShell(),
				"New Diagram..",
				"Please Enter the name of the new diagram", "", 
				new IInputValidator() {

					@Override
					public String isValid(String newText) {
						if (newText.length()==0)
							return "Please enter a name.";
						
						
						
						for (Diagram d : file.getDiagrams()) {
							if (d.getName().equals(newText)) {
								return "A diagram with that name already exists!";
							}
						}
						return null;										
					}
			
		});

		if (dlg.open() == Dialog.OK) {
			String name = dlg.getValue();
			this.modelView.getEditingDomain().getCommandStack().execute(
					new CreateDiagramCommand(name, file));
		}

	}
}