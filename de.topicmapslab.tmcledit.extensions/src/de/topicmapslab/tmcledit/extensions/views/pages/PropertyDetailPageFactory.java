package de.topicmapslab.tmcledit.extensions.views.pages;

import java.util.HashMap;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class PropertyDetailPageFactory {
	private static final String TOPIC_MAP_SCHEMA = "TopicMapSchema";
	private static final String TOPIC_TYPE = "TopicType";
	private static final String DIAGRAM = "Diagram";
	private static final String ROLE = "role";
	private static final String PREFIX_MAPPING = "PrefixMapping";
	private static final String ASSOCIATION = "association";
	private static final String OCCURENCE_CONSTRAINT = "occurence_constraint";
	private static final String NAME_CONSTRAINT = "name_constraint";
	private static final String IDENTIFIER_CONSTRAINT = "idenntifier_constraint";
	
	private EmptyPage emptyPage;
	private HashMap<String, AbstractModelPage> pageMap = new HashMap<String, AbstractModelPage>();
	
	private final ScrolledPageBook pageBook;

	public PropertyDetailPageFactory(ScrolledPageBook pageBook) {
		super();
		this.pageBook = pageBook;
		emptyPage = new EmptyPage();
		emptyPage.createControl(pageBook.getContainer());
		this.pageBook.registerPage(emptyPage.getID(), emptyPage.getControl());
	}
	
	
	
	public AbstractModelPage getPageFor(Object model) {
		AbstractModelPage page = emptyPage;
		
		if (model instanceof TopicType) {
			page = pageMap.get(TOPIC_TYPE);
			if (page==null) {
				page = new TopicTypePage();
				page.createControl(pageBook.getContainer());
				pageMap.put(TOPIC_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
			return page;
		} else if  ( (model instanceof MappingElement) || (model instanceof EObjectContainmentEList) ) {
			page = pageMap.get(PREFIX_MAPPING);
			if (page==null) {
				page = new PrefixMappingPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(PREFIX_MAPPING, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof Diagram) {
			page = pageMap.get(DIAGRAM);
			if (page==null) {
				page = new DiagramPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(DIAGRAM, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof RoleTypeConstraints) {
			page = pageMap.get(ROLE);
			if (page==null) {
				page = new RoleModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(ROLE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof AssociationTypeConstraint) {
			page = pageMap.get(ASSOCIATION);
			if (page==null) {
				page = new AssociationConstraintModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(ASSOCIATION, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		}  else if (model instanceof OccurenceTypeConstraint) {
			page = pageMap.get(OCCURENCE_CONSTRAINT);
			if (page==null) {
				page = new OccurenceConstraintDetailPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(OCCURENCE_CONSTRAINT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		}  else if (model instanceof NameTypeConstraint) {
			page = pageMap.get(NAME_CONSTRAINT);
			if (page==null) {
				page = new NameConstraintDetailPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(NAME_CONSTRAINT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if  ((model instanceof SubjectLocatorConstraint) || (model instanceof SubjectIdentifierConstraint) ) {
			page = pageMap.get(IDENTIFIER_CONSTRAINT);
			if (page==null) {
				page = new IdentifierConstraintModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(IDENTIFIER_CONSTRAINT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof TopicMapSchema) {
			page = pageMap.get(TOPIC_MAP_SCHEMA);
			if (page==null) {
				page = new TopicMapSchemaPropertyPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(TOPIC_MAP_SCHEMA, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		}
		return page;
	}



	public AbstractModelPage getEmptyPage() {
		return emptyPage;
	}
	
}
