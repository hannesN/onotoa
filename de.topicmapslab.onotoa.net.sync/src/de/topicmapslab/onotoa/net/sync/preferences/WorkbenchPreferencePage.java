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
package de.topicmapslab.onotoa.net.sync.preferences;

import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.topicmapslab.onotoa.net.sync.common.ServerData;

/**
 * @author Hannes Niederhausen
 * 
 */
public class WorkbenchPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private CheckboxTableViewer viewer;
	private List<ServerData> dataList;

	private Button addButton;
	private Button removeButton;
	private Button exportButton;
	private Button importButton;

	/**
	 * 
	 */
	public WorkbenchPreferencePage() {

	}

	/**
	 * @param title
	 */
	public WorkbenchPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param image
	 */
	public WorkbenchPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected Control createContents(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		
		comp.setLayout(new GridLayout(2, false));
		
		createTable(comp);
		
		createButtonBar(comp);
		
		
		return comp;
	}

	private void createButtonBar(Composite comp) {
		// TODO Auto-generated method stub
		
	}

	private void createTable(Composite comp) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

	private class ServerDataLabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ServerData sd = (ServerData) element;
			switch (columnIndex) {
			case 0:
				return sd.getName();
			case 1:
				return sd.getHost();
			case 2:
				return sd.getPort();
			}
			return null;
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}

	}
}
