/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class EmptyPage extends AbstractModelPage {

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout());
		toolkit.createLabel(comp, "Dumm di dumm, das is die Default seite");
		
		setControl(comp);
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void updateUI() {
	}

	@Override
	public void notifyChanged(Notification notification) {
	}
}