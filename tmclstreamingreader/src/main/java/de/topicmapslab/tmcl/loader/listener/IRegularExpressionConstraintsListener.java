package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a
 * regular-expression constraint. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IRegularExpressionConstraintsListener extends
		ITypeConstraintsListener {

	/**
	 * Event is fired if a regular-expression-constraint is found.<br />
	 * <p>
	 * CTM: <code> matches-regexp(regexp) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param regexp
	 *            the regular-expression
	 */
	public void regularExpressionConstraintElement(Topic type,
			final String regexp);
}
