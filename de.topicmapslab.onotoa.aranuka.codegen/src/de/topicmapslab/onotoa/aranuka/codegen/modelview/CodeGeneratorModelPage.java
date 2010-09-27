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
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import java.util.EventObject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.kuria.annotation.AnnotationBindingFactory;
import de.topicmapslab.kuria.swtgenerator.WidgetGenerator;
import de.topicmapslab.kuria.swtgenerator.edit.InputMask;
import de.topicmapslab.onotoa.aranuka.codegen.commands.SetAnnotationsCommand;
import de.topicmapslab.onotoa.aranuka.codegen.model.FieldData;
import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;
import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorDataContentProvider;
import de.topicmapslab.tmcledit.model.views.extension.AbstractModelPage;

/**
 * @author Hannes Niederhausen
 *
 */
public class CodeGeneratorModelPage extends AbstractModelPage implements CommandStackListener {

	
	private Composite control;
	private InputMask inputMask;

	private final Class<? extends GeneratorData> modelType;
	
	
	
	/**
     * @param modelType
     */
    public CodeGeneratorModelPage(Class<? extends GeneratorData> modelType) {
	    super();
	    this.modelType = modelType;
    }


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		control = toolkit.createComposite(parent);
		control.setLayout(new GridLayout());
		AnnotationBindingFactory fac = new AnnotationBindingFactory();
		fac.addClass(GeneratorData.class);
		fac.addClass(FieldData.class);
		fac.addClass(modelType);
	
		WidgetGenerator widgetGenerator = new WidgetGenerator(fac.getBindingContainer());
		inputMask = widgetGenerator.generateEditable(modelType, control);
		inputMask.getComposite().setLayoutData(new GridData(GridData.FILL_BOTH));
		inputMask.setContentProvider(new GeneratorDataContentProvider());
		adapt(toolkit, inputMask.getComposite());
		
		Button applyButton = toolkit.createButton(control, "Apply", SWT.PUSH);
		applyButton.addSelectionListener(new SelectionAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetAnnotationsCommand cmd = new SetAnnotationsCommand((GeneratorData) inputMask.getModel());
				// prepare data which means copy old values
				cmd.prepare();
				// persisting the data which means the annotations are created and set
				inputMask.persist();
				// now execute the command which stores the new values
				getCommandStack().execute(cmd);
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCommandStack(CommandStack commandStack) {
		if (getCommandStack()!=null)
			getCommandStack().removeCommandStackListener(this);
	    super.setCommandStack(commandStack);
	    if (getCommandStack()!=null)
	    	getCommandStack().addCommandStackListener(this);
	}

	private void adapt(FormToolkit toolkit, Control control) {
		toolkit.adapt(control, true, true);
		if (control instanceof Composite) {
			for (Control c : ((Composite) control).getChildren()) {
				adapt(toolkit, c);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Control getControl() {
	    return control;
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public void aboutToHide() {
    	setModel(null);
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void updateUI() {
	    inputMask.setModel(getModel());
	    inputMask.setEnabled(getModel()!=null);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(Object model) {
        super.setModel(model);
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return "de.topcimapslab.codegenerator.annotaionpage";
    }


	/**
     * {@inheritDoc}
     */
    @Override
    public void commandStackChanged(EventObject arg0) {
	    Object source = arg0.getSource();
	    if (source instanceof CommandStack) {
	    	Command cmd = ((CommandStack) source).getMostRecentCommand();
			if (cmd instanceof SetAnnotationsCommand) {
	    		if (((SetAnnotationsCommand) cmd).getData().equals(inputMask.getModel()))
	    			inputMask.refresh();
	    	}
	    }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
    	if (getCommandStack()!=null)
    		getCommandStack().removeCommandStackListener(this);
        super.dispose();
    }
}
