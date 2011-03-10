package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraint of
 * association-types. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IAssociationTypeConstraintsListener extends
		IIdentityConstraintsListener, IScopedConstraintsListener,
		IReifiableConstraintsListener {

	/**
	 * Event is fired if a association-role-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-role(rt, min, max) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param roleType
	 *            the role type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 */
	public void associationRoleConstraintElement(final Topic type,
			final Topic roleType, final String cardMin, final String cardMax);

	/**
	 * Event is fired if a role-combnation-constraint is found.<br />
	 * <p>
	 * CTM: <code> role-combination(rt,pt,ort,opt) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param roleType
	 *            the role type
	 * @param playerType
	 *            the type of the player
	 * @param otherRoleType
	 *            the other role type
	 * @param otherPlayerType
	 *            the type of the other player
	 */
	public void roleCombinationConstraintElement(final Topic type,
			final Topic roleType, final Topic playerType,
			final Topic otherRoleType, final Topic otherPlayerType);

}
