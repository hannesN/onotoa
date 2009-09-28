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
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetCardinalitiesCommand;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;
import de.topicmapslab.tmcledit.model.commands.SetOverlapCommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeLocatorsCommand;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.StringListSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.SubjectIdentifierListDialog;
import de.topicmapslab.tmcledit.model.dialogs.TopicSelectionDialog;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

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

	protected CTabItem item;
	private Text overlapText;
	private ControlDecoration nameDecorator;
	
	private Text reifiertypeText;
	private Button browseButton;
	private CCombo cardCombo;
	private Button hasReifierConstraintButton;

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
		nameDecorator = new ControlDecoration(nameText, SWT.LEFT|SWT.TOP);
		nameDecorator.setMarginWidth(2);
		nameDecorator.setShowOnlyOnFocus(true);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		nameText.setLayoutData(gd);
		nameText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				finishName();
			}
		});
		
		nameText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.character==SWT.CR)
					finishName();
			}
		});
		
		nameText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				TopicType topic = ModelIndexer.getTopicIndexer().getTopicTypeByName(nameText.getText());
				if ( (topic!=null) && (!topic.equals(getModel())) ){
					nameDecorator.setImage(FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage());
					nameDecorator.setDescriptionText("Name already in use!");
					nameDecorator.show();
				} else {
					nameDecorator.hide();
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
				SubjectIdentifierListDialog dlg = new SubjectIdentifierListDialog(
						identifierText.getShell());
				dlg.setText("Subject Identifier...");
				dlg.setTopicName(getCastedModel().getName());
				dlg.setSelectedTopics(type.getIdentifiers());
				dlg.setInputDescription("Please enter the new subject identifier.");

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
				dlg.setInputDescription("Please enter the new subject locator.");

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
				dlg.setTitle("Is a Selection...");
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
				dlg.setTitle("Kind of Selection...");

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
				dlg.setSelectedTopics(((TopicType) getModel()).getOverlap());
				dlg.setTitle("Overlap Selection...");

				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(
							new SetOverlapCommand(dlg.getSelectedTopics(),
									(TopicType) getModel()));
				}
			}

		});

		toolkit.createLabel(comp, "isAbstract:");
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
	
	public CTabItem getItem() {
	    return item;
    }

	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER)
			return; 
		
		if (notification.getNotifier()==getModel()) {
			if (notification.getFeatureID(Class.class)==ModelPackage.TOPIC_TYPE__NAME) {
				updateName();
				return;
			}
			if (notification.getFeatureID(Class.class)==ModelPackage.TOPIC_TYPE__IDENTIFIERS) {
				updateIdentifierts();
				return;
			}
			
			if (notification.getFeatureID(Class.class)==ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT) {
				TopicReifiesConstraint tmp = (TopicReifiesConstraint) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(this);
				
				tmp = (TopicReifiesConstraint) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(this);
				
				updateReifierUI();
				return;
			}
		}
		if (notification.getNotifier() instanceof TopicReifiesConstraint) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.TOPIC_REIFIES_CONSTRAINT__TYPE) {
				TopicType tmp = (TopicType) notification.getOldValue();
				if (tmp!=null)
					tmp.eAdapters().remove(this);
				
				tmp = (TopicType) notification.getNewValue();
				if (tmp!=null)
					tmp.eAdapters().add(this);
				
			}
			
			updateReifierUI();
			
			
			
		}
		
		
			
		updateUI();
	}

	private void createReifiesControl(Composite parent, FormToolkit toolkit) {
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		toolkit.createLabel(parent, "reifies:").setLayoutData(gd);

		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		comp.setLayout(layout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		comp.setLayoutData(gd);

		hasReifierConstraintButton = toolkit.createButton(comp, "constrained", SWT.CHECK);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		hasReifierConstraintButton.setLayoutData(gd);

		cardCombo = new CCombo(comp, SWT.BORDER);
		cardCombo.setItems(new String[] { "may", "cannot", "must" });
		cardCombo.select(0);

		reifiertypeText = toolkit.createText(comp, "", SWT.BORDER | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		reifiertypeText.setLayoutData(gd);

		browseButton = toolkit.createButton(comp, "...", SWT.PUSH);

		hookReifierListener();
	}
	
	private void hookReifierListener() {
		cardCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (cardCombo.getSelectionIndex()) {
				case 0:
					setMayReifier();
					break;
				case 1:
					setCannotReifier();
					break;
				case 2:
					setMustHaveReifier();
					break;
				}

			}
		});
		hasReifierConstraintButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (hasReifierConstraintButton.getSelection()) {
					cardCombo.select(0);
					cardCombo.setEnabled(true);
					browseButton.setEnabled(true);
					setMayReifier();
				} else {
					cardCombo.clearSelection();
					cardCombo.setEnabled(false);
					browseButton.setEnabled(false);
					reifiertypeText.setText("");
					getCommandStack().execute(
					        new GenericSetCommand(getCastedModel(),
					                ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT, null));
				}
			}
		});

		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(browseButton.getShell(), false);
				if (dlg.open() == Dialog.OK) {
					getCommandStack().execute(
					        new GenericSetCommand(getCastedModel().getTopicReifiesConstraint(),
					                ModelPackage.TOPIC_REIFIES_CONSTRAINT__TYPE, dlg.getFirstResult()));
				}
			}
		});
	}

	private void setMustHaveReifier() {
		browseButton.setEnabled(true);
		setReifierCardinality(1, 1);
	}

	private void setCannotReifier() {
		browseButton.setEnabled(false);
		setReifierCardinality(0, 0);
	}

	private void setMayReifier() {
		browseButton.setEnabled(true);
		setReifierCardinality(0, 1);
	}
	
	private void setReifierCardinality(int min, int max) {
		TopicReifiesConstraint trc = getCastedModel().getTopicReifiesConstraint();
		if (trc == null) {
			trc = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
			trc.setCardMin(Integer.toString(min));
			trc.setCardMax(Integer.toString(max));
			getCommandStack().execute(
			        new GenericSetCommand(getCastedModel(),
			                ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT, trc));
			return;
		}

		getCommandStack().execute(new SetCardinalitiesCommand(trc, Integer.toString(min), Integer.toString(max)));
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
		abstractButton.setEnabled(enabled);
	}
	
	private void updateReifierUI() {
		if (hasReifierConstraintButton==null)
			return;
		
		if (getCastedModel() == null) {
			hasReifierConstraintButton.setEnabled(false);
			cardCombo.setEnabled(false);
			browseButton.setEnabled(false);
			reifiertypeText.setEnabled(false);
			reifiertypeText.setText("");
			return;
		}
		TopicReifiesConstraint trc = getCastedModel().getTopicReifiesConstraint();
		hasReifierConstraintButton.setEnabled(true);
		cardCombo.setEnabled(true);
		browseButton.setEnabled(true);
		reifiertypeText.setEnabled(true);
		hasReifierConstraintButton.setSelection(trc != null);
		String text = "";
		if (trc != null) {
			if (trc.getType() != null)
				text = trc.getType().getName();
			else
				text = "tmdm:subject";
			
			if (trc.getCardMin().equals("0")) {
				if (trc.getCardMax().equals("0")) {
					cardCombo.select(1);
					browseButton.setEnabled(false);
					text = "";
				} else {
					cardCombo.select(0);
				}
			} else {
				cardCombo.select(2);
			}
		} else {
			cardCombo.setEnabled(false);
			browseButton.setEnabled(false);
		}
		
		reifiertypeText.setText(text);
	}
	
	@Override
	public void updateUI() {
		if (nameText != null) {
			TopicType t = (TopicType) getModel();
			if (t != null) {
				
				item.setText(getTopicType(t));

				updateName();
				updateIdentifierts();

				StringBuffer b = new StringBuffer();
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
			updateReifierUI();
		}
		super.updateUI();
	}

	@Override
	public void setModel(Object model) {
		if (getModel()!=null) {
			TopicReifiesConstraint trc = getCastedModel().getTopicReifiesConstraint();
			if (trc!=null) {
				trc.eAdapters().remove(this);
				if (trc.getType()!=null)
					trc.getType().eAdapters().remove(this);
			}
		}
	    super.setModel(model);
	    
	    if (model==null)
	    	return;
	    
	    TopicReifiesConstraint trc = getCastedModel().getTopicReifiesConstraint();
		if (trc!=null) {
			trc.eAdapters().add(this);
			if (trc.getType()!=null)
				trc.getType().eAdapters().add(this);
		}
	}
	
	private StringBuffer updateIdentifierts() {
	    StringBuffer b = new StringBuffer();
	    for (String s : getCastedModel().getIdentifiers()) {
	    	b.append(s);
	    	b.append(", ");
	    }
	    if (b.length() > 0)
	    	identifierText.setText(b.substring(0, b.length() - 2));
	    else
	    	identifierText.setText("");
	    return b;
    }

	private void updateName() {
	    nameText.setText(getCastedModel().getName());
    }

	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {
		createReifiesControl(parent, toolkit);
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
		case TOPIC_TYPE:
			return "Topic Type";
		default:
			return "Topic";
		}
	}
	
	private TopicType getCastedModel() {
        return (TopicType) super.getModel();
    }

	private void finishName() {
	    if (nameText.getText().length() > 0) {
	    	Command cmd;
	    	TopicType tt = getCastedModel();
	    	if (tt.getName().equals(nameText.getText()))
	    		return;
	    	
	    	cmd=new RenameTopicTypeCommand(tt, nameText.getText());
	    	
	    	if (cmd.canExecute()) {
	    		getCommandStack().execute(cmd);
	    		nameDecorator.hide();
	    	} else {
				String errormsg = "Name: "+nameText.getText()+" already used!";
				MessageDialog.openError(getSite().getShell(), "Invalid name", errormsg);
				
				nameText.setText(getCastedModel().getName());
	    	}
	    	
	    }
    }
}
