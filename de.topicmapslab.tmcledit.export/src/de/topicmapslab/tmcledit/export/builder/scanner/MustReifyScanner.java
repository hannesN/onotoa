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
import org.tmapi.core.Topic;

import de.topicmapslab.ctm.writer.templates.TemplateMatching;
import de.topicmapslab.tmql4j.common.model.query.IQuery;
import de.topicmapslab.tmql4j.resultprocessing.model.IResult;

/**
 * @author Hannes Niederhausen
 *
 */
public class MustReifyScanner extends AbstractConstraintScanner {

	@Override
	protected void parseResults(IQuery q) {
		for (IResult result : q.getResults()) {
			TemplateMatching matching = new TemplateMatching();
			matching.setContext((Topic) result.getResults().get(0));
			matching.addArgument(result.getResults().get(1));
			matching.addAffectedConstruct((Construct) result.getResults().get(2));
			
			Object tmp = result.getResults().get(3);
			if (tmp instanceof Collection<?>)
				addAffectedConstructs((Collection<?>) tmp, matching);
			else
				matching.addAffectedConstruct((Construct) tmp);
			
			addMatching(matching);
		}
	}

	@Override
    protected String getQuery() {
	    return "FOR $c IN // tmcl:topic-reifies-constraint "
	    + "WHERE ( $c / tmcl:card-min == \"1\"^^xsd:int AND $c / tmcl:card-max == \"1\"^^xsd:int ) "
		+ "RETURN ( "
		+ "$c >> traverse tmcl:constrained-topic-type, "
		+ "$c >> traverse tmcl:constrained-statement || \"tmdm:subject\", " 
		+ "$c , $c << players)";
    }

	

}
