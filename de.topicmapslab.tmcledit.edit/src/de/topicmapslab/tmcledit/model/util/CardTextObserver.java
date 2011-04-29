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
package de.topicmapslab.tmcledit.model.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.AbstractCardinalityConstraint;
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;

/**
 * Helper class to observe text fields for cardinalities
 * 
 * @author Hannes Niederhausen
 * 
 */
public class CardTextObserver implements FocusListener, DisposeListener,
		VerifyListener,  KeyListener {

	private final IModelProvider modelProvider;
	private final Text text;
	private final boolean isMin;

	protected CardTextObserver(Text text, boolean isMin) {
		this(text, null, isMin);
	}
	
	protected CardTextObserver(Text text, IModelProvider modelProvider,
			boolean isMin) {
		super();
		this.text = text;
		this.isMin = isMin;
		this.modelProvider = modelProvider;

		if (modelProvider!=null) {
			this.text.addFocusListener(this);
			this.text.addKeyListener(this);	
		}
		this.text.addDisposeListener(this);
		this.text.addVerifyListener(this);
		
		

	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		finish();
	}

	private void finish() {

		AbstractCardinalityConstraint model = (AbstractCardinalityConstraint) modelProvider.getModel();
		
		if (text.getText().length()==0) {
			if (isMin)
				text.setText(model.getCardMin());
			else
				text.setText(model.getCardMax());
		}
		
		if (isMin) {
			if (text.getText().equals(model.getCardMin()))
				return;
		} else {
			if (text.getText().equals(model.getCardMax()))
				return;
		}
		
		modelProvider.getCommandStack().execute(
				new SetCardinalityCommand(model, isMin, text.getText()));
    }

	public void widgetDisposed(DisposeEvent e) {
		text.removeFocusListener(this);
		text.removeVerifyListener(this);
		text.removeDisposeListener(this);
	}

	/**
	 * Factory method which adds the listeners used to observ the given text field
	 * @param text the text used to edit the cardinality
	 * @param modelProvider the model provider containing the model which will be modified by the text field
	 * @param isMin flag whether its a cardmin field or a card max field (which may contain an '*'
	 */
	public static void observe(Text text, IModelProvider modelProvider,
			boolean isMin) {
		new CardTextObserver(text, modelProvider, isMin);
	}

	/**
	 * Creates an instance of the verify listener.
	 * 
	 * The method is used, when the content of a text field should be verified, but the 
	 * model will be modified elsewhere. For instance in TextCellEditors.
	 * 
	 */
	public static void  addVerifyListener(Text text, boolean isMin) {
		new CardTextObserver(text, isMin);
	}
	
	public void verifyText(VerifyEvent e) {
		String text = e.text;
		
		Text textField = (Text) e.getSource();
		
		String text2 = textField.getText();
		if ( (text2.equals("*")) && (text.length()>0) && (e.start!=0)){
			e.doit = false;
			return;
		}
		
		if ( ( (text2.length()==0) 
			 || (e.start==0) )
			&& (text.equals("*")) && !isMin)
			return;

		
		
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])) {
				e.doit = false;
				return;
			}
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.character==SWT.CR)
			finish();
    }

	public void keyReleased(KeyEvent ke) {
    }
}