/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://topicmapslab.de/tmceledit/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tmceledit";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = de.topicmapslab.tmcledit.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl <em>TMCL Construct</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTMCLConstruct()
	 * @generated
	 */
	int TMCL_CONSTRUCT = 30;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMCL_CONSTRUCT__SEE_ALSO = 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMCL_CONSTRUCT__COMMENT = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMCL_CONSTRUCT__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMCL_CONSTRUCT__ANNOTATIONS = 3;

	/**
	 * The number of structural features of the '<em>TMCL Construct</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TMCL_CONSTRUCT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl <em>Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicType()
	 * @generated
	 */
	int TOPIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__SEE_ALSO = TMCL_CONSTRUCT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__COMMENT = TMCL_CONSTRUCT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__DESCRIPTION = TMCL_CONSTRUCT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ANNOTATIONS = TMCL_CONSTRUCT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__IDENTIFIERS = TMCL_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ID_TYPE = TMCL_CONSTRUCT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ABSTRACT = TMCL_CONSTRUCT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ISA = TMCL_CONSTRUCT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__AKO = TMCL_CONSTRUCT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = TMCL_CONSTRUCT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__NAME_CONTRAINTS = TMCL_CONSTRUCT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TMCL_CONSTRUCT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TMCL_CONSTRUCT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__KIND = TMCL_CONSTRUCT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__OVERLAP = TMCL_CONSTRUCT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__NAME = TMCL_CONSTRUCT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__LOCATORS = TMCL_CONSTRUCT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = TMCL_CONSTRUCT_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE_FEATURE_COUNT = TMCL_CONSTRUCT_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.AbstractConstraint <em>Abstract Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.AbstractConstraint
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractConstraint()
	 * @generated
	 */
	int ABSTRACT_CONSTRAINT = 36;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRAINT__SEE_ALSO = TMCL_CONSTRUCT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRAINT__COMMENT = TMCL_CONSTRUCT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRAINT__DESCRIPTION = TMCL_CONSTRUCT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRAINT__ANNOTATIONS = TMCL_CONSTRUCT__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>Abstract Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRAINT_FEATURE_COUNT = TMCL_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpConstraintImpl <em>Abstract Reg Exp Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractRegExpConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractRegExpConstraint()
	 * @generated
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT__SEE_ALSO = ABSTRACT_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT__COMMENT = ABSTRACT_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT__DESCRIPTION = ABSTRACT_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT__ANNOTATIONS = ABSTRACT_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT__REGEXP = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Reg Exp Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractCardinalityContraintImpl <em>Abstract Cardinality Contraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractCardinalityContraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractCardinalityContraint()
	 * @generated
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT = 18;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__SEE_ALSO = ABSTRACT_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__COMMENT = ABSTRACT_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__DESCRIPTION = ABSTRACT_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__ANNOTATIONS = ABSTRACT_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Cardinality Contraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractTypedCardinalityConstraintImpl <em>Abstract Typed Cardinality Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractTypedCardinalityConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractTypedCardinalityConstraint()
	 * @generated
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT = 28;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO = ABSTRACT_CARDINALITY_CONTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT = ABSTRACT_CARDINALITY_CONTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION = ABSTRACT_CARDINALITY_CONTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS = ABSTRACT_CARDINALITY_CONTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN = ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX = ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE = ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Typed Cardinality Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeConstraintImpl <em>Occurrence Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.OccurrenceTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurrenceTypeConstraint()
	 * @generated
	 */
	int OCCURRENCE_TYPE_CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT__UNIQUE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Occurrence Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl <em>Name Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameTypeConstraint()
	 * @generated
	 */
	int NAME_TYPE_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The number of structural features of the '<em>Name Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl <em>Role Player Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRolePlayerConstraint()
	 * @generated
	 */
	int ROLE_PLAYER_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__SEE_ALSO = ABSTRACT_CARDINALITY_CONTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__COMMENT = ABSTRACT_CARDINALITY_CONTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__DESCRIPTION = ABSTRACT_CARDINALITY_CONTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__ANNOTATIONS = ABSTRACT_CARDINALITY_CONTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__CARD_MIN = ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__CARD_MAX = ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__PLAYER = ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT__ROLE = ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Role Player Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_PLAYER_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CARDINALITY_CONTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl <em>Topic Map Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicMapSchema()
	 * @generated
	 */
	int TOPIC_MAP_SCHEMA = 5;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__SEE_ALSO = TMCL_CONSTRUCT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__COMMENT = TMCL_CONSTRUCT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__DESCRIPTION = TMCL_CONSTRUCT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__ANNOTATIONS = TMCL_CONSTRUCT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Topic Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__TOPIC_TYPES = TMCL_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Association Type Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS = TMCL_CONSTRUCT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__MAPPINGS = TMCL_CONSTRUCT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__INCLUDES = TMCL_CONSTRUCT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Base Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__BASE_LOCATOR = TMCL_CONSTRUCT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__NAME = TMCL_CONSTRUCT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Topic Map Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA_FEATURE_COUNT = TMCL_CONSTRUCT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.SubjectLocatorConstraintImpl <em>Subject Locator Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.SubjectLocatorConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getSubjectLocatorConstraint()
	 * @generated
	 */
	int SUBJECT_LOCATOR_CONSTRAINT = 6;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__SEE_ALSO = ABSTRACT_REG_EXP_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__COMMENT = ABSTRACT_REG_EXP_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__DESCRIPTION = ABSTRACT_REG_EXP_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__ANNOTATIONS = ABSTRACT_REG_EXP_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__REGEXP = ABSTRACT_REG_EXP_CONSTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__CARD_MIN = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__CARD_MAX = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Subject Locator Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT_FEATURE_COUNT = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl <em>Subject Identifier Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getSubjectIdentifierConstraint()
	 * @generated
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT = 7;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__SEE_ALSO = ABSTRACT_REG_EXP_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__COMMENT = ABSTRACT_REG_EXP_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__DESCRIPTION = ABSTRACT_REG_EXP_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__ANNOTATIONS = ABSTRACT_REG_EXP_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__REGEXP = ABSTRACT_REG_EXP_CONSTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Subject Identifier Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT_FEATURE_COUNT = ABSTRACT_REG_EXP_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractTypedConstraintImpl <em>Abstract Typed Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractTypedConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractTypedConstraint()
	 * @generated
	 */
	int ABSTRACT_TYPED_CONSTRAINT = 20;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT__SEE_ALSO = ABSTRACT_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT__COMMENT = ABSTRACT_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT__DESCRIPTION = ABSTRACT_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT__ANNOTATIONS = ABSTRACT_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT__TYPE = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Typed Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPED_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl <em>Association Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationTypeConstraint()
	 * @generated
	 */
	int ASSOCIATION_TYPE_CONSTRAINT = 8;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__TYPE = ABSTRACT_TYPED_CONSTRAINT__TYPE;

	/**
	 * The feature id for the '<em><b>Player Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS = ABSTRACT_TYPED_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Association Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.MappingElementImpl <em>Mapping Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.MappingElementImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getMappingElement()
	 * @generated
	 */
	int MAPPING_ELEMENT = 9;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_ELEMENT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_ELEMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Mapping Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.NodeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 10;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__POS_X = 0;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__POS_Y = 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TypeNodeImpl <em>Type Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TypeNodeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTypeNode()
	 * @generated
	 */
	int TYPE_NODE = 11;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__POS_X = NODE__POS_X;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__POS_Y = NODE__POS_Y;

	/**
	 * The feature id for the '<em><b>Topic Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__TOPIC_TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.BendpointImpl <em>Bendpoint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.BendpointImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getBendpoint()
	 * @generated
	 */
	int BENDPOINT = 12;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT__POS_X = 0;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT__POS_Y = 1;

	/**
	 * The number of structural features of the '<em>Bendpoint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENDPOINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl <em>Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.EdgeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getEdge()
	 * @generated
	 */
	int EDGE = 13;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__BENDPOINTS = 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TYPE = 3;

	/**
	 * The feature id for the '<em><b>Role Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__ROLE_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Label Positions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__LABEL_POSITIONS = 5;

	/**
	 * The number of structural features of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl <em>Association Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationNode()
	 * @generated
	 */
	int ASSOCIATION_NODE = 14;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__POS_X = NODE__POS_X;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__POS_Y = NODE__POS_Y;

	/**
	 * The feature id for the '<em><b>Association Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Association Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.DiagramImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 15;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__EDGES = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NODES = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NAME = 2;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__COMMENTS = 3;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.FileImpl <em>File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.FileImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getFile()
	 * @generated
	 */
	int FILE = 16;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__DIAGRAMS = 0;

	/**
	 * The feature id for the '<em><b>Topic Map Schema</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__TOPIC_MAP_SCHEMA = 1;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__FILENAME = 2;

	/**
	 * The feature id for the '<em><b>Dirty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__DIRTY = 3;

	/**
	 * The number of structural features of the '<em>File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ScopeConstraintImpl <em>Scope Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ScopeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopeConstraint()
	 * @generated
	 */
	int SCOPE_CONSTRAINT = 17;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The number of structural features of the '<em>Scope Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.LabelPosImpl <em>Label Pos</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.LabelPosImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getLabelPos()
	 * @generated
	 */
	int LABEL_POS = 19;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_POS__POS_X = 0;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_POS__POS_Y = 1;

	/**
	 * The number of structural features of the '<em>Label Pos</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_POS_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ScopedTopicTypeImpl <em>Scoped Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ScopedTopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopedTopicType()
	 * @generated
	 */
	int SCOPED_TOPIC_TYPE = 21;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__SEE_ALSO = TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__COMMENT = TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__DESCRIPTION = TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__ANNOTATIONS = TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__IDENTIFIERS = TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__ABSTRACT = TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__KIND = TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__OVERLAP = TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__NAME = TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__LOCATORS = TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE__SCOPE = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Scoped Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_TOPIC_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl <em>Association Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationType()
	 * @generated
	 */
	int ASSOCIATION_TYPE = 22;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__SEE_ALSO = SCOPED_TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__COMMENT = SCOPED_TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__DESCRIPTION = SCOPED_TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ANNOTATIONS = SCOPED_TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__IDENTIFIERS = SCOPED_TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ID_TYPE = SCOPED_TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ABSTRACT = SCOPED_TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ISA = SCOPED_TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__AKO = SCOPED_TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__OCCURRENCE_CONSTRAINTS = SCOPED_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__NAME_CONTRAINTS = SCOPED_TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = SCOPED_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__SUBJECT_LOCATOR_CONSTRAINT = SCOPED_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__KIND = SCOPED_TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__OVERLAP = SCOPED_TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__NAME = SCOPED_TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__LOCATORS = SCOPED_TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__TOPIC_REIFIES_CONSTRAINT = SCOPED_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__SCOPE = SCOPED_TOPIC_TYPE__SCOPE;

	/**
	 * The feature id for the '<em><b>Reifier Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__REIFIER_CONSTRAINT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ROLES = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Role Combinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE__ROLE_COMBINATIONS = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Association Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_FEATURE_COUNT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl <em>Occurrence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurrenceType()
	 * @generated
	 */
	int OCCURRENCE_TYPE = 23;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__SEE_ALSO = SCOPED_TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__COMMENT = SCOPED_TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__DESCRIPTION = SCOPED_TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__ANNOTATIONS = SCOPED_TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__IDENTIFIERS = SCOPED_TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__ID_TYPE = SCOPED_TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__ABSTRACT = SCOPED_TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__ISA = SCOPED_TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__AKO = SCOPED_TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__OCCURRENCE_CONSTRAINTS = SCOPED_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__NAME_CONTRAINTS = SCOPED_TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = SCOPED_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__SUBJECT_LOCATOR_CONSTRAINT = SCOPED_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__KIND = SCOPED_TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__OVERLAP = SCOPED_TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__NAME = SCOPED_TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__LOCATORS = SCOPED_TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__TOPIC_REIFIES_CONSTRAINT = SCOPED_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__SCOPE = SCOPED_TOPIC_TYPE__SCOPE;

	/**
	 * The feature id for the '<em><b>Reifier Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__REIFIER_CONSTRAINT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reg Exp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__REG_EXP = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__UNIQUE = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE__DATA_TYPE = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Occurrence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURRENCE_TYPE_FEATURE_COUNT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RoleConstraintImpl <em>Role Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RoleConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleConstraint()
	 * @generated
	 */
	int ROLE_CONSTRAINT = 24;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The number of structural features of the '<em>Role Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl <em>Role Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RoleTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleType()
	 * @generated
	 */
	int ROLE_TYPE = 25;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__SEE_ALSO = TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__COMMENT = TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__DESCRIPTION = TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ANNOTATIONS = TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__IDENTIFIERS = TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ABSTRACT = TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__OCCURRENCE_CONSTRAINTS = TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__KIND = TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__OVERLAP = TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__NAME = TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__LOCATORS = TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__TOPIC_REIFIES_CONSTRAINT = TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Role Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl <em>Role Combination Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleCombinationConstraint()
	 * @generated
	 */
	int ROLE_COMBINATION_CONSTRAINT = 26;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__SEE_ALSO = ABSTRACT_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__COMMENT = ABSTRACT_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__DESCRIPTION = ABSTRACT_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__ANNOTATIONS = ABSTRACT_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__PLAYER = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Other Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Other Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT__ROLE = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Role Combination Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_COMBINATION_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeImpl <em>Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.NameTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameType()
	 * @generated
	 */
	int NAME_TYPE = 27;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SEE_ALSO = SCOPED_TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__COMMENT = SCOPED_TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__DESCRIPTION = SCOPED_TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ANNOTATIONS = SCOPED_TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__IDENTIFIERS = SCOPED_TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ID_TYPE = SCOPED_TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ABSTRACT = SCOPED_TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ISA = SCOPED_TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__AKO = SCOPED_TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__OCCURRENCE_CONSTRAINTS = SCOPED_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__NAME_CONTRAINTS = SCOPED_TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = SCOPED_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SUBJECT_LOCATOR_CONSTRAINT = SCOPED_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__KIND = SCOPED_TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__OVERLAP = SCOPED_TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__NAME = SCOPED_TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__LOCATORS = SCOPED_TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__TOPIC_REIFIES_CONSTRAINT = SCOPED_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SCOPE = SCOPED_TOPIC_TYPE__SCOPE;

	/**
	 * The feature id for the '<em><b>Reifier Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__REIFIER_CONSTRAINT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reg Exp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__REG_EXP = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_FEATURE_COUNT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.CommentImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 29;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__POS_X = NODE__POS_X;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__POS_Y = NODE__POS_Y;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__CONTENT = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__WIDTH = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__HEIGHT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = NODE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ReifierConstraintImpl <em>Reifier Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ReifierConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getReifierConstraint()
	 * @generated
	 */
	int REIFIER_CONSTRAINT = 31;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The number of structural features of the '<em>Reifier Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIER_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ReifiableTopicTypeImpl <em>Reifiable Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ReifiableTopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getReifiableTopicType()
	 * @generated
	 */
	int REIFIABLE_TOPIC_TYPE = 32;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__SEE_ALSO = TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__COMMENT = TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__DESCRIPTION = TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__ANNOTATIONS = TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__IDENTIFIERS = TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__ABSTRACT = TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__KIND = TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__OVERLAP = TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__NAME = TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__LOCATORS = TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Reifier Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reifiable Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REIFIABLE_TOPIC_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ScopedReifiableTopicTypeImpl <em>Scoped Reifiable Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ScopedReifiableTopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopedReifiableTopicType()
	 * @generated
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE = 33;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__SEE_ALSO = SCOPED_TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__COMMENT = SCOPED_TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__DESCRIPTION = SCOPED_TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__ANNOTATIONS = SCOPED_TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__IDENTIFIERS = SCOPED_TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__ID_TYPE = SCOPED_TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__ABSTRACT = SCOPED_TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__ISA = SCOPED_TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__AKO = SCOPED_TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = SCOPED_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__NAME_CONTRAINTS = SCOPED_TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = SCOPED_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = SCOPED_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__KIND = SCOPED_TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__OVERLAP = SCOPED_TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__NAME = SCOPED_TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__LOCATORS = SCOPED_TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = SCOPED_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__SCOPE = SCOPED_TOPIC_TYPE__SCOPE;

	/**
	 * The feature id for the '<em><b>Reifier Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Scoped Reifiable Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPED_REIFIABLE_TOPIC_TYPE_FEATURE_COUNT = SCOPED_TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AnnotationImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 34;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpTopicTypeImpl <em>Abstract Reg Exp Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractRegExpTopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractRegExpTopicType()
	 * @generated
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE = 35;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__SEE_ALSO = TOPIC_TYPE__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__COMMENT = TOPIC_TYPE__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__DESCRIPTION = TOPIC_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__ANNOTATIONS = TOPIC_TYPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__IDENTIFIERS = TOPIC_TYPE__IDENTIFIERS;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__ABSTRACT = TOPIC_TYPE__ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = TOPIC_TYPE__OCCURRENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__KIND = TOPIC_TYPE__KIND;

	/**
	 * The feature id for the '<em><b>Overlap</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__OVERLAP = TOPIC_TYPE__OVERLAP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__NAME = TOPIC_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__LOCATORS = TOPIC_TYPE__LOCATORS;

	/**
	 * The feature id for the '<em><b>Topic Reifies Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Reg Exp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Abstract Reg Exp Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_REG_EXP_TOPIC_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TopicReifiesConstraintImpl <em>Topic Reifies Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TopicReifiesConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicReifiesConstraint()
	 * @generated
	 */
	int TOPIC_REIFIES_CONSTRAINT = 37;

	/**
	 * The feature id for the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__SEE_ALSO = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__SEE_ALSO;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__COMMENT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__COMMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__DESCRIPTION = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__ANNOTATIONS = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__CARD_MIN = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__CARD_MAX = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT__TYPE = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT__TYPE;

	/**
	 * The number of structural features of the '<em>Topic Reifies Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_REIFIES_CONSTRAINT_FEATURE_COUNT = ABSTRACT_TYPED_CARDINALITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicId()
	 * @generated
	 */
	int TOPIC_ID = 38;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.EdgeType <em>Edge Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.EdgeType
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getEdgeType()
	 * @generated
	 */
	int EDGE_TYPE = 39;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.KindOfTopicType <em>Kind Of Topic Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.KindOfTopicType
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getKindOfTopicType()
	 * @generated
	 */
	int KIND_OF_TOPIC_TYPE = 40;


	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.TopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType
	 * @generated
	 */
	EClass getTopicType();

	/**
	 * Returns the meta object for the attribute list '{@link de.topicmapslab.tmcledit.model.TopicType#getIdentifiers <em>Identifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Identifiers</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getIdentifiers()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Identifiers();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#getIdType <em>Id Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getIdType()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_IdType();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#isAbstract()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Abstract();

	/**
	 * Returns the meta object for the reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getIsa <em>Isa</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Isa</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getIsa()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_Isa();

	/**
	 * Returns the meta object for the reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getAko <em>Ako</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ako</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getAko()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_Ako();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getOccurrenceConstraints <em>Occurrence Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Occurrence Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getOccurrenceConstraints()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_OccurrenceConstraints();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getNameContraints <em>Name Contraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Name Contraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getNameContraints()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_NameContraints();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectIdentifierConstraints <em>Subject Identifier Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subject Identifier Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getSubjectIdentifierConstraints()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_SubjectIdentifierConstraints();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectLocatorConstraint <em>Subject Locator Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subject Locator Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getSubjectLocatorConstraint()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_SubjectLocatorConstraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getKind()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Kind();

	/**
	 * Returns the meta object for the reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getOverlap <em>Overlap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Overlap</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getOverlap()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_Overlap();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getName()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Name();

	/**
	 * Returns the meta object for the attribute list '{@link de.topicmapslab.tmcledit.model.TopicType#getLocators <em>Locators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Locators</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getLocators()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Locators();

	/**
	 * Returns the meta object for the containment reference '{@link de.topicmapslab.tmcledit.model.TopicType#getTopicReifiesConstraint <em>Topic Reifies Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Topic Reifies Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getTopicReifiesConstraint()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_TopicReifiesConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractRegExpConstraint <em>Abstract Reg Exp Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Reg Exp Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpConstraint
	 * @generated
	 */
	EClass getAbstractRegExpConstraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractRegExpConstraint#getRegexp <em>Regexp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regexp</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpConstraint#getRegexp()
	 * @see #getAbstractRegExpConstraint()
	 * @generated
	 */
	EAttribute getAbstractRegExpConstraint_Regexp();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint <em>Occurrence Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurrence Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint
	 * @generated
	 */
	EClass getOccurrenceTypeConstraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint#isUnique()
	 * @see #getOccurrenceTypeConstraint()
	 * @generated
	 */
	EAttribute getOccurrenceTypeConstraint_Unique();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.NameTypeConstraint <em>Name Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.NameTypeConstraint
	 * @generated
	 */
	EClass getNameTypeConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint <em>Role Player Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Player Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.RolePlayerConstraint
	 * @generated
	 */
	EClass getRolePlayerConstraint();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getPlayer <em>Player</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Player</em>'.
	 * @see de.topicmapslab.tmcledit.model.RolePlayerConstraint#getPlayer()
	 * @see #getRolePlayerConstraint()
	 * @generated
	 */
	EReference getRolePlayerConstraint_Player();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see de.topicmapslab.tmcledit.model.RolePlayerConstraint#getRole()
	 * @see #getRolePlayerConstraint()
	 * @generated
	 */
	EReference getRolePlayerConstraint_Role();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.TopicMapSchema <em>Topic Map Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic Map Schema</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema
	 * @generated
	 */
	EClass getTopicMapSchema();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getTopicTypes <em>Topic Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Topic Types</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getTopicTypes()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EReference getTopicMapSchema_TopicTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Association Type Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EReference getTopicMapSchema_AssociationTypeConstraints();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings <em>Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mappings</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EReference getTopicMapSchema_Mappings();

	/**
	 * Returns the meta object for the attribute list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getIncludes <em>Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Includes</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getIncludes()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EAttribute getTopicMapSchema_Includes();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getBaseLocator <em>Base Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base Locator</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getBaseLocator()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EAttribute getTopicMapSchema_BaseLocator();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getName()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EAttribute getTopicMapSchema_Name();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.SubjectLocatorConstraint <em>Subject Locator Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subject Locator Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.SubjectLocatorConstraint
	 * @generated
	 */
	EClass getSubjectLocatorConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint <em>Subject Identifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subject Identifier Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint
	 * @generated
	 */
	EClass getSubjectIdentifierConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint <em>Association Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint
	 * @generated
	 */
	EClass getAssociationTypeConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getPlayerConstraints <em>Player Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Player Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getPlayerConstraints()
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 */
	EReference getAssociationTypeConstraint_PlayerConstraints();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.MappingElement <em>Mapping Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mapping Element</em>'.
	 * @see de.topicmapslab.tmcledit.model.MappingElement
	 * @generated
	 */
	EClass getMappingElement();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.MappingElement#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see de.topicmapslab.tmcledit.model.MappingElement#getKey()
	 * @see #getMappingElement()
	 * @generated
	 */
	EAttribute getMappingElement_Key();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.MappingElement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.topicmapslab.tmcledit.model.MappingElement#getValue()
	 * @see #getMappingElement()
	 * @generated
	 */
	EAttribute getMappingElement_Value();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see de.topicmapslab.tmcledit.model.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Node#getPosX <em>Pos X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos X</em>'.
	 * @see de.topicmapslab.tmcledit.model.Node#getPosX()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_PosX();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Node#getPosY <em>Pos Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Y</em>'.
	 * @see de.topicmapslab.tmcledit.model.Node#getPosY()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_PosY();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.TypeNode <em>Type Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Node</em>'.
	 * @see de.topicmapslab.tmcledit.model.TypeNode
	 * @generated
	 */
	EClass getTypeNode();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.TypeNode#getTopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.TypeNode#getTopicType()
	 * @see #getTypeNode()
	 * @generated
	 */
	EReference getTypeNode_TopicType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Bendpoint <em>Bendpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bendpoint</em>'.
	 * @see de.topicmapslab.tmcledit.model.Bendpoint
	 * @generated
	 */
	EClass getBendpoint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Bendpoint#getPosX <em>Pos X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos X</em>'.
	 * @see de.topicmapslab.tmcledit.model.Bendpoint#getPosX()
	 * @see #getBendpoint()
	 * @generated
	 */
	EAttribute getBendpoint_PosX();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Bendpoint#getPosY <em>Pos Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Y</em>'.
	 * @see de.topicmapslab.tmcledit.model.Bendpoint#getPosY()
	 * @see #getBendpoint()
	 * @generated
	 */
	EAttribute getBendpoint_PosY();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge
	 * @generated
	 */
	EClass getEdge();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.Edge#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bendpoints</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getBendpoints()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Bendpoints();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getSource()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Source();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getTarget()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Target();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Edge#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getType()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Type();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.Edge#getRoleConstraint <em>Role Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getRoleConstraint()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_RoleConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.Edge#getLabelPositions <em>Label Positions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Label Positions</em>'.
	 * @see de.topicmapslab.tmcledit.model.Edge#getLabelPositions()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_LabelPositions();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AssociationNode <em>Association Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Node</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationNode
	 * @generated
	 */
	EClass getAssociationNode();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint <em>Association Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint()
	 * @see #getAssociationNode()
	 * @generated
	 */
	EReference getAssociationNode_AssociationConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see de.topicmapslab.tmcledit.model.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.Diagram#getEdges <em>Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edges</em>'.
	 * @see de.topicmapslab.tmcledit.model.Diagram#getEdges()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Edges();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.Diagram#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see de.topicmapslab.tmcledit.model.Diagram#getNodes()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Nodes();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Diagram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.topicmapslab.tmcledit.model.Diagram#getName()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.Diagram#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see de.topicmapslab.tmcledit.model.Diagram#getComments()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Comments();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.File <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File</em>'.
	 * @see de.topicmapslab.tmcledit.model.File
	 * @generated
	 */
	EClass getFile();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.File#getDiagrams <em>Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagrams</em>'.
	 * @see de.topicmapslab.tmcledit.model.File#getDiagrams()
	 * @see #getFile()
	 * @generated
	 */
	EReference getFile_Diagrams();

	/**
	 * Returns the meta object for the containment reference '{@link de.topicmapslab.tmcledit.model.File#getTopicMapSchema <em>Topic Map Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Topic Map Schema</em>'.
	 * @see de.topicmapslab.tmcledit.model.File#getTopicMapSchema()
	 * @see #getFile()
	 * @generated
	 */
	EReference getFile_TopicMapSchema();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.File#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see de.topicmapslab.tmcledit.model.File#getFilename()
	 * @see #getFile()
	 * @generated
	 */
	EAttribute getFile_Filename();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.File#isDirty <em>Dirty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dirty</em>'.
	 * @see de.topicmapslab.tmcledit.model.File#isDirty()
	 * @see #getFile()
	 * @generated
	 */
	EAttribute getFile_Dirty();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ScopeConstraint <em>Scope Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.ScopeConstraint
	 * @generated
	 */
	EClass getScopeConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint <em>Abstract Cardinality Contraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Cardinality Contraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractCardinalityContraint
	 * @generated
	 */
	EClass getAbstractCardinalityContraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMin <em>Card Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Min</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMin()
	 * @see #getAbstractCardinalityContraint()
	 * @generated
	 */
	EAttribute getAbstractCardinalityContraint_CardMin();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMax <em>Card Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Max</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMax()
	 * @see #getAbstractCardinalityContraint()
	 * @generated
	 */
	EAttribute getAbstractCardinalityContraint_CardMax();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.LabelPos <em>Label Pos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Pos</em>'.
	 * @see de.topicmapslab.tmcledit.model.LabelPos
	 * @generated
	 */
	EClass getLabelPos();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.LabelPos#getPosX <em>Pos X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos X</em>'.
	 * @see de.topicmapslab.tmcledit.model.LabelPos#getPosX()
	 * @see #getLabelPos()
	 * @generated
	 */
	EAttribute getLabelPos_PosX();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.LabelPos#getPosY <em>Pos Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Y</em>'.
	 * @see de.topicmapslab.tmcledit.model.LabelPos#getPosY()
	 * @see #getLabelPos()
	 * @generated
	 */
	EAttribute getLabelPos_PosY();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractTypedConstraint <em>Abstract Typed Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Typed Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractTypedConstraint
	 * @generated
	 */
	EClass getAbstractTypedConstraint();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.AbstractTypedConstraint#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractTypedConstraint#getType()
	 * @see #getAbstractTypedConstraint()
	 * @generated
	 */
	EReference getAbstractTypedConstraint_Type();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ScopedTopicType <em>Scoped Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scoped Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.ScopedTopicType
	 * @generated
	 */
	EClass getScopedTopicType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.ScopedTopicType#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scope</em>'.
	 * @see de.topicmapslab.tmcledit.model.ScopedTopicType#getScope()
	 * @see #getScopedTopicType()
	 * @generated
	 */
	EReference getScopedTopicType_Scope();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AssociationType <em>Association Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationType
	 * @generated
	 */
	EClass getAssociationType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.AssociationType#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Roles</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationType#getRoles()
	 * @see #getAssociationType()
	 * @generated
	 */
	EReference getAssociationType_Roles();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.AssociationType#getRoleCombinations <em>Role Combinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Role Combinations</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationType#getRoleCombinations()
	 * @see #getAssociationType()
	 * @generated
	 */
	EReference getAssociationType_RoleCombinations();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.OccurrenceType <em>Occurrence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurrence Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceType
	 * @generated
	 */
	EClass getOccurrenceType();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.OccurrenceType#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceType#isUnique()
	 * @see #getOccurrenceType()
	 * @generated
	 */
	EAttribute getOccurrenceType_Unique();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.OccurrenceType#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceType#getDataType()
	 * @see #getOccurrenceType()
	 * @generated
	 */
	EAttribute getOccurrenceType_DataType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.RoleConstraint <em>Role Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleConstraint
	 * @generated
	 */
	EClass getRoleConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.RoleType <em>Role Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleType
	 * @generated
	 */
	EClass getRoleType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint <em>Role Combination Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Combination Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint
	 * @generated
	 */
	EClass getRoleCombinationConstraint();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getPlayer <em>Player</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Player</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getPlayer()
	 * @see #getRoleCombinationConstraint()
	 * @generated
	 */
	EReference getRoleCombinationConstraint_Player();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherPlayer <em>Other Player</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Other Player</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherPlayer()
	 * @see #getRoleCombinationConstraint()
	 * @generated
	 */
	EReference getRoleCombinationConstraint_OtherPlayer();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherRole <em>Other Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Other Role</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherRole()
	 * @see #getRoleCombinationConstraint()
	 * @generated
	 */
	EReference getRoleCombinationConstraint_OtherRole();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getRole()
	 * @see #getRoleCombinationConstraint()
	 * @generated
	 */
	EReference getRoleCombinationConstraint_Role();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.NameType <em>Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.NameType
	 * @generated
	 */
	EClass getNameType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint <em>Abstract Typed Cardinality Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Typed Cardinality Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint
	 * @generated
	 */
	EClass getAbstractTypedCardinalityConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see de.topicmapslab.tmcledit.model.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Comment#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see de.topicmapslab.tmcledit.model.Comment#getContent()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Content();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Comment#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see de.topicmapslab.tmcledit.model.Comment#getWidth()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Width();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Comment#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see de.topicmapslab.tmcledit.model.Comment#getHeight()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Height();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.TMCLConstruct <em>TMCL Construct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TMCL Construct</em>'.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct
	 * @generated
	 */
	EClass getTMCLConstruct();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getSee_also <em>See also</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>See also</em>'.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct#getSee_also()
	 * @see #getTMCLConstruct()
	 * @generated
	 */
	EAttribute getTMCLConstruct_See_also();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct#getComment()
	 * @see #getTMCLConstruct()
	 * @generated
	 */
	EAttribute getTMCLConstruct_Comment();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct#getDescription()
	 * @see #getTMCLConstruct()
	 * @generated
	 */
	EAttribute getTMCLConstruct_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct#getAnnotations()
	 * @see #getTMCLConstruct()
	 * @generated
	 */
	EReference getTMCLConstruct_Annotations();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ReifierConstraint <em>Reifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reifier Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.ReifierConstraint
	 * @generated
	 */
	EClass getReifierConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ReifiableTopicType <em>Reifiable Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reifiable Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.ReifiableTopicType
	 * @generated
	 */
	EClass getReifiableTopicType();

	/**
	 * Returns the meta object for the containment reference '{@link de.topicmapslab.tmcledit.model.ReifiableTopicType#getReifierConstraint <em>Reifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reifier Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.ReifiableTopicType#getReifierConstraint()
	 * @see #getReifiableTopicType()
	 * @generated
	 */
	EReference getReifiableTopicType_ReifierConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ScopedReifiableTopicType <em>Scoped Reifiable Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scoped Reifiable Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.ScopedReifiableTopicType
	 * @generated
	 */
	EClass getScopedReifiableTopicType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see de.topicmapslab.tmcledit.model.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Annotation#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see de.topicmapslab.tmcledit.model.Annotation#getKey()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Key();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.Annotation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.topicmapslab.tmcledit.model.Annotation#getValue()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Value();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractRegExpTopicType <em>Abstract Reg Exp Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Reg Exp Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpTopicType
	 * @generated
	 */
	EClass getAbstractRegExpTopicType();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractRegExpTopicType#getRegExp <em>Reg Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reg Exp</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpTopicType#getRegExp()
	 * @see #getAbstractRegExpTopicType()
	 * @generated
	 */
	EAttribute getAbstractRegExpTopicType_RegExp();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractConstraint <em>Abstract Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractConstraint
	 * @generated
	 */
	EClass getAbstractConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.TopicReifiesConstraint <em>Topic Reifies Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic Reifies Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicReifiesConstraint
	 * @generated
	 */
	EClass getTopicReifiesConstraint();

	/**
	 * Returns the meta object for enum '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Topic Id</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @generated
	 */
	EEnum getTopicId();

	/**
	 * Returns the meta object for enum '{@link de.topicmapslab.tmcledit.model.EdgeType <em>Edge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Edge Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.EdgeType
	 * @generated
	 */
	EEnum getEdgeType();

	/**
	 * Returns the meta object for enum '{@link de.topicmapslab.tmcledit.model.KindOfTopicType <em>Kind Of Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Kind Of Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.KindOfTopicType
	 * @generated
	 */
	EEnum getKindOfTopicType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl <em>Topic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.TopicTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicType()
		 * @generated
		 */
		EClass TOPIC_TYPE = eINSTANCE.getTopicType();

		/**
		 * The meta object literal for the '<em><b>Identifiers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__IDENTIFIERS = eINSTANCE.getTopicType_Identifiers();

		/**
		 * The meta object literal for the '<em><b>Id Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__ID_TYPE = eINSTANCE.getTopicType_IdType();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__ABSTRACT = eINSTANCE.getTopicType_Abstract();

		/**
		 * The meta object literal for the '<em><b>Isa</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__ISA = eINSTANCE.getTopicType_Isa();

		/**
		 * The meta object literal for the '<em><b>Ako</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__AKO = eINSTANCE.getTopicType_Ako();

		/**
		 * The meta object literal for the '<em><b>Occurrence Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__OCCURRENCE_CONSTRAINTS = eINSTANCE.getTopicType_OccurrenceConstraints();

		/**
		 * The meta object literal for the '<em><b>Name Contraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__NAME_CONTRAINTS = eINSTANCE.getTopicType_NameContraints();

		/**
		 * The meta object literal for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = eINSTANCE.getTopicType_SubjectIdentifierConstraints();

		/**
		 * The meta object literal for the '<em><b>Subject Locator Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = eINSTANCE.getTopicType_SubjectLocatorConstraint();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__KIND = eINSTANCE.getTopicType_Kind();

		/**
		 * The meta object literal for the '<em><b>Overlap</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__OVERLAP = eINSTANCE.getTopicType_Overlap();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__NAME = eINSTANCE.getTopicType_Name();

		/**
		 * The meta object literal for the '<em><b>Locators</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__LOCATORS = eINSTANCE.getTopicType_Locators();

		/**
		 * The meta object literal for the '<em><b>Topic Reifies Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINT = eINSTANCE.getTopicType_TopicReifiesConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpConstraintImpl <em>Abstract Reg Exp Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractRegExpConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractRegExpConstraint()
		 * @generated
		 */
		EClass ABSTRACT_REG_EXP_CONSTRAINT = eINSTANCE.getAbstractRegExpConstraint();

		/**
		 * The meta object literal for the '<em><b>Regexp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REG_EXP_CONSTRAINT__REGEXP = eINSTANCE.getAbstractRegExpConstraint_Regexp();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeConstraintImpl <em>Occurrence Type Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.OccurrenceTypeConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurrenceTypeConstraint()
		 * @generated
		 */
		EClass OCCURRENCE_TYPE_CONSTRAINT = eINSTANCE.getOccurrenceTypeConstraint();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCURRENCE_TYPE_CONSTRAINT__UNIQUE = eINSTANCE.getOccurrenceTypeConstraint_Unique();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl <em>Name Type Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameTypeConstraint()
		 * @generated
		 */
		EClass NAME_TYPE_CONSTRAINT = eINSTANCE.getNameTypeConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl <em>Role Player Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRolePlayerConstraint()
		 * @generated
		 */
		EClass ROLE_PLAYER_CONSTRAINT = eINSTANCE.getRolePlayerConstraint();

		/**
		 * The meta object literal for the '<em><b>Player</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_PLAYER_CONSTRAINT__PLAYER = eINSTANCE.getRolePlayerConstraint_Player();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_PLAYER_CONSTRAINT__ROLE = eINSTANCE.getRolePlayerConstraint_Role();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl <em>Topic Map Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicMapSchema()
		 * @generated
		 */
		EClass TOPIC_MAP_SCHEMA = eINSTANCE.getTopicMapSchema();

		/**
		 * The meta object literal for the '<em><b>Topic Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_MAP_SCHEMA__TOPIC_TYPES = eINSTANCE.getTopicMapSchema_TopicTypes();

		/**
		 * The meta object literal for the '<em><b>Association Type Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS = eINSTANCE.getTopicMapSchema_AssociationTypeConstraints();

		/**
		 * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_MAP_SCHEMA__MAPPINGS = eINSTANCE.getTopicMapSchema_Mappings();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_MAP_SCHEMA__INCLUDES = eINSTANCE.getTopicMapSchema_Includes();

		/**
		 * The meta object literal for the '<em><b>Base Locator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_MAP_SCHEMA__BASE_LOCATOR = eINSTANCE.getTopicMapSchema_BaseLocator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_MAP_SCHEMA__NAME = eINSTANCE.getTopicMapSchema_Name();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.SubjectLocatorConstraintImpl <em>Subject Locator Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.SubjectLocatorConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getSubjectLocatorConstraint()
		 * @generated
		 */
		EClass SUBJECT_LOCATOR_CONSTRAINT = eINSTANCE.getSubjectLocatorConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl <em>Subject Identifier Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getSubjectIdentifierConstraint()
		 * @generated
		 */
		EClass SUBJECT_IDENTIFIER_CONSTRAINT = eINSTANCE.getSubjectIdentifierConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl <em>Association Type Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationTypeConstraint()
		 * @generated
		 */
		EClass ASSOCIATION_TYPE_CONSTRAINT = eINSTANCE.getAssociationTypeConstraint();

		/**
		 * The meta object literal for the '<em><b>Player Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS = eINSTANCE.getAssociationTypeConstraint_PlayerConstraints();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.MappingElementImpl <em>Mapping Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.MappingElementImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getMappingElement()
		 * @generated
		 */
		EClass MAPPING_ELEMENT = eINSTANCE.getMappingElement();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_ELEMENT__KEY = eINSTANCE.getMappingElement_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_ELEMENT__VALUE = eINSTANCE.getMappingElement_Value();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.NodeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__POS_X = eINSTANCE.getNode_PosX();

		/**
		 * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__POS_Y = eINSTANCE.getNode_PosY();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.TypeNodeImpl <em>Type Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.TypeNodeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTypeNode()
		 * @generated
		 */
		EClass TYPE_NODE = eINSTANCE.getTypeNode();

		/**
		 * The meta object literal for the '<em><b>Topic Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_NODE__TOPIC_TYPE = eINSTANCE.getTypeNode_TopicType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.BendpointImpl <em>Bendpoint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.BendpointImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getBendpoint()
		 * @generated
		 */
		EClass BENDPOINT = eINSTANCE.getBendpoint();

		/**
		 * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENDPOINT__POS_X = eINSTANCE.getBendpoint_PosX();

		/**
		 * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENDPOINT__POS_Y = eINSTANCE.getBendpoint_PosY();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl <em>Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.EdgeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getEdge()
		 * @generated
		 */
		EClass EDGE = eINSTANCE.getEdge();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__BENDPOINTS = eINSTANCE.getEdge_Bendpoints();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__TYPE = eINSTANCE.getEdge_Type();

		/**
		 * The meta object literal for the '<em><b>Role Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__ROLE_CONSTRAINT = eINSTANCE.getEdge_RoleConstraint();

		/**
		 * The meta object literal for the '<em><b>Label Positions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__LABEL_POSITIONS = eINSTANCE.getEdge_LabelPositions();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl <em>Association Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationNode()
		 * @generated
		 */
		EClass ASSOCIATION_NODE = eINSTANCE.getAssociationNode();

		/**
		 * The meta object literal for the '<em><b>Association Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT = eINSTANCE.getAssociationNode_AssociationConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.DiagramImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__EDGES = eINSTANCE.getDiagram_Edges();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__NODES = eINSTANCE.getDiagram_Nodes();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__NAME = eINSTANCE.getDiagram_Name();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__COMMENTS = eINSTANCE.getDiagram_Comments();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.FileImpl <em>File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.FileImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getFile()
		 * @generated
		 */
		EClass FILE = eINSTANCE.getFile();

		/**
		 * The meta object literal for the '<em><b>Diagrams</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE__DIAGRAMS = eINSTANCE.getFile_Diagrams();

		/**
		 * The meta object literal for the '<em><b>Topic Map Schema</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE__TOPIC_MAP_SCHEMA = eINSTANCE.getFile_TopicMapSchema();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE__FILENAME = eINSTANCE.getFile_Filename();

		/**
		 * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE__DIRTY = eINSTANCE.getFile_Dirty();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ScopeConstraintImpl <em>Scope Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ScopeConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopeConstraint()
		 * @generated
		 */
		EClass SCOPE_CONSTRAINT = eINSTANCE.getScopeConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractCardinalityContraintImpl <em>Abstract Cardinality Contraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractCardinalityContraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractCardinalityContraint()
		 * @generated
		 */
		EClass ABSTRACT_CARDINALITY_CONTRAINT = eINSTANCE.getAbstractCardinalityContraint();

		/**
		 * The meta object literal for the '<em><b>Card Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN = eINSTANCE.getAbstractCardinalityContraint_CardMin();

		/**
		 * The meta object literal for the '<em><b>Card Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX = eINSTANCE.getAbstractCardinalityContraint_CardMax();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.LabelPosImpl <em>Label Pos</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.LabelPosImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getLabelPos()
		 * @generated
		 */
		EClass LABEL_POS = eINSTANCE.getLabelPos();

		/**
		 * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_POS__POS_X = eINSTANCE.getLabelPos_PosX();

		/**
		 * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_POS__POS_Y = eINSTANCE.getLabelPos_PosY();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractTypedConstraintImpl <em>Abstract Typed Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractTypedConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractTypedConstraint()
		 * @generated
		 */
		EClass ABSTRACT_TYPED_CONSTRAINT = eINSTANCE.getAbstractTypedConstraint();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TYPED_CONSTRAINT__TYPE = eINSTANCE.getAbstractTypedConstraint_Type();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ScopedTopicTypeImpl <em>Scoped Topic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ScopedTopicTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopedTopicType()
		 * @generated
		 */
		EClass SCOPED_TOPIC_TYPE = eINSTANCE.getScopedTopicType();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPED_TOPIC_TYPE__SCOPE = eINSTANCE.getScopedTopicType_Scope();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl <em>Association Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationType()
		 * @generated
		 */
		EClass ASSOCIATION_TYPE = eINSTANCE.getAssociationType();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_TYPE__ROLES = eINSTANCE.getAssociationType_Roles();

		/**
		 * The meta object literal for the '<em><b>Role Combinations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_TYPE__ROLE_COMBINATIONS = eINSTANCE.getAssociationType_RoleCombinations();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl <em>Occurrence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurrenceType()
		 * @generated
		 */
		EClass OCCURRENCE_TYPE = eINSTANCE.getOccurrenceType();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCURRENCE_TYPE__UNIQUE = eINSTANCE.getOccurrenceType_Unique();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCURRENCE_TYPE__DATA_TYPE = eINSTANCE.getOccurrenceType_DataType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.RoleConstraintImpl <em>Role Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.RoleConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleConstraint()
		 * @generated
		 */
		EClass ROLE_CONSTRAINT = eINSTANCE.getRoleConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl <em>Role Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.RoleTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleType()
		 * @generated
		 */
		EClass ROLE_TYPE = eINSTANCE.getRoleType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl <em>Role Combination Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleCombinationConstraint()
		 * @generated
		 */
		EClass ROLE_COMBINATION_CONSTRAINT = eINSTANCE.getRoleCombinationConstraint();

		/**
		 * The meta object literal for the '<em><b>Player</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_COMBINATION_CONSTRAINT__PLAYER = eINSTANCE.getRoleCombinationConstraint_Player();

		/**
		 * The meta object literal for the '<em><b>Other Player</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER = eINSTANCE.getRoleCombinationConstraint_OtherPlayer();

		/**
		 * The meta object literal for the '<em><b>Other Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE = eINSTANCE.getRoleCombinationConstraint_OtherRole();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_COMBINATION_CONSTRAINT__ROLE = eINSTANCE.getRoleCombinationConstraint_Role();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeImpl <em>Name Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.NameTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameType()
		 * @generated
		 */
		EClass NAME_TYPE = eINSTANCE.getNameType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractTypedCardinalityConstraintImpl <em>Abstract Typed Cardinality Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractTypedCardinalityConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractTypedCardinalityConstraint()
		 * @generated
		 */
		EClass ABSTRACT_TYPED_CARDINALITY_CONSTRAINT = eINSTANCE.getAbstractTypedCardinalityConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.CommentImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__CONTENT = eINSTANCE.getComment_Content();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__WIDTH = eINSTANCE.getComment_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__HEIGHT = eINSTANCE.getComment_Height();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl <em>TMCL Construct</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTMCLConstruct()
		 * @generated
		 */
		EClass TMCL_CONSTRUCT = eINSTANCE.getTMCLConstruct();

		/**
		 * The meta object literal for the '<em><b>See also</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMCL_CONSTRUCT__SEE_ALSO = eINSTANCE.getTMCLConstruct_See_also();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMCL_CONSTRUCT__COMMENT = eINSTANCE.getTMCLConstruct_Comment();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TMCL_CONSTRUCT__DESCRIPTION = eINSTANCE.getTMCLConstruct_Description();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TMCL_CONSTRUCT__ANNOTATIONS = eINSTANCE.getTMCLConstruct_Annotations();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ReifierConstraintImpl <em>Reifier Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ReifierConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getReifierConstraint()
		 * @generated
		 */
		EClass REIFIER_CONSTRAINT = eINSTANCE.getReifierConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ReifiableTopicTypeImpl <em>Reifiable Topic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ReifiableTopicTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getReifiableTopicType()
		 * @generated
		 */
		EClass REIFIABLE_TOPIC_TYPE = eINSTANCE.getReifiableTopicType();

		/**
		 * The meta object literal for the '<em><b>Reifier Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT = eINSTANCE.getReifiableTopicType_ReifierConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ScopedReifiableTopicTypeImpl <em>Scoped Reifiable Topic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ScopedReifiableTopicTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopedReifiableTopicType()
		 * @generated
		 */
		EClass SCOPED_REIFIABLE_TOPIC_TYPE = eINSTANCE.getScopedReifiableTopicType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AnnotationImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__KEY = eINSTANCE.getAnnotation_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__VALUE = eINSTANCE.getAnnotation_Value();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpTopicTypeImpl <em>Abstract Reg Exp Topic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractRegExpTopicTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractRegExpTopicType()
		 * @generated
		 */
		EClass ABSTRACT_REG_EXP_TOPIC_TYPE = eINSTANCE.getAbstractRegExpTopicType();

		/**
		 * The meta object literal for the '<em><b>Reg Exp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP = eINSTANCE.getAbstractRegExpTopicType_RegExp();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.AbstractConstraint <em>Abstract Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.AbstractConstraint
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractConstraint()
		 * @generated
		 */
		EClass ABSTRACT_CONSTRAINT = eINSTANCE.getAbstractConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.TopicReifiesConstraintImpl <em>Topic Reifies Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.TopicReifiesConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicReifiesConstraint()
		 * @generated
		 */
		EClass TOPIC_REIFIES_CONSTRAINT = eINSTANCE.getTopicReifiesConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.TopicId
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicId()
		 * @generated
		 */
		EEnum TOPIC_ID = eINSTANCE.getTopicId();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.EdgeType <em>Edge Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.EdgeType
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getEdgeType()
		 * @generated
		 */
		EEnum EDGE_TYPE = eINSTANCE.getEdgeType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.KindOfTopicType <em>Kind Of Topic Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.KindOfTopicType
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getKindOfTopicType()
		 * @generated
		 */
		EEnum KIND_OF_TOPIC_TYPE = eINSTANCE.getKindOfTopicType();

	}

} //ModelPackage
