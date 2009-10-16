/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import de.topicmapslab.tmcledit.domaindiagram.Activator;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.util.Base64;

public class AddImageAction extends Action {
	/**
	 * 
	 */
	private TypeNode typeNode;
	

	public AddImageAction(TypeNode typeNode, String text) {
		super(text);
		this.typeNode = typeNode;
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
			
			this.typeNode.setImage(Base64.byteArrayToBase64(buf));
			
		} catch (Exception e) {
			Activator.getDefault().log(e);
		}
	}
}