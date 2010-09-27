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
import java.util.List;

import org.tmapi.core.Construct;

import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.tmql4j.common.model.query.IQuery;
import de.topicmapslab.tmql4j.resultprocessing.model.IResult;

/**
 * Scanner for the has-scope template
 * 
 * @author Hannes Niederhausen
 *
 */
public class HasScopeScanner extends AbstractConstraintScanner {

	@Override
	protected void parseResults(IQuery q) {
		for (IResult rs : q.getResults()) {
			List<Object> res = rs.getResults();
			TemplateMatching matching = new TemplateMatching();
			
			matching.setContext((Construct) res.get(0));
			matching.addArgument(res.get(1));
			matching.addArgument(res.get(2));
			matching.addArgument(res.get(3));
			
			matching.addAffectedConstruct((Construct) res.get(4));
			Object tmp = res.get(5);
			if (tmp instanceof Collection<?>) {
				addAffectedConstructs((Collection<?>) tmp, matching);
			} else {
				matching.addAffectedConstruct((Construct) tmp);
			}
			addMatching(matching);
		}

	}

	@Override
	protected String getQuery() {
		return  "FOR $c IN // tmcl:scope-constraint " +
				"RETURN ( " +
				"$c >> traverse tmcl:constrained-statement, " +
				"$c >> traverse tmcl:constrained-scope, " +
				"$c / tmcl:card-min || \"0\", " +
				"$c / tmcl:card-min || \"1\", " +
				"$c," +
				"$c << players ) ";
	}

}
