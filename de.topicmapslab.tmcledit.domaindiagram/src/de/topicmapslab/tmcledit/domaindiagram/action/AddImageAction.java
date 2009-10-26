/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.Base64;

public class AddImageAction extends AbstractCommandStackAction {
	/**
	 * 
	 */
	private TypeNode typeNode;
	

	public AddImageAction(CommandStack commandStack, TypeNode typeNode) {
		super(commandStack);
		this.typeNode = typeNode;
		if (typeNode.getImage()==null)
			setText("Add Image...");
		else
			setText("Change Image...");
	}

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
			
			GenericSetCommand command = new GenericSetCommand(typeNode, ModelPackage.TYPE_NODE__IMAGE, Base64.byteArrayToBase64(buf));
			getCommandStack().execute(command);
			
		} catch (Exception e) {
			Activator.getDefault().log(e);
		}
	}
}