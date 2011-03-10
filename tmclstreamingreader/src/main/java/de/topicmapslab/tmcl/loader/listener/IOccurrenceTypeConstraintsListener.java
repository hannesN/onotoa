package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints of
 * occurrence-types. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IOccurrenceTypeConstraintsListener extends
		IScopedConstraintsListener, IReifiableConstraintsListener,
		IRegularExpressionConstraintsListener, IIdentityConstraintsListener {

	/**
	 * Event is fired if a occurrence-datatype-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-datatype(dt) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param datatype
	 *            the datatype
	 */
	public void occurrenceDatatypeConstraintElement(Topic type,
			final String datatype);

	/**
	 * Event is fired if a unique-value-constraint is found.<br />
	 * <p>
	 * CTM: <code> is-unique() </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 */
	public void uniqueValueConstraintElement(Topic type);

}
