/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getIdentifiers <em>Identifiers</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getIdType <em>Id Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getIsa <em>Isa</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getAko <em>Ako</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getOccurrenceConstraints <em>Occurrence Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getNameContraints <em>Name Contraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getSubjectIdentifierConstraints <em>Subject Identifier Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getSubjectLocatorConstraints <em>Subject Locator Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getOverlap <em>Overlap</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getLocators <em>Locators</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicTypeImpl#getTopicReifiesConstraints <em>Topic Reifies Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicTypeImpl extends TMCLConstructImpl implements TopicType {
	/**
	 * The cached value of the '{@link #getIdentifiers() <em>Identifiers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<String> identifiers;

	/**
	 * The default value of the '{@link #getIdType() <em>Id Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdType()
	 * @generated
	 * @ordered
	 */
	protected static final TopicId ID_TYPE_EDEFAULT = TopicId.IDENTIFIER;

	/**
	 * The cached value of the '{@link #getIdType() <em>Id Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdType()
	 * @generated
	 * @ordered
	 */
	protected TopicId idType = ID_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIsa() <em>Isa</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsa()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicType> isa;

	/**
	 * The cached value of the '{@link #getAko() <em>Ako</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAko()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicType> ako;

	/**
	 * The cached value of the '{@link #getOccurrenceConstraints() <em>Occurrence Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccurrenceConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<OccurrenceTypeConstraint> occurrenceConstraints;

	/**
	 * The cached value of the '{@link #getNameContraints() <em>Name Contraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameContraints()
	 * @generated
	 * @ordered
	 */
	protected EList<NameTypeConstraint> nameContraints;

	/**
	 * The cached value of the '{@link #getSubjectIdentifierConstraints() <em>Subject Identifier Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectIdentifierConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<SubjectIdentifierConstraint> subjectIdentifierConstraints;

	/**
	 * The cached value of the '{@link #getSubjectLocatorConstraints() <em>Subject Locator Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectLocatorConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<SubjectLocatorConstraint> subjectLocatorConstraints;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final KindOfTopicType KIND_EDEFAULT = KindOfTopicType.TOPIC_TYPE;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected KindOfTopicType kind = KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOverlap() <em>Overlap</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverlap()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicType> overlap;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLocators() <em>Locators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocators()
	 * @generated
	 * @ordered
	 */
	protected EList<String> locators;

	/**
	 * The cached value of the '{@link #getTopicReifiesConstraints() <em>Topic Reifies Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicReifiesConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicReifiesConstraint> topicReifiesConstraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopicTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TOPIC_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getIdentifiers() {
		if (identifiers == null) {
			identifiers = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.TOPIC_TYPE__IDENTIFIERS);
		}
		return identifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicId getIdType() {
		return idType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdType(TopicId newIdType) {
		TopicId oldIdType = idType;
		idType = newIdType == null ? ID_TYPE_EDEFAULT : newIdType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_TYPE__ID_TYPE, oldIdType, idType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_TYPE__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getIsa() {
		if (isa == null) {
			isa = new EObjectResolvingEList<TopicType>(TopicType.class, this, ModelPackage.TOPIC_TYPE__ISA);
		}
		return isa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getAko() {
		if (ako == null) {
			ako = new EObjectResolvingEList<TopicType>(TopicType.class, this, ModelPackage.TOPIC_TYPE__AKO);
		}
		return ako;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OccurrenceTypeConstraint> getOccurrenceConstraints() {
		if (occurrenceConstraints == null) {
			occurrenceConstraints = new EObjectContainmentEList<OccurrenceTypeConstraint>(OccurrenceTypeConstraint.class, this, ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS);
		}
		return occurrenceConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NameTypeConstraint> getNameContraints() {
		if (nameContraints == null) {
			nameContraints = new EObjectContainmentEList<NameTypeConstraint>(NameTypeConstraint.class, this, ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS);
		}
		return nameContraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubjectIdentifierConstraint> getSubjectIdentifierConstraints() {
		if (subjectIdentifierConstraints == null) {
			subjectIdentifierConstraints = new EObjectContainmentEList<SubjectIdentifierConstraint>(SubjectIdentifierConstraint.class, this, ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS);
		}
		return subjectIdentifierConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubjectLocatorConstraint> getSubjectLocatorConstraints() {
		if (subjectLocatorConstraints == null) {
			subjectLocatorConstraints = new EObjectContainmentEList<SubjectLocatorConstraint>(SubjectLocatorConstraint.class, this, ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS);
		}
		return subjectLocatorConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KindOfTopicType getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(KindOfTopicType newKind) {
		KindOfTopicType oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_TYPE__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getOverlap() {
		if (overlap == null) {
			overlap = new EObjectResolvingEList<TopicType>(TopicType.class, this, ModelPackage.TOPIC_TYPE__OVERLAP);
		}
		return overlap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getLocators() {
		if (locators == null) {
			locators = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.TOPIC_TYPE__LOCATORS);
		}
		return locators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicReifiesConstraint> getTopicReifiesConstraints() {
		if (topicReifiesConstraints == null) {
			topicReifiesConstraints = new EObjectContainmentEList<TopicReifiesConstraint>(TopicReifiesConstraint.class, this, ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS);
		}
		return topicReifiesConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS:
				return ((InternalEList<?>)getOccurrenceConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
				return ((InternalEList<?>)getNameContraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
				return ((InternalEList<?>)getSubjectIdentifierConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS:
				return ((InternalEList<?>)getSubjectLocatorConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS:
				return ((InternalEList<?>)getTopicReifiesConstraints()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TOPIC_TYPE__IDENTIFIERS:
				return getIdentifiers();
			case ModelPackage.TOPIC_TYPE__ID_TYPE:
				return getIdType();
			case ModelPackage.TOPIC_TYPE__ABSTRACT:
				return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_TYPE__ISA:
				return getIsa();
			case ModelPackage.TOPIC_TYPE__AKO:
				return getAko();
			case ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS:
				return getOccurrenceConstraints();
			case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
				return getNameContraints();
			case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
				return getSubjectIdentifierConstraints();
			case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS:
				return getSubjectLocatorConstraints();
			case ModelPackage.TOPIC_TYPE__KIND:
				return getKind();
			case ModelPackage.TOPIC_TYPE__OVERLAP:
				return getOverlap();
			case ModelPackage.TOPIC_TYPE__NAME:
				return getName();
			case ModelPackage.TOPIC_TYPE__LOCATORS:
				return getLocators();
			case ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS:
				return getTopicReifiesConstraints();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TOPIC_TYPE__IDENTIFIERS:
				getIdentifiers().clear();
				getIdentifiers().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__ID_TYPE:
				setIdType((TopicId)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__ABSTRACT:
				setAbstract(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_TYPE__ISA:
				getIsa().clear();
				getIsa().addAll((Collection<? extends TopicType>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__AKO:
				getAko().clear();
				getAko().addAll((Collection<? extends TopicType>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS:
				getOccurrenceConstraints().clear();
				getOccurrenceConstraints().addAll((Collection<? extends OccurrenceTypeConstraint>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
				getNameContraints().clear();
				getNameContraints().addAll((Collection<? extends NameTypeConstraint>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
				getSubjectIdentifierConstraints().clear();
				getSubjectIdentifierConstraints().addAll((Collection<? extends SubjectIdentifierConstraint>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS:
				getSubjectLocatorConstraints().clear();
				getSubjectLocatorConstraints().addAll((Collection<? extends SubjectLocatorConstraint>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__KIND:
				setKind((KindOfTopicType)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__OVERLAP:
				getOverlap().clear();
				getOverlap().addAll((Collection<? extends TopicType>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__LOCATORS:
				getLocators().clear();
				getLocators().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS:
				getTopicReifiesConstraints().clear();
				getTopicReifiesConstraints().addAll((Collection<? extends TopicReifiesConstraint>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.TOPIC_TYPE__IDENTIFIERS:
				getIdentifiers().clear();
				return;
			case ModelPackage.TOPIC_TYPE__ID_TYPE:
				setIdType(ID_TYPE_EDEFAULT);
				return;
			case ModelPackage.TOPIC_TYPE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_TYPE__ISA:
				getIsa().clear();
				return;
			case ModelPackage.TOPIC_TYPE__AKO:
				getAko().clear();
				return;
			case ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS:
				getOccurrenceConstraints().clear();
				return;
			case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
				getNameContraints().clear();
				return;
			case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
				getSubjectIdentifierConstraints().clear();
				return;
			case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS:
				getSubjectLocatorConstraints().clear();
				return;
			case ModelPackage.TOPIC_TYPE__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case ModelPackage.TOPIC_TYPE__OVERLAP:
				getOverlap().clear();
				return;
			case ModelPackage.TOPIC_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.TOPIC_TYPE__LOCATORS:
				getLocators().clear();
				return;
			case ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS:
				getTopicReifiesConstraints().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.TOPIC_TYPE__IDENTIFIERS:
				return identifiers != null && !identifiers.isEmpty();
			case ModelPackage.TOPIC_TYPE__ID_TYPE:
				return idType != ID_TYPE_EDEFAULT;
			case ModelPackage.TOPIC_TYPE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case ModelPackage.TOPIC_TYPE__ISA:
				return isa != null && !isa.isEmpty();
			case ModelPackage.TOPIC_TYPE__AKO:
				return ako != null && !ako.isEmpty();
			case ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS:
				return occurrenceConstraints != null && !occurrenceConstraints.isEmpty();
			case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
				return nameContraints != null && !nameContraints.isEmpty();
			case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
				return subjectIdentifierConstraints != null && !subjectIdentifierConstraints.isEmpty();
			case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINTS:
				return subjectLocatorConstraints != null && !subjectLocatorConstraints.isEmpty();
			case ModelPackage.TOPIC_TYPE__KIND:
				return kind != KIND_EDEFAULT;
			case ModelPackage.TOPIC_TYPE__OVERLAP:
				return overlap != null && !overlap.isEmpty();
			case ModelPackage.TOPIC_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.TOPIC_TYPE__LOCATORS:
				return locators != null && !locators.isEmpty();
			case ModelPackage.TOPIC_TYPE__TOPIC_REIFIES_CONSTRAINTS:
				return topicReifiesConstraints != null && !topicReifiesConstraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (identifiers: ");
		result.append(identifiers);
		result.append(", idType: ");
		result.append(idType);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", kind: ");
		result.append(kind);
		result.append(", name: ");
		result.append(name);
		result.append(", locators: ");
		result.append(locators);
		result.append(')');
		return result.toString();
	}

} //TopicTypeImpl
