package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints of scoped-types. 
 * The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IScopedConstraintsListener {

	/**
	 * Event is fired if a scope-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-scope(tt, min, max) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param scopeType
	 *            the type of the scope            
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void scopeConstraintElement(Topic type, Topic scopeType,
			final String cardMin, final String cardMax);

}
