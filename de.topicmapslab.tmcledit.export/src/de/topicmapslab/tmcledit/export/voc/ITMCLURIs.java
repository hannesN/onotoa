package de.topicmapslab.tmcledit.export.voc;

/**
 * Interface containing the subject identifier of TMCL
 * 
 * @author Hannes Niederhausen
 * 
 */
public interface ITMCLURIs {
	/**
	 * General prefix for the types.
	 */
	public final static String PREFIX = "http://psi.topicmaps.org/tmcl/";

	// types
	public static String TOPIC_TYPE = PREFIX + "topic-type";

	public static String NAME_TYPE = PREFIX + "name-type";

	public static String OCCURRENCE_TYPE = PREFIX + "occurrence-type";

	public static String ROLE_TYPE = PREFIX + "role-type";

	public static String TOPIC_MAP = PREFIX + "topic-map";

	// constraints

	public static String CONSTRAINT = PREFIX + "constraint";

	public static String ABSTRACT_CONSTRAINT = PREFIX + "abstract-constraint";

	public static String DENIAL_CONSTRAINT = PREFIX + "denial-constraint";

	public static String ASSOCIATION_ROLE_CONSTRAINT = PREFIX + "association-role-constraint";

	public static String OCCURRENCE_DATATYPE_CONSTRAINT = PREFIX + "occurrence-datatype-constraint";

	public static String ITEM_IDENTIFIER_CONSTRAINT = PREFIX + "item_identifier-constraint";

	public static String REGULAR_EXPRESSION_CONSTRAINT = PREFIX + "regular-expression-constraint";

	public static String SCOPE_REQUIRED_CONSTRAINT = PREFIX + "scope-required-constraint";

	public static String SUBJECT_IDENTIFIER_CONSTRAINT = PREFIX + "subject-identifier-constraint";

	public static String SUBJECT_LOCATOR_CONSTRAINT = PREFIX + "subject-locator-constraint";

	public static String TOPIC_NAME_CONSTRAINT = PREFIX + "topic-name-constraint";

	public static String TOPIC_OCCURRENCE_CONSTRAINT = PREFIX + "topic-occurrence-constraint";

	public static String TOPIC_REIFIES_CONSTRAINT = PREFIX + "topic-reifies-constraint";

	public static String TOPIC_ROLE_CONSTRAINT = PREFIX + "topic-role-constraint";

	public static String ROLE_COMBINATION_CONSTRAINT = PREFIX + "role-combination-constraint";

	public static String OVERLAP_DECLARATION = PREFIX + "overlap-declaration";

	public static String ALLOWED = PREFIX + "allowed";
	public static String ALLOWED_REIFIER = PREFIX + "allowed-reifier";
	public static String ALLOWS = PREFIX + "allows";
	public static String ASSOCIATION_TYPE = PREFIX + "association-type";
	public static String BELONGS_TO = PREFIX + "belongs-to";
	public static String CARD_MAX = PREFIX + "card-max";
	public static String CARD_MIN = PREFIX + "card-min";
	public static String COMMENT = PREFIX + "comment";
	public static String CONSTRAINED = PREFIX + "constrained";
	public static String CONSTRAINED_CONSTRUCT = PREFIX + "constrained-construct";
	public static String CONSTRAINED_ROLE = PREFIX + "constrained-role";
	public static String CONSTRAINED_SCOPE = PREFIX + "constrained-scope";
	public static String CONSTRAINED_SCOPE_TOPIC = PREFIX + "constrained-scope-topic";
	public static String CONSTRAINED_STATEMENT = PREFIX + "constrained-statement";
	public static String CONSTRAINED_TOPIC_TYPE = PREFIX + "constrained-topic-type";
	public static String CONTAINEE = PREFIX + "containee";
	public static String CONTAINER = PREFIX + "container";
	public static String DATATYPE = PREFIX + "datatype";
	public static String DESCRIPTION = PREFIX + "description";
	public static String INCLUDE_SCHEMA = PREFIX + "includes-schema";
	public static String OTHER_CONSTRAINED_ROLE = PREFIX + "other-constrained-role";
	public static String OTHER_CONSTRAINED_TOPIC_TYPE = PREFIX + "other-constrained-topic-type";
	public static String OVERLAPS = PREFIX + "overlaps";
	public static String REGEXP = PREFIX + "regexp";
	public static String REIFIER_CONSTRAINT = PREFIX + "reifier-constraint";
	public static String REQUIREMENT_CONSTRAINT = PREFIX + "requirement-constraint";
	public static String SCHEMA = PREFIX + "schema";
	public static String SCHEMA_RESOURCE = PREFIX + "schema-resource";
	public static String SCOPE_CONSTRAINT = PREFIX + "scope-constraint";
	public static String SEE_ALSO = PREFIX + "see-also";
	public static String UNIQUE_VALUE_CONSTRAINT = PREFIX + "unique-value-constraint";
	public static String USED = PREFIX + "used";
	public static String USER = PREFIX + "user";
	public static String USER_DEFINED_CONSTRAINT = PREFIX + "user-defined-constraint";
	public static String USES_SCHEMA = PREFIX + "uses-schema";
	public static String VALIDATION_EXPRESSION = PREFIX + "validation-expression";
	public static String VARIANT_NAME_CONSTRAINT = PREFIX + "variant-name-constraint";
	public static String VERSION = PREFIX + "version";
}
