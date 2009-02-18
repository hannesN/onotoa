/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NameTypeImpl extends ScopedTopicTypeImpl implements NameType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected NameTypeImpl() {
		super();
		setKind(KindOfTopicType.NAME_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NAME_TYPE;
	}

} //NameTypeImpl
