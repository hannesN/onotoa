package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.HashMap;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.ui.part.PageBook;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicType;

public class PropertyDetailPageFactory {
	private static final String TOPIC_TYPE = "TopicType";
	private static final String DIAGRAM = "Diagram";
	private static final String PREFIX_MAPPING = "PrefixMapping";
	
	private EmptyPage emptyPage;
	
	private HashMap<String, AbstractModelPage> pageMap = new HashMap<String, AbstractModelPage>();
	
	private final PageBook pageBook;

	public PropertyDetailPageFactory(PageBook pageBook) {
		super();
		this.pageBook = pageBook;
		
		emptyPage = new EmptyPage();
		emptyPage.createControl(pageBook);
	}
	
	
	
	public AbstractModelPage getPageFor(Object model) {
		AbstractModelPage page = emptyPage;
		
		if (model instanceof TopicType) {
			page = pageMap.get(TOPIC_TYPE);
			if (page==null) {
				page = new TopicTypePage();
				page.createControl(pageBook);
				pageMap.put(TOPIC_TYPE, page);
			}
			return page;
		} else if  ( (model instanceof MappingElement) || (model instanceof EObjectContainmentEList) ) {
			page = pageMap.get(PREFIX_MAPPING);
			if (page==null) {
				page = new PrefixMappingPage();
				page.createControl(pageBook);
				pageMap.put(PREFIX_MAPPING, page);
			}
		} else if (model instanceof Diagram) {
			page = pageMap.get(DIAGRAM);
			if (page==null) {
				page = new DiagramPage();
				page.createControl(pageBook);
				pageMap.put(DIAGRAM, page);
			}
		}
		return page;
	}



	public AbstractModelPage getEmptyPage() {
		return emptyPage;
	}
	
}
