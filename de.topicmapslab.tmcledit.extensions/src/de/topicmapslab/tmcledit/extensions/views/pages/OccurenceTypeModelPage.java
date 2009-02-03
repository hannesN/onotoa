/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.commands.SetDatatypeCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceTypeModelPage extends ScopedTopicTypePage {

	private final String xsdDatatypes[] = {
			"xsd:anyType",
			"xsd:anyURI",
			"xsd:base64Binary",
			"xsd:boolean",
			"xsd:byte",
			"xsd:date",
			"xsd:dateTime",
			"xsd:decimal",
			"xsd:double",
			"xsd:duration",
			"xsd:ENTITIES",
			"xsd:ENTITY",
			"xsd:float",
			"xsd:gDay",
			"xsd:gMonth",
			"xsd:gMonthDay",
			"xsd:gYear",
			"xsd:gYearMonth",
			"xsd:hexBinary",
			"xsd:ID",
			"xsd:IDREF",
			"xsd:IDREFS",
			"xsd:int",
			"xsd:integer",
			"xsd:language",
			"xsd:long",
			"xsd:Name",
			"xsd:NCName",
			"xsd:negativeInteger",
			"xsd:NMTOKEN",
			"xsd:NMTOKENS",
			"xsd:nonNegativeInteger",
			"xsd:nonPositiveInteger",
			"xsd:normalizedString",
			"xsd:NOTATION",
			"xsd:positiveInteger",
			"xsd:QName",
			"xsd:short",
			"xsd:string",
			"xsd:time",
			"xsd:token",
			"xsd:unsignedByte",
			"xsd:unsignedInt",
			"xsd:unsignedLong",
			"xsd:unsignedShort"
	};
	
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
				SetDatatypeCommand cmd = new SetDatatypeCommand(getCastedModel(), datatypeText.getText());
				getCommandStack().execute(cmd);
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
				ListDialog dlg = new ListDialog(datatypeButton.getShell());
				dlg.setLabelProvider(new LabelProvider(){
					@Override
					public String getText(Object element) {
						return (String) element;
					}
				});
				dlg.setContentProvider(new ArrayContentProvider());
				
				if (getCastedModel().getDataType()!=null)
					dlg.setInitialSelections(new String[]{getCastedModel().getDataType()});
				
				dlg.setInput(xsdDatatypes);
				
				if (dlg.open()==Dialog.OK) {
					if (dlg.getResult().length>0) {
						String r = (String) dlg.getResult()[0];
						SetDatatypeCommand cmd = new SetDatatypeCommand(getCastedModel(), r);
						getCommandStack().execute(cmd);
					}
					
				}
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
