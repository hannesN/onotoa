/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class AdapterGraphicalEditPart extends AbstractGraphicalEditPart implements Adapter{

	private Notifier target;
	
	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}
	
	@Override
	public void activate() {
		super.activate();
		if (getModel() instanceof EObject) {
			EObject obj = (EObject) getModel();
			obj.eAdapters().add(this);
		
		}
	}
	
	@Override
	public void deactivate() {
		if (getModel() instanceof EObject) {
			EObject obj = (EObject) getModel();
			obj.eAdapters().remove(this);
		
		}
		super.deactivate();
	}
}
