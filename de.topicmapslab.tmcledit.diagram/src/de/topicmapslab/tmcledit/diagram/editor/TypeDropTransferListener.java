/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import de.topicmapslab.tmcledit.diagram.creationfactories.OccurenceConstraintCreationFactory;
import de.topicmapslab.tmcledit.diagram.creationfactories.TypeNodeCreationFactory;
import de.topicmapslab.tmcledit.diagram.editparts.NodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class TypeDropTransferListener extends AbstractTransferDropTargetListener {

	private TypeNodeCreationFactory nodeFac = new TypeNodeCreationFactory(true);
	private OccurenceConstraintCreationFactory occFac = new OccurenceConstraintCreationFactory();

	private final TopicMapSchema schema;
	
	public TypeDropTransferListener(EditPartViewer viewer, Diagram diagram) {
		super(viewer);
		this.schema = ((File)diagram.eContainer()).getTopicMapSchema();
	}
		
	@Override
	public Transfer getTransfer() {
		return TextTransfer.getInstance();
	}
		
	@Override
	protected void updateTargetRequest() {
		CreateRequest req = ((CreateRequest)getTargetRequest());
		req.setLocation(getDropLocation());
		EditPart part = getViewer().findObjectAt(getDropLocation());
		if (part instanceof NodeEditPart) {
			req.setFactory(occFac);
		} else {
			req.setFactory(nodeFac);
		}
	}

	@Override
	protected Request createTargetRequest() {
		CreateRequest req = new CreateRequest();
		req.setFactory(nodeFac);

		return req;
	}

	@Override
	protected void handleDragOver() {
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOver();
	}

	@Override
	protected void handleDrop() {
		CreateRequest req = ((CreateRequest)getTargetRequest());
		
		String objId = (String) getCurrentEvent().data;
		nodeFac.setTopicType(null);
		
		TopicType dropedType = null;
		for (TopicType tt : schema.getTopicTypes()) {
			if (tt.toString().equals(objId))
				dropedType = tt;
		}
		
		
			nodeFac.setTopicType(dropedType);
			req.setFactory(nodeFac);
		  
		if (dropedType.getKind()==KindOfTopicType.OCCURENCE_TYPE) {
			occFac.setTopicType(dropedType);
			req.setFactory(occFac);
		} 
		
		
		super.handleDrop();
	}
	

}
