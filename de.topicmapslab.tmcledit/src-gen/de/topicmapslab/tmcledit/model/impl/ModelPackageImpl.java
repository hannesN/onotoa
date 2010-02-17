/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AbstractUniqueValueTopicType;
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.OnoObject;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedReifiableTopicType;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass topicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractRegExpConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass occurrenceTypeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameTypeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rolePlayerConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass topicMapSchemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectLocatorConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectIdentifierConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationTypeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bendpointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractCardinalityContraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelPosEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTypedConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopedTopicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass occurrenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleCombinationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTypedCardinalityConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tmclConstructEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reifierConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reifiableTopicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopedReifiableTopicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractRegExpTopicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass topicReifiesConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass onoObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractUniqueValueTopicTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum topicIdEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum edgeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum kindOfTopicTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTopicType() {
		return topicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_Identifiers() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_IdType() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_Abstract() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_Isa() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_Ako() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_OccurrenceConstraints() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_NameContraints() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_SubjectIdentifierConstraints() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_SubjectLocatorConstraints() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_Kind() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_Overlap() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_Name() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicType_Locators() {
		return (EAttribute)topicTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicType_TopicReifiesConstraints() {
		return (EReference)topicTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractRegExpConstraint() {
		return abstractRegExpConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractRegExpConstraint_Regexp() {
		return (EAttribute)abstractRegExpConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOccurrenceTypeConstraint() {
		return occurrenceTypeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNameTypeConstraint() {
		return nameTypeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRolePlayerConstraint() {
		return rolePlayerConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRolePlayerConstraint_Player() {
		return (EReference)rolePlayerConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRolePlayerConstraint_Role() {
		return (EReference)rolePlayerConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTopicMapSchema() {
		return topicMapSchemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicMapSchema_TopicTypes() {
		return (EReference)topicMapSchemaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicMapSchema_AssociationTypeConstraints() {
		return (EReference)topicMapSchemaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTopicMapSchema_Mappings() {
		return (EReference)topicMapSchemaEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicMapSchema_Includes() {
		return (EAttribute)topicMapSchemaEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicMapSchema_BaseLocator() {
		return (EAttribute)topicMapSchemaEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTopicMapSchema_Name() {
		return (EAttribute)topicMapSchemaEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubjectLocatorConstraint() {
		return subjectLocatorConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubjectIdentifierConstraint() {
		return subjectIdentifierConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociationTypeConstraint() {
		return associationTypeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociationTypeConstraint_PlayerConstraints() {
		return (EReference)associationTypeConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMappingElement() {
		return mappingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingElement_Key() {
		return (EAttribute)mappingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingElement_Value() {
		return (EAttribute)mappingElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_PosX() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_PosY() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeNode() {
		return typeNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeNode_TopicType() {
		return (EReference)typeNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTypeNode_Image() {
		return (EAttribute)typeNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBendpoint() {
		return bendpointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBendpoint_PosX() {
		return (EAttribute)bendpointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBendpoint_PosY() {
		return (EAttribute)bendpointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdge() {
		return edgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Bendpoints() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Source() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Target() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdge_Type() {
		return (EAttribute)edgeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_RoleConstraint() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_LabelPositions() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociationNode() {
		return associationNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociationNode_AssociationConstraint() {
		return (EReference)associationNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDiagram() {
		return diagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Edges() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Nodes() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDiagram_Name() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDiagram_Comments() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFile() {
		return fileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFile_Diagrams() {
		return (EReference)fileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFile_TopicMapSchema() {
		return (EReference)fileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFile_Filename() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFile_Dirty() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopeConstraint() {
		return scopeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractCardinalityContraint() {
		return abstractCardinalityContraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCardinalityContraint_CardMin() {
		return (EAttribute)abstractCardinalityContraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractCardinalityContraint_CardMax() {
		return (EAttribute)abstractCardinalityContraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelPos() {
		return labelPosEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelPos_PosX() {
		return (EAttribute)labelPosEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelPos_PosY() {
		return (EAttribute)labelPosEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypedConstraint() {
		return abstractTypedConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypedConstraint_Type() {
		return (EReference)abstractTypedConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopedTopicType() {
		return scopedTopicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScopedTopicType_Scope() {
		return (EReference)scopedTopicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociationType() {
		return associationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociationType_Roles() {
		return (EReference)associationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociationType_RoleCombinations() {
		return (EReference)associationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOccurrenceType() {
		return occurrenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOccurrenceType_DataType() {
		return (EAttribute)occurrenceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleConstraint() {
		return roleConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleType() {
		return roleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoleCombinationConstraint() {
		return roleCombinationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleCombinationConstraint_Player() {
		return (EReference)roleCombinationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleCombinationConstraint_OtherPlayer() {
		return (EReference)roleCombinationConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleCombinationConstraint_OtherRole() {
		return (EReference)roleCombinationConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoleCombinationConstraint_Role() {
		return (EReference)roleCombinationConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNameType() {
		return nameTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypedCardinalityConstraint() {
		return abstractTypedCardinalityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Content() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Width() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Height() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMCLConstruct() {
		return tmclConstructEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMCLConstruct_See_also() {
		return (EAttribute)tmclConstructEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMCLConstruct_Comment() {
		return (EAttribute)tmclConstructEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMCLConstruct_Description() {
		return (EAttribute)tmclConstructEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMCLConstruct_Annotations() {
		return (EReference)tmclConstructEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReifierConstraint() {
		return reifierConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReifiableTopicType() {
		return reifiableTopicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReifiableTopicType_ReifierConstraint() {
		return (EReference)reifiableTopicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopedReifiableTopicType() {
		return scopedReifiableTopicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnnotation_Key() {
		return (EAttribute)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnnotation_Value() {
		return (EAttribute)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractRegExpTopicType() {
		return abstractRegExpTopicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractRegExpTopicType_RegExp() {
		return (EAttribute)abstractRegExpTopicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractConstraint() {
		return abstractConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTopicReifiesConstraint() {
		return topicReifiesConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainDiagram() {
		return domainDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOnoObject() {
		return onoObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOnoObject_Id() {
		return (EAttribute)onoObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractUniqueValueTopicType() {
		return abstractUniqueValueTopicTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractUniqueValueTopicType_Unique() {
		return (EAttribute)abstractUniqueValueTopicTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTopicId() {
		return topicIdEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEdgeType() {
		return edgeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getKindOfTopicType() {
		return kindOfTopicTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		topicTypeEClass = createEClass(TOPIC_TYPE);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__IDENTIFIERS);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__ID_TYPE);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__ABSTRACT);
		createEReference(topicTypeEClass, TOPIC_TYPE__ISA);
		createEReference(topicTypeEClass, TOPIC_TYPE__AKO);
		createEReference(topicTypeEClass, TOPIC_TYPE__OCCURRENCE_CONSTRAINTS);
		createEReference(topicTypeEClass, TOPIC_TYPE__NAME_CONTRAINTS);
		createEReference(topicTypeEClass, TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS);
		createEReference(topicTypeEClass, TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__KIND);
		createEReference(topicTypeEClass, TOPIC_TYPE__OVERLAP);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__NAME);
		createEAttribute(topicTypeEClass, TOPIC_TYPE__LOCATORS);
		createEReference(topicTypeEClass, TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS);

		abstractRegExpConstraintEClass = createEClass(ABSTRACT_REG_EXP_CONSTRAINT);
		createEAttribute(abstractRegExpConstraintEClass, ABSTRACT_REG_EXP_CONSTRAINT__REGEXP);

		occurrenceTypeConstraintEClass = createEClass(OCCURRENCE_TYPE_CONSTRAINT);

		nameTypeConstraintEClass = createEClass(NAME_TYPE_CONSTRAINT);

		rolePlayerConstraintEClass = createEClass(ROLE_PLAYER_CONSTRAINT);
		createEReference(rolePlayerConstraintEClass, ROLE_PLAYER_CONSTRAINT__PLAYER);
		createEReference(rolePlayerConstraintEClass, ROLE_PLAYER_CONSTRAINT__ROLE);

		topicMapSchemaEClass = createEClass(TOPIC_MAP_SCHEMA);
		createEReference(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__TOPIC_TYPES);
		createEReference(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS);
		createEReference(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__MAPPINGS);
		createEAttribute(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__INCLUDES);
		createEAttribute(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__BASE_LOCATOR);
		createEAttribute(topicMapSchemaEClass, TOPIC_MAP_SCHEMA__NAME);

		subjectLocatorConstraintEClass = createEClass(SUBJECT_LOCATOR_CONSTRAINT);

		subjectIdentifierConstraintEClass = createEClass(SUBJECT_IDENTIFIER_CONSTRAINT);

		associationTypeConstraintEClass = createEClass(ASSOCIATION_TYPE_CONSTRAINT);
		createEReference(associationTypeConstraintEClass, ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS);

		mappingElementEClass = createEClass(MAPPING_ELEMENT);
		createEAttribute(mappingElementEClass, MAPPING_ELEMENT__KEY);
		createEAttribute(mappingElementEClass, MAPPING_ELEMENT__VALUE);

		nodeEClass = createEClass(NODE);
		createEAttribute(nodeEClass, NODE__POS_X);
		createEAttribute(nodeEClass, NODE__POS_Y);

		typeNodeEClass = createEClass(TYPE_NODE);
		createEReference(typeNodeEClass, TYPE_NODE__TOPIC_TYPE);
		createEAttribute(typeNodeEClass, TYPE_NODE__IMAGE);

		bendpointEClass = createEClass(BENDPOINT);
		createEAttribute(bendpointEClass, BENDPOINT__POS_X);
		createEAttribute(bendpointEClass, BENDPOINT__POS_Y);

		edgeEClass = createEClass(EDGE);
		createEReference(edgeEClass, EDGE__BENDPOINTS);
		createEReference(edgeEClass, EDGE__SOURCE);
		createEReference(edgeEClass, EDGE__TARGET);
		createEAttribute(edgeEClass, EDGE__TYPE);
		createEReference(edgeEClass, EDGE__ROLE_CONSTRAINT);
		createEReference(edgeEClass, EDGE__LABEL_POSITIONS);

		associationNodeEClass = createEClass(ASSOCIATION_NODE);
		createEReference(associationNodeEClass, ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT);

		diagramEClass = createEClass(DIAGRAM);
		createEReference(diagramEClass, DIAGRAM__EDGES);
		createEReference(diagramEClass, DIAGRAM__NODES);
		createEAttribute(diagramEClass, DIAGRAM__NAME);
		createEReference(diagramEClass, DIAGRAM__COMMENTS);

		fileEClass = createEClass(FILE);
		createEReference(fileEClass, FILE__DIAGRAMS);
		createEReference(fileEClass, FILE__TOPIC_MAP_SCHEMA);
		createEAttribute(fileEClass, FILE__FILENAME);
		createEAttribute(fileEClass, FILE__DIRTY);

		scopeConstraintEClass = createEClass(SCOPE_CONSTRAINT);

		abstractCardinalityContraintEClass = createEClass(ABSTRACT_CARDINALITY_CONTRAINT);
		createEAttribute(abstractCardinalityContraintEClass, ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN);
		createEAttribute(abstractCardinalityContraintEClass, ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX);

		labelPosEClass = createEClass(LABEL_POS);
		createEAttribute(labelPosEClass, LABEL_POS__POS_X);
		createEAttribute(labelPosEClass, LABEL_POS__POS_Y);

		abstractTypedConstraintEClass = createEClass(ABSTRACT_TYPED_CONSTRAINT);
		createEReference(abstractTypedConstraintEClass, ABSTRACT_TYPED_CONSTRAINT__TYPE);

		scopedTopicTypeEClass = createEClass(SCOPED_TOPIC_TYPE);
		createEReference(scopedTopicTypeEClass, SCOPED_TOPIC_TYPE__SCOPE);

		associationTypeEClass = createEClass(ASSOCIATION_TYPE);
		createEReference(associationTypeEClass, ASSOCIATION_TYPE__ROLES);
		createEReference(associationTypeEClass, ASSOCIATION_TYPE__ROLE_COMBINATIONS);

		occurrenceTypeEClass = createEClass(OCCURRENCE_TYPE);
		createEAttribute(occurrenceTypeEClass, OCCURRENCE_TYPE__DATA_TYPE);

		roleConstraintEClass = createEClass(ROLE_CONSTRAINT);

		roleTypeEClass = createEClass(ROLE_TYPE);

		roleCombinationConstraintEClass = createEClass(ROLE_COMBINATION_CONSTRAINT);
		createEReference(roleCombinationConstraintEClass, ROLE_COMBINATION_CONSTRAINT__PLAYER);
		createEReference(roleCombinationConstraintEClass, ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER);
		createEReference(roleCombinationConstraintEClass, ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE);
		createEReference(roleCombinationConstraintEClass, ROLE_COMBINATION_CONSTRAINT__ROLE);

		nameTypeEClass = createEClass(NAME_TYPE);

		abstractTypedCardinalityConstraintEClass = createEClass(ABSTRACT_TYPED_CARDINALITY_CONSTRAINT);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__CONTENT);
		createEAttribute(commentEClass, COMMENT__WIDTH);
		createEAttribute(commentEClass, COMMENT__HEIGHT);

		tmclConstructEClass = createEClass(TMCL_CONSTRUCT);
		createEAttribute(tmclConstructEClass, TMCL_CONSTRUCT__SEE_ALSO);
		createEAttribute(tmclConstructEClass, TMCL_CONSTRUCT__COMMENT);
		createEAttribute(tmclConstructEClass, TMCL_CONSTRUCT__DESCRIPTION);
		createEReference(tmclConstructEClass, TMCL_CONSTRUCT__ANNOTATIONS);

		reifierConstraintEClass = createEClass(REIFIER_CONSTRAINT);

		reifiableTopicTypeEClass = createEClass(REIFIABLE_TOPIC_TYPE);
		createEReference(reifiableTopicTypeEClass, REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT);

		scopedReifiableTopicTypeEClass = createEClass(SCOPED_REIFIABLE_TOPIC_TYPE);

		annotationEClass = createEClass(ANNOTATION);
		createEAttribute(annotationEClass, ANNOTATION__KEY);
		createEAttribute(annotationEClass, ANNOTATION__VALUE);

		abstractRegExpTopicTypeEClass = createEClass(ABSTRACT_REG_EXP_TOPIC_TYPE);
		createEAttribute(abstractRegExpTopicTypeEClass, ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP);

		abstractConstraintEClass = createEClass(ABSTRACT_CONSTRAINT);

		topicReifiesConstraintEClass = createEClass(TOPIC_REIFIES_CONSTRAINT);

		domainDiagramEClass = createEClass(DOMAIN_DIAGRAM);

		onoObjectEClass = createEClass(ONO_OBJECT);
		createEAttribute(onoObjectEClass, ONO_OBJECT__ID);

		abstractUniqueValueTopicTypeEClass = createEClass(ABSTRACT_UNIQUE_VALUE_TOPIC_TYPE);
		createEAttribute(abstractUniqueValueTopicTypeEClass, ABSTRACT_UNIQUE_VALUE_TOPIC_TYPE__UNIQUE);

		// Create enums
		topicIdEEnum = createEEnum(TOPIC_ID);
		edgeTypeEEnum = createEEnum(EDGE_TYPE);
		kindOfTopicTypeEEnum = createEEnum(KIND_OF_TOPIC_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		topicTypeEClass.getESuperTypes().add(this.getTMCLConstruct());
		abstractRegExpConstraintEClass.getESuperTypes().add(this.getAbstractConstraint());
		occurrenceTypeConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		nameTypeConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		rolePlayerConstraintEClass.getESuperTypes().add(this.getAbstractCardinalityContraint());
		topicMapSchemaEClass.getESuperTypes().add(this.getTMCLConstruct());
		subjectLocatorConstraintEClass.getESuperTypes().add(this.getAbstractRegExpConstraint());
		subjectLocatorConstraintEClass.getESuperTypes().add(this.getAbstractCardinalityContraint());
		subjectIdentifierConstraintEClass.getESuperTypes().add(this.getAbstractRegExpConstraint());
		subjectIdentifierConstraintEClass.getESuperTypes().add(this.getAbstractCardinalityContraint());
		associationTypeConstraintEClass.getESuperTypes().add(this.getAbstractTypedConstraint());
		mappingElementEClass.getESuperTypes().add(this.getOnoObject());
		nodeEClass.getESuperTypes().add(this.getOnoObject());
		typeNodeEClass.getESuperTypes().add(this.getNode());
		bendpointEClass.getESuperTypes().add(this.getOnoObject());
		edgeEClass.getESuperTypes().add(this.getOnoObject());
		associationNodeEClass.getESuperTypes().add(this.getNode());
		diagramEClass.getESuperTypes().add(this.getOnoObject());
		fileEClass.getESuperTypes().add(this.getOnoObject());
		scopeConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		abstractCardinalityContraintEClass.getESuperTypes().add(this.getAbstractConstraint());
		labelPosEClass.getESuperTypes().add(this.getOnoObject());
		abstractTypedConstraintEClass.getESuperTypes().add(this.getAbstractConstraint());
		scopedTopicTypeEClass.getESuperTypes().add(this.getTopicType());
		associationTypeEClass.getESuperTypes().add(this.getScopedTopicType());
		associationTypeEClass.getESuperTypes().add(this.getScopedReifiableTopicType());
		occurrenceTypeEClass.getESuperTypes().add(this.getScopedTopicType());
		occurrenceTypeEClass.getESuperTypes().add(this.getScopedReifiableTopicType());
		occurrenceTypeEClass.getESuperTypes().add(this.getAbstractRegExpTopicType());
		occurrenceTypeEClass.getESuperTypes().add(this.getAbstractUniqueValueTopicType());
		roleConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		roleTypeEClass.getESuperTypes().add(this.getTopicType());
		roleCombinationConstraintEClass.getESuperTypes().add(this.getAbstractConstraint());
		nameTypeEClass.getESuperTypes().add(this.getScopedTopicType());
		nameTypeEClass.getESuperTypes().add(this.getScopedReifiableTopicType());
		nameTypeEClass.getESuperTypes().add(this.getAbstractRegExpTopicType());
		nameTypeEClass.getESuperTypes().add(this.getAbstractUniqueValueTopicType());
		abstractTypedCardinalityConstraintEClass.getESuperTypes().add(this.getAbstractCardinalityContraint());
		abstractTypedCardinalityConstraintEClass.getESuperTypes().add(this.getAbstractTypedConstraint());
		commentEClass.getESuperTypes().add(this.getNode());
		tmclConstructEClass.getESuperTypes().add(this.getOnoObject());
		reifierConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		reifiableTopicTypeEClass.getESuperTypes().add(this.getTopicType());
		scopedReifiableTopicTypeEClass.getESuperTypes().add(this.getScopedTopicType());
		scopedReifiableTopicTypeEClass.getESuperTypes().add(this.getReifiableTopicType());
		annotationEClass.getESuperTypes().add(this.getOnoObject());
		abstractRegExpTopicTypeEClass.getESuperTypes().add(this.getTopicType());
		abstractConstraintEClass.getESuperTypes().add(this.getTMCLConstruct());
		topicReifiesConstraintEClass.getESuperTypes().add(this.getAbstractTypedCardinalityConstraint());
		domainDiagramEClass.getESuperTypes().add(this.getDiagram());
		abstractUniqueValueTopicTypeEClass.getESuperTypes().add(this.getTopicType());

		// Initialize classes and features; add operations and parameters
		initEClass(topicTypeEClass, TopicType.class, "TopicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTopicType_Identifiers(), ecorePackage.getEString(), "identifiers", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicType_IdType(), this.getTopicId(), "idType", "IDENTIFIER", 1, 1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicType_Abstract(), ecorePackage.getEBoolean(), "abstract", "false", 1, 1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_Isa(), this.getTopicType(), null, "isa", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_Ako(), this.getTopicType(), null, "ako", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_OccurrenceConstraints(), this.getOccurrenceTypeConstraint(), null, "occurrenceConstraints", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_NameContraints(), this.getNameTypeConstraint(), null, "nameContraints", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_SubjectIdentifierConstraints(), this.getSubjectIdentifierConstraint(), null, "subjectIdentifierConstraints", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_SubjectLocatorConstraints(), this.getSubjectLocatorConstraint(), null, "subjectLocatorConstraints", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicType_Kind(), this.getKindOfTopicType(), "kind", "TopicType", 1, 1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_Overlap(), this.getTopicType(), null, "overlap", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicType_Name(), ecorePackage.getEString(), "name", null, 1, 1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicType_Locators(), ecorePackage.getEString(), "locators", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicType_TopicReifiesConstraints(), this.getTopicReifiesConstraint(), null, "topicReifiesConstraints", null, 0, -1, TopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractRegExpConstraintEClass, AbstractRegExpConstraint.class, "AbstractRegExpConstraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractRegExpConstraint_Regexp(), ecorePackage.getEString(), "regexp", ".*", 1, 1, AbstractRegExpConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(occurrenceTypeConstraintEClass, OccurrenceTypeConstraint.class, "OccurrenceTypeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nameTypeConstraintEClass, NameTypeConstraint.class, "NameTypeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rolePlayerConstraintEClass, RolePlayerConstraint.class, "RolePlayerConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRolePlayerConstraint_Player(), this.getTopicType(), null, "player", null, 1, 1, RolePlayerConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRolePlayerConstraint_Role(), this.getRoleConstraint(), null, "role", null, 1, 1, RolePlayerConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(topicMapSchemaEClass, TopicMapSchema.class, "TopicMapSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTopicMapSchema_TopicTypes(), this.getTopicType(), null, "topicTypes", null, 0, -1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicMapSchema_AssociationTypeConstraints(), this.getAssociationTypeConstraint(), null, "associationTypeConstraints", null, 0, -1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTopicMapSchema_Mappings(), this.getMappingElement(), null, "mappings", null, 0, -1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicMapSchema_Includes(), ecorePackage.getEString(), "includes", null, 0, -1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicMapSchema_BaseLocator(), ecorePackage.getEString(), "baseLocator", "", 1, 1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTopicMapSchema_Name(), ecorePackage.getEString(), "name", null, 0, 1, TopicMapSchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subjectLocatorConstraintEClass, SubjectLocatorConstraint.class, "SubjectLocatorConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(subjectIdentifierConstraintEClass, SubjectIdentifierConstraint.class, "SubjectIdentifierConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(associationTypeConstraintEClass, AssociationTypeConstraint.class, "AssociationTypeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociationTypeConstraint_PlayerConstraints(), this.getRolePlayerConstraint(), null, "playerConstraints", null, 1, -1, AssociationTypeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingElementEClass, MappingElement.class, "MappingElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMappingElement_Key(), ecorePackage.getEString(), "key", null, 1, 1, MappingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMappingElement_Value(), ecorePackage.getEString(), "value", null, 1, 1, MappingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNode_PosX(), ecorePackage.getEInt(), "posX", null, 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_PosY(), ecorePackage.getEInt(), "posY", null, 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeNodeEClass, TypeNode.class, "TypeNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeNode_TopicType(), this.getTopicType(), null, "topicType", null, 1, 1, TypeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypeNode_Image(), ecorePackage.getEString(), "image", null, 0, 1, TypeNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bendpointEClass, Bendpoint.class, "Bendpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBendpoint_PosX(), ecorePackage.getEInt(), "posX", null, 1, 1, Bendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBendpoint_PosY(), ecorePackage.getEInt(), "posY", null, 1, 1, Bendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdge_Bendpoints(), this.getBendpoint(), null, "bendpoints", null, 0, -1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Source(), this.getNode(), null, "source", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Target(), this.getNode(), null, "target", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdge_Type(), this.getEdgeType(), "type", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_RoleConstraint(), this.getRolePlayerConstraint(), null, "roleConstraint", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_LabelPositions(), this.getLabelPos(), null, "labelPositions", null, 0, 2, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(associationNodeEClass, AssociationNode.class, "AssociationNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociationNode_AssociationConstraint(), this.getAssociationTypeConstraint(), null, "associationConstraint", null, 1, 1, AssociationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagram_Edges(), this.getEdge(), null, "edges", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagram_Nodes(), this.getNode(), null, "nodes", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagram_Name(), ecorePackage.getEString(), "name", null, 1, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagram_Comments(), this.getComment(), null, "comments", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFile_Diagrams(), this.getDiagram(), null, "diagrams", null, 0, -1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFile_TopicMapSchema(), this.getTopicMapSchema(), null, "topicMapSchema", null, 1, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_Filename(), ecorePackage.getEString(), "filename", null, 0, 1, File.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_Dirty(), ecorePackage.getEBoolean(), "dirty", "false", 1, 1, File.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeConstraintEClass, ScopeConstraint.class, "ScopeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractCardinalityContraintEClass, AbstractCardinalityContraint.class, "AbstractCardinalityContraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractCardinalityContraint_CardMin(), ecorePackage.getEString(), "cardMin", "0", 0, 1, AbstractCardinalityContraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractCardinalityContraint_CardMax(), ecorePackage.getEString(), "cardMax", "*", 0, 1, AbstractCardinalityContraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelPosEClass, LabelPos.class, "LabelPos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabelPos_PosX(), ecorePackage.getEInt(), "posX", "0", 1, 1, LabelPos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelPos_PosY(), ecorePackage.getEInt(), "posY", "0", 1, 1, LabelPos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractTypedConstraintEClass, AbstractTypedConstraint.class, "AbstractTypedConstraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractTypedConstraint_Type(), this.getTopicType(), null, "type", null, 1, 1, AbstractTypedConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopedTopicTypeEClass, ScopedTopicType.class, "ScopedTopicType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopedTopicType_Scope(), this.getScopeConstraint(), null, "scope", null, 0, -1, ScopedTopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(associationTypeEClass, AssociationType.class, "AssociationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociationType_Roles(), this.getRoleConstraint(), null, "roles", null, 1, -1, AssociationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociationType_RoleCombinations(), this.getRoleCombinationConstraint(), null, "roleCombinations", null, 0, -1, AssociationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(occurrenceTypeEClass, OccurrenceType.class, "OccurrenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOccurrenceType_DataType(), ecorePackage.getEString(), "dataType", "xsd:anyType", 1, 1, OccurrenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleConstraintEClass, RoleConstraint.class, "RoleConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roleTypeEClass, RoleType.class, "RoleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roleCombinationConstraintEClass, RoleCombinationConstraint.class, "RoleCombinationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoleCombinationConstraint_Player(), this.getTopicType(), null, "player", null, 1, 1, RoleCombinationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoleCombinationConstraint_OtherPlayer(), this.getTopicType(), null, "otherPlayer", null, 1, 1, RoleCombinationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoleCombinationConstraint_OtherRole(), this.getRoleType(), null, "otherRole", null, 1, 1, RoleCombinationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoleCombinationConstraint_Role(), this.getRoleType(), null, "role", null, 1, 1, RoleCombinationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameTypeEClass, NameType.class, "NameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractTypedCardinalityConstraintEClass, AbstractTypedCardinalityConstraint.class, "AbstractTypedCardinalityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Content(), ecorePackage.getEString(), "content", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Width(), ecorePackage.getEInt(), "width", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Height(), ecorePackage.getEInt(), "height", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tmclConstructEClass, TMCLConstruct.class, "TMCLConstruct", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTMCLConstruct_See_also(), ecorePackage.getEString(), "see_also", null, 0, 1, TMCLConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTMCLConstruct_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, TMCLConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTMCLConstruct_Description(), ecorePackage.getEString(), "description", null, 0, 1, TMCLConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTMCLConstruct_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, TMCLConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reifierConstraintEClass, ReifierConstraint.class, "ReifierConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(reifiableTopicTypeEClass, ReifiableTopicType.class, "ReifiableTopicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReifiableTopicType_ReifierConstraint(), this.getReifierConstraint(), null, "reifierConstraint", null, 0, 1, ReifiableTopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopedReifiableTopicTypeEClass, ScopedReifiableTopicType.class, "ScopedReifiableTopicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnnotation_Key(), ecorePackage.getEString(), "key", null, 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAnnotation_Value(), ecorePackage.getEString(), "value", null, 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractRegExpTopicTypeEClass, AbstractRegExpTopicType.class, "AbstractRegExpTopicType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractRegExpTopicType_RegExp(), ecorePackage.getEString(), "regExp", ".*", 1, 1, AbstractRegExpTopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractConstraintEClass, AbstractConstraint.class, "AbstractConstraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(topicReifiesConstraintEClass, TopicReifiesConstraint.class, "TopicReifiesConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(domainDiagramEClass, DomainDiagram.class, "DomainDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(onoObjectEClass, OnoObject.class, "OnoObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOnoObject_Id(), ecorePackage.getEInt(), "id", null, 1, 1, OnoObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractUniqueValueTopicTypeEClass, AbstractUniqueValueTopicType.class, "AbstractUniqueValueTopicType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractUniqueValueTopicType_Unique(), ecorePackage.getEBoolean(), "unique", "false", 0, 1, AbstractUniqueValueTopicType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(topicIdEEnum, TopicId.class, "TopicId");
		addEEnumLiteral(topicIdEEnum, TopicId.SUBJECT_LOCATOR);
		addEEnumLiteral(topicIdEEnum, TopicId.SUBJECT_IDENTIFIER);
		addEEnumLiteral(topicIdEEnum, TopicId.ITEM_IDENTIFIER);
		addEEnumLiteral(topicIdEEnum, TopicId.IDENTIFIER);

		initEEnum(edgeTypeEEnum, EdgeType.class, "EdgeType");
		addEEnumLiteral(edgeTypeEEnum, EdgeType.IS_ATYPE);
		addEEnumLiteral(edgeTypeEEnum, EdgeType.AKO_TYPE);
		addEEnumLiteral(edgeTypeEEnum, EdgeType.ROLE_CONSTRAINT_TYPE);

		initEEnum(kindOfTopicTypeEEnum, KindOfTopicType.class, "KindOfTopicType");
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.TOPIC_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.OCCURRENCE_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.NAME_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.ROLE_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.ASSOCIATION_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.SCOPE_TYPE);
		addEEnumLiteral(kindOfTopicTypeEEnum, KindOfTopicType.NO_TYPE);

		// Create resource
		createResource(eNS_URI);
	}

} //ModelPackageImpl
