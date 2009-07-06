/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
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

import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;
import de.topicmapslab.tmcledit.model.commands.SetOverlapCommand;
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

	private CTabItem item;
	private Text overlapText;

	public TopicTypePage() {
		super("topic type");
	}

	public TopicTypePage(String id) {
		super(id);
	}

	public Composite createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		Composite comp = toolkit.createComposite(parent);
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
							new RenameTopicTypeCommand((TopicType) getModel(),
									nameText.getText()));
				}
			}
		});

		toolkit.createLabel(comp, "Subject Identifiers:");
		identifierText = toolkit.createText(comp, "", SWT.BORDER
				| SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		identifierText.setLayoutData(gd);

		Button button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicType type = (TopicType) getModel();
				StringListSelectionDialog dlg = new StringListSelectionDialog(
						identifierText.getShell());
				dlg.setSelectedTopics(type.getIdentifiers());
				dlg
						.setInputDescription("Please enter the new subject identifier.");

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(
							new SetTopicTypeIdentifiersCommand(dlg
									.getStringList(), (TopicType) getModel()));
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
				StringListSelectionDialog dlg = new StringListSelectionDialog(
						identifierText.getShell());
				dlg.setSelectedTopics(type.getLocators());
				dlg
						.setInputDescription("Please enter the new subject locator.");

				if (dlg.open() == Dialog.OK) {
					getCommandStack()
							.execute(
									new SetTopicTypeLocatorsCommand(dlg
											.getStringList(),
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
					getCommandStack().execute(
							new SetIsACommand(dlg.getSelectedTopics(),
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
					getCommandStack().execute(
							new SetAkoCommand(dlg.getSelectedTopics(),
									(TopicType) getModel()));
				}
			}

		});

		toolkit.createLabel(comp, "overlap:");
		overlapText = toolkit
				.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		overlapText.setLayoutData(gd);
		button = toolkit.createButton(comp, "...", SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicSelectionDialog dlg = new TopicSelectionDialog(
						overlapText.getShell(), (TopicType) getModel());
				dlg.setSelectedTopics(((TopicType) getModel()).getAko());

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(
							new SetOverlapCommand(dlg.getSelectedTopics(),
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
				getCommandStack().execute(
						new SetAbstractTopicTypeCommand((TopicType) getModel(),
								abstractButton.getSelection()));
			}
		});

		createAdditionalControls(comp, toolkit);

		return comp;
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		item = new CTabItem(folder, SWT.None);
		item.setText("Topic Type");
		item.setControl(createPage(folder));
	}

	public void notifyChanged(Notification notification) {
		updateUI();
	}

	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}
	
	@Override
	public void updateUI() {
		if (nameText != null) {
			TopicType t = (TopicType) getModel();
			if (t != null) {
				nameText.setText(t.getName());
				item.setText(getTopicType(t));

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

				for (TopicType tt : t.getOverlap()) {
					b.append(tt.getName());
					b.append(", ");
				}
				if (b.length() > 0)
					overlapText.setText(b.substring(0, b.length() - 2));
				else
					overlapText.setText("");

				abstractButton.setSelection(t.isAbstract());
			} else {
				item.setText("Topic Type");
				nameText.setText("");
				identifierText.setText("");
				locatorText.setText("");
				isAText.setText("");
				akoText.setText("");
				abstractButton.setSelection(false);
				overlapText.setText("");
			}

		}
		super.updateUI();
	}

	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {
	}

	private String getTopicType(TopicType t) {
		if (t == null)
			return "Topic Type";
		switch (t.getKind()) {
		case ROLE_TYPE:
			return "Role Type";
		case NAME_TYPE:
			return "Name Type";
		case ASSOCIATION_TYPE:
			return "Association Type";
		case OCCURRENCE_TYPE:
			return "Occurrence Type";
		case SCOPE_TYPE:
			return "Scope Type";
		default:
			return "Topic Type";
		}
	}
}
