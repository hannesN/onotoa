package de.topicmapslab.tmcledit.model.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationTypeConstraintEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.AssociationsTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.RoleTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicMapSchemaEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.part.Messages;
import de.topicmapslab.tmcledit.model.diagram.part.TmcleditDiagramEditorPlugin;

/**
 * @generated
 */
public class TmcleditModelingAssistantProvider extends
		ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof NameTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2002);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2003);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2004);
			types.add(TmcleditElementTypes.NameTypeConstraint_2001);
			return types;
		}
		if (editPart instanceof ScopeTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2006);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2007);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2008);
			types.add(TmcleditElementTypes.NameTypeConstraint_2005);
			return types;
		}
		if (editPart instanceof RoleTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2010);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2011);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2012);
			types.add(TmcleditElementTypes.NameTypeConstraint_2009);
			return types;
		}
		if (editPart instanceof AssociationsTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2014);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2015);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2016);
			types.add(TmcleditElementTypes.NameTypeConstraint_2013);
			return types;
		}
		if (editPart instanceof OccurenceTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2018);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2019);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2020);
			types.add(TmcleditElementTypes.NameTypeConstraint_2017);
			return types;
		}
		if (editPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.NameTypeConstraint_2021);
			types.add(TmcleditElementTypes.OccurenceTypeConstraint_2022);
			types.add(TmcleditElementTypes.SubjectIdentifierConstraint_2023);
			types.add(TmcleditElementTypes.SubjectLocatorConstraint_2024);
			return types;
		}
		if (editPart instanceof TopicMapSchemaEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.NameType_1001);
			types.add(TmcleditElementTypes.ScopeType_1002);
			types.add(TmcleditElementTypes.RoleType_1003);
			types.add(TmcleditElementTypes.AssociationsType_1004);
			types.add(TmcleditElementTypes.OccurenceType_1005);
			types.add(TmcleditElementTypes.AssociationTypeConstraint_1006);
			types.add(TmcleditElementTypes.TopicType_1007);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AssociationTypeConstraintEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.RoleTypeConstraints_3001);
			return types;
		}
		if (sourceEditPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.TopicTypeAko_3002);
			types.add(TmcleditElementTypes.TopicTypeIsa_3003);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			types.add(TmcleditElementTypes.RoleTypeConstraints_3001);
			types.add(TmcleditElementTypes.TopicTypeAko_3002);
			types.add(TmcleditElementTypes.TopicTypeIsa_3003);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AssociationTypeConstraintEditPart) {
			List types = new ArrayList();
			if (targetEditPart instanceof TopicTypeEditPart) {
				types.add(TmcleditElementTypes.RoleTypeConstraints_3001);
			}
			return types;
		}
		if (sourceEditPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			if (targetEditPart instanceof TopicTypeEditPart) {
				types.add(TmcleditElementTypes.TopicTypeAko_3002);
			}
			if (targetEditPart instanceof TopicTypeEditPart) {
				types.add(TmcleditElementTypes.TopicTypeIsa_3003);
			}
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			if (relationshipType == TmcleditElementTypes.RoleTypeConstraints_3001) {
				types.add(TmcleditElementTypes.AssociationTypeConstraint_1006);
			}
			if (relationshipType == TmcleditElementTypes.TopicTypeAko_3002) {
				types.add(TmcleditElementTypes.TopicType_1007);
			}
			if (relationshipType == TmcleditElementTypes.TopicTypeIsa_3003) {
				types.add(TmcleditElementTypes.TopicType_1007);
			}
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AssociationTypeConstraintEditPart) {
			List types = new ArrayList();
			if (relationshipType == TmcleditElementTypes.RoleTypeConstraints_3001) {
				types.add(TmcleditElementTypes.TopicType_1007);
			}
			return types;
		}
		if (sourceEditPart instanceof TopicTypeEditPart) {
			List types = new ArrayList();
			if (relationshipType == TmcleditElementTypes.TopicTypeAko_3002) {
				types.add(TmcleditElementTypes.TopicType_1007);
			}
			if (relationshipType == TmcleditElementTypes.TopicTypeIsa_3003) {
				types.add(TmcleditElementTypes.TopicType_1007);
			}
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target,
				relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source,
				relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				TmcleditDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.TmcleditModelingAssistantProviderMessage);
		dialog.setTitle(Messages.TmcleditModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
