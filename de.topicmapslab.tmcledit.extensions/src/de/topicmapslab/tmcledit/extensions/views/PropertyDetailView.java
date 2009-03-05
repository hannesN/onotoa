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
package de.topicmapslab.tmcledit.extensions.views;

import java.util.Map;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.extensions.views.pages.AbstractModelPage;
import de.topicmapslab.tmcledit.extensions.views.pages.PropertyDetailPageFactory;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeTopic;

/**
 * This view shows the properties of a selected element. It's works like the
 * eclipse properties view and is used to replace it.
 * 
 * @author Hannes Niederhausen
 * 
 * 
 */
public class PropertyDetailView extends ViewPart implements ISelectionListener {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.PropertyDetailView";

	private ScrolledPageBook pageBook;
	private AbstractModelPage currentPage;
	private PropertyDetailPageFactory pageFactory;

	@Override
	public void init(IViewSite site) throws PartInitException {
		site.getPage().addSelectionListener(this);
		super.init(site);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void setFocus() {
	}

	private void setCurrentPage(AbstractModelPage currentPage) {
		if (this.currentPage!=null)
			this.currentPage.aboutToHide();
		
		this.currentPage = currentPage;
		pageBook.showPage(currentPage.getID());
		pageBook.reflow(true);
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if ( ( (part instanceof ModelView) || (part instanceof TMCLDiagramEditor) || (part instanceof ValidationErrorView)) 
				&& (selection instanceof IStructuredSelection) ){
			// register actions
			if (part instanceof ModelView) {
				Map<String, IAction> ar = ((ModelView)part).getActionRegistry();
				IActionBars actionBars = getViewSite().getActionBars();
				
				String tmp = ActionFactory.UNDO.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.get(tmp));
				
				tmp = ActionFactory.REDO.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.get(tmp));
				
				tmp = ActionFactory.SAVE.getId();
				actionBars.setGlobalActionHandler(tmp, (IAction) ar.get(tmp));
			}
			IStructuredSelection sel = (IStructuredSelection) selection;
			if (!sel.isEmpty()) {
				Object obj = sel.getFirstElement();
				
				if (obj instanceof TreeObject) {
					obj = ((TreeTopic)obj).getModel();
				}
				AbstractModelPage page = pageFactory.getPageFor(obj);
				setCurrentPage(page);
				page.setModel(obj);
				
				if (part instanceof ValidationErrorView)
					part = part.getSite().getWorkbenchWindow().getActivePage().findView(ModelView.ID);
				
				if (part instanceof ModelView) {
					ModelView modelView = (ModelView) part;
					page.setCommandStack(modelView.getEditingDomain().getCommandStack());
				} else if (part instanceof TMCLDiagramEditor) {
					TMCLDiagramEditor currentEditor = (TMCLDiagramEditor) part;
					page.setCommandStack(currentEditor.getEditingDomain().getCommandStack());
				}
			}
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		pageBook = toolkit.createPageBook(parent, SWT.V_SCROLL);
		pageBook.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		pageFactory = new PropertyDetailPageFactory(pageBook);

		setCurrentPage(pageFactory.getEmptyPage());

	}

}
