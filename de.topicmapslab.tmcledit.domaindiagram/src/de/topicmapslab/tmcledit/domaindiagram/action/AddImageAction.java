/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import de.topicmapslab.tmcledit.diagram.action.AbstractCommandStackAction;
import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.SetImageCommand;
import de.topicmapslab.tmcledit.model.util.Base64;

/**
 * Action to add an image to a topic type.
 * 
 * @author Hannes Niederhausen
 *
 */
public class AddImageAction extends AbstractCommandStackAction {
	/**
	 * type node to change
	 */
	private TypeNode typeNode;
	

	/**
	 * Constructor
	 * 
	 * @param commandStack the {@link CommandStack} which executes the 
	 * @param typeNode the 
	 */
	public AddImageAction(CommandStack commandStack, TypeNode typeNode) {
		super(commandStack);
		this.typeNode = typeNode;
		if (typeNode.getImage()==null)
			setText("Add Image...");
		else
			setText("Change Image...");
	}

	/**
	 * Sets the node to change
	 * 
	 * @param typeNode the node which receives the model
	 */
	public void setTypeNode(TypeNode typeNode) {
		this.typeNode = typeNode;
	}
	
	@Override
	public void run() {
		try {
			
			FileDialog dialog = new FileDialog(Activator.getCurrentShell(), SWT.OPEN);
			String filename = dialog.open();
			if (filename==null)
				return;
			
			File file = new File(filename);
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[(int) file.getAbsoluteFile().length()];
			fis.read(buf);
			Image img = null;
			try {
				img = new Image(Display.getCurrent(), new ByteArrayInputStream(buf));
			} catch (Exception imgException) {
				MessageDialog.openError(Activator.getCurrentShell(), "Invalid file", "Invalid file type");
				return;
			} finally {
				if ( (img!=null) && (!img.isDisposed()) ) {
					img.dispose();
				}
			}
			
			Command command = new SetImageCommand(typeNode.getTopicType(), Base64.byteArrayToBase64(buf));
			getCommandStack().execute(command);
			
		} catch (Exception e) {
			Activator.getDefault().log(e);
		}
	}
}