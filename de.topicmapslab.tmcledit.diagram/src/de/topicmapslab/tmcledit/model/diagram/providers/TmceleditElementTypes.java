package de.topicmapslab.tmcledit.model.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditDiagramEditorPlugin;

/**
 * @generated
 */
public class TmceleditElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private TmceleditElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType TopicMapSchema_79 = getElementType("de.topicmapslab.tmcledit.diagram.TopicMapSchema_79"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameType_1001 = getElementType("de.topicmapslab.tmcledit.diagram.NameType_1001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ScopeType_1002 = getElementType("de.topicmapslab.tmcledit.diagram.ScopeType_1002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleType_1003 = getElementType("de.topicmapslab.tmcledit.diagram.RoleType_1003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType AssociationsType_1004 = getElementType("de.topicmapslab.tmcledit.diagram.AssociationsType_1004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceType_1005 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceType_1005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType AssociationTypeConstraint_1006 = getElementType("de.topicmapslab.tmcledit.diagram.AssociationTypeConstraint_1006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TopicType_1007 = getElementType("de.topicmapslab.tmcledit.diagram.TopicType_1007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2001 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2002 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2003 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2004 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2005 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2006 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2007 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2008 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2009 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2010 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2011 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2012 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2012"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2013 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2013"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2014 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2014"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2015 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2015"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2016 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2016"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2017 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2017"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2018 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2018"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2019 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2019"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2020 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2020"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType NameTypeConstraint_2021 = getElementType("de.topicmapslab.tmcledit.diagram.NameTypeConstraint_2021"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OccurenceTypeConstraint_2022 = getElementType("de.topicmapslab.tmcledit.diagram.OccurenceTypeConstraint_2022"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectIdentifierConstraint_2023 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectIdentifierConstraint_2023"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SubjectLocatorConstraint_2024 = getElementType("de.topicmapslab.tmcledit.diagram.SubjectLocatorConstraint_2024"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType RoleTypeConstraints_3001 = getElementType("de.topicmapslab.tmcledit.diagram.RoleTypeConstraints_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TopicTypeAko_3002 = getElementType("de.topicmapslab.tmcledit.diagram.TopicTypeAko_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TopicTypeIsa_3003 = getElementType("de.topicmapslab.tmcledit.diagram.TopicTypeIsa_3003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return TmceleditDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(TopicMapSchema_79, TMPackage.eINSTANCE
					.getTopicMapSchema());

			elements.put(NameType_1001, TMPackage.eINSTANCE.getNameType());

			elements.put(ScopeType_1002, TMPackage.eINSTANCE.getScopeType());

			elements.put(RoleType_1003, TMPackage.eINSTANCE.getRoleType());

			elements.put(AssociationsType_1004, TMPackage.eINSTANCE
					.getAssociationsType());

			elements.put(OccurenceType_1005, TMPackage.eINSTANCE
					.getOccurenceType());

			elements.put(AssociationTypeConstraint_1006, TMPackage.eINSTANCE
					.getAssociationTypeConstraint());

			elements.put(TopicType_1007, TMPackage.eINSTANCE.getTopicType());

			elements.put(NameTypeConstraint_2001, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2002, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2003, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2004, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(NameTypeConstraint_2005, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2006, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2007, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2008, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(NameTypeConstraint_2009, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2010, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2011, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2012, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(NameTypeConstraint_2013, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2014, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2015, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2016, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(NameTypeConstraint_2017, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2018, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2019, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2020, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(NameTypeConstraint_2021, TMPackage.eINSTANCE
					.getNameTypeConstraint());

			elements.put(OccurenceTypeConstraint_2022, TMPackage.eINSTANCE
					.getOccurenceTypeConstraint());

			elements.put(SubjectIdentifierConstraint_2023, TMPackage.eINSTANCE
					.getsubjectIdentifierConstraint());

			elements.put(SubjectLocatorConstraint_2024, TMPackage.eINSTANCE
					.getsubjectLocatorConstraint());

			elements.put(RoleTypeConstraints_3001, TMPackage.eINSTANCE
					.getRoleTypeConstraints());

			elements.put(TopicTypeAko_3002, TMPackage.eINSTANCE
					.getTopicType_Ako());

			elements.put(TopicTypeIsa_3003, TMPackage.eINSTANCE
					.getTopicType_Isa());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(TopicMapSchema_79);
			KNOWN_ELEMENT_TYPES.add(NameType_1001);
			KNOWN_ELEMENT_TYPES.add(ScopeType_1002);
			KNOWN_ELEMENT_TYPES.add(RoleType_1003);
			KNOWN_ELEMENT_TYPES.add(AssociationsType_1004);
			KNOWN_ELEMENT_TYPES.add(OccurenceType_1005);
			KNOWN_ELEMENT_TYPES.add(AssociationTypeConstraint_1006);
			KNOWN_ELEMENT_TYPES.add(TopicType_1007);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2001);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2002);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2003);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2004);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2005);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2006);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2007);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2008);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2009);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2010);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2011);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2012);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2013);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2014);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2015);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2016);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2017);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2018);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2019);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2020);
			KNOWN_ELEMENT_TYPES.add(NameTypeConstraint_2021);
			KNOWN_ELEMENT_TYPES.add(OccurenceTypeConstraint_2022);
			KNOWN_ELEMENT_TYPES.add(SubjectIdentifierConstraint_2023);
			KNOWN_ELEMENT_TYPES.add(SubjectLocatorConstraint_2024);
			KNOWN_ELEMENT_TYPES.add(RoleTypeConstraints_3001);
			KNOWN_ELEMENT_TYPES.add(TopicTypeAko_3002);
			KNOWN_ELEMENT_TYPES.add(TopicTypeIsa_3003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

}
