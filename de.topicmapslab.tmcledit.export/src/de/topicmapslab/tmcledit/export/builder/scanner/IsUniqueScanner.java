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
 * Scanner to find unique value constraints
 * 
 * @author Hannes Niederhausen
 *
 */
public class IsUniqueScanner extends AbstractConstraintScanner {

	@Override
	protected void parseResults(IQuery q) {
		for (IResult r : q.getResults()) {
			TemplateMatching matching = new TemplateMatching();
			matching.setContext((Construct) r.getResults().get(0));
						
			matching.addAffectedConstruct((Construct) r.getResults().get(1));
			addAffectedConstructs((Collection<?>) r.get(2), matching);
			addMatching(matching);
		}

	}

	@Override
	protected String getQuery() {
		return "FOR $c IN // tmcl:unique-value-constraint " +
			   "RETURN $c >> traverse tmcl:constrained-statement, " +
			   "$c, " +
			   "$c << players";
	}

}
