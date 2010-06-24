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
package de.topicmapslab.tmcledit.model.dialogs;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;

public class FilterTopicSelectionDialog extends FilteredItemsSelectionDialog {

	private static final String SETTINGS = FilterTopicSelectionDialog.class.getCanonicalName();
	
	private List<KindOfTopicType> kindOfTopicType = null;

	private TopicTypeComparator topicTypeComparator;
	
	private List<TopicType> excludeList;
	
	public FilterTopicSelectionDialog(Shell shell, KindOfTopicType... kind) {
		this(shell, false);
		this.kindOfTopicType = Arrays.asList(kind);
		setInitialPattern("?");
	}
	
	public FilterTopicSelectionDialog(Shell shell, boolean multi) {
		super(shell, multi);
		
		setListLabelProvider(new ListLabelProvider());
		setDetailsLabelProvider(new DetailLabelProvider());
		this.kindOfTopicType = Collections.emptyList();
		setInitialPattern("?");
	}
	
	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return new Composite(parent, SWT.NONE);
	}

	@Override
	protected ItemsFilter createFilter() {
		return new TopicFilter();
	}
	
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		List<TopicType> types = ModelIndexer.getTopicIndexer().getTopicTypes();
		progressMonitor.beginTask("Filling Topic List", types.size());

		for (TopicType type : types) {
			if (kindOfTopicType.size()>0) {
				if (kindOfTopicType.contains(type.getKind())) {
					contentProvider.add(type, itemsFilter);
				}
			} else {
				contentProvider.add(type, itemsFilter);
			}
			progressMonitor.worked(1);
		}
		
		
	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = TmcleditEditPlugin.getPlugin().getDialogSettings().getSection(SETTINGS);
		if (settings==null)
			settings = TmcleditEditPlugin.getPlugin().getDialogSettings().addNewSection(SETTINGS);
		
		return settings;
	}

	@Override
	public String getElementName(Object item) {
		return ((TopicType)item).getName();
	}

	@Override
	protected Comparator getItemsComparator() {
		if (topicTypeComparator==null)
			topicTypeComparator = new TopicTypeComparator();
		
		return topicTypeComparator;
	}

	public void setExcludeList(List<TopicType> excludeList) {
	    this.excludeList = excludeList;
    }
	
	public List<TopicType> getExcludeList() {
		if (excludeList==null)
			return Collections.emptyList();
		return excludeList;
    }
	
	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}

	private final class TopicTypeComparator implements Comparator<TopicType> {
		public int compare(TopicType o1, TopicType o2) {
			if (o1.equals(o2))
				return 0;
			
			TopicType tt1 = (TopicType) o1;
			TopicType tt2 = (TopicType) o2;
			
			return tt1.getName().compareTo(tt2.getName());
		}
	}

	private class TopicFilter extends ItemsFilter {

		@Override
		public boolean isConsistentItem(Object item) {
			return true;
		}

		@Override
		public boolean matchItem(Object item) {

			TopicType tt = (TopicType) item;
			
			if (getExcludeList().contains(tt))
				return false;
			
			return matches(tt.getName());
		}
		
	}
	
	private class ListLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			return null;
		}

		public String getText(Object element) {
			if (element==null)
				return "";
				
			TopicType tt = (TopicType) element;
			return tt.getName();
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
	
	private class DetailLabelProvider implements ILabelProvider {

		public Image getImage(Object element) {
			return null;
		}

		public String getText(Object element) {
			if (element==null)
				return "";
			TopicType tt = (TopicType) element;
			return tt.getName();
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
