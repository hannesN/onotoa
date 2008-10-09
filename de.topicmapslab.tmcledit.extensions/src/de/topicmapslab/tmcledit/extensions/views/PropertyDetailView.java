/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
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
import de.topicmapslab.tmcledit.extensions.views.pages.EmptyPage;
import de.topicmapslab.tmcledit.extensions.views.pages.TopicTypePage;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeTopic;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * This view shows the properties of a selected element. It's works like 
 * the eclipse properties view and is used to replace it.
 * 
 *  @author Hannes Niederhausen
 * 
 *
 */
public class PropertyDetailView extends ViewPart implements ISelectionListener {

	private static final String TOPIC_TYPE = "TopicType";

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.PropertyDetailView";

	private PageBook pageBook;
	
	private HashMap<String, AbstractModelPage> pageMap = new HashMap<String, AbstractModelPage>();

	private EmptyPage emptyPage;
	
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
			pageBook.showPage(emptyPage.getControl());
			IStructuredSelection sel = (IStructuredSelection) selection;
			if (!sel.isEmpty()) {
				Object obj = sel.getFirstElement();
				
				if (obj instanceof TreeTopic) {
					obj = ((TreeTopic)obj).getTopic();
				}
				if (obj instanceof TopicType) {
					AbstractModelPage page = pageMap.get(TOPIC_TYPE);
					if (page == null) {
						page = new TopicTypePage();
						page.createControl(pageBook);
						pageMap.put(TOPIC_TYPE, page);
					}
					
					if (part instanceof ModelView) {
						ModelView modelView = (ModelView) part;
						page.setTopicMapSchema(modelView.getCurrentTopicMapSchema());
						page.setEditingDomain(modelView.getEditingDomain());
					} else {
						TMCLDiagramEditor currentEditor = (TMCLDiagramEditor) part;
						File file = (File) currentEditor.getDiagram().eContainer();
						page.setTopicMapSchema(file.getTopicMapSchema());
						page.setEditingDomain(currentEditor.getEditingDomain());
						
					}
					pageBook.showPage(page.getControl());
					page.setModel((EObject) obj);
					
				}
			}
		}
		
	}

	@Override
	public void createPartControl(Composite parent) {
		pageBook = new PageBook(parent, SWT.NONE);
		
		emptyPage = new EmptyPage();
		emptyPage.createControl(pageBook);
		
		pageBook.showPage(emptyPage.getControl());
		
	}


}
