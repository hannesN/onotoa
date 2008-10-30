package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.extensions.dialogs.TopicSelectionDialog;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ScopedConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetConstraintScopeCommand;

public abstract class AbstractScopedContraintModelPage extends
		AbstractConstraintModelPage {

	
	protected Text scopeText;
	private Button scopeButton;
	
	
	public AbstractScopedContraintModelPage(String id) {
		super(id);
	}
	
	@Override
	protected void createCommonConstraintControls(Composite parent,
			FormToolkit toolkit) {
		super.createCommonConstraintControls(parent, toolkit);
		GridDataFactory fac = GridDataFactory.createFrom(new GridData(GridData.FILL_HORIZONTAL));
		
		toolkit.createLabel(parent, "scope:");
		Composite comp = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		comp.setLayout(layout);
		fac.applyTo(comp);
		
		
		
		scopeText = toolkit.createText(comp, "", SWT.BORDER);
		scopeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		fac.applyTo(scopeText);
		scopeButton = toolkit.createButton(comp, "...", SWT.PUSH);
		
		hookButtonListeners();
	}

	protected void hookButtonListeners() {
		scopeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TopicSelectionDialog dlg = new TopicSelectionDialog(scopeButton.getShell(), null, KindOfTopicType.SCOPE_TYPE);
				dlg.setSelectedTopics(getCastedModel().getScope());
				if (Dialog.OK==dlg.open()) {
					List<TopicType> newScope = dlg.getSelectedTopics();
					getCommandStack().execute(new SetConstraintScopeCommand(getCastedModel(), newScope));
				}
			}
		});
	}
	
	private ScopedConstraint getCastedModel() {
		return (ScopedConstraint) getModel();
	}
	
	
	private void redrawScopeList() {
		StringBuffer buffer = new StringBuffer();
		
		for (Iterator<TopicType> it = getCastedModel().getScope().iterator(); it.hasNext();) {
			TopicType tt = it.next();
			buffer.append(tt.getId());
			if (it.hasNext())
				buffer.append(", ");
		}
		
		scopeText.setText(buffer.toString());
	}
	
	@Override
	public void updateUI() {
		super.updateUI();
		redrawScopeList();
	}
}
