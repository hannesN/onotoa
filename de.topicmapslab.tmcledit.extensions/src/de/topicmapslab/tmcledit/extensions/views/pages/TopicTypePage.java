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

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetExclusiveCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeLocatorsCommand;
import de.topicmapslab.tmcledit.model.dialogs.StringListSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.TopicSelectionDialog;

/**
 * Property detail page for topic types.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class TopicTypePage extends AbstractModelPage implements Adapter {

	private Text nameText;
	private Text identifierText;
	private Text locatorText;
	private Text isAText;
	private Text akoText;
	private Button abstractButton;

	private Section section;
	private Text exclusiveText;

	public TopicTypePage() {
		super("topic type");
	}
	
	public TopicTypePage(String id) {
		super(id);
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

		toolkit.createLabel(comp, "Subject Identifiers:");
		identifierText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		identifierText.setLayoutData(gd);

		Button button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicType type = (TopicType) getModel();
				StringListSelectionDialog dlg = new StringListSelectionDialog(identifierText.getShell());
				dlg.setSelectedTopics(type.getIdentifiers());
				dlg.setInputDescription("Please enter the new subject identifier.");

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(new SetTopicTypeIdentifiersCommand(dlg.getStringList(),
							(TopicType) getModel()));
				}
			}
		});
		

		toolkit.createLabel(comp, "Subject Locators:");
		locatorText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		locatorText.setLayoutData(gd);

		button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicType type = (TopicType) getModel();
				StringListSelectionDialog dlg = new StringListSelectionDialog(identifierText.getShell());
				dlg.setSelectedTopics(type.getLocators());
				dlg.setInputDescription("Please enter the new subject locator.");

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(new SetTopicTypeLocatorsCommand(dlg.getStringList(),
							(TopicType) getModel()));
				}
			}
		});
		
		toolkit.createLabel(comp, "is a:");
		isAText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		isAText.setLayoutData(gd);

		button = toolkit.createButton(comp, "...", SWT.PUSH);
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
		
		toolkit.createLabel(comp, "exclusive:");
		exclusiveText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		exclusiveText.setLayoutData(gd);
		button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicSelectionDialog dlg = new TopicSelectionDialog(exclusiveText
						.getShell(), (TopicType) getModel());
				dlg.setSelectedTopics(((TopicType) getModel()).getAko());

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(new SetExclusiveCommand(dlg.getSelectedTopics(),
							(TopicType) getModel()));
				}
			}

			
		});

		toolkit.createLabel(comp, "isAbstract");
		abstractButton = toolkit.createButton(comp, "", SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 2;
		abstractButton.setLayoutData(gd);
		abstractButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandStack().execute(new SetAbstractTopicTypeCommand((TopicType) getModel(), abstractButton.getSelection()));
			}
		});
		
		section.setClient(comp);
		
		createAdditionalControls(comp, toolkit);
		
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
			nameText.setText(t.getName());

			StringBuffer b = new StringBuffer();
			for (String s : t.getIdentifiers()) {
				b.append(s);
				b.append(", ");
			}
			if (b.length() > 0)
				identifierText.setText(b.substring(0, b.length() - 2));
			else
				identifierText.setText("");
			
			b.setLength(0);
			
			for (String s : t.getLocators()) {
				b.append(s);
				b.append(", ");
			}
			if (b.length() > 0)
				locatorText.setText(b.substring(0, b.length() - 2));
			else
				locatorText.setText("");
			
			b.setLength(0);
			
			for (TopicType tt : t.getIsa()) {
				b.append(tt.getName());
				b.append(", ");
			}
			if (b.length() > 0)
				isAText.setText(b.substring(0, b.length() - 2));
			else
				isAText.setText("");

			b.setLength(0);
			for (TopicType tt : t.getAko()) {
				b.append(tt.getName());
				b.append(", ");
			}
			if (b.length() > 0)
				akoText.setText(b.substring(0, b.length() - 2));
			else
				akoText.setText("");
			
			b.setLength(0);
			
			for (TopicType tt : t.getExclusive()) {
				b.append(tt.getName());
				b.append(", ");
			}
			if (b.length() > 0)
				exclusiveText.setText(b.substring(0, b.length() - 2));
			else
				exclusiveText.setText("");

			
			abstractButton.setSelection(t.isAbstract());
		}
	}

	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {
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
