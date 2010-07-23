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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.views;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.views.extension.IModelPage;
import de.topicmapslab.tmcledit.model.views.pages.PropertyDetailPageFactory;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeTopic;

/**
 * This view shows the properties of a selected element. It's works like the
 * eclipse properties view and is used to replace it.
 * 
 * @author Hannes Niederhausen
 * 
 * 
 */
public class PropertyDetailView extends ViewPart implements ISelectionChangedListener {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.PropertyDetailView";

	private ScrolledPageBook pageBook;
	private IModelPage currentPage;
	private PropertyDetailPageFactory pageFactory;
	private Object lastSelection = null;

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().addSelectionChangedListener(this);
	}

	@Override
	public void dispose() {
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().addSelectionChangedListener(this);
		pageFactory.dispose();
		pageFactory = null;
		super.dispose();
	}

	@Override
	public void setFocus() {
	}

	private void setCurrentPage(IModelPage currentPage) {
		if (this.currentPage != null)
			this.currentPage.aboutToHide();

		this.currentPage = currentPage;
		pageBook.showPage(currentPage.getId());
		pageBook.reflow(true);
	}

    private void setSelection(ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection sel = (IStructuredSelection) selection;
		if ((sel != null) && (!sel.isEmpty())) {
			Object obj = sel.getFirstElement();

			if (obj instanceof TreeObject) {
				obj = ((TreeTopic) obj).getModel();
			}
			if (obj.equals(lastSelection))
				return;

			lastSelection = obj;
			IModelPage page = pageFactory.getPageFor(obj);
			
			try {
				setCurrentPage(page);
				page.setModel(obj);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			registerModelView(page);
		}
	}

	private void registerModelView(IModelPage page) {
		ModelView modelView = ModelView.getInstance();
		if (modelView != null) {
			page.setCommandStack(modelView.getEditingDomain().getCommandStack());
			// register actions
			ActionRegistry ar = (ActionRegistry) modelView.getAdapter(ActionRegistry.class);
			if (ar != null) {
				IActionBars actionBars = getViewSite().getActionBars();

				String tmp = ActionFactory.UNDO.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

				tmp = ActionFactory.REDO.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

				tmp = ActionFactory.SAVE.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

				tmp = ActionFactory.CLOSE.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));
			}
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());

		pageBook = toolkit.createPageBook(parent, SWT.V_SCROLL);
		pageBook.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		pageFactory = new PropertyDetailPageFactory(pageBook, (IViewSite) getSite());

		setCurrentPage(pageFactory.getEmptyPage());
		ISelection sel = TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().getSelection();
		setSelection(sel);
	}

	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		if (selection.size()==1)
			setSelection(selection);
		else
			setCurrentPage(pageFactory.getEmptyPage());
	    
    }

}
