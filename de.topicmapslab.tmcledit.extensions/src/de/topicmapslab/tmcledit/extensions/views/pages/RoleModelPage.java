package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.TopicIndexer;

public class RoleModelPage extends AbstractModelPage{

	private Text cardMinText;
	private Text cardMaxText;
	private Text roleText;
	
	
	@Override
	public void updateUI() {
		RoleTypeConstraints rtc = (RoleTypeConstraints) getModel();
		cardMinText.setText(rtc.getCardMin());
		cardMaxText.setText(rtc.getCardMax());
		
		if (rtc.getType()!=null)
			roleText.setText(rtc.getType().getId());
		else
			roleText.setText("no type");
		
	}

	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(3, false));
		
	
		
		toolkit.createLabel(comp, "Role:");
		
		roleText = toolkit.createText(comp, "", SWT.BORDER);
		roleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button selectButton = toolkit.createButton(comp, "...", SWT.PUSH);
		selectButton.addSelectionListener(new SelectionListener());
		
				
		toolkit.createLabel(comp, "cardMin:");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMinText = toolkit.createText(comp, "", SWT.BORDER);
		cardMinText.setLayoutData(gd);
		cardMinText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			getCastedModel().setCardMin(cardMinText.getText());	
			}
		});
		
		toolkit.createLabel(comp, "cardMax:");
		cardMaxText = toolkit.createText(comp, "", SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cardMaxText.setLayoutData(gd);
		
		cardMaxText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			getCastedModel().setCardMax(cardMaxText.getText());	
			}
		});
		setControl(comp);
	}
	
	protected RoleTypeConstraints getCastedModel() {
		return (RoleTypeConstraints) getModel();
	}

	@Override
	public void notifyChanged(Notification notification) {
		updateUI();
		
	}

	private class SelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			ListSelectionDialog dlg = new ListSelectionDialog(
					roleText.getShell(),
					TopicIndexer.getInstance().getRoleTypes(),
					new ArrayContentProvider(),
					new TopicLabelProvider(),
					"Choose the tole type");
			
			if (dlg.open()==Dialog.OK) {
				if (dlg.getResult().length>0)
					getCastedModel().setType((TopicType) dlg.getResult()[0]);
				else
					getCastedModel().setType(null);
			}
			
			
		}
	}
	
	private class TopicLabelProvider implements ILabelProvider {

		@Override
		public Image getImage(Object element) {
			return ImageProvider.getImage(ImageConstants.ROLETYPE);
		}

		@Override
		public String getText(Object element) {
			return ((TopicType)element).getId();
		}

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}
		
	}
}
