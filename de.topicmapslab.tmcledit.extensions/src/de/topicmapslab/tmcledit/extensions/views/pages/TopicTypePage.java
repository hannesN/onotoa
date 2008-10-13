/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.extensions.TopicSelectionDialog;
import de.topicmapslab.tmcledit.extensions.command.RenameCommand;
import de.topicmapslab.tmcledit.extensions.command.SetIsACommand;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * Property detail page for topic types.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class TopicTypePage extends AbstractModelPage implements Adapter {

	private Text nameText;
	private Text isAText;
	private Text akoText;
	private Button abstractButton;

	private Section section;

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		section = toolkit.createSection(parent, Section.EXPANDED
				| Section.TITLE_BAR);
		section.setText("Dies st ein n test");
		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new GridLayout(3, false));

		toolkit.createLabel(comp, "Name:");
		nameText = toolkit.createText(comp, "", SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		nameText.setLayoutData(gd);
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (nameText.getText().length() > 0) {
					getEditingDomain().getCommandStack().execute(
							new RenameCommand((TopicType) getModel(), nameText
									.getText()));
				}
			}
		});

		toolkit.createLabel(comp, "is a:");
		isAText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		isAText.setLayoutData(gd);

		Button button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicSelectionDialog dlg = new TopicSelectionDialog(isAText
						.getShell());
				dlg.setAvailableTopics(getTopicMapSchema().getTopicTypes());
				dlg.setSelectedTopics(((TopicType) getModel()).getIsa());

				if (dlg.open() == Dialog.OK) {
						/* TODO  is setting command 
					getEditingDomain().getCommandStack().execute(
							new SetIsACommand(dlg.getSelectedTopics(),
									(TopicType) getModel())));
								*/
					new SetIsACommand(dlg.getSelectedTopics(),
							(TopicType) getModel()).execute();
				}
			}
		});

		toolkit.createLabel(comp, "kind of:");
		akoText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		akoText.setLayoutData(gd);

		section.setClient(comp);
	}

	@Override
	public Control getControl() {
		return section;
	}

	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
	}

	@Override
	public void updateUI() {
		if (nameText != null) {
			TopicType t = (TopicType) getModel();
			if (getTopicType(t) == null)
				section.setText("<...>");
			else
				section.setText(getTopicType(t));
			nameText.setText(t.getId());

			StringBuffer b = new StringBuffer();
			for (TopicType tt : t.getIsa()) {
				b.append(tt.getId());
				b.append(", ");
			}
			if (b.length() > 0)
				isAText.setText(b.substring(0, b.length() - 2));
			else
				isAText.setText("");

			b.setLength(0);
			for (TopicType tt : t.getAko()) {
				b.append(tt.getId());
				b.append(", ");
			}
			if (b.length() > 0)
				akoText.setText(b.substring(0, b.length() - 2));
			else
				akoText.setText("");
		}
	}

	@Override
	public void setModel(EObject model) {
		super.setModel(model);
	}

	private String getTopicType(TopicType t) {
		switch (t.getKind()) {
		case ROLE_TYPE:
			return "Role Type";
		case NAME_TYPE:
			return "Name Type";
		case ASSOCIATION_TYPE:
			return "Association Type";
		case OCCURENCE_TYPE:
			return "OccurenceType";
		case SCOPE_TYPE:
			return "Scope Type";
		default:
			return "Topic Type";
		}
	}
}
