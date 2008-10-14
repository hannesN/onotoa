/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.extensions.views.pages.AbstractModelPage;
import de.topicmapslab.tmcledit.extensions.views.pages.PropertyDetailPageFactory;
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

	private PageBook pageBook;

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

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
		if ( ( (part instanceof ModelView) || (part instanceof TMCLDiagramEditor) ) 
				&& (selection instanceof IStructuredSelection) ){
			
			IStructuredSelection sel = (IStructuredSelection) selection;
			if (!sel.isEmpty()) {
				Object obj = sel.getFirstElement();
				
				if (obj instanceof TreeTopic) {
					obj = ((TreeTopic)obj).getTopic();
				}
				

				AbstractModelPage page = pageFactory.getPageFor(obj);
				pageBook.showPage(page.getControl());
				page.setModel(obj);
				
				
				if (part instanceof ModelView) {
					ModelView modelView = (ModelView) part;
					page.setCommandStack(modelView.getEditingDomain().getCommandStack());
				} else {
					TMCLDiagramEditor currentEditor = (TMCLDiagramEditor) part;
					page.setCommandStack(currentEditor.getEditingDomain().getCommandStack());
				}
				
					

			}
		}
		
	}

	@Override
	public void createPartControl(Composite parent) {
		pageBook = new PageBook(parent, SWT.NONE);
		pageFactory = new PropertyDetailPageFactory(pageBook);

		pageBook.showPage(pageFactory.getEmptyPage().getControl());

	}

}
