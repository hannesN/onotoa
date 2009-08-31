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
package de.topicmapslab.tmcledit.diagram.preferences;

import java.util.HashMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.preferences.ColorScheme.ColorDefinition;

/**
 * Dialog to edit ColorScheme instances.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class ColorSchemeEditor extends Dialog {

	private ColorScheme scheme;
	private Text nameText;
	private ColorSelector topicColor;
	private ColorSelector secTopicColor;
	private ColorSelector commentColor;
	private ColorSelector secCommentColor;
	
	private ColorSelector commentFontColor;
	private ColorSelector topicFontColor;

	private Button useGradient;

	private HashMap<Object, String> errMap = new HashMap<Object, String>();

	protected ColorSchemeEditor(Shell parentShell) {
		super(parentShell);
		this.scheme = ColorScheme.getDefault().clone();
		this.scheme.setName("noname");
	}

	protected ColorSchemeEditor(Shell parentShell, ColorScheme scheme) {
		super(parentShell);
		this.scheme = scheme;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Color Scheme Editor...");
		newShell.setSize(400, 600);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyDialogFont(comp);
		
		Label l = new Label(comp, SWT.NONE);
		l.setText("Name:");
		
		nameText = new Text(comp, SWT.BORDER);
		nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		nameText.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				validateName();	
				validateColors();
				
			}
		});
		
		l = new Label(comp, SWT.NONE);
		l.setText("Topic Color:");
		topicColor = new ColorSelector(comp);
				
		l = new Label(comp, SWT.NONE);
		l.setText("Comment Color:");
		commentColor = new ColorSelector(comp);
		
		
		l = new Label(comp, SWT.NONE);
		l.setText("Topic Font Color:");
		topicFontColor = new ColorSelector(comp);
		
		l = new Label(comp, SWT.NONE);
		l.setText("Comment Font Color:");
		commentFontColor = new ColorSelector(comp);
		
		useGradient = new Button(comp, SWT.CHECK);
		useGradient.setText("Use Gradient");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		useGradient.setLayoutData(gd);
		
		
		l = new Label(comp, SWT.NONE);
		l.setText("Secondary Topic Color:");
		secTopicColor = new ColorSelector(comp);
		secTopicColor.addListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				validateColors();
				validateName();
			}
		});

		l = new Label(comp, SWT.NONE);
		l.setText("Secondary Comment Color:");
		secCommentColor = new ColorSelector(comp);
		secCommentColor.addListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				validateColors();
				validateName();
			}
		});
		
		useGradient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				secTopicColor.setEnabled(useGradient.getSelection());
				secCommentColor.setEnabled(useGradient.getSelection());
				validateColors();
				validateName();
			}
		});
		
		
		initFields();
		
		
		
		
		return comp;
	}

	private void initFields() {
		nameText.setText(scheme.getName());

		ColorDefinition cd = scheme.getTopicColor();
		topicColor.setColorValue(new RGB(cd.r, cd.g, cd.b));

		cd = scheme.getCommentColor();
		commentColor.setColorValue(new RGB(cd.r, cd.g, cd.b));
		
		cd = scheme.getCommentFontColor();
		commentFontColor.setColorValue(new RGB(cd.r, cd.g, cd.b));
		
		cd = scheme.getTopicFontColor();
		topicFontColor.setColorValue(new RGB(cd.r, cd.g, cd.b));

		cd = scheme.getTopicSecondaryColor();
		if (cd != null) {
			secTopicColor.setColorValue(cd.getRGB());
			cd = scheme.getCommentSecondaryColor();
			secCommentColor.setColorValue(cd.getRGB());
			useGradient.setSelection(true);
		} else {
			secTopicColor.setEnabled(false);
			secCommentColor.setEnabled(false);
			useGradient.setSelection(false);
		}

	}

	private void validateName() {
		String name = nameText.getText();
		String errorMsg = null;
		if (name.length() == 0)
			errorMsg = "No name given!";
		else if (name.equals(ColorScheme.getDefault().getName())) {
			errorMsg = "Name already in use.";
		} else {
			for (ColorScheme cs : DiagramActivator.getDefault().getSchemeList()) {
				if (cs == scheme)
					continue;
				if (cs.getName().equals(name)) {
					errorMsg = "Name already in use.";
					break;
				}
			}
		}

		setErrorMessage(nameText, errorMsg);
	}

	private void validateColors() {
		if (useGradient.getSelection()) {
			if (secCommentColor.getColorValue() == null) {
				setErrorMessage(secCommentColor,
						"No Secondary Color for Comments chosen");
			} else {
				setErrorMessage(secCommentColor, null);
			}
			if (secTopicColor.getColorValue() == null) {
				setErrorMessage(secTopicColor,
						"No Secondary Color for Comments chosen");
			} else {
				setErrorMessage(secTopicColor, null);
			}

		} else {
			setErrorMessage(secTopicColor, null);
			setErrorMessage(secCommentColor, null);
		}
	}

	private void setErrorMessage(Object source, String errorMsg) {

		if (errorMsg == null)
			errMap.remove(source);
		else
			errMap.put(source, errorMsg);

		Button button = getButton(IDialogConstants.OK_ID);
		if (button != null)
			button.setEnabled(errMap.isEmpty());
		// TODO Fehlermeldung
	}

	@Override
	protected void okPressed() {
		scheme.setName(nameText.getText());

		ColorDefinition cd = scheme.getTopicColor();
		cd.setRGB(topicColor.getColorValue());
		cd.dispose();

		cd = scheme.getCommentColor();
		cd.setRGB(commentColor.getColorValue());
		cd.dispose();

		cd = scheme.getTopicFontColor();
		cd.setRGB(topicFontColor.getColorValue());
		cd.dispose();
		
		cd = scheme.getCommentFontColor();
		cd.setRGB(commentFontColor.getColorValue());
		cd.dispose();
		
		if (useGradient.getSelection()) {
			cd = scheme.getTopicSecondaryColor();
			RGB rgb = secTopicColor.getColorValue();
			if (cd != null) {
				cd.setRGB(rgb);
				cd.dispose();
			} else {
				scheme.setTopicSecondaryColor(new ColorDefinition(rgb));
			}

			cd = scheme.getCommentSecondaryColor();
			rgb = secCommentColor.getColorValue();
			if (cd != null) {
				cd.setRGB(rgb);
				cd.dispose();
			} else {
				scheme.setCommentSecondaryColor(new ColorDefinition(rgb));
			}
		} else {
			scheme.setTopicSecondaryColor(null);
			scheme.setCommentSecondaryColor(null);
		}

		super.okPressed();
	}

	public ColorScheme getScheme() {
		return scheme;
	}

}
