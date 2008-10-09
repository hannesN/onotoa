package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.diagram.command.RemoveNodeCommand;
import de.topicmapslab.tmcledit.diagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;

public class RemoveFromDiagramAction extends Action implements UpdateAction {

	public final static String ID = "de.topicmapslab.tmcleditor.removefromdiagram";
	
	private EditPart selectedEditPart;
	
	private final CommandStack commandStack;
	
	
	
	public RemoveFromDiagramAction(CommandStack commandStack) {
		super();
		this.commandStack = commandStack;
		update();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		return sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE);
	}
	
	@Override
	public void run() {
		if (selectedEditPart instanceof NodeEditPart) {
			Node node = (Node) selectedEditPart.getModel();
			commandStack.execute(new RemoveNodeCommand((Diagram) node.eContainer(), node));
		}
		// TODO Remove of node sub elements
	}
	
	@Override
	public String getText() {
		return "Remove from Diagram";
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public void setSelectedEditPart(EditPart selectedEditPart) {
		this.selectedEditPart = selectedEditPart;
		update();
	}
	
	@Override
	public void update() {
		if ( (selectedEditPart instanceof NodeEditPart)
			|| (selectedEditPart instanceof OccurenceTypeConstraintEditPart)
			|| (selectedEditPart instanceof NameTypeConstraintEditPart)
		 ) {
			setEnabled(true);
		} else {
			setEnabled(false);
		}
	}

}
