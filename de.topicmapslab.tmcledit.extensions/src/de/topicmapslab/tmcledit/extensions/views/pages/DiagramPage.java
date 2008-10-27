package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;

public class DiagramPage extends AbstractModelPage {

	private Text nameText;

	@Override
	public void updateUI() {
		nameText.setText(((Diagram) getModel()).getName());
	}

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));

		toolkit.createLabel(comp, "Name:");

		nameText = toolkit.createText(comp, "", SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setControl(comp);
		hookNameTextListeners();
	}

	private void hookNameTextListeners() {
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String newName = nameText.getText();
				
				if (newName.length() == 0)
					return;
				
				if (newName.equals(((Diagram) getModel()).getName()))
					return;

				getCommandStack().execute(new RenameDiagramCommand(newName,
										(Diagram) getModel()));

			}
		});

	}

	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
	}
}