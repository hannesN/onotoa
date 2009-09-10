/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TMCLConstruct;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TMCL Construct</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl#getSee_also <em>See also</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TMCLConstructImpl#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TMCLConstructImpl extends EObjectImpl implements TMCLConstruct {
	/**
	 * The default value of the '{@link #getSee_also() <em>See also</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSee_also()
	 * @generated
	 * @ordered
	 */
	protected static final String SEE_ALSO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSee_also() <em>See also</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSee_also()
	 * @generated
	 * @ordered
	 */
	protected String see_also = SEE_ALSO_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtension() <em>Extension</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtension()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> extension;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TMCLConstructImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TMCL_CONSTRUCT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSee_also() {
		return see_also;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSee_also(String newSee_also) {
		String oldSee_also = see_also;
		see_also = newSee_also;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMCL_CONSTRUCT__SEE_ALSO, oldSee_also, see_also));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMCL_CONSTRUCT__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMCL_CONSTRUCT__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getExtension() {
		if (extension == null) {
			extension = new EcoreEMap<String,String>(ModelPackage.Literals.ESTRING_TO_ESTRING_MAP, EStringToEStringMapImpl.class, this, ModelPackage.TMCL_CONSTRUCT__EXTENSION);
		}
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TMCL_CONSTRUCT__EXTENSION:
				return ((InternalEList<?>)getExtension()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TMCL_CONSTRUCT__SEE_ALSO:
				return getSee_also();
			case ModelPackage.TMCL_CONSTRUCT__COMMENT:
				return getComment();
			case ModelPackage.TMCL_CONSTRUCT__DESCRIPTION:
				return getDescription();
			case ModelPackage.TMCL_CONSTRUCT__EXTENSION:
				if (coreType) return getExtension();
				else return getExtension().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TMCL_CONSTRUCT__SEE_ALSO:
				setSee_also((String)newValue);
				return;
			case ModelPackage.TMCL_CONSTRUCT__COMMENT:
				setComment((String)newValue);
				return;
			case ModelPackage.TMCL_CONSTRUCT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ModelPackage.TMCL_CONSTRUCT__EXTENSION:
				((EStructuralFeature.Setting)getExtension()).set(newValue);
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
			case ModelPackage.TMCL_CONSTRUCT__SEE_ALSO:
				setSee_also(SEE_ALSO_EDEFAULT);
				return;
			case ModelPackage.TMCL_CONSTRUCT__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case ModelPackage.TMCL_CONSTRUCT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ModelPackage.TMCL_CONSTRUCT__EXTENSION:
				getExtension().clear();
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
			case ModelPackage.TMCL_CONSTRUCT__SEE_ALSO:
				return SEE_ALSO_EDEFAULT == null ? see_also != null : !SEE_ALSO_EDEFAULT.equals(see_also);
			case ModelPackage.TMCL_CONSTRUCT__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case ModelPackage.TMCL_CONSTRUCT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ModelPackage.TMCL_CONSTRUCT__EXTENSION:
				return extension != null && !extension.isEmpty();
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
		result.append(" (see_also: ");
		result.append(see_also);
		result.append(", comment: ");
		result.append(comment);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //TMCLConstructImpl
