/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

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
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationNodeEditPart extends NodeEditPart{

	Label typeLabel;
	
	@Override
	protected IFigure createFigure() {
		Label figure = new CircleFigure();
		
		figure.setText("foo:association");		
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
	}
	
	private AssociationNode getCastedModel() {
		return (AssociationNode) getModel();
	}
	
	@Override
	protected void refreshVisuals() {
		AssociationNode node = getCastedModel();
		Rectangle r = new Rectangle(node.getPosX(), node.getPosY(), -1, -1);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
        
        TopicType associationType = node.getAssociationConstraint().getType();
		if (associationType!=null)
        	((CircleFigure)getFigure()).setText(associationType.getName());
        
		super.refreshVisuals();
	}

	@Override
	public void activate() {
		super.activate();
		AssociationTypeConstraint associationConstraint = getCastedModel().getAssociationConstraint();
		associationConstraint.eAdapters().add(this);
		if (associationConstraint.getType()!=null)
			associationConstraint.getType().eAdapters().add(this);
		
	}
	
	@Override
	public void deactivate() {
		AssociationTypeConstraint associationConstraint = getCastedModel().getAssociationConstraint();
		associationConstraint.eAdapters().remove(this);
		if (associationConstraint.getType()!=null)
			associationConstraint.getType().eAdapters().remove(this);
		super.deactivate();
	}
	
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new EllipseAnchor(getFigure());
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.REMOVING_ADAPTER) {
			return;
		}
		if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__EDGES)
			refreshSourceConnections();
		if (notification.getNotifier()==getModel())
			refreshVisuals();
		if ( (notification.getEventType()==Notification.SET) && (notification.getNotifier().equals(getCastedModel().getAssociationConstraint())) ) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE) {
				if (notification.getOldValue()!=null)
					((EObject)notification.getOldValue()).eAdapters().remove(this);
				
				if (notification.getNewValue()!=null)
					((EObject)notification.getNewValue()).eAdapters().add(this);
			}
			refreshVisuals();
		}
	}

}
