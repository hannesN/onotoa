package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import de.topicmapslab.tmcledit.diagram.directedit.TMCLDirectEditManager;
import de.topicmapslab.tmcledit.diagram.figures.EditableLabel;
import de.topicmapslab.tmcledit.diagram.figures.SelectionFigure;

public abstract class AbstractLabelEditPart extends AdapterGraphicalEditPart {

	private DirectEditManager manager;
	private EditableLabel nameLabel;
	private Label typeLabel;
	

	public AbstractLabelEditPart() {
		super();
	}

	@Override
	protected IFigure createFigure() {
	
		figure = new SelectionFigure();
		
		ToolbarLayout layout = new ToolbarLayout(true);
		layout.setSpacing(5);
		figure.setLayoutManager(layout);
		
		nameLabel = new EditableLabel("");
		figure.add(nameLabel);
		
		typeLabel = new Label();
		figure.add(typeLabel);
		
		return figure;
	}

	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		
		SelectionFigure f = (SelectionFigure) getFigure();
		if (value==EditPart.SELECTED_NONE) {
			f.setSelected(false);
		} else {
			f.setSelected(true);
		}
		
		
	}

	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if (req instanceof DirectEditRequest) {
				Label label = directEditHitTest(((DirectEditRequest) req).getLocation().getCopy());
				if (label!=null) {
					fillExtendedData(req.getExtendedData());
					performDirectEdit(label);
					
				}
			}
		}
		super.performRequest(req);
	}

	@SuppressWarnings("unchecked")
	protected void fillExtendedData(Map extendedData) {		
	}

	private void performDirectEdit(Label label) {
		if (manager == null) {
			manager = new TMCLDirectEditManager(this, TextCellEditor.class, label);					
		}
		manager.show();
	}

	protected Label directEditHitTest(Point requestLoc) {
		
		getNameLabel().translateToRelative(requestLoc);
		if (getNameLabel().containsPoint(requestLoc))
			return getNameLabel();
		
		return null;
	}

	public DirectEditManager getManager() {
		return manager;
	}

	public EditableLabel getNameLabel() {
		return nameLabel;
	}

	public Label getTypeLabel() {
		return typeLabel;
	}
	
	public void revertNameChange() {
		figure.setVisible(true);
		refreshVisuals();
	}
	
	public void handleNameChange(String value) {
		getNameLabel().setText(value);
		figure.setVisible(false);
		refreshVisuals();
	}

}