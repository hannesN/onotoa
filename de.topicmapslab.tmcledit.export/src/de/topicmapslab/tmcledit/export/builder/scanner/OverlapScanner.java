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

import org.tmapi.core.Construct;

import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * Scanner to find overlap declarations
 * 
 * @author Hannes Niederhausen
 *
 */
public class OverlapScanner extends AbstractConstraintScanner {

	@Override
	protected void parseResults(IQuery q) {
		for (IResult r : q.getResults()) {
			TemplateMatching matching = new TemplateMatching();
			matching.setContext((Construct) r.getResults().get(0));
			matching.addArgument(r.getResults().get(1));
			
			matching.addAffectedConstruct((Construct) r.getResults().get(2));
			addAffectedConstructs((Collection<?>) r.get(3), matching);
			addMatching(matching);
		}

	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.export.builder.scanner.AbstractConstraintScanner#getQuery()
	 */
	@Override
	protected String getQuery() {
		return TMCLPREFIX+"FOR $c IN // tmcl:overlap-declaration " +
			   "RETURN $c >> traverse tmcl:overlaps[0], " +
			   "$c >> traverse tmcl:overlaps[1], " +
			   "$c, $c << players << roles";
	}

}
