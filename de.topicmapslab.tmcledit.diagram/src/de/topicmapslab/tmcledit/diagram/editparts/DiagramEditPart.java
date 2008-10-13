/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import de.topicmapslab.tmcledit.diagram.policies.DiagramLayoutEditPolicy;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DiagramEditPart extends AdapterGraphicalEditPart {

	private XYLayout layout;

	private PrefixMappingEditPart prefixMappingEditPart;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure = new FreeformLayer();
			figure.setOpaque(true);
			figure.setBackgroundColor(ColorConstants.white);
			layout = new FreeformLayout();
			figure.setLayoutManager(layout);
		}
		return figure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DiagramLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
		
	}
	
	// tiny hack to omit a mess of listeners/adapters
	
	@Override
	protected void addChild(EditPart child, int index) {
		super.addChild(child, index);
		if (child instanceof PrefixMappingEditPart) {
			this.prefixMappingEditPart = (PrefixMappingEditPart) child;
		}
	}

	public PrefixMappingEditPart getPrefixMappingEditPart() {
		return prefixMappingEditPart;
	}
	// hack end
	
	@SuppressWarnings("unchecked")
	@Override
	protected List getModelChildren() {
		Diagram d = (Diagram) getModel();
		List result = new ArrayList();
		result.addAll(d.getNodes());
		result.add(((File)d.eContainer()).getTopicMapSchema().getMappings());
		
		return result;
	}

	@SuppressWarnings("unchecked")
	private void updateEdges(Edge edge) {
		// find editparts for edges nodes
		TypeNodeEditPart sep = null;
		TypeNodeEditPart tep = null;
		Iterator it = getChildren().iterator();
		while ((it.hasNext()) && ((sep == null) || (tep == null))) {
			Object nextObj = it.next();
			if (nextObj instanceof TypeNodeEditPart) {
				TypeNodeEditPart tmpEp = (TypeNodeEditPart) nextObj;
				if (tmpEp.getModel().equals(edge.getSource()))
					sep = tmpEp;
				if (tmpEp.getModel().equals(edge.getTarget()))
					tep = tmpEp;
			}
		}
		
		if ((sep!=null) && (tep!=null)) {
			sep.refresh();
			tep.refresh();
		}
		
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__EDGES) {
			if (notification.getEventType()==Notification.ADD) {
				updateEdges((Edge) notification.getNewValue());
			}
		} else if (notification.getFeatureID(EList.class)==ModelPackage.DIAGRAM__NODES) {
			refreshChildren();	
		}
	}

}
