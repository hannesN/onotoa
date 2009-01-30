/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.OccurenceType;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceTypeModelPage extends ScopedTopicTypePage {

	private Text datatypeText;
	private Button datatypeButton;
	
	public OccurenceTypeModelPage() {
		super("occurence type");
	}

	@Override
	protected void createAdditionalControls(Composite parent,
			FormToolkit toolkit) {
		
		toolkit.createLabel(parent, "Datatype:");
		datatypeText = toolkit.createText(parent, "", SWT.BORDER);
		datatypeText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		datatypeText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				getCastedModel().setDataType(datatypeText.getText());
			}
		});
		
		datatypeButton = toolkit.createButton(parent, "...", SWT.PUSH);
		hookButtonListener();
		
		super.createAdditionalControls(parent, toolkit);
	}
	
	private void hookButtonListener() {
		
		datatypeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
	}
	
	private OccurenceType getCastedModel() {
		return (OccurenceType) getModel();
	}
	
	@Override
	public void updateUI() {
		if (getCastedModel().getDataType()!=null)
			datatypeText.setText(getCastedModel().getDataType());
		else
			datatypeText.setText("");
		
		super.updateUI();
	}
	
}
