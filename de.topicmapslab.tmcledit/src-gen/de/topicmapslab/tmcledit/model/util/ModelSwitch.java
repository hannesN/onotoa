/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
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
				if (result == null) result = caseTMCLConstruct(topicType);
				if (result == null) result = caseOnoObject(topicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT: {
				AbstractRegExpConstraint abstractRegExpConstraint = (AbstractRegExpConstraint)theEObject;
				T result = caseAbstractRegExpConstraint(abstractRegExpConstraint);
				if (result == null) result = caseAbstractConstraint(abstractRegExpConstraint);
				if (result == null) result = caseTMCLConstruct(abstractRegExpConstraint);
				if (result == null) result = caseOnoObject(abstractRegExpConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OCCURRENCE_TYPE_CONSTRAINT: {
				OccurrenceTypeConstraint occurrenceTypeConstraint = (OccurrenceTypeConstraint)theEObject;
				T result = caseOccurrenceTypeConstraint(occurrenceTypeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(occurrenceTypeConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(occurrenceTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(occurrenceTypeConstraint);
				if (result == null) result = caseAbstractConstraint(occurrenceTypeConstraint);
				if (result == null) result = caseTMCLConstruct(occurrenceTypeConstraint);
				if (result == null) result = caseOnoObject(occurrenceTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NAME_TYPE_CONSTRAINT: {
				NameTypeConstraint nameTypeConstraint = (NameTypeConstraint)theEObject;
				T result = caseNameTypeConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(nameTypeConstraint);
				if (result == null) result = caseAbstractConstraint(nameTypeConstraint);
				if (result == null) result = caseTMCLConstruct(nameTypeConstraint);
				if (result == null) result = caseOnoObject(nameTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_PLAYER_CONSTRAINT: {
				RolePlayerConstraint rolePlayerConstraint = (RolePlayerConstraint)theEObject;
				T result = caseRolePlayerConstraint(rolePlayerConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(rolePlayerConstraint);
				if (result == null) result = caseAbstractConstraint(rolePlayerConstraint);
				if (result == null) result = caseTMCLConstruct(rolePlayerConstraint);
				if (result == null) result = caseOnoObject(rolePlayerConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TOPIC_MAP_SCHEMA: {
				TopicMapSchema topicMapSchema = (TopicMapSchema)theEObject;
				T result = caseTopicMapSchema(topicMapSchema);
				if (result == null) result = caseTMCLConstruct(topicMapSchema);
				if (result == null) result = caseOnoObject(topicMapSchema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUBJECT_LOCATOR_CONSTRAINT: {
				SubjectLocatorConstraint subjectLocatorConstraint = (SubjectLocatorConstraint)theEObject;
				T result = caseSubjectLocatorConstraint(subjectLocatorConstraint);
				if (result == null) result = caseAbstractRegExpConstraint(subjectLocatorConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(subjectLocatorConstraint);
				if (result == null) result = caseAbstractConstraint(subjectLocatorConstraint);
				if (result == null) result = caseTMCLConstruct(subjectLocatorConstraint);
				if (result == null) result = caseOnoObject(subjectLocatorConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT: {
				SubjectIdentifierConstraint subjectIdentifierConstraint = (SubjectIdentifierConstraint)theEObject;
				T result = caseSubjectIdentifierConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseAbstractRegExpConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseAbstractConstraint(subjectIdentifierConstraint);
				if (result == null) result = caseTMCLConstruct(subjectIdentifierConstraint);
				if (result == null) result = caseOnoObject(subjectIdentifierConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT: {
				AssociationTypeConstraint associationTypeConstraint = (AssociationTypeConstraint)theEObject;
				T result = caseAssociationTypeConstraint(associationTypeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(associationTypeConstraint);
				if (result == null) result = caseAbstractConstraint(associationTypeConstraint);
				if (result == null) result = caseTMCLConstruct(associationTypeConstraint);
				if (result == null) result = caseOnoObject(associationTypeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.MAPPING_ELEMENT: {
				MappingElement mappingElement = (MappingElement)theEObject;
				T result = caseMappingElement(mappingElement);
				if (result == null) result = caseOnoObject(mappingElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = caseOnoObject(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TYPE_NODE: {
				TypeNode typeNode = (TypeNode)theEObject;
				T result = caseTypeNode(typeNode);
				if (result == null) result = caseNode(typeNode);
				if (result == null) result = caseOnoObject(typeNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.BENDPOINT: {
				Bendpoint bendpoint = (Bendpoint)theEObject;
				T result = caseBendpoint(bendpoint);
				if (result == null) result = caseOnoObject(bendpoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EDGE: {
				Edge edge = (Edge)theEObject;
				T result = caseEdge(edge);
				if (result == null) result = caseOnoObject(edge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_NODE: {
				AssociationNode associationNode = (AssociationNode)theEObject;
				T result = caseAssociationNode(associationNode);
				if (result == null) result = caseNode(associationNode);
				if (result == null) result = caseOnoObject(associationNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DIAGRAM: {
				Diagram diagram = (Diagram)theEObject;
				T result = caseDiagram(diagram);
				if (result == null) result = caseOnoObject(diagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.FILE: {
				File file = (File)theEObject;
				T result = caseFile(file);
				if (result == null) result = caseOnoObject(file);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPE_CONSTRAINT: {
				ScopeConstraint scopeConstraint = (ScopeConstraint)theEObject;
				T result = caseScopeConstraint(scopeConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(scopeConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(scopeConstraint);
				if (result == null) result = caseAbstractTypedConstraint(scopeConstraint);
				if (result == null) result = caseAbstractConstraint(scopeConstraint);
				if (result == null) result = caseTMCLConstruct(scopeConstraint);
				if (result == null) result = caseOnoObject(scopeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_CARDINALITY_CONSTRAINT: {
				AbstractCardinalityConstraint abstractCardinalityConstraint = (AbstractCardinalityConstraint)theEObject;
				T result = caseAbstractCardinalityConstraint(abstractCardinalityConstraint);
				if (result == null) result = caseAbstractConstraint(abstractCardinalityConstraint);
				if (result == null) result = caseTMCLConstruct(abstractCardinalityConstraint);
				if (result == null) result = caseOnoObject(abstractCardinalityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.LABEL_POS: {
				LabelPos labelPos = (LabelPos)theEObject;
				T result = caseLabelPos(labelPos);
				if (result == null) result = caseOnoObject(labelPos);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_TYPED_CONSTRAINT: {
				AbstractTypedConstraint abstractTypedConstraint = (AbstractTypedConstraint)theEObject;
				T result = caseAbstractTypedConstraint(abstractTypedConstraint);
				if (result == null) result = caseAbstractConstraint(abstractTypedConstraint);
				if (result == null) result = caseTMCLConstruct(abstractTypedConstraint);
				if (result == null) result = caseOnoObject(abstractTypedConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPED_TOPIC_TYPE: {
				ScopedTopicType scopedTopicType = (ScopedTopicType)theEObject;
				T result = caseScopedTopicType(scopedTopicType);
				if (result == null) result = caseTopicType(scopedTopicType);
				if (result == null) result = caseTMCLConstruct(scopedTopicType);
				if (result == null) result = caseOnoObject(scopedTopicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ASSOCIATION_TYPE: {
				AssociationType associationType = (AssociationType)theEObject;
				T result = caseAssociationType(associationType);
				if (result == null) result = caseScopedReifiableTopicType(associationType);
				if (result == null) result = caseScopedTopicType(associationType);
				if (result == null) result = caseReifiableTopicType(associationType);
				if (result == null) result = caseTopicType(associationType);
				if (result == null) result = caseTMCLConstruct(associationType);
				if (result == null) result = caseOnoObject(associationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OCCURRENCE_TYPE: {
				OccurrenceType occurrenceType = (OccurrenceType)theEObject;
				T result = caseOccurrenceType(occurrenceType);
				if (result == null) result = caseScopedReifiableTopicType(occurrenceType);
				if (result == null) result = caseAbstractRegExpTopicType(occurrenceType);
				if (result == null) result = caseAbstractUniqueValueTopicType(occurrenceType);
				if (result == null) result = caseScopedTopicType(occurrenceType);
				if (result == null) result = caseReifiableTopicType(occurrenceType);
				if (result == null) result = caseTopicType(occurrenceType);
				if (result == null) result = caseTMCLConstruct(occurrenceType);
				if (result == null) result = caseOnoObject(occurrenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_CONSTRAINT: {
				RoleConstraint roleConstraint = (RoleConstraint)theEObject;
				T result = caseRoleConstraint(roleConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(roleConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(roleConstraint);
				if (result == null) result = caseAbstractTypedConstraint(roleConstraint);
				if (result == null) result = caseAbstractConstraint(roleConstraint);
				if (result == null) result = caseTMCLConstruct(roleConstraint);
				if (result == null) result = caseOnoObject(roleConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_TYPE: {
				RoleType roleType = (RoleType)theEObject;
				T result = caseRoleType(roleType);
				if (result == null) result = caseTopicType(roleType);
				if (result == null) result = caseTMCLConstruct(roleType);
				if (result == null) result = caseOnoObject(roleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT: {
				RoleCombinationConstraint roleCombinationConstraint = (RoleCombinationConstraint)theEObject;
				T result = caseRoleCombinationConstraint(roleCombinationConstraint);
				if (result == null) result = caseAbstractConstraint(roleCombinationConstraint);
				if (result == null) result = caseTMCLConstruct(roleCombinationConstraint);
				if (result == null) result = caseOnoObject(roleCombinationConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NAME_TYPE: {
				NameType nameType = (NameType)theEObject;
				T result = caseNameType(nameType);
				if (result == null) result = caseScopedReifiableTopicType(nameType);
				if (result == null) result = caseAbstractRegExpTopicType(nameType);
				if (result == null) result = caseAbstractUniqueValueTopicType(nameType);
				if (result == null) result = caseScopedTopicType(nameType);
				if (result == null) result = caseReifiableTopicType(nameType);
				if (result == null) result = caseTopicType(nameType);
				if (result == null) result = caseTMCLConstruct(nameType);
				if (result == null) result = caseOnoObject(nameType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_TYPED_CARDINALITY_CONSTRAINT: {
				AbstractTypedCardinalityConstraint abstractTypedCardinalityConstraint = (AbstractTypedCardinalityConstraint)theEObject;
				T result = caseAbstractTypedCardinalityConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseAbstractTypedConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseAbstractConstraint(abstractTypedCardinalityConstraint);
				if (result == null) result = caseTMCLConstruct(abstractTypedCardinalityConstraint);
				if (result == null) result = caseOnoObject(abstractTypedCardinalityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				T result = caseComment(comment);
				if (result == null) result = caseNode(comment);
				if (result == null) result = caseOnoObject(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMCL_CONSTRUCT: {
				TMCLConstruct tmclConstruct = (TMCLConstruct)theEObject;
				T result = caseTMCLConstruct(tmclConstruct);
				if (result == null) result = caseOnoObject(tmclConstruct);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.REIFIER_CONSTRAINT: {
				ReifierConstraint reifierConstraint = (ReifierConstraint)theEObject;
				T result = caseReifierConstraint(reifierConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(reifierConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(reifierConstraint);
				if (result == null) result = caseAbstractTypedConstraint(reifierConstraint);
				if (result == null) result = caseAbstractConstraint(reifierConstraint);
				if (result == null) result = caseTMCLConstruct(reifierConstraint);
				if (result == null) result = caseOnoObject(reifierConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.REIFIABLE_TOPIC_TYPE: {
				ReifiableTopicType reifiableTopicType = (ReifiableTopicType)theEObject;
				T result = caseReifiableTopicType(reifiableTopicType);
				if (result == null) result = caseTopicType(reifiableTopicType);
				if (result == null) result = caseTMCLConstruct(reifiableTopicType);
				if (result == null) result = caseOnoObject(reifiableTopicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE: {
				ScopedReifiableTopicType scopedReifiableTopicType = (ScopedReifiableTopicType)theEObject;
				T result = caseScopedReifiableTopicType(scopedReifiableTopicType);
				if (result == null) result = caseScopedTopicType(scopedReifiableTopicType);
				if (result == null) result = caseReifiableTopicType(scopedReifiableTopicType);
				if (result == null) result = caseTopicType(scopedReifiableTopicType);
				if (result == null) result = caseTMCLConstruct(scopedReifiableTopicType);
				if (result == null) result = caseOnoObject(scopedReifiableTopicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ANNOTATION: {
				Annotation annotation = (Annotation)theEObject;
				T result = caseAnnotation(annotation);
				if (result == null) result = caseOnoObject(annotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE: {
				AbstractRegExpTopicType abstractRegExpTopicType = (AbstractRegExpTopicType)theEObject;
				T result = caseAbstractRegExpTopicType(abstractRegExpTopicType);
				if (result == null) result = caseTopicType(abstractRegExpTopicType);
				if (result == null) result = caseTMCLConstruct(abstractRegExpTopicType);
				if (result == null) result = caseOnoObject(abstractRegExpTopicType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_CONSTRAINT: {
				AbstractConstraint abstractConstraint = (AbstractConstraint)theEObject;
				T result = caseAbstractConstraint(abstractConstraint);
				if (result == null) result = caseTMCLConstruct(abstractConstraint);
				if (result == null) result = caseOnoObject(abstractConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TOPIC_REIFIES_CONSTRAINT: {
				TopicReifiesConstraint topicReifiesConstraint = (TopicReifiesConstraint)theEObject;
				T result = caseTopicReifiesConstraint(topicReifiesConstraint);
				if (result == null) result = caseAbstractTypedCardinalityConstraint(topicReifiesConstraint);
				if (result == null) result = caseAbstractCardinalityConstraint(topicReifiesConstraint);
				if (result == null) result = caseAbstractTypedConstraint(topicReifiesConstraint);
				if (result == null) result = caseAbstractConstraint(topicReifiesConstraint);
				if (result == null) result = caseTMCLConstruct(topicReifiesConstraint);
				if (result == null) result = caseOnoObject(topicReifiesConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOMAIN_DIAGRAM: {
				DomainDiagram domainDiagram = (DomainDiagram)theEObject;
				T result = caseDomainDiagram(domainDiagram);
				if (result == null) result = caseDiagram(domainDiagram);
				if (result == null) result = caseOnoObject(domainDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ONO_OBJECT: {
				OnoObject onoObject = (OnoObject)theEObject;
				T result = caseOnoObject(onoObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_UNIQUE_VALUE_TOPIC_TYPE: {
				AbstractUniqueValueTopicType abstractUniqueValueTopicType = (AbstractUniqueValueTopicType)theEObject;
				T result = caseAbstractUniqueValueTopicType(abstractUniqueValueTopicType);
				if (result == null) result = caseTopicType(abstractUniqueValueTopicType);
				if (result == null) result = caseTMCLConstruct(abstractUniqueValueTopicType);
				if (result == null) result = caseOnoObject(abstractUniqueValueTopicType);
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Reg Exp Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Reg Exp Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractRegExpConstraint(AbstractRegExpConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Occurrence Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Occurrence Type Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOccurrenceTypeConstraint(OccurrenceTypeConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Role Player Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Player Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRolePlayerConstraint(RolePlayerConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Bendpoint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bendpoint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBendpoint(Bendpoint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Cardinality Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Cardinality Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractCardinalityConstraint(AbstractCardinalityConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Occurrence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Occurrence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOccurrenceType(OccurrenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleConstraint(RoleConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Role Combination Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Combination Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleCombinationConstraint(RoleCombinationConstraint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMCL Construct</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMCL Construct</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMCLConstruct(TMCLConstruct object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reifier Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reifier Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReifierConstraint(ReifierConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reifiable Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reifiable Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReifiableTopicType(ReifiableTopicType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scoped Reifiable Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scoped Reifiable Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopedReifiableTopicType(ScopedReifiableTopicType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotation(Annotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Reg Exp Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Reg Exp Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractRegExpTopicType(AbstractRegExpTopicType object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Topic Reifies Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Topic Reifies Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTopicReifiesConstraint(TopicReifiesConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainDiagram(DomainDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ono Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ono Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOnoObject(OnoObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Unique Value Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Unique Value Topic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractUniqueValueTopicType(AbstractUniqueValueTopicType object) {
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
