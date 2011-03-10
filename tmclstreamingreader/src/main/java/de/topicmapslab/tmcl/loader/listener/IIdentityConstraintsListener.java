package de.topicmapslab.tmcl.loader.listener;

import org.tmapi.core.Topic;

import de.topicmapslab.tmcl.loader.reader.ConstraintReader;

/**
 * Interface definition of a Simple API for TMCL (SAT) listener.
 * <p>
 * The listener is called if a {@link ConstraintReader} found a indicator or
 * locator constraint. The following constraints are supported:<br />
 * - : <code></code><br />
 * 
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public interface IIdentityConstraintsListener extends ITypeConstraintsListener {

	/**
	 * Event is fired if a subject-identifier-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-subject-identifier(min, max, regexp) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 * @param regExp
	 *            the regular-expression
	 */
	public void subjectIdentifierConstraintElement(final Topic type,
			final String cardMin, final String cardMax, final String regExp);
	
	/**
	 * Event is fired if a item-identifier-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-item-identifier(min, max, regexp) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 * @param regExp
	 *            the regular-expression
	 */
	public void itemIdentififerConstraintElement(final Topic type,
			final String cardMin, final String cardMax, final String regExp);

	/**
	 * Event is fired if a subject-locator-constraint is found.<br />
	 * <p>
	 * CTM: <code> has-subject-locator(min, max, regexp) </code><br />
	 * </p>
	 * 
	 * @param type
	 *            the topic type
	 * @param cardMin
	 *            the minimal cardinality
	 * @param cardMax
	 *            the maximal cardinality
	 * @param regExp
	 *            the regular-expression
	 */
	public void subjectLocatorConstraintElement(final Topic type,
			final String cardMin, final String cardMax, final String regExp);
}
