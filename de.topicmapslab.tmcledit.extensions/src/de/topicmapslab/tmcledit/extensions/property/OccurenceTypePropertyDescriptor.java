/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.property;

import org.eclipse.draw2d.GridData;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurenceType;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceTypePropertyDescriptor extends PropertyDescriptor {

	public OccurenceTypePropertyDescriptor(Object object,
			IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}

	@Override
	public CellEditor createPropertyEditor(Composite composite) {
	    if (!itemPropertyDescriptor.canSetProperty(object))
	    {
	      return null;
	    }
	    
	    CellEditor result = null;
	    
	    
	    
	    return result;
	}
	
	private class OccurenceTypeCellEditor extends CellEditor {

		@Override
		protected Control createControl(Composite parent) {
			Composite comp = new Composite(parent,SWT.None);
			
			GridLayout layout = new GridLayout(2, false);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			layout.verticalSpacing = 0;
			layout.horizontalSpacing = 0;
			comp.setLayout(layout);
			
			Combo combo = new Combo(comp, SWT.BORDER);
			combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			
			final Button button = new Button(comp, SWT.NONE);
			button.setText("...");
			button.setToolTipText("Create new OccrenceType");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					InputDialog dlg = new InputDialog(button.getShell(), "New OccureceType Name",
							"Please enter the name of the new OccurenceType",
							"", new IInputValidator() {
						@Override
						public String isValid(String newText) {
							if (newText.length()==0)
								return "Please enter a name!";
							// TODO check if name already exists
							return null;
						}
					});
					
					if (dlg.open()==InputDialog.OK) {
						OccurenceType oc = ModelFactory.eINSTANCE.createOccurenceType();
						oc.setId(dlg.getValue());
						
						
						
					}
						
				}
			});
			
			
			return comp;
		}

		@Override
		protected Object doGetValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void doSetFocus() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void doSetValue(Object value) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
