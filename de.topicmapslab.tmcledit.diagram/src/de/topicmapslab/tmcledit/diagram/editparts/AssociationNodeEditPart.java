/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
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
        
        TopicType associationType = node.getAssociationConstraint().getAssociationType();
		if (associationType!=null)
        	((CircleFigure)getFigure()).setText(associationType.getName());
        
		super.refreshVisuals();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelSourceConnections() {
		return super.getModelSourceConnections();
	}
	/*
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		
		TopicType type = getCastedModel().getAssociationConstraint().getAssociationType();
		if (type==null)
			return Collections.EMPTY_LIST;
		else {
			List result = new ArrayList();
			result.add(type);
			return result;
		}
	}
*/
	@Override
	public void activate() {
		super.activate();
		AssociationTypeConstraint associationConstraint = getCastedModel().getAssociationConstraint();
		associationConstraint.eAdapters().add(this);
		if (associationConstraint.getAssociationType()!=null)
			associationConstraint.getAssociationType().eAdapters().add(this);
		
	}
	
	@Override
	public void deactivate() {
		AssociationTypeConstraint associationConstraint = getCastedModel().getAssociationConstraint();
		associationConstraint.eAdapters().remove(this);
		if (associationConstraint.getAssociationType()!=null)
			associationConstraint.getAssociationType().eAdapters().remove(this);
		super.deactivate();
	}
	
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopCircleAngle(getFigure());
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
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE) {
				if (notification.getOldValue()!=null)
					((EObject)notification.getOldValue()).eAdapters().remove(this);
				
				if (notification.getNewValue()!=null)
					((EObject)notification.getNewValue()).eAdapters().add(this);
			}
			refreshVisuals();
		}
	}	

	/**
	 * This anchor works like the ChobBoxAnchor, but instead of
	 * using a box it uses a circle
	 * @author Hannes Niederhausen
	 *
	 */
	private class ChopCircleAngle extends AbstractConnectionAnchor {

		public ChopCircleAngle(IFigure owner) {
			setOwner(owner);
		}
		
		@Override
		public Point getLocation(Point reference) {
			Rectangle bounds = Rectangle.SINGLETON; 
			bounds.setBounds(getOwner().getBounds());

			getOwner().translateToAbsolute(bounds);
			
			int radius = bounds.width/2;
			Point middlePoint = new Point(bounds.x+radius, bounds.y+radius);
			
			
			
			double dx = reference.x-middlePoint.x;
			double dy = reference.y-middlePoint.y;
			
			double length = Math.sqrt((dx*dx+dy*dy));
			dx /= length;
			dy /= length;
			
			
			Point result = new Point();
			
			result.x = (int) (middlePoint.x+radius*dx);
			result.y = (int) (middlePoint.y+radius*dy);
			
			return result;
		}
		
	}

}
