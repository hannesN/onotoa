/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;

import de.topicmapslab.tmcledit.diagram.figures.CircleFigure;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AssociationNodeEditPart extends NodeEditPart {

	Label typeLabel;

	@Override
	protected IFigure createFigure() {
		Label figure = new CircleFigure();

		figure.setText("foo:association");

		return figure;
	}

	private void addScope(StringBuffer buffer) {
		TopicType type = getCastedModel().getAssociationConstraint().getType();
		if (type == null)
			return;
		if (type instanceof AssociationType) {
			AssociationType assType = (AssociationType) type;

			for (ScopeConstraint sc : assType.getScope()) {
				buffer.append("\n@");
				buffer.append(sc.getType().getName());
				buffer.append("  [");
				buffer.append(sc.getCardMin());
				buffer.append("..");
				buffer.append(sc.getCardMax());
				buffer.append("]");
			}
		}
	}

	private AssociationNode getCastedModel() {
		return (AssociationNode) getModel();
	}

	@Override
	protected void refreshVisuals() {
		AssociationNode node = getCastedModel();
		if (getParent()==null)
			return;
		Rectangle r = new Rectangle(node.getPosX(), node.getPosY(), -1, -1);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);

		TopicType associationType = node.getAssociationConstraint().getType();
		StringBuffer buffer = new StringBuffer();
		if (associationType != null) {
			buffer.append(associationType.getName());
			addScope(buffer);
		} else {
			buffer.append("No type set");
		}

		((CircleFigure) getFigure()).setText(buffer.toString());

		super.refreshVisuals();
	}

	@Override
	public void activate() {
		super.activate();
		AssociationTypeConstraint associationConstraint = getCastedModel()
				.getAssociationConstraint();
		associationConstraint.eAdapters().add(this);
		if (associationConstraint.getType() != null)
			associationConstraint.getType().eAdapters().add(this);

	}

	@Override
	public void deactivate() {
		AssociationTypeConstraint associationConstraint = getCastedModel()
				.getAssociationConstraint();
		associationConstraint.eAdapters().remove(this);
		if (associationConstraint.getType() != null)
			associationConstraint.getType().eAdapters().remove(this);
		super.deactivate();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	@Override
	public void setModel(Object model) {
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			tt.eAdapters().remove(this);
			if ((tt instanceof AssociationType)) {
				for (ScopeConstraint sc : ((AssociationType) tt).getScope()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			}
		}
		super.setModel(model);
		if (getCastedModel() != null) {
			TopicType tt = getCastedModel().getAssociationConstraint()
					.getType();
			if (tt==null)
				return;
			tt.eAdapters().add(this);
			if ((tt instanceof AssociationType)) {
				for (ScopeConstraint sc : ((AssociationType) tt).getScope()) {
					sc.eAdapters().add(this);
					sc.getType().eAdapters().add(this);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.REMOVING_ADAPTER) {
			return;
		}
		if (notification.getFeatureID(EList.class) == ModelPackage.DIAGRAM__EDGES)
			refreshSourceConnections();
		if (notification.getNotifier() == getModel())
			refreshVisuals();
		if ((notification.getEventType() == Notification.SET)
				&& (notification.getNotifier().equals(getCastedModel()
						.getAssociationConstraint()))) {
			if (notification.getFeatureID(TopicType.class) == ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue() != null)
					((EObject) notification.getOldValue()).eAdapters().remove(
							this);

				if (notification.getNewValue() != null)
					((EObject) notification.getNewValue()).eAdapters()
							.add(this);
			}
		} else if (notification.getFeatureID(List.class) == ModelPackage.ASSOCIATION_TYPE__SCOPE) {
			if (notification.getEventType() == Notification.ADD) {
				ScopeConstraint sc = (ScopeConstraint) notification
						.getNewValue();
				sc.eAdapters().add(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().add(this);
			} else if (notification.getEventType() == Notification.ADD_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification
						.getNewValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			} else if (notification.getEventType() == Notification.REMOVE) {
				ScopeConstraint sc = (ScopeConstraint) notification
						.getOldValue();
				sc.eAdapters().remove(this);
				if (sc.getType() != null)
					sc.getType().eAdapters().remove(this);
			} else if (notification.getEventType() == Notification.REMOVE_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification
						.getOldValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType() != null)
						sc.getType().eAdapters().remove(this);
				}
			}
		}
		refreshVisuals();

	}

}
