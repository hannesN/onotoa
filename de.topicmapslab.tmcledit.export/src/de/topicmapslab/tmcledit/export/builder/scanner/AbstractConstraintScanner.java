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
package de.topicmapslab.tmcledit.export.builder.scanner;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.tmapi.core.Construct;
import org.tmapi.core.TopicMap;

import de.topicmapslab.ctm.writer.templates.ITemplateScanner;
import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.majortom.core.TopicMapSystemFactoryImpl;
import de.topicmapslab.tmcledit.export.Activator;
import de.topicmapslab.tmql4j.components.processor.runtime.ITMQLRuntime;
import de.topicmapslab.tmql4j.components.processor.runtime.TMQLRuntimeFactory;
import de.topicmapslab.tmql4j.path.components.processor.runtime.TmqlRuntime2007;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * @author Sven Krosse
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractConstraintScanner implements ITemplateScanner {

	protected static String TMCLPREFIX = "%prefix tmcl http://psi.topicmaps.org/tmcl/ ";
	
	private Set<TemplateMatching> matchings;

	public Set<TemplateMatching> getAdaptiveConstructs(TopicMap topicMap) {
		try {
			matchings = new HashSet<TemplateMatching>();

			ITMQLRuntime runtime = TMQLRuntimeFactory.newFactory().newRuntime(
			        new TopicMapSystemFactoryImpl().newTopicMapSystem(), TmqlRuntime2007.TMQL_2007 );

			String query = getQuery();
			IQuery q = runtime.run(topicMap, query);
			parseResults(q);
			return matchings;

		} catch (Exception e) {
			Activator.getDefault().logException(e);
		}
		return new HashSet<TemplateMatching>();
	}

	protected abstract void parseResults(IQuery q);
	
	protected void addMatching(TemplateMatching matching) {
		matchings.add(matching);
	}

	protected abstract String getQuery();

	protected void addAffectedConstructs(Collection<?> coll, TemplateMatching matching) {
		for (Object o : coll) {
			matching.addAffectedConstruct((Construct) o);
		}
	}
}
