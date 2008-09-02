package de.topicmapslab.tmcledit.model.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractLabelViewFactory;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.diagram.edit.parts.NameTypeConstraint4EditPart;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditVisualIDRegistry;

/**
 * @generated
 */
public class NameTypeConstraint4ViewFactory extends AbstractLabelViewFactory {

	/**
	 * @generated
	 */
	protected List createStyles(View view) {
		List styles = new ArrayList();
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
					.getType(NameTypeConstraint4EditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
	}
}
