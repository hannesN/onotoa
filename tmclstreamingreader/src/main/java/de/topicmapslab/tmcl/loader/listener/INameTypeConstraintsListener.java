package de.topicmapslab.tmcl.loader.listener;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a constraints of name-types. 
 * The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface INameTypeConstraintsListener extends
		IScopedConstraintsListener, IReifiableConstraintsListener,
		IRegularExpressionConstraintsListener,
		IIdentityConstraintsListener {

}
