package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Map;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;

import de.topicmapslab.tmcledit.diagram.policies.PrefixMappingElementEditPolicy;
import de.topicmapslab.tmcledit.model.MappingElement;

public class PrefixMappingElementEditPart extends AbstractLabelEditPart {

	// helper variable to set the direct edit request extended data map
	private boolean editingKey;
	
	@Override
	protected void createEditPolicies() {

	}

	@Override
	protected Label directEditHitTest(Point requestLoc) {
		Label result = super.directEditHitTest(requestLoc.getCopy());
		
		if (result == null) {
			getTypeLabel().translateToRelative(requestLoc);
			if (getTypeLabel().containsPoint(requestLoc)) {
				result = getTypeLabel();
				editingKey = false;
			}
		} else {
			editingKey = true;
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void fillExtendedData(Map extendedData) {
		if (editingKey)
			extendedData.put(PrefixMappingElementEditPolicy.EXT_EDITED,
					PrefixMappingElementEditPolicy.EXT_EDITED_KEY);
		else
			extendedData.put(PrefixMappingElementEditPolicy.EXT_EDITED,
					PrefixMappingElementEditPolicy.EXT_EDITED_VALUE);
	}
	
	@Override
	protected void refreshVisuals() {
		getNameLabel().setText(getCastedModel().getKey());
		getTypeLabel().setText(getCastedModel().getValue());
	}
	
	private MappingElement getCastedModel() {
		return (MappingElement) getModel();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		refreshVisuals();
	}

}
