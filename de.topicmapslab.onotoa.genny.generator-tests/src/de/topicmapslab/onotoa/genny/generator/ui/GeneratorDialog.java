package de.topicmapslab.onotoa.genny.generator.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GeneratorDialog extends Dialog {

	private static String val = "Hallo\n" +
			"Dies ist ein kleiner Test," +
			"ganz ehrlich, \n" +
			"ist nur ganz kleine.";
	
	protected GeneratorDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		StyledText text = new StyledText(parent, SWT.BORDER);
		text.setContent(new GeneratorContent());
		text.setText(val);
		return text;
	}
	
	
	public static void main(String[] args) {
		Display d = new Display();
		GeneratorDialog dlg = new GeneratorDialog(null);
		dlg.open();
	}
}
