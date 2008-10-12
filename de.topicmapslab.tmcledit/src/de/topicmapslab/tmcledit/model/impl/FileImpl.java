/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.FileImpl#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.FileImpl#getTopicMapSchema <em>Topic Map Schema</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.FileImpl#getFilename <em>Filename</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.FileImpl#isDirty <em>Dirty</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileImpl extends EObjectImpl implements File {
	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList<Diagram> diagrams;

	/**
	 * The cached value of the '{@link #getTopicMapSchema() <em>Topic Map Schema</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicMapSchema()
	 * @generated
	 * @ordered
	 */
	protected TopicMapSchema topicMapSchema;

	/**
	 * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected static final String FILENAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected String filename = FILENAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirty()
	 * @generated
	 * @ordered
	 */
	protected boolean dirty = DIRTY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Diagram> getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectContainmentEList<Diagram>(Diagram.class, this, ModelPackage.FILE__DIAGRAMS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicMapSchema getTopicMapSchema() {
		return topicMapSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTopicMapSchema(TopicMapSchema newTopicMapSchema, NotificationChain msgs) {
		TopicMapSchema oldTopicMapSchema = topicMapSchema;
		topicMapSchema = newTopicMapSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.FILE__TOPIC_MAP_SCHEMA, oldTopicMapSchema, newTopicMapSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTopicMapSchema(TopicMapSchema newTopicMapSchema) {
		if (newTopicMapSchema != topicMapSchema) {
			NotificationChain msgs = null;
			if (topicMapSchema != null)
				msgs = ((InternalEObject)topicMapSchema).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.FILE__TOPIC_MAP_SCHEMA, null, msgs);
			if (newTopicMapSchema != null)
				msgs = ((InternalEObject)newTopicMapSchema).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.FILE__TOPIC_MAP_SCHEMA, null, msgs);
			msgs = basicSetTopicMapSchema(newTopicMapSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILE__TOPIC_MAP_SCHEMA, newTopicMapSchema, newTopicMapSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilename(String newFilename) {
		String oldFilename = filename;
		filename = newFilename;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILE__FILENAME, oldFilename, filename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirty(boolean newDirty) {
		boolean oldDirty = dirty;
		dirty = newDirty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.FILE__DIRTY, oldDirty, dirty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.FILE__DIAGRAMS:
				return ((InternalEList<?>)getDiagrams()).basicRemove(otherEnd, msgs);
			case ModelPackage.FILE__TOPIC_MAP_SCHEMA:
				return basicSetTopicMapSchema(null, msgs);
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
			case ModelPackage.FILE__DIAGRAMS:
				return getDiagrams();
			case ModelPackage.FILE__TOPIC_MAP_SCHEMA:
				return getTopicMapSchema();
			case ModelPackage.FILE__FILENAME:
				return getFilename();
			case ModelPackage.FILE__DIRTY:
				return isDirty() ? Boolean.TRUE : Boolean.FALSE;
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
			case ModelPackage.FILE__DIAGRAMS:
				getDiagrams().clear();
				getDiagrams().addAll((Collection<? extends Diagram>)newValue);
				return;
			case ModelPackage.FILE__TOPIC_MAP_SCHEMA:
				setTopicMapSchema((TopicMapSchema)newValue);
				return;
			case ModelPackage.FILE__FILENAME:
				setFilename((String)newValue);
				return;
			case ModelPackage.FILE__DIRTY:
				setDirty(((Boolean)newValue).booleanValue());
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
			case ModelPackage.FILE__DIAGRAMS:
				getDiagrams().clear();
				return;
			case ModelPackage.FILE__TOPIC_MAP_SCHEMA:
				setTopicMapSchema((TopicMapSchema)null);
				return;
			case ModelPackage.FILE__FILENAME:
				setFilename(FILENAME_EDEFAULT);
				return;
			case ModelPackage.FILE__DIRTY:
				setDirty(DIRTY_EDEFAULT);
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
			case ModelPackage.FILE__DIAGRAMS:
				return diagrams != null && !diagrams.isEmpty();
			case ModelPackage.FILE__TOPIC_MAP_SCHEMA:
				return topicMapSchema != null;
			case ModelPackage.FILE__FILENAME:
				return FILENAME_EDEFAULT == null ? filename != null : !FILENAME_EDEFAULT.equals(filename);
			case ModelPackage.FILE__DIRTY:
				return dirty != DIRTY_EDEFAULT;
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
		result.append(" (filename: ");
		result.append(filename);
		result.append(", dirty: ");
		result.append(dirty);
		result.append(')');
		return result.toString();
	}

} //FileImpl
