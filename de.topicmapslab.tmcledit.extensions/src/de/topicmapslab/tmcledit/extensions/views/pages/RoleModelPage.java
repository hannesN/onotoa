package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.extensions.util.CardTextObserver;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.commands.SetRoleConstraintCommand;

public class RoleModelPage extends AbstractModelPage{

	private Text cardMinText;
	private Text cardMaxText;
	private Combo roleCombo;

	
	private AssociationTypeModelPage assPage;
	
	public RoleModelPage() {
		super("role");
	}
	
	@Override
	public void updateUI() {
		RolePlayerConstraint rpc = getCastedModel();
		
		updateCombo();
		
		cardMinText.setText(rpc.getCardMin());
		cardMaxText.setText(rpc.getCardMax());
		
	}

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		CTabFolder folder = new CTabFolder(parent, SWT.NONE);

		CTabItem item1 = new CTabItem(folder, SWT.NONE);
		item1.setText("Role Player Properties");
		Composite comp = createRoleConstraintProps(folder, toolkit);
		
		item1.setControl(comp);
		
		assPage = new AssociationTypeModelPage();
		assPage.createControl(folder);
		
		CTabItem item2 = new CTabItem(folder, SWT.NONE);
		item2.setText("Association Type Properties");
		item2.setControl(assPage.getControl());
		
		folder.setSelection(item1);
		setControl(folder);
	}

	@Override
	public void setCommandStack(CommandStack commandStack) {
		super.setCommandStack(commandStack);
		assPage.setCommandStack(commandStack);
	}
	
	private Composite createRoleConstraintProps(Composite parent,
			FormToolkit toolkit) {
		
		Section section = toolkit.createSection(parent, Section.EXPANDED
				| Section.TITLE_BAR);
		section.setText("Role Player Constraint");
		Composite comp = toolkit.createComposite(section);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 0;
		gridLayout.horizontalSpacing = 0;
		comp.setLayout(gridLayout);
		
		
		toolkit.createLabel(comp, "Role:");
		roleCombo = new Combo(comp, SWT.BORDER);
		roleCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		toolkit.adapt(roleCombo);
		
		roleCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = roleCombo.getSelectionIndex();
				if (index>-1) {
					RoleConstraint rc = getAssociationType().getRoles().get(index);
					SetRoleConstraintCommand cmd = new SetRoleConstraintCommand(getCastedModel(), rc);
					getCommandStack().execute(cmd);
				}
			}
		});
		
				
		toolkit.createLabel(comp, "cardMin:");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		cardMinText = toolkit.createText(comp, "", SWT.BORDER);
		cardMinText.setLayoutData(gd);
		CardTextObserver.observe(cardMinText, this, true);
				
		toolkit.createLabel(comp, "cardMax:");
		cardMaxText = toolkit.createText(comp, "", SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		cardMaxText.setLayoutData(gd);
		CardTextObserver.observe(cardMaxText, this, false);
		
		section.setClient(comp);
		return section;
	}
	
	private void updateCombo() {
		int index = -1;
		int i=0;
		roleCombo.removeAll();
		for (RoleConstraint rc : getAssociationType().getRoles()) {
			roleCombo.add(rc.getType().getName());
			if (rc.equals(getCastedModel().getRole()))
				index = i;
			i++;
		}
		if (index>-1)
			roleCombo.select(index);
	}
	
	protected RolePlayerConstraint getCastedModel() {
		return (RolePlayerConstraint) getModel();
	}

	protected AssociationType getAssociationType() {
		return (AssociationType) ((AssociationTypeConstraint) getCastedModel().eContainer()).getType();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
	}

	@Override
	public void setModel(Object model) {
		RolePlayerConstraint rpc = (RolePlayerConstraint) getCastedModel();
		
		if ( (rpc!=null) && (rpc.getRole()!=null) )
			rpc.getRole().eAdapters().remove(this);
		
		super.setModel(model);
		
		rpc = (RolePlayerConstraint) model;
		
		if (rpc.getRole()!=null)
			rpc.getRole().eAdapters().add(this);
		
		if (assPage != null)
			assPage.setModel(getAssociationType());
	}

}
