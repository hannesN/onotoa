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
package de.topicmapslab.tmcledit.model.views.pages;

import java.util.HashMap;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.part.PageSite;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.extension.ExtensionManager;
import de.topicmapslab.tmcledit.model.util.extension.ModelViewExtensionInfo;
import de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider;

public class PropertyDetailPageFactory {
	private static final String TOPIC_MAP_SCHEMA = "TopicMapSchema";
	private static final String TOPIC_TYPE = "TopicType";
	private static final String ROLE_TYPE = "ROleType";
	private static final String NAME_TYPE = "NameType";
	private static final String OCCURRENCE_TYPE = "OccurrenceType";
	private static final String ASSOCIATION_TYPE = "AssociationType";
	private static final String DIAGRAM = "Diagram";
	private static final String ROLE = "role";
	private static final String PREFIX_MAPPING = "PrefixMapping";
	private static final String ASSOCIATION = "association";
	private static final String OCCURRENCE_CONSTRAINT = "occurrence_constraint";
	private static final String NAME_CONSTRAINT = "name_constraint";
	private static final String IDENTIFIER_CONSTRAINT = "idenntifier_constraint";
	private static final String COMMENT = "comment";
	private static final String SCOPE_CONSTRAINT = "scopeconstraint";
	private static final String REIFIER_CONSTRAINT = "reifierconstraint";
	
	private EmptyPage emptyPage;
	private HashMap<String, AbstractModelPage> pageMap = new HashMap<String, AbstractModelPage>();
	
	private final ScrolledPageBook pageBook;
	private final IViewSite viewSite;

	public PropertyDetailPageFactory(ScrolledPageBook pageBook, IViewSite viewSite) {
		super();
		this.pageBook = pageBook;
		emptyPage = new EmptyPage();
		emptyPage.createControl(pageBook.getContainer());
		this.pageBook.registerPage(emptyPage.getID(), emptyPage.getControl());
		this.viewSite = viewSite;
	}
	
	public void dispose() {
		for (AbstractModelPage page : pageMap.values()) {
			page.dispose();
		}
		pageMap.clear();
	}
	
	
	public AbstractModelPage getPageFor(Object model) {
		AbstractModelPage page = emptyPage;
		
		if (model instanceof OccurrenceType) {
			page = pageMap.get(OCCURRENCE_TYPE);
			if (page==null) {
				page = new OccurrenceTypeModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(OCCURRENCE_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof NameType) {
			page = pageMap.get(NAME_TYPE);
			if (page==null) {
				page = new NameTypeModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(NAME_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
			return page;
		} else if (model instanceof AssociationType) {
			page = pageMap.get(ASSOCIATION_TYPE);
			if (page==null) {
				page = new AssociationTypeModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(ASSOCIATION_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof RoleType) {
			page = pageMap.get(ROLE_TYPE);
			if (page==null) {
				page = new RoleTypePage();
				page.createControl(pageBook.getContainer());
				pageMap.put(ROLE_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof TopicType) {
			page = pageMap.get(TOPIC_TYPE);
			if (page==null) {
				page = new TopicTypePage();
				page.createControl(pageBook.getContainer());
				pageMap.put(TOPIC_TYPE, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
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
		} else if (model instanceof RolePlayerConstraint) {
			page = pageMap.get(ROLE);
			if (page==null) {
				page = new TopicRoleConstraintPage();
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
		}  else if (model instanceof OccurrenceTypeConstraint) {
			page = pageMap.get(OCCURRENCE_CONSTRAINT);
			if (page==null) {
				page = new OccurrenceConstraintDetailPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(OCCURRENCE_CONSTRAINT, page);
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
		} else if  ((model instanceof SubjectLocatorConstraint) 
					|| (model instanceof SubjectIdentifierConstraint)
					|| (model instanceof ItemIdentifierConstraint)) {
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
		} else if (model instanceof Comment) {
			page = pageMap.get(COMMENT);
			if (page==null) {
				page = new CommentPropertyModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(COMMENT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof ScopeConstraint) {
			page = pageMap.get(SCOPE_CONSTRAINT);
			if (page==null) {
				page = new ScopeConstraintModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(SCOPE_CONSTRAINT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else if (model instanceof ReifierConstraint) {
			page = pageMap.get(REIFIER_CONSTRAINT);
			if (page==null) {
				page = new ReifierConstraintModelPage();
				page.createControl(pageBook.getContainer());
				pageMap.put(REIFIER_CONSTRAINT, page);
				pageBook.registerPage(page.getID(), page.getControl());
			}
		} else {
			ExtensionManager extensionManager = TmcleditEditPlugin.getExtensionManager();
			for (ModelViewExtensionInfo infos : extensionManager.getModelViewExtensionInfos()) {
				IModelViewProvider mvp = infos.getProvider();
				
			}
			
		}
		if (page.getSite()==null)
			page.init(new PageSite(viewSite));
		
		return page;
	}



	public AbstractModelPage getEmptyPage() {
		return emptyPage;
	}
	
}
