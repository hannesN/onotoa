package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.Activator;
import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.Diagram;

public class TreeDiagram extends TreeObject {

	private final Diagram diagram;
	
	
	public TreeDiagram(ModelView modelView, Diagram diagram) {
		super(modelView);
		this.diagram = diagram;
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

}
