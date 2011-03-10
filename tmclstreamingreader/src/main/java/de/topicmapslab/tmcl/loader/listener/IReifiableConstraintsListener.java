package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints of
 * reifiable-types. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IReifiableConstraintsListener extends ITypeConstraintsListener {

	/**
	 * Event is fired if a reifier-constraint is found.<br />
	 * <p>
	 * CTM:
	 * <code> must-have-reifier(rt) | may-have-reifier(rt) | cannot-have-reifier() </code>
	 * <br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param reifierType
	 *            the type of the reifier
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void reifierConstraintElement(Topic type, Topic reifierType,
			final String cardMin, final String cardMax);


}
