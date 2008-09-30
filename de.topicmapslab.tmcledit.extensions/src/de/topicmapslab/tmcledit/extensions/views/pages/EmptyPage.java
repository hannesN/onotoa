/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.Page;

public class EmptyPage extends Page {
	private Composite comp;

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		toolkit.createLabel(comp, "Dumm di dumm, das is die Default seite");
	}

	@Override
	public Control getControl() {
		return comp;
	}

	@Override
	public void setFocus() {
	}
}