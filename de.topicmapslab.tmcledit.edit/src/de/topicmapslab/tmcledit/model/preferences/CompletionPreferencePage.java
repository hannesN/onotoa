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
package de.topicmapslab.tmcledit.model.preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.util.extension.PSIProviderInfo;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CompletionPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private static final String PREF_DEVIDER = "-----";

	private List<PSIInfoWrapper> wrapperList;
	private List<PSIProviderInfo> infoList;

	private CheckboxTableViewer viewer;

	/**
	 * 
	 */
	public CompletionPreferencePage() {
	}

	/**
	 * @param title
	 */
	public CompletionPreferencePage(String title) {
		super(title);
	}

	/**
	 * @param title
	 * @param image
	 */
	public CompletionPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);

		comp.setLayout(new GridLayout());

		Label l = new Label(comp, SWT.NONE);
		l.setText("Select PSIProvider you want to use.");
		
		viewer = CheckboxTableViewer.newCheckList(comp, SWT.BORDER);
		viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setCheckStateProvider(new ICheckStateProvider() {

			public boolean isGrayed(Object element) {
				return false;
			}

			public boolean isChecked(Object element) {
				return ((PSIInfoWrapper) element).active;
			}
		});
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				PSIInfoWrapper info = (PSIInfoWrapper) event.getElement();
				info.active = event.getChecked();
			}
		});

		viewer.setInput(getWrapperList());
		return comp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		IPreferenceStore store = TmcleditEditPlugin.getPlugin().getPreferenceStore();
		infoList = new ArrayList<PSIProviderInfo>(TmcleditEditPlugin.Implementation.getExtensionManager()
		        .getPsiProviderInfos());

		for (String s : store.getString(PreferenceConstants.P_PSIPREFERENCES).split("\\n")) {
			if (s.length() > 0) {
				String tmp[] = s.split(PREF_DEVIDER);
				if (tmp.length != 2) {
					TmcleditEditPlugin.getPlugin().log("Invalid arraysize: " + s);
					continue;
				}
				PSIProviderInfo pi = findProviderInfo(tmp[0]);
				if (pi == null) {
					TmcleditEditPlugin.getPlugin().log("Extension not found: " + tmp[0]);
					continue;
				}

				boolean active = Boolean.parseBoolean(tmp[1]);
				pi.setInUse(active);
				addWrapperInfo(pi, active);
			}
		}

		for (PSIProviderInfo info : infoList) {
			addWrapperInfo(info, true);
		}
		// freeing memory.. sort of ;)
		infoList = null;

	}

	private List<PSIInfoWrapper> getWrapperList() {
		if (wrapperList == null)
			return Collections.emptyList();
		return wrapperList;
	}

	private void addWrapperInfo(PSIProviderInfo prov, boolean active) {
		if (wrapperList == null)
			wrapperList = new ArrayList<PSIInfoWrapper>(2);
		wrapperList.add(new PSIInfoWrapper(prov, active));
	}

	private PSIProviderInfo findProviderInfo(String string) {
		Iterator<PSIProviderInfo> it = infoList.iterator();
		while (it.hasNext()) {
			PSIProviderInfo info = it.next();
			if (info.getId().equals(string)) {
				it.remove();
				return info;
			}
		}

		return null;
	}

	@Override
	public boolean performCancel() {
		for (PSIInfoWrapper wrapper : getWrapperList()) {
			PSIProviderInfo pi = wrapper.info;
			wrapper.active = pi.isInUse();
		}
		return true;
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		for (PSIInfoWrapper wrapper : getWrapperList()) {
			wrapper.active = true;
		}
		viewer.refresh();
	}

	@Override
	public boolean performOk() {
		String tmp = "";

		for (PSIInfoWrapper wrapper : getWrapperList()) {
			PSIProviderInfo pi = wrapper.info;
			pi.setInUse(wrapper.active);
			tmp += pi.getId() + PREF_DEVIDER + pi.isInUse() + "\n";
		}
		String tmp2 = tmp.substring(0, tmp.length() - 1);
		TmcleditEditPlugin.getPlugin().getPreferenceStore().putValue(PreferenceConstants.P_PSIPREFERENCES, tmp2);
		return true;
	}

	private class PSIInfoWrapper {
		public PSIProviderInfo info;
		public boolean active;

		public PSIInfoWrapper(PSIProviderInfo info, boolean active) {
			super();
			this.info = info;
			this.active = active;
		}
	}

	private class LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			PSIInfoWrapper wrapper = (PSIInfoWrapper) element;
			return wrapper.info.getName();
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
