package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints of
 * topic-types. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface ITopicTypeConstraintsListener extends
		IIdentityConstraintsListener {

	/**
	 * Event is fired if a topic-occurrence-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-occurrence(ot, min, max) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param occurrenceType
	 *            the found occurrence type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void topicOccurrenceConstraintElement(Topic type,
			Topic occurrenceType, final String cardMin, final String cardMax);

	/**
	 * Event is fired if a topic-name-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-name(nt, min, max) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param nameType
	 *            the found name type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void topicNameConstraintElement(Topic type, Topic nameType,
			final String cardMin, final String cardMax);

	/**
	 * Event is fired if a topic-role-constraint is found.<br />
	 * <p>
	 * CTM: <code> plays-role(rt, at, min, max) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param associationType
	 *            the association type
	 * @param roleType
	 *            the found role type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void topicRoleConstraintElement(final Topic type,
			final Topic associationType, final Topic roleType,
			final String cardMin, final String cardMax);

	/**
	 * Event is fired if a topic-reifies-constraint is found.<br />
	 * <p>
	 * CTM: <code> may-reify(rt) | must-reify(rt) | cannot-reify() </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param reifiedType
	 *            the type of the reified element
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void topicReifiesConstraint(final Topic type,
			final Topic reifiedType, final String cardMin, final String cardMax);


}
