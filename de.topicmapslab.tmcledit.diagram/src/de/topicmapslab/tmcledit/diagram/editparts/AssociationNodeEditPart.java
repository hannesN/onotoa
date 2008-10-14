/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.GraphicalEditPart;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.Node;

/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationNodeEditPart extends NodeEditPart{

	@Override
	protected IFigure createFigure() {
		Ellipse figure = new Ellipse();
		
		figure.setSize(40, 40);
		
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
		Node node = getCastedModel();
		Rectangle r = new Rectangle(node.getPosX(), node.getPosY(), -1, -1);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
		super.refreshVisuals();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List getModelSourceConnections() {
		// TODO Auto-generated method stub
		return super.getModelSourceConnections();
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__EDGES)
			refreshSourceConnections();
		if (notification.getNotifier()==getModel())
			refreshVisuals();
	}	
	

}
