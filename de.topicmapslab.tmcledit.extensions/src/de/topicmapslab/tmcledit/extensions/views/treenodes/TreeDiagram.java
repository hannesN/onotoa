package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.Activator;
import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;

public class TreeDiagram extends TreeObject {

	private final Diagram diagram;
	
	
	public TreeDiagram(ModelView modelView, Diagram diagram) {
		super(modelView);
		this.diagram = diagram;
		diagram.eAdapters().add(this);
	}
	
	@Override
	public String getName() {
		return diagram.getName();
	}
	
	@Override
	public void handleDoubleClick() {
		try {
			Activator.getDefault().getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(new TMCLEditorInput(diagram, 
							getModelView().getEditingDomain(),
							(UndoAction) getModelView().getActionRegistry().get(ActionFactory.UNDO.getId()),
							(RedoAction) getModelView().getActionRegistry().get(ActionFactory.REDO.getId()),
							true), TMCLDiagramEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Image getImage() {
		return null;// ImageProvider.getImage(ImageConstants.DIAGRAM);
	}
	
	@Override
	public void handleRename() {
		String oldName = diagram.getName();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree()
				.getShell(), "New Diagram Name..", "Please enter the new diagram name",
				oldName, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						if (newText.length() == 0)
							return "no name given";

						return null;
					}
				});
		if (InputDialog.OK == dlg.open()) {
			getModelView().getEditingDomain().getCommandStack().execute(
					new RenameDiagramCommand(dlg.getValue(), diagram));
		}
	}

	@Override
	public void dispose() {
		diagram.eAdapters().remove(this);
		super.dispose();
	}
	
	@Override
	public Object getModel() {
		return diagram;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if ( (notification.getEventType()==Notification.SET) && (notification.getFeatureID(String.class)==ModelPackage.DIAGRAM__NAME)){
			getModelView().getViewer().refresh(this);
		}
	}
}
