package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.Activator;
import de.topicmapslab.tmcledit.model.Diagram;

public class TreeDiagram extends TreeObject {

	private final Diagram diagram;
	
	public TreeDiagram(TreeViewer viewer, Diagram diagram, EditingDomain editingDomain) {
		super(viewer, editingDomain);
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
					.getActivePage().openEditor(new TMCLEditorInput(diagram, true, editingDomain), TMCLDiagramEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
