/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getPlayerConstraints <em>Player Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeConstraintImpl extends AbstractTypedConstraintImpl implements AssociationTypeConstraint {
	/**
	 * The cached value of the '{@link #getPlayerConstraints() <em>Player Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlayerConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<RolePlayerConstraint> playerConstraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationTypeConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ASSOCIATION_TYPE_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RolePlayerConstraint> getPlayerConstraints() {
		if (playerConstraints == null) {
			playerConstraints = new EObjectContainmentEList<RolePlayerConstraint>(RolePlayerConstraint.class, this, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS);
		}
		return playerConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return ((InternalEList<?>)getPlayerConstraints()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return getPlayerConstraints();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				getPlayerConstraints().clear();
				getPlayerConstraints().addAll((Collection<? extends RolePlayerConstraint>)newValue);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				getPlayerConstraints().clear();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return playerConstraints != null && !playerConstraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + ((playerConstraints == null) ? 0 : playerConstraints.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (!super.equals(obj))
		    return false;
	    if (!(obj instanceof AssociationTypeConstraintImpl))
		    return false;
	    AssociationTypeConstraintImpl other = (AssociationTypeConstraintImpl) obj;
	    if (playerConstraints == null) {
		    if (other.playerConstraints != null)
			    return false;
	    } else if (!playerConstraints.equals(other.playerConstraints))
		    return false;
	   
	    return true;
    }

	
} //AssociationTypeConstraintImpl
