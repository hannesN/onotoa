package de.topicmapslab.tmcledit.model.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.ScopeTypeIdEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicMapSchemaEditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeIdentifierCompartment3EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.TopicTypeTopicTypOccurenceCompartment2EditPart;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditVisualIDRegistry;

/**
 * @generated
 */
public class ScopeTypeViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = TmceleditVisualIDRegistry
					.getType(ScopeTypeEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		if (!TopicMapSchemaEditPart.MODEL_ID.equals(TmceleditVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", TopicMapSchemaEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(
				eObjectAdapter,
				view,
				TmceleditVisualIDRegistry
						.getType(ScopeTypeIdEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						TmceleditVisualIDRegistry
								.getType(TopicTypeIdentifierCompartment2EditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						TmceleditVisualIDRegistry
								.getType(TopicTypeTopicTypOccurenceCompartment2EditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
		getViewService()
				.createNode(
						eObjectAdapter,
						view,
						TmceleditVisualIDRegistry
								.getType(TopicTypeIdentifierCompartment3EditPart.VISUAL_ID),
						ViewUtil.APPEND, true, getPreferencesHint());
	}
}
