/**
 * <copyright>
 * </copyright>
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
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl <em>Topic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TopicTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicType()
	 * @generated
	 */
	int TOPIC_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ID = 0;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ID_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__IS_ABSTRACT = 2;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__ISA = 3;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__AKO = 4;

	/**
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__OCCURENCE_CONSTRAINTS = 5;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__NAME_CONTRAINTS = 6;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = 7;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT = 8;

	/**
	 * The number of structural features of the '<em>Topic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.OccurenceTypeImpl <em>Occurence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.OccurenceTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurenceType()
	 * @generated
	 */
	int OCCURENCE_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__ID = TOPIC_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__IS_ABSTRACT = TOPIC_TYPE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__OCCURENCE_CONSTRAINTS = TOPIC_TYPE__OCCURENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Occurence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeImpl <em>Name Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.NameTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameType()
	 * @generated
	 */
	int NAME_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ID = TOPIC_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__IS_ABSTRACT = TOPIC_TYPE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__OCCURENCE_CONSTRAINTS = TOPIC_TYPE__OCCURENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Name Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.ScopeTypeImpl <em>Scope Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.ScopeTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopeType()
	 * @generated
	 */
	int SCOPE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__ID = TOPIC_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__IS_ABSTRACT = TOPIC_TYPE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__OCCURENCE_CONSTRAINTS = TOPIC_TYPE__OCCURENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Scope Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractContraintImpl <em>Abstract Contraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AbstractContraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractContraint()
	 * @generated
	 */
	int ABSTRACT_CONTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT__CARD_MIN = 0;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT__CARD_MAX = 1;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT__REGEXP = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT__NAME = 3;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT__SCOPE = 4;

	/**
	 * The number of structural features of the '<em>Abstract Contraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONTRAINT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.OccurenceTypeConstraintImpl <em>Occurence Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.OccurenceTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurenceTypeConstraint()
	 * @generated
	 */
	int OCCURENCE_TYPE_CONSTRAINT = 5;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__CARD_MIN = ABSTRACT_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__CARD_MAX = ABSTRACT_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__REGEXP = ABSTRACT_CONTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__NAME = ABSTRACT_CONTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__SCOPE = ABSTRACT_CONTRAINT__SCOPE;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__UNIQUE = ABSTRACT_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__DATA_TYPE = ABSTRACT_CONTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT__TYPE = ABSTRACT_CONTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Occurence Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCURENCE_TYPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONTRAINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl <em>Name Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.NameTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getNameTypeConstraint()
	 * @generated
	 */
	int NAME_TYPE_CONSTRAINT = 6;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__CARD_MIN = ABSTRACT_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__CARD_MAX = ABSTRACT_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__REGEXP = ABSTRACT_CONTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__NAME = ABSTRACT_CONTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__SCOPE = ABSTRACT_CONTRAINT__SCOPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT__TYPE = ABSTRACT_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Name Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_TYPE_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationsTypeImpl <em>Associations Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AssociationsTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationsType()
	 * @generated
	 */
	int ASSOCIATIONS_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__ID = TOPIC_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__IS_ABSTRACT = TOPIC_TYPE__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Isa</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__ISA = TOPIC_TYPE__ISA;

	/**
	 * The feature id for the '<em><b>Ako</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__AKO = TOPIC_TYPE__AKO;

	/**
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__OCCURENCE_CONSTRAINTS = TOPIC_TYPE__OCCURENCE_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Name Contraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__NAME_CONTRAINTS = TOPIC_TYPE__NAME_CONTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS = TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE__SUBJECT_LOCATOR_CONSTRAINT = TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Associations Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATIONS_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl <em>Role Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RoleTypeImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleType()
	 * @generated
	 */
	int ROLE_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ID = TOPIC_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Id Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__ID_TYPE = TOPIC_TYPE__ID_TYPE;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__IS_ABSTRACT = TOPIC_TYPE__IS_ABSTRACT;

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
	 * The feature id for the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE__OCCURENCE_CONSTRAINTS = TOPIC_TYPE__OCCURENCE_CONSTRAINTS;

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
	 * The number of structural features of the '<em>Role Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_FEATURE_COUNT = TOPIC_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl <em>Role Type Constraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleTypeConstraints()
	 * @generated
	 */
	int ROLE_TYPE_CONSTRAINTS = 9;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS__CARD_MIN = 0;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS__CARD_MAX = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Topic Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Association Type Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS__ASSOCIATION_TYPE_CONSTRAINT = 4;

	/**
	 * The number of structural features of the '<em>Role Type Constraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_TYPE_CONSTRAINTS_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl <em>Topic Map Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicMapSchema()
	 * @generated
	 */
	int TOPIC_MAP_SCHEMA = 10;

	/**
	 * The feature id for the '<em><b>Topic Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__TOPIC_TYPES = 0;

	/**
	 * The feature id for the '<em><b>Role Type Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS = 1;

	/**
	 * The feature id for the '<em><b>Association Type Constraints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS = 2;

	/**
	 * The feature id for the '<em><b>Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__MAPPINGS = 3;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA__INCLUDES = 4;

	/**
	 * The number of structural features of the '<em>Topic Map Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_MAP_SCHEMA_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.subjectLocatorConstraintImpl <em>subject Locator Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.subjectLocatorConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getsubjectLocatorConstraint()
	 * @generated
	 */
	int SUBJECT_LOCATOR_CONSTRAINT = 11;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__CARD_MIN = ABSTRACT_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__CARD_MAX = ABSTRACT_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__REGEXP = ABSTRACT_CONTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__NAME = ABSTRACT_CONTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT__SCOPE = ABSTRACT_CONTRAINT__SCOPE;

	/**
	 * The number of structural features of the '<em>subject Locator Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_LOCATOR_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.subjectIdentifierConstraintImpl <em>subject Identifier Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.subjectIdentifierConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getsubjectIdentifierConstraint()
	 * @generated
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN = ABSTRACT_CONTRAINT__CARD_MIN;

	/**
	 * The feature id for the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX = ABSTRACT_CONTRAINT__CARD_MAX;

	/**
	 * The feature id for the '<em><b>Regexp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__REGEXP = ABSTRACT_CONTRAINT__REGEXP;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__NAME = ABSTRACT_CONTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT__SCOPE = ABSTRACT_CONTRAINT__SCOPE;

	/**
	 * The number of structural features of the '<em>subject Identifier Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_IDENTIFIER_CONSTRAINT_FEATURE_COUNT = ABSTRACT_CONTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl <em>Association Type Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationTypeConstraint()
	 * @generated
	 */
	int ASSOCIATION_TYPE_CONSTRAINT = 13;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Association Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Association Type Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_TYPE_CONSTRAINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.impl.MappingElementImpl <em>Mapping Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.impl.MappingElementImpl
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getMappingElement()
	 * @generated
	 */
	int MAPPING_ELEMENT = 14;

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
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicId()
	 * @generated
	 */
	int TOPIC_ID = 15;


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
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getId()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_Id();

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
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.TopicType#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#isIsAbstract()
	 * @see #getTopicType()
	 * @generated
	 */
	EAttribute getTopicType_IsAbstract();

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
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicType#getOccurenceConstraints <em>Occurence Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Occurence Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType#getOccurenceConstraints()
	 * @see #getTopicType()
	 * @generated
	 */
	EReference getTopicType_OccurenceConstraints();

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
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.OccurenceType <em>Occurence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurence Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurenceType
	 * @generated
	 */
	EClass getOccurenceType();

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
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.ScopeType <em>Scope Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.ScopeType
	 * @generated
	 */
	EClass getScopeType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AbstractContraint <em>Abstract Contraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Contraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint
	 * @generated
	 */
	EClass getAbstractContraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractContraint#getCardMin <em>Card Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Min</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint#getCardMin()
	 * @see #getAbstractContraint()
	 * @generated
	 */
	EAttribute getAbstractContraint_CardMin();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractContraint#getCardMax <em>Card Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Max</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint#getCardMax()
	 * @see #getAbstractContraint()
	 * @generated
	 */
	EAttribute getAbstractContraint_CardMax();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractContraint#getRegexp <em>Regexp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Regexp</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint#getRegexp()
	 * @see #getAbstractContraint()
	 * @generated
	 */
	EAttribute getAbstractContraint_Regexp();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.AbstractContraint#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint#getName()
	 * @see #getAbstractContraint()
	 * @generated
	 */
	EAttribute getAbstractContraint_Name();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.AbstractContraint#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Scope</em>'.
	 * @see de.topicmapslab.tmcledit.model.AbstractContraint#getScope()
	 * @see #getAbstractContraint()
	 * @generated
	 */
	EReference getAbstractContraint_Scope();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint <em>Occurence Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurence Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurenceTypeConstraint
	 * @generated
	 */
	EClass getOccurenceTypeConstraint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#isUnique()
	 * @see #getOccurenceTypeConstraint()
	 * @generated
	 */
	EAttribute getOccurenceTypeConstraint_Unique();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#getDataType()
	 * @see #getOccurenceTypeConstraint()
	 * @generated
	 */
	EAttribute getOccurenceTypeConstraint_DataType();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#getType()
	 * @see #getOccurenceTypeConstraint()
	 * @generated
	 */
	EReference getOccurenceTypeConstraint_Type();

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
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.NameTypeConstraint#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.NameTypeConstraint#getType()
	 * @see #getNameTypeConstraint()
	 * @generated
	 */
	EReference getNameTypeConstraint_Type();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.AssociationsType <em>Associations Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Associations Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationsType
	 * @generated
	 */
	EClass getAssociationsType();

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
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints <em>Role Type Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Type Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints
	 * @generated
	 */
	EClass getRoleTypeConstraints();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMin <em>Card Min</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Min</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMin()
	 * @see #getRoleTypeConstraints()
	 * @generated
	 */
	EAttribute getRoleTypeConstraints_CardMin();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMax <em>Card Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Card Max</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMax()
	 * @see #getRoleTypeConstraints()
	 * @generated
	 */
	EAttribute getRoleTypeConstraints_CardMax();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints#getType()
	 * @see #getRoleTypeConstraints()
	 * @generated
	 */
	EReference getRoleTypeConstraints_Type();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getTopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints#getTopicType()
	 * @see #getRoleTypeConstraints()
	 * @generated
	 */
	EReference getRoleTypeConstraints_TopicType();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getAssociationTypeConstraint <em>Association Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints#getAssociationTypeConstraint()
	 * @see #getRoleTypeConstraints()
	 * @generated
	 */
	EReference getRoleTypeConstraints_AssociationTypeConstraint();

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
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getRoleTypeConstraints <em>Role Type Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Role Type Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getRoleTypeConstraints()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EReference getTopicMapSchema_RoleTypeConstraints();

	/**
	 * Returns the meta object for the containment reference '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Association Type Constraints</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints()
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	EReference getTopicMapSchema_AssociationTypeConstraints();

	/**
	 * Returns the meta object for the reference list '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings <em>Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mappings</em>'.
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
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.subjectLocatorConstraint <em>subject Locator Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>subject Locator Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.subjectLocatorConstraint
	 * @generated
	 */
	EClass getsubjectLocatorConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.model.subjectIdentifierConstraint <em>subject Identifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>subject Identifier Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.subjectIdentifierConstraint
	 * @generated
	 */
	EClass getsubjectIdentifierConstraint();

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
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Scope</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getScope()
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 */
	EReference getAssociationTypeConstraint_Scope();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getAssociationType <em>Association Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getAssociationType()
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 */
	EReference getAssociationTypeConstraint_AssociationType();

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
	 * Returns the meta object for enum '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Topic Id</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @generated
	 */
	EEnum getTopicId();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__ID = eINSTANCE.getTopicType_Id();

		/**
		 * The meta object literal for the '<em><b>Id Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__ID_TYPE = eINSTANCE.getTopicType_IdType();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC_TYPE__IS_ABSTRACT = eINSTANCE.getTopicType_IsAbstract();

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
		 * The meta object literal for the '<em><b>Occurence Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_TYPE__OCCURENCE_CONSTRAINTS = eINSTANCE.getTopicType_OccurenceConstraints();

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
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.OccurenceTypeImpl <em>Occurence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.OccurenceTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurenceType()
		 * @generated
		 */
		EClass OCCURENCE_TYPE = eINSTANCE.getOccurenceType();

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
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.ScopeTypeImpl <em>Scope Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.ScopeTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getScopeType()
		 * @generated
		 */
		EClass SCOPE_TYPE = eINSTANCE.getScopeType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AbstractContraintImpl <em>Abstract Contraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AbstractContraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAbstractContraint()
		 * @generated
		 */
		EClass ABSTRACT_CONTRAINT = eINSTANCE.getAbstractContraint();

		/**
		 * The meta object literal for the '<em><b>Card Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTRAINT__CARD_MIN = eINSTANCE.getAbstractContraint_CardMin();

		/**
		 * The meta object literal for the '<em><b>Card Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTRAINT__CARD_MAX = eINSTANCE.getAbstractContraint_CardMax();

		/**
		 * The meta object literal for the '<em><b>Regexp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTRAINT__REGEXP = eINSTANCE.getAbstractContraint_Regexp();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_CONTRAINT__NAME = eINSTANCE.getAbstractContraint_Name();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_CONTRAINT__SCOPE = eINSTANCE.getAbstractContraint_Scope();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.OccurenceTypeConstraintImpl <em>Occurence Type Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.OccurenceTypeConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getOccurenceTypeConstraint()
		 * @generated
		 */
		EClass OCCURENCE_TYPE_CONSTRAINT = eINSTANCE.getOccurenceTypeConstraint();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCURENCE_TYPE_CONSTRAINT__UNIQUE = eINSTANCE.getOccurenceTypeConstraint_Unique();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCURENCE_TYPE_CONSTRAINT__DATA_TYPE = eINSTANCE.getOccurenceTypeConstraint_DataType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCCURENCE_TYPE_CONSTRAINT__TYPE = eINSTANCE.getOccurenceTypeConstraint_Type();

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
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAME_TYPE_CONSTRAINT__TYPE = eINSTANCE.getNameTypeConstraint_Type();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.AssociationsTypeImpl <em>Associations Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.AssociationsTypeImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getAssociationsType()
		 * @generated
		 */
		EClass ASSOCIATIONS_TYPE = eINSTANCE.getAssociationsType();

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
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl <em>Role Type Constraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getRoleTypeConstraints()
		 * @generated
		 */
		EClass ROLE_TYPE_CONSTRAINTS = eINSTANCE.getRoleTypeConstraints();

		/**
		 * The meta object literal for the '<em><b>Card Min</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_TYPE_CONSTRAINTS__CARD_MIN = eINSTANCE.getRoleTypeConstraints_CardMin();

		/**
		 * The meta object literal for the '<em><b>Card Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_TYPE_CONSTRAINTS__CARD_MAX = eINSTANCE.getRoleTypeConstraints_CardMax();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_TYPE_CONSTRAINTS__TYPE = eINSTANCE.getRoleTypeConstraints_Type();

		/**
		 * The meta object literal for the '<em><b>Topic Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE = eINSTANCE.getRoleTypeConstraints_TopicType();

		/**
		 * The meta object literal for the '<em><b>Association Type Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_TYPE_CONSTRAINTS__ASSOCIATION_TYPE_CONSTRAINT = eINSTANCE.getRoleTypeConstraints_AssociationTypeConstraint();

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
		 * The meta object literal for the '<em><b>Role Type Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS = eINSTANCE.getTopicMapSchema_RoleTypeConstraints();

		/**
		 * The meta object literal for the '<em><b>Association Type Constraints</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS = eINSTANCE.getTopicMapSchema_AssociationTypeConstraints();

		/**
		 * The meta object literal for the '<em><b>Mappings</b></em>' reference list feature.
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
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.subjectLocatorConstraintImpl <em>subject Locator Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.subjectLocatorConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getsubjectLocatorConstraint()
		 * @generated
		 */
		EClass SUBJECT_LOCATOR_CONSTRAINT = eINSTANCE.getsubjectLocatorConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.impl.subjectIdentifierConstraintImpl <em>subject Identifier Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.impl.subjectIdentifierConstraintImpl
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getsubjectIdentifierConstraint()
		 * @generated
		 */
		EClass SUBJECT_IDENTIFIER_CONSTRAINT = eINSTANCE.getsubjectIdentifierConstraint();

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
		 * The meta object literal for the '<em><b>Scope</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_TYPE_CONSTRAINT__SCOPE = eINSTANCE.getAssociationTypeConstraint_Scope();

		/**
		 * The meta object literal for the '<em><b>Association Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE = eINSTANCE.getAssociationTypeConstraint_AssociationType();

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
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.model.TopicId <em>Topic Id</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.TopicId
		 * @see de.topicmapslab.tmcledit.model.impl.ModelPackageImpl#getTopicId()
		 * @generated
		 */
		EEnum TOPIC_ID = eINSTANCE.getTopicId();

	}

} //ModelPackage
