package de.topicmapslab.tmcledit.model.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.OccurenceTypeConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectIdentifierConstraint5EditPart;
import de.topicmapslab.tmcledit.model.diagram.edit.parts.SubjectLocatorConstraint6EditPart;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditDiagramUpdater;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditNodeDescriptor;
import de.topicmapslab.tmcledit.model.diagram.part.TmceleditVisualIDRegistry;

/**
 * @generated
 */
public class OccurenceTypeCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = TmceleditDiagramUpdater
				.getOccurenceType_1005SemanticChildren(viewObject).iterator(); it
				.hasNext();) {
			result.add(((TmceleditNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = TmceleditVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case OccurenceTypeConstraint5EditPart.VISUAL_ID:
		case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
		case SubjectLocatorConstraint6EditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(TMPackage.eINSTANCE
					.getTopicType_OccurenceConstraints());
			myFeaturesToSynchronize.add(TMPackage.eINSTANCE
					.getTopicType_SubjectIdentifierConstraints());
			myFeaturesToSynchronize.add(TMPackage.eINSTANCE
					.getTopicType_SubjectLocatorConstraint());
		}
		return myFeaturesToSynchronize;
	}

}
