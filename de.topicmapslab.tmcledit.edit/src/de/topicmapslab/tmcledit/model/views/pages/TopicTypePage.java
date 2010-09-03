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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
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
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddTopicReifiesConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.RemoveTopicReifiesConstraintsCommand;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.commands.SetCannotReifyConstraint;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;
import de.topicmapslab.tmcledit.model.commands.SetOverlapCommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeLocatorsCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.dialogs.StringListSelectionDialog;
import de.topicmapslab.tmcledit.model.dialogs.SubjectIdentifierListDialog;
import de.topicmapslab.tmcledit.model.dialogs.TopicSelectionDialog;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.index.TopicIndexer;
import de.topicmapslab.tmcledit.model.views.widgets.TypedCardinalityConstraintWidget;

/**
 * Property detail page for topic types.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class TopicTypePage extends AbstractEMFModelPage implements Adapter {

	private Text nameText;
	private Text identifierText;
	private Text locatorText;
	private Text isAText;
	private Text akoText;
	private Button abstractButton;
	private Button cannotReifyButton;

	protected CTabItem item;
	private Text overlapText;
	private ControlDecoration nameDecorator;
	
	private TypedCardinalityConstraintWidget reifiesControl;

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
			
			if (notification.getFeatureID(EList.class)== ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS) {
				refreshRefies();
				
			}

		}
			
		updateUI();
	}

	private void refreshRefies() {
		// widgets aren't initialized
		if (cannotReifyButton==null)
			return;
		TopicType t = getCastedModel();
		if (t.getTopicReifiesConstraints().size()==1) {
			boolean cannotReify = t.getTopicReifiesConstraints().get(0).getType()==null;
			cannotReifyButton.setSelection(cannotReify);
			reifiesControl.setEnabled(!cannotReify);
		} else {
			cannotReifyButton.setSelection(false);
			reifiesControl.setEnabled(true);
		}
		reifiesControl.getTableViewer().refresh();
    }

	private void createReifiesControl(Composite parent, FormToolkit toolkit) {
		toolkit.createLabel(parent, "Refies:");
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		cannotReifyButton = toolkit.createButton(parent, "Cannot reify", SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 2;
		cannotReifyButton.setLayoutData(gd);
		// placeholder
		toolkit.createComposite(parent);
		
		reifiesControl = new TypedCardinalityConstraintWidget(parent, toolkit, getCommandStack(), false);
		reifiesControl.setMaxCardinality(1);
		hookReifierListener();
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
	    super.setCommandStack(commandStack);
	    if (reifiesControl!=null)
	    	reifiesControl.setCommandStack(commandStack);
	}
	
	private void hookReifierListener() {
			reifiesControl.getAddButton().addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					TopicIndexer instance = ModelIndexer.getTopicIndexer();
					List<TopicType> list = new ArrayList<TopicType>();
					list.addAll(instance.getTopicTypes());
					list.removeAll(instance.getTypesByKind(KindOfTopicType.TOPIC_TYPE));
					
					
					for (TopicReifiesConstraint rc : getCastedModel().getTopicReifiesConstraints()) {
						if (rc.getType()!=null)
							list.remove(rc.getType());
					}

					ListSelectionDialog dlg = new ListSelectionDialog(reifiesControl.getShell(), list, new ArrayContentProvider(),
					        reifiesControl.new TopicLabelProvider(), "Choose the reifyable type");

					if (dlg.open() == Dialog.OK) {
						if (dlg.getResult().length == 0)
							return;

						List<TopicReifiesConstraint> trcl = new ArrayList<TopicReifiesConstraint>();
						for (Object tt : dlg.getResult()) {
							TopicReifiesConstraint trc = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
							trc.setType((TopicType) tt);
							trc.setCardMin("0");
							trc.setCardMax("1");
							trcl.add(trc);
						}
						AddTopicReifiesConstraintsCommand cmd = new AddTopicReifiesConstraintsCommand(getCastedModel(), trcl);
						getCommandStack().execute(cmd);

					}

				}
			});

			reifiesControl.getNewButton().addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					NewTopicTypeWizard wizard = new NewTopicTypeWizard(KindOfTopicType.TOPIC_TYPE);
					WizardDialog dlg = new WizardDialog(reifiesControl.getShell(), wizard);

					if (dlg.open() == Dialog.OK) {
						CompoundCommand cmd = new CompoundCommand();
						TopicType tt = wizard.getNewTopicType();
						cmd.append(new CreateTopicTypeCommand((TopicMapSchema) getCastedModel().eContainer(), tt));
						TopicReifiesConstraint trc = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
						trc.setType(tt);
						trc.setCardMin("0");
						trc.setCardMax("1");
						cmd.append(new AddTopicReifiesConstraintsCommand(getCastedModel(), trc));
						if (cmd.canExecute())
							getCommandStack().execute(cmd);

					}
				}
			});

			reifiesControl.getRemoveButton().addSelectionListener(new SelectionAdapter() {
				@SuppressWarnings("unchecked")
				@Override
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection sel = (IStructuredSelection) reifiesControl.getTableViewer().getSelection();

					if (sel.isEmpty())
						return;

					List<TopicReifiesConstraint> removeList = new ArrayList<TopicReifiesConstraint>();
					Iterator<TopicReifiesConstraint> it = sel.iterator();
					while (it.hasNext()) {
						removeList.add(it.next());
					}

					RemoveTopicReifiesConstraintsCommand cmd = new RemoveTopicReifiesConstraintsCommand(getCastedModel(), removeList);
					getCommandStack().execute(cmd);
				}
			});
			
			reifiesControl.getTableViewer().addFilter(new ViewerFilter() {
				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					TopicReifiesConstraint trc = (TopicReifiesConstraint) element;
					
				    return trc.getType()!=null;
				}
			});
			
			cannotReifyButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (cannotReifyButton.getSelection()) {
						getCommandStack().execute(new SetCannotReifyConstraint(getCastedModel()));
					} else {
						getCommandStack().execute(
								new RemoveTopicReifiesConstraintsCommand(getCastedModel(), 
										getCastedModel().getTopicReifiesConstraints()));
					}
					reifiesControl.setEnabled(!cannotReifyButton.getSelection());
				}
			});
	}

	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
		abstractButton.setEnabled(enabled);
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
				
				refreshRefies();
				
			} else {
				item.setText("Topic Type");
				nameText.setText("");
				identifierText.setText("");
				locatorText.setText("");
				isAText.setText("");
				akoText.setText("");
				abstractButton.setSelection(false);
				overlapText.setText("");
				// button can be null if we have a non topic type page (subclasses) 
				// because they don't call this.createAdditionalControls
				if (cannotReifyButton!=null)
					cannotReifyButton.setSelection(false);
			}

		}
		super.updateUI();
	}

	@Override
	public void setModel(Object model) {
		if (getModel()!=null) {
			for (TopicReifiesConstraint trc : getCastedModel().getTopicReifiesConstraints()) {
				trc.eAdapters().remove(this);
				if (trc.getType()!=null)
					trc.getType().eAdapters().remove(this);
			}
		}
	    super.setModel(model);
	    
	    if (model==null)
	    	return;
	    if (reifiesControl!=null) {
	    	reifiesControl.setInput(getCastedModel().getTopicReifiesConstraints());
	    	if (((TopicType) model).getTopicReifiesConstraints().size()==1) {
				boolean cannotReify = ((TopicType) model).getTopicReifiesConstraints().get(0).getType()==null;
				cannotReifyButton.setSelection(cannotReify);
				reifiesControl.setEnabled(!cannotReify);
			}
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
