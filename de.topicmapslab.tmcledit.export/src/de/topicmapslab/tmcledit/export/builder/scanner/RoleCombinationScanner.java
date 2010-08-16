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
 * Scanner for the role-combination template
 * 
 * @author Hannes Niederhausen
 *
 */
public class RoleCombinationScanner extends AbstractConstraintScanner {

	@Override
	protected void parseResults(IQuery q) {
		for (IResult r : q.getResults()) {
			List<Object> rl = r.getResults();
			TemplateMatching tm = new TemplateMatching();
			tm.setContext((Construct) rl.get(0));
			tm.addArgument(rl.get(1));
			tm.addArgument(rl.get(2));
			tm.addArgument(rl.get(3));
			tm.addArgument(rl.get(4));
			
			tm.addAffectedConstruct((Construct) rl.get(5));
			addAffectedConstructs((Collection<?>) rl.get(6), tm);
			
			
			addMatching(tm);
		}
	}

	@Override
	protected String getQuery() {
		return  "FOR $c IN // tmcl:role-combination-constraint " +
				"RETURN ( " +
				"$c >> traverse tmcl:constrained-statement, " +
				"$c >> traverse tmcl:constrained-role, " +
				"$c >> traverse tmcl:constrained-topic-type, " +
				"$c >> traverse tmcl:other-constrained-role, " +
				"$c >> traverse tmcl:other-constrained-topic-type, " +
				"$c, " +
				"$c << players )";
	}

}
