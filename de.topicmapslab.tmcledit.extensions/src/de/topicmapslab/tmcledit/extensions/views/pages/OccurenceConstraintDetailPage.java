package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
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
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.extensions.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.extensions.dialogs.NewTopicTypeWizard;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class OccurenceConstraintDetailPage extends AbstractScopedContraintModelPage {
	
	private Text typeText;
	private Button typeButton;
	private Text datatypeText;
	private Button datatypeButton;
	private Button uniqueButton;
	private Section section;
	
	public OccurenceConstraintDetailPage() {
		super("occurence constraint");
	}
	
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		section = toolkit.createSection(parent, Section.EXPANDED
				| Section.TITLE_BAR);
		section.setText("Occurence Constraint");
		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new GridLayout(2, false));
		
		Hyperlink link = toolkit.createHyperlink(comp, "Type:", SWT.NONE);
		link.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e) {
				NewTopicTypeWizard wizard = new NewTopicTypeWizard();
				wizard.setDefaultType(KindOfTopicType.OCCURENCE_TYPE);
				WizardDialog dlg = new WizardDialog(section.getShell(), wizard);
				
				if (dlg.open()==Dialog.OK) {
					TopicType tt = wizard.getNewTopicType();
					ModelIndexer.getInstance().getTopicMapSchema().getTopicTypes().add(tt);
					getCastedModel().setType(tt);
				}
				
			}
		});

		createTypeComposite(toolkit, comp);
		
		toolkit.createLabel(comp, "Datatype:");
		createDatatypeComposite(toolkit, comp);
		
		createCommonConstraintControls(comp, toolkit);
		
		toolkit.createLabel(comp, "Unique:");
		uniqueButton = toolkit.createButton(comp, "", SWT.CHECK);
		section.setClient(comp);
		setControl(section);
		
		hookButtonListener();
	}

	private void createTypeComposite(FormToolkit toolkit, Composite parent) {
		Composite typeComp = toolkit.createComposite(parent);
		typeComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		typeComp.setLayout(layout);
		typeText = toolkit.createText(typeComp, "", SWT.BORDER);
		typeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		typeButton = toolkit.createButton(typeComp, "...", SWT.PUSH);
	}

	private void createDatatypeComposite(FormToolkit toolkit, Composite parent) {
		Composite typeComp = toolkit.createComposite(parent);
		typeComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		typeComp.setLayout(layout);
		datatypeText = toolkit.createText(typeComp, "", SWT.BORDER);
		datatypeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		datatypeText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				getCastedModel().setDataType(datatypeText.getText());
			}
		});
		
		datatypeButton = toolkit.createButton(typeComp, "...", SWT.PUSH);
	}
	
	private void hookButtonListener() {
		typeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilterTopicSelectionDialog dlg = new FilterTopicSelectionDialog(typeButton.getShell(), KindOfTopicType.OCCURENCE_TYPE);
				if (Dialog.OK==dlg.open()) {
					getCommandStack().execute(
									new GenericSetCommand(
											getModel(),
											ModelPackage.OCCURENCE_TYPE_CONSTRAINT__TYPE,
											dlg.getFirstResult()));
				}
			}
		});
		
		datatypeButton.addSelectionListener(new SelectionAdapter(){
			
		});
		
		uniqueButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getCommandStack().execute(
						new GenericSetCommand(
								getModel(),
								ModelPackage.OCCURENCE_TYPE_CONSTRAINT__UNIQUE,
								new Boolean(uniqueButton.getSelection())));
			}
		});
	}

	@Override
	public void updateUI() {
		OccurenceTypeConstraint castedModel = getCastedModel();
		if (castedModel.getType().getName()!=null) 
			typeText.setText(castedModel.getType().getName());
		else
			typeText.setText("");
		
		if (castedModel.getDataType()!=null)
			datatypeText.setText(castedModel.getDataType());
		else
			datatypeText.setText("");
		
		uniqueButton.setSelection(castedModel.isUnique());
		
		super.updateUI();
	}
	
	protected OccurenceTypeConstraint getCastedModel() {
		return (OccurenceTypeConstraint) getModel();
	}
}
