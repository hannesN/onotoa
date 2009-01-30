/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.util;

import de.topicmapslab.tmcledit.model.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.TOPIC_TYPE: {
				TopicType topicType = (TopicType)theEObject;
				T result = caseTopicType(topicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_CONSTRAINT: {
				AbstractConstraint abstractConstraint = (AbstractConstraint)theEObject;
				T result = caseAbstractConstraint(abstractConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(abstractConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OCCURENCE_TYPE_CONSTRAINT: {
				OccurenceTypeConstraint occurenceTypeConstraint = (OccurenceTypeConstraint)theEObject;
				T result = caseOccurenceTypeConstraint(occurenceTypeConstraint);
				if (result == null) result = caseAbstractConstraint(occurenceTypeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(occurenceTypeConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(occurenceTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(occurenceTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NAME_TYPE_CONSTRAINT: {
				NameTypeConstraint nameTypeConstraint = (NameTypeConstraint)theEObject;
				T result = caseNameTypeConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(nameTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(nameTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_PLAYER_CONSTRAINTS: {
				RolePlayerConstraints rolePlayerConstraints = (RolePlayerConstraints)theEObject;
				T result = caseRolePlayerConstraints(rolePlayerConstraints);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(rolePlayerConstraints);
				if (result == null) result = caseAbstractCardinalityContraint(rolePlayerConstraints);
				if (result == null) result = caseAbstractTypedConstraint(rolePlayerConstraints);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TOPIC_MAP_SCHEMA: {
				TopicMapSchema topicMapSchema = (TopicMapSchema)theEObject;
				T result = caseTopicMapSchema(topicMapSchema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUBJECT_LOCATOR_CONSTRAINT: {
				SubjectLocatorConstraint subjectLocatorConstraint = (SubjectLocatorConstraint)theEObject;
				T result = caseSubjectLocatorConstraint(subjectLocatorConstraint);
				if (result == null) result = caseAbstractConstraint(subjectLocatorConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(subjectLocatorConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT: {
				SubjectIdentifierConstraint subjectIdentifierConstraint = (SubjectIdentifierConstraint)theEObject;
				T result = caseSubjectIdentifierConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseAbstractConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(subjectIdentifierConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT: {
				AssociationTypeConstraint associationTypeConstraint = (AssociationTypeConstraint)theEObject;
				T result = caseAssociationTypeConstraint(associationTypeConstraint);
				if (result == null) result = caseAbstractConstraint(associationTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(associationTypeConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(associationTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.MAPPING_ELEMENT: {
				MappingElement mappingElement = (MappingElement)theEObject;
				T result = caseMappingElement(mappingElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TYPE_NODE: {
				TypeNode typeNode = (TypeNode)theEObject;
				T result = caseTypeNode(typeNode);
				if (result == null) result = caseNode(typeNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.BENDPOINTS: {
				Bendpoints bendpoints = (Bendpoints)theEObject;
				T result = caseBendpoints(bendpoints);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EDGE: {
				Edge edge = (Edge)theEObject;
				T result = caseEdge(edge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_NODE: {
				AssociationNode associationNode = (AssociationNode)theEObject;
				T result = caseAssociationNode(associationNode);
				if (result == null) result = caseNode(associationNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DIAGRAM: {
				Diagram diagram = (Diagram)theEObject;
				T result = caseDiagram(diagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.FILE: {
				File file = (File)theEObject;
				T result = caseFile(file);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPE_CONSTRAINT: {
				ScopeConstraint scopeConstraint = (ScopeConstraint)theEObject;
				T result = caseScopeConstraint(scopeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(scopeConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(scopeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(scopeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT: {
				AbstractCardinalityContraint abstractCardinalityContraint = (AbstractCardinalityContraint)theEObject;
				T result = caseAbstractCardinalityContraint(abstractCardinalityContraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LABEL_POS: {
				LabelPos labelPos = (LabelPos)theEObject;
				T result = caseLabelPos(labelPos);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_TYPED_CONSTRAINT: {
				AbstractTypedConstraint abstractTypedConstraint = (AbstractTypedConstraint)theEObject;
				T result = caseAbstractTypedConstraint(abstractTypedConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPED_TOPIC_TYPE: {
				ScopedTopicType scopedTopicType = (ScopedTopicType)theEObject;
				T result = caseScopedTopicType(scopedTopicType);
				if (result == null) result = caseTopicType(scopedTopicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_TYPE: {
				AssociationType associationType = (AssociationType)theEObject;
				T result = caseAssociationType(associationType);
				if (result == null) result = caseScopedTopicType(associationType);
				if (result == null) result = caseTopicType(associationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OCCURENCE_TYPE: {
				OccurenceType occurenceType = (OccurenceType)theEObject;
				T result = caseOccurenceType(occurenceType);
				if (result == null) result = caseScopedTopicType(occurenceType);
				if (result == null) result = caseTopicType(occurenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_CONSTRAINTS: {
				RoleConstraints roleConstraints = (RoleConstraints)theEObject;
				T result = caseRoleConstraints(roleConstraints);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(roleConstraints);
				if (result == null) result = caseAbstractCardinalityContraint(roleConstraints);
				if (result == null) result = caseAbstractTypedConstraint(roleConstraints);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_TYPE: {
				RoleType roleType = (RoleType)theEObject;
				T result = caseRoleType(roleType);
				if (result == null) result = caseTopicType(roleType);
				if (result == null) result = caseOtherRolePlayerConstraint(roleType);
				if (result == null) result = caseAbstractCardinalityContraint(roleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT: {
				OtherRolePlayerConstraint otherRolePlayerConstraint = (OtherRolePlayerConstraint)theEObject;
				T result = caseOtherRolePlayerConstraint(otherRolePlayerConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(otherRolePlayerConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NAME_TYPE: {
				NameType nameType = (NameType)theEObject;
				T result = caseNameType(nameType);
				if (result == null) result = caseScopedTopicType(nameType);
				if (result == null) result = caseTopicType(nameType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_TYPED_CARDINALITY_CONSTRAINT: {
				AbstractTypedCardinalityConstraint abstractTypedCardinalityConstraint = (AbstractTypedCardinalityConstraint)theEObject;
				T result = caseAbstractTypedCardinalityConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseAbstractCardinalityContraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseAbstractTypedConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTopicType(TopicType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractConstraint(AbstractConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Occurence Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Occurence Type Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOccurenceTypeConstraint(OccurenceTypeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Name Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Name Type Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameTypeConstraint(NameTypeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Player Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Player Constraints</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRolePlayerConstraints(RolePlayerConstraints object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Topic Map Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Topic Map Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTopicMapSchema(TopicMapSchema object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject Locator Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject Locator Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubjectLocatorConstraint(SubjectLocatorConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject Identifier Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject Identifier Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubjectIdentifierConstraint(SubjectIdentifierConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association Type Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociationTypeConstraint(AssociationTypeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mapping Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mapping Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappingElement(MappingElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNode(Node object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeNode(TypeNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bendpoints</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bendpoints</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBendpoints(Bendpoints object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdge(Edge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociationNode(AssociationNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiagram(Diagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFile(File object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scope Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopeConstraint(ScopeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Cardinality Contraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Cardinality Contraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractCardinalityContraint(AbstractCardinalityContraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Pos</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Pos</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelPos(LabelPos object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Typed Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Typed Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractTypedConstraint(AbstractTypedConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scoped Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scoped Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopedTopicType(ScopedTopicType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociationType(AssociationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Occurence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Occurence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOccurenceType(OccurenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Constraints</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleConstraints(RoleConstraints object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleType(RoleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Other Role Player Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Other Role Player Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOtherRolePlayerConstraint(OtherRolePlayerConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Name Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameType(NameType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Typed Cardinality Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Typed Cardinality Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractTypedCardinalityConstraint(AbstractTypedCardinalityConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
