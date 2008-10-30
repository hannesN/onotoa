/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.extensions.dialogs.TopicSelectionDialog;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;

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

	public TopicTypePage() {
		super("topic type");
	}
	
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
					getCommandStack().execute(
							new RenameTopicTypeCommand((TopicType) getModel(), nameText
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
						.getShell(), (TopicType) getModel());
				dlg.setSelectedTopics(((TopicType) getModel()).getIsa());

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(new SetIsACommand(dlg.getSelectedTopics(),
							(TopicType) getModel()));
				}
			}
		});

		toolkit.createLabel(comp, "kind of:");
		akoText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		akoText.setLayoutData(gd);
		button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicSelectionDialog dlg = new TopicSelectionDialog(akoText
						.getShell(), (TopicType) getModel());
				dlg.setSelectedTopics(((TopicType) getModel()).getAko());

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(new SetAkoCommand(dlg.getSelectedTopics(),
							(TopicType) getModel()));
				}
			}

			
		});

		toolkit.createLabel(comp, "isAbstract");
		abstractButton = toolkit.createButton(comp, "", SWT.CHECK);
		abstractButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandStack().execute(new SetAbstractTopicTypeCommand((TopicType) getModel(), abstractButton.getSelection()));
			}
		});
		
		section.setClient(comp);
		setControl(section);
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
			
			abstractButton.setSelection(t.isIsAbstract());
		}
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
