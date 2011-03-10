package de.topicmapslab.tmcl.loader.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.tmapi.core.Locator;
import org.tmapi.core.Topic;
import org.tmapi.core.TopicMap;
import org.tmapi.core.TopicMapSystem;

import de.topicmapslab.tmcl.loader.listener.IAssociationTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IIdentityConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IOccurrenceTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IRegularExpressionConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IReifiableConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.IScopedConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITopicTypeConstraintsListener;
import de.topicmapslab.tmcl.loader.listener.ITypeConstraintsListener;
import de.topicmapslab.tmql4j.components.processor.results.model.IResult;
import de.topicmapslab.tmql4j.components.processor.results.model.IResultSet;
import de.topicmapslab.tmql4j.components.processor.results.tmdm.SimpleResultSet;
import de.topicmapslab.tmql4j.components.processor.runtime.ITMQLRuntime;
import de.topicmapslab.tmql4j.components.processor.runtime.TMQLRuntimeFactory;
import de.topicmapslab.tmql4j.components.processor.runtime.module.PrefixHandler;
import de.topicmapslab.tmql4j.exception.TMQLRuntimeException;
import de.topicmapslab.tmql4j.path.components.processor.runtime.TmqlRuntime2007;
import de.topicmapslab.tmql4j.query.IQuery;

/**
 * Ultiliy class to represent all TMCL templates exception the additional ones.
 * <p>
 * The class provides one method for each tmcl-template. The method use a TMQL
 * query to extract all necessary information form the underlying topic map
 * reprsentation of the TMCL document and fire events handled by external
 * listeners.
 * </p>
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public class Constraints {

	
	/**
	 * list of all registered listeners
	 */
	private final List<ITypeConstraintsListener> listeners = new LinkedList<ITypeConstraintsListener>();
	/**
	 * the prefix clause of each TMQL query defining the TMCL prefix
	 */
	private final String prefix = "%prefix tmcl http://psi.topicmaps.org/tmcl/ ";


	private final TopicMap topicMap;
	
	private final TopicMapSystem topicMapSystem;
	
	/**
	 * base constructor to create a simple instance of this utility class
	 * 
	 * @param runtime
	 *            the TMQL runtime which shall be used to execute the TMQL
	 *            queries
	 */
	public Constraints(TopicMap topicMap, TopicMapSystem topicMapSystem) {
		this.topicMap = topicMap;
		this.topicMapSystem = topicMapSystem;
	}

	/**
	 * Add the given listener to the internal list if it was not add before.
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public void addEventListener(ITypeConstraintsListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * Removes the given listener from the internal list
	 * 
	 * @param listener
	 *            the listener to remove
	 */
	public void removeListener(ITypeConstraintsListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Method represents the overlaps()-template of a type definition.
	 * <p>
	 * the method look for all topic-types which are overlapping with the given
	 * one.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 *  # Overlap Declaration <br />
	 *   def overlaps($tt1, $tt2)<br />
	 *   ?c isa tmcl:overlap-declaration.<br />
	 *    tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt1)<br />
	 *    tmcl:overlaps(tmcl:allows : ?c, tmcl:allowed : $tt2)<br />
	 *  end
	 * </code>
	 * </p>
	 * 
	 */
	public final void overlaps() {
		final String query = prefix + "FOR $c IN  // tmcl:overlap-declaration "
				+ "RETURN  $c >> traverse tmcl:overlaps [0], $c >> traverse tmcl:overlaps [1]";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					Iterator<Object> iterator = result.iterator();
					Object result0 = iterator.next();
					Object result1 = iterator.next();
					for (ITypeConstraintsListener listener : listeners) {
						listener.overlapDeclarationElement((Topic) result0,
								(Topic) result1);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method represents the is-abstract()-template of a type definition.
	 * <p>
	 * the method check if the topic-type is declared as abstract. one.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Abstract Topic Type Constraint <br />
	 * def is-abstract($tt) <br />
	 * ?c isa tmcl:abstract-constraint. <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void isAbstract() {
		final String query = prefix + "FOR $c IN // tmcl:abstract-constraint "
				+ "RETURN $c >> traverse tmcl:constrained-topic-type";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					Topic type = (Topic) result.first();
					for (ITypeConstraintsListener listener : listeners) {
						listener.isAbstractElement(type);
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the has-subject-identifier()-template of a type
	 * definition.
	 * <p>
	 * the method look for all subject-identifier-constraints of this topic type
	 * ( regexp , card-min and card-max ).
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Subject Identifier Constraint <br />
	 * def has-subject-identifier($tt, $min, $max, $regexp) <br />
	 * ?c isa tmcl:subject-identifier-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max; <br />
	 * tmcl:regexp: $regexp. <br />
	 *  <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasSubjectIdentifier() {
		final String query = prefix
				+ "FOR $c IN // tmcl:subject-identifier-constraint "
				+ "RETURN ( $c >> traverse tmcl:constrained-topic-type, " +
						"$c / tmcl:card-min || \"0\" , " +
						"$c / tmcl:card-max || \"*\", " +
						"$c / tmcl:regexp || \".*\")";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					for (ITypeConstraintsListener listener : listeners) {
						Iterator<Object> iterator = result.iterator();
						if (listener instanceof IIdentityConstraintsListener) {
							((IIdentityConstraintsListener) listener)
									.subjectIdentifierConstraintElement(
											(Topic) iterator.next(), 
											iterator.next().toString(),
											iterator.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the has-item-identifier()-template of a type
	 * definition.
	 * <p>
	 * the method look for all item-identifier-constraints of this topic type (
	 * regexp , card-min and card-max ).
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Item Identifier Constraint <br />
	 * def has-item-identifier($tt, $min, $max, $regexp) <br />
	 * ?c isa tmcl:item-identifier-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max; <br />
	 * tmcl:regexp: $regexp. <br />
	 *  <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasItemIdentifier() {
		final String query = prefix + "FOR $c IN // tmcl:item-identifier-constraint "
				+ "RETURN ( $c >> traverse tmcl:constrained-construct, " +
						"$c / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\", $c / tmcl:regexp || \".*\")";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					for (ITypeConstraintsListener listener : listeners) {
						Iterator<Object> iterator = result.iterator();
						if (listener instanceof IIdentityConstraintsListener) {
							((IIdentityConstraintsListener) listener)
									.itemIdentififerConstraintElement(
											(Topic) iterator.next(), iterator
													.next().toString(),
											iterator.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the has-subject-locator()-template of a type
	 * definition.
	 * <p>
	 * the method look for all subject-locator-constraints of this topic type (
	 * regexp , card-min and card-max ).
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Subject Locator Constraint <br />
	 * def has-subject-locator($tt, $min, $max, $regexp) <br />
	 * ?c isa tmcl:subject-locator-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max; <br />
	 * tmcl:regexp: $regexp. <br />
	 *  <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasSubjectLocator() {
		final String query = prefix
				+ "FOR $c IN // tmcl:subject-locator-constraint "
				+ "RETURN ( $c >> traverse tmcl:constrained-topic-type, " +
						"$c / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" , $c / tmcl:regexp || \".*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					for (ITypeConstraintsListener listener : listeners) {
						Iterator<Object> iterator = result.iterator();
						if (listener instanceof IIdentityConstraintsListener) {
							((IIdentityConstraintsListener) listener)
									.subjectLocatorConstraintElement(
											(Topic) iterator.next(), iterator
													.next().toString(),
											iterator.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the has-name()-template of a type definition.
	 * <p>
	 * the method look for all topic-name-constraints of the given topic type
	 * and extract the name-type and their cardinality.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Topic Name Constraint <br />
	 * def has-name($tt, $nt, $min, $max) <br />
	 * ?c isa tmcl:topic-name-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max. <br />
	 * 	   <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $nt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasName() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/topic-name-constraint"))
			return;
		final String query = prefix
				+ "FOR $c IN // tmcl:topic-name-constraint "
				+ "RETURN  ( $c >> traverse tmcl:constrained-topic-type, "
				+ "$c >> traverse tmcl:constrained-statement, "
				+ "$c  / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() == 4) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof ITopicTypeConstraintsListener) {
							Iterator<Object> iterator = result.iterator();
							((ITopicTypeConstraintsListener) listener)
									.topicNameConstraintElement(
											(Topic) iterator.next(),
											(Topic) iterator.next(), iterator
													.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method represents the has-occurrence()-template of a type definition.
	 * <p>
	 * the method look for all topic-occurrence-constraints of the given topic
	 * type and extract the occurrence-type and their cardinality.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Topic Occurrence Constraint <br />
	 * def has-occurrence($tt, $ot, $min, $max) <br />
	 * ?c isa tmcl:topic-occurrence-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max. <br />
	 * 	   <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $ot) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasOccurrence() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/topic-occurrence-constraint"))
			return;
		final String query = prefix
				+ "FOR $c IN // tmcl:topic-occurrence-constraint  "
				+ "RETURN  (  $c >> traverse tmcl:constrained-topic-type, "
				+ "$c >> traverse tmcl:constrained-statement , $c  / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() == 4) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof ITopicTypeConstraintsListener) {
							Iterator<Object> iterator = result.iterator();
							((ITopicTypeConstraintsListener) listener)
									.topicOccurrenceConstraintElement(
											(Topic) iterator.next(),
											(Topic) iterator.next(), iterator
													.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the plays-role()-template of a type definition.
	 * <p>
	 * the method look for all topic-role-constraints of the given topic type
	 * and extract the role-type and their cardinality.
	 * 
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Topic Role Constraint <br />
	 * def plays-role($tt, $rt, $at, $min, $max) <br />
	 * ?c isa tmcl:topic-role-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max. <br />
	 * 	   <br />
	 * tmcl:constrained-topic-type(tmcl:constrains : ?c, tmcl:constrained : $tt) <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $rt) <br />
	 * tmcl:constrained-role(tmcl:constrains : ?c, tmcl:constrained : $at) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void playsRole() {
		final String query = prefix
				+ "FOR $c IN // tmcl:topic-role-constraint  "
				+ "RETURN  ( "
				+ "$c >> traverse tmcl:constrained-topic-type , "
				+ "$c >> traverse tmcl:constrained-statement , "
				+ "$c >> traverse tmcl:constrained-role, "
				+ "$c / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() == 5) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof ITopicTypeConstraintsListener) {
							Iterator<Object> iterator = result.iterator();
							((ITopicTypeConstraintsListener) listener)
									.topicRoleConstraintElement(
											(Topic) iterator.next(),
											(Topic) iterator.next(),
											(Topic) iterator.next(), 
											iterator.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the hasScope()-template of a type definition.
	 * <p>
	 * the method look for all scope-constraints of the given type and extract
	 * the scope-type and their cardinality.
	 * 
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Scope Constraint <br />
	   def has-scope($st, $tt, $min, $max)
		  ?c isa tmcl:scope-constraint;
		    tmcl:card-min: $min;
		    tmcl:card-max: $max.
		  
		  tmcl:constrained-statement(tmcl:constraint : ?c, tmcl:constrained : $st)
		  tmcl:constrained-scope(tmcl:constraint : ?c, tmcl:constrained : $tt)
		end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasScope() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/scope-constraint"))
			return;
		final String query = prefix + "FOR $c IN // tmcl:scope-constraint "
				+ "RETURN  ( $c >> traverse  tmcl:constrained-statement, "
				+ "$c >> traverse  tmcl:constrained-scope, "
				+ "$c / tmcl:card-min || 0 , "
				+ "$c / tmcl:card-max || \"*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof IScopedConstraintsListener) {
							Iterator<Object> iterator = result.iterator();
							((IScopedConstraintsListener) listener)
									.scopeConstraintElement((Topic) iterator
											.next(), (Topic) iterator.next(),
											iterator.next().toString(),
											iterator.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Finds the Topic Refies Constraint specified like: <code>
		  c isa tmcl:topic-reifies-constraint;
		    tmcl:card-min: 1;
		    tmcl:card-max: 1.
		  tmcl:constrained-topic-type(tmcl:constraint: c, tmcl:constrained: tt)
		  tmcl:constrained-statement(tmcl:constraint: c, tmcl:constrained: st)
		end
	 * </code>
	 * 
	 */
	public final void topicRefiesConstraint() {
		final String query = prefix
				+ "FOR $c IN // tmcl:topic-reifies-constraint "
				+ "RETURN ( "
				+ "$c >> traverse tmcl:constrained-topic-type, "
				+ "$c >> traverse tmcl:constrained-statement || \"tmdm:subject\", "
				+ "$c / tmcl:card-min || 0 ," + "$c / tmcl:card-max )";
		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() == 4) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof ITopicTypeConstraintsListener) {
							ITopicTypeConstraintsListener listener2 = ((ITopicTypeConstraintsListener) listener);
							Iterator<Object> resultIt = result.iterator();
							Topic reifierType = (Topic) resultIt.next();
							Object stm = resultIt.next();
							Topic stmType = (stm instanceof String) ? null
									: (Topic) stm;
							
							listener2.topicReifiesConstraint(
									reifierType, 
									stmType, 
									resultIt.next().toString(), 
									resultIt.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Finds the Refier Constraint specified like: <code>
		  c isa tmcl:reifier-constraint;
		    tmcl:card-min: 1;
		    tmcl:card-max: 1.
		  tmcl:constrained-statement(tmcl:constraint: c, tmcl:constrained: st)
		  tmcl:allowed-reifier(tmcl:allows: c, tmcl:allowed: tt)
		end
	 * </code>
	 */
	public final void reifierConstraint() {
		final String query = prefix + "FOR $c IN // tmcl:reifier-constraint "
				+ "RETURN ( "
				+ "$c >> traverse tmcl:constrained-statement, "
				+ "$c >> traverse tmcl:allowed-reifier, "
				+ "$c / tmcl:card-min || 0 ," 
				+ "$c / tmcl:card-max )";
		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() == 4) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof IReifiableConstraintsListener) {
							IReifiableConstraintsListener listener2 = ((IReifiableConstraintsListener) listener);
							Iterator<Object> resIt = result.iterator();
							listener2.reifierConstraintElement(
									(Topic) resIt.next(), 
									(Topic) resIt.next(), 
									resIt.next().toString(), 
									resIt.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method represents the has-role()-template of a type definition.
	 * <p>
	 * the method look for all association-role-constraints of the given
	 * association type and extract the role-type and their cardinality.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Association Role Constraint <br />
	 * def has-role($at, $rt, $min, $max) <br />
	 * ?c isa tmcl:association-role-constraint; <br />
	 * tmcl:card-min: $min; <br />
	 * tmcl:card-max: $max. <br />
	 * 	   <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $at) <br />
	 * tmcl:constrained-role(tmcl:constrains : ?c, tmcl:constrained : $rt) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 * @param type
	 *            the topic-type
	 */
	public final void hasRole() {
		final String query = prefix
				+ "FOR $c IN // tmcl:association-role-constraint  "
				+ "RETURN  ( "
				+ " $c >> traverse tmcl:constrained-statement, "
				+ " $c >> traverse tmcl:constrained-role, "
				+ " $c / tmcl:card-min || \"0\" , $c / tmcl:card-max || \"*\" )";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					Iterator<Object> iterator = result.iterator();
					Topic assocType = (Topic) iterator.next();
					Topic roleType = (Topic) iterator.next();
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof IAssociationTypeConstraintsListener) {
							IAssociationTypeConstraintsListener listener2 = ((IAssociationTypeConstraintsListener) listener);
							listener2.associationRoleConstraintElement(
									assocType, 
									roleType, 
									iterator.next().toString(), 
									iterator.next().toString());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the role-combination()-template of a type definition.
	 * <p>
	 * the method look for all role-combination-constraints of the given
	 * association type and extract the role-type, the role-player, the other
	 * role-type and the other role-player.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Role Combination Constraint <br />
	 * def role-combination($at, $rt, $tt, $ort, $ott) <br />
	 * ?c isa tmcl:role-combination-constraint. <br />
	 *  <br />
	 * tmcl:constrained-statement(tmcl:constrains: ?c, tmcl:constrained: $at) <br />
	 * tmcl:constrained-role(tmcl:constrains: ?c, tmcl:constrained: $rt) <br />
	 * tmcl:constrained-topic-type(tmcl:constrains: ?c, tmcl:constrained: $tt) <br />
	 * tmcl:other-constrained-role(tmcl:constrains: ?c, tmcl:constrained: $ort) <br />
	 * tmcl:other-constrained-topic-type(tmcl:constrains: ?c, tmcl:constrained:
	 * $ott) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void roleCombination() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/role-combination-constraint"))
			return;
		final String query = prefix
				+ "FOR $c IN // tmcl:role-combination-constraint " + "RETURN ("
				+ " $c >> traverse tmcl:constrained-statement ,"
				+ " $c >> traverse tmcl:constrained-role ,"
				+ " $c >> traverse tmcl:constrained-topic-type ,"
				+ " $c >> traverse tmcl:other-constrained-role ,"
				+ " $c >> traverse tmcl:other-constrained-topic-type " + ")";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					Iterator<Object> iterator = result.iterator();
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof IAssociationTypeConstraintsListener) {
							IAssociationTypeConstraintsListener listener2 = ((IAssociationTypeConstraintsListener) listener);
							listener2.roleCombinationConstraintElement(
									(Topic) iterator.next(),
									(Topic) iterator.next(),
									(Topic) iterator.next(),
									(Topic) iterator.next(),
									(Topic) iterator.next());
						}
					}
				}
			}
		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the has-datatype()-template of a type definition.
	 * <p>
	 * the method look for all occurrence-datatype-constraints of the given
	 * occurrence type and extract the datatype.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Occurrence Data Type Constraint
	 * def has-datatype($ot, $dt) <br />
	 * ?c isa tmcl:occurrence-datatype-constraint; <br />
	 * tmcl:datatype: $dt. <br />
	 *  <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $ot) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void hasDatatype() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/occurrence-datatype-constraint"))
			return;
		final String query = prefix
				+ "FOR $c IN // tmcl:occurrence-datatype-constraint "
				+ "RETURN ($c >> traverse tmcl:constrained-statement, $c / tmcl:datatype)";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (ITypeConstraintsListener listener : listeners) {
					if (listener instanceof IOccurrenceTypeConstraintsListener) {
						Iterator<Object> it = result.iterator();
						((IOccurrenceTypeConstraintsListener) listener)
								.occurrenceDatatypeConstraintElement(
										(Topic) it.next(), it.next().toString());
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the isUnique()-template of a type definition.
	 * <p>
	 * the method look if the values of given occurrence type shall be unique..
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Unique Value Constraint <br />
	 * def is-unique($st) <br />
	 * ?c isa tmcl:unique-value-constraint. <br />
	 *  <br />
	 * tmcl:constrained-statement(tmcl:constrains : ?c, tmcl:constrained : $st) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void isUnique() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/unique-value-constraint"))
			return;
		final String query = prefix + " FOR $c IN // tmcl:unique-value-constraint "
				+ "RETURN $c >> traverse tmcl:constrained-statement";

		try {
			SimpleResultSet set = execute(query);
			for (IResult result : set) {
				if (result.size() > 0) {
					for (ITypeConstraintsListener listener : listeners) {
						if (listener instanceof IOccurrenceTypeConstraintsListener) {
							((IOccurrenceTypeConstraintsListener) listener)
									.uniqueValueConstraintElement((Topic) result
											.iterator().next());
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method represents the matchesRegexp()-template of a type definition.
	 * <p>
	 * the method look if there is a regular expression defined for values of
	 * the given name or occurrence type.
	 * </p>
	 * 
	 * <p>
	 * <code>
	 * # Regular Expression Constraint <br />
	 * def matches-regexp($st, $regexp) <br />
	 * ?c isa tmcl:regular-expression-constraint; <br />
	 * tmcl:regexp: $regexp. <br />
	 * 	   <br />
	 * tmcl:constrained-statement(tmcl:constrains: ?c, tmcl:constrained: $st) <br />
	 * end
	 * </code>
	 * </p>
	 * 
	 */
	public final void matchesRegexp() {
		if (!topicExists("http://psi.topicmaps.org/tmcl/regular-expression-constraint"))
			return;
		final String query = prefix
				+ "FOR $c IN //tmcl:regular-expression-constraint "
				+ "RETURN ($c >> traverse tmcl:constrained-statement, $c / tmcl:regexp || \".*\")";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				for (ITypeConstraintsListener listener : listeners) {
					if (listener instanceof IRegularExpressionConstraintsListener) {
						Iterator<Object> it = result.iterator();
						((IRegularExpressionConstraintsListener) listener)
								.regularExpressionConstraintElement(
										(Topic) it.next(), it.next().toString());
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	// /**
	// * Method use a TMQL query to extract all names of the given topic type.
	// *
	// * @param type
	// * the topic type
	// */
	// public final void typeName() {
	// final String query = prefix + " " + getIdentifier(type)
	// + "  / tm:name ";
	//
	// try {
	// SimpleResultSet set = execute(query);
	//
	// for (IResult result : set) {
	// for (Object obj : result) {
	// for (ITypeConstraintsListener listener : listeners) {
	// listener.typeName(type, obj.toString());
	// }
	// }
	// }
	//
	// } catch (TMQLRuntimeException e) {
	// throw new RuntimeException(e);
	// // e.printStackTrace();
	// }
	// }
	//
	/**
	 * Method use a TMQL query to extract all types of the given topic type.
	 * 
	 * @param type
	 *            the topic type
	 */
	@SuppressWarnings("unchecked")
	public final void instanceOf() {
		final String query = "%pragma taxonometry tm:intransitive " 
				+ prefix
				+ " // tm:subject ( . , . >> types )";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				if (result.size() > 1) {
					Iterator<Object> it = result.iterator();
					Topic instance = (Topic) it.next();
					Object type =  it.next();
					if (type==null) {
						continue;
					} else  if (type instanceof Topic) {
						for (ITypeConstraintsListener listener : listeners) {
							listener.isInstanceOf(instance, (Topic) type);
						}
					} else {
						for (Topic t : (Collection<Topic>) type) {
							for (ITypeConstraintsListener listener : listeners) {
								listener.isInstanceOf(instance, t);
							}	
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Method use a TMQL query to extract all supertypes of the given topic
	 * type.
	 * 
	 * @param type
	 *            the topic type
	 */
	@SuppressWarnings("unchecked")
	public final void kindOf() {
		final String query = "%pragma taxonometry tm:intransitive "
				+ prefix
				+ " // tm:subject ( . , . >> supertypes )";

		try {
			SimpleResultSet set = execute(query);

			for (IResult result : set) {
				if (result.size() > 1) {
					Iterator<Object> it = result.iterator();
					Topic instance = (Topic) it.next();
					Object type =  it.next();
					if (type == null) {
						continue;
					} else if (type instanceof Topic) {
						for (ITypeConstraintsListener listener : listeners) {
							listener.aKindOf(instance, (Topic) type);
						}
					} else {
						for (Topic t : (Collection<Topic>) type) {
							for (ITypeConstraintsListener listener : listeners) {
								listener.aKindOf(instance, t);
							}	
						}
					}
				}
			}

		} catch (TMQLRuntimeException e) {
			throw new RuntimeException(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Internal method to execute a TMQL query.
	 * 
	 * @param <T>
	 *            the result set type
	 * @param query
	 *            the query to execute
	 * @return the result set of the given query
	 * @throws TMQLRuntimeException
	 *             thrown if execution fails
	 */
	@SuppressWarnings("unchecked")
	private final <T extends IResultSet<?>> T execute(final String query)
			throws TMQLRuntimeException {
		IQuery q = getRuntime().run(topicMap, query);
		return (T) q.getResults();
	}

	/**
	 * Checks if a topic with the given subject identifier exists in the topic
	 * map
	 * 
	 * @param subjectIdentifier
	 * @return
	 */
	private boolean topicExists(String subjectIdentifier) {
		Locator l = topicMap.createLocator(subjectIdentifier);
		return topicMap.getTopicBySubjectIdentifier(l) != null;
	}

	/**
	 * Get access to the created TMQL4J runtime
	 * 
	 * @return the reference
	 * @throws TMQLRuntimeException 
	 */
	protected ITMQLRuntime getRuntime() throws TMQLRuntimeException {
		ITMQLRuntime runtime = TMQLRuntimeFactory.newFactory().newRuntime(topicMapSystem, TmqlRuntime2007.TMQL_2007);
		PrefixHandler prefixHandler = runtime.getLanguageContext().getPrefixHandler();
		prefixHandler.registerPrefix("tmcl", "http://psi.topicmaps.org/tmcl/");
		return runtime;
	}
	
	/**
	 * 
	 * @return The list of registered listeners
	 */
	public List<ITypeConstraintsListener> getListeners() {
		return listeners;
	}
}
