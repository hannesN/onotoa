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
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * @author niederhausen
 *
 */
public class HasRoleScanner extends AbstractConstraintScanner {

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.export.builder.scanner.AbstractConstraintScanner#parseResults(de.topicmapslab.tmql4j.common.model.query.IQuery)
	 */
	@Override
	protected void parseResults(IQuery q) {
		for (IResult rs : q.getResults()) {
			TemplateMatching matching = new TemplateMatching();
			
			List<Object> r = rs.getResults();
			
			matching.setContext((Construct) r.get(0));
			matching.addArgument(r.get(1));
			matching.addArgument(r.get(2));
			matching.addArgument(r.get(3));
			
			matching.addAffectedConstruct((Construct) r.get(4));
			addAffectedConstructs((Collection<?>) r.get(5), matching);
			
			addMatching(matching);
		}

	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.export.builder.scanner.AbstractConstraintScanner#getQuery()
	 */
	@Override
	protected String getQuery() {
		return "FOR $c IN // tmcl:association-role-constraint " +
				"RETURN (" +
				"$c >> traverse tmcl:constrained-statement, " +
				"$c >> traverse tmcl:constrained-role, " +
				"$c / tmcl:card-min || \"0\", " +
				"$c / tmcl:card-max || \"*\"," +
				"$c," +
				"$c << players )";
				
	}

}
