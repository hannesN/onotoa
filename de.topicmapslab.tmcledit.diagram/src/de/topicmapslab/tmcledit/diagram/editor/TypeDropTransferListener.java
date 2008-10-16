package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import de.topicmapslab.tmcledit.diagram.editparts.NodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class TypeDropTransferListener extends AbstractTransferDropTargetListener {

	private TypeNodeCreationFactory nodeFac = new TypeNodeCreationFactory();
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
		
		if (dropedType.getKind()==KindOfTopicType.TOPIC_TYPE) {
			nodeFac.setTopicType(dropedType);
			req.setFactory(nodeFac);
		} else if (dropedType.getKind()==KindOfTopicType.OCCURENCE_TYPE) {
			occFac.setTopicType(dropedType);
			req.setFactory(occFac);
		}
		
		
		super.handleDrop();
	}
	
	private class TypeNodeCreationFactory implements CreationFactory {

		private TopicType topicType;
		
		@Override
		public Object getNewObject() {
			TypeNode tn = ModelFactory.eINSTANCE.createTypeNode();
			if (topicType==null)
				topicType = de.topicmapslab.tmcledit.model.ModelFactory.eINSTANCE.createTopicType();
			tn.setTopicType(topicType);
			
			return tn;
		}

		public void setTopicType(TopicType topicType) {
			this.topicType = topicType;
		}
		
		@Override
		public Object getObjectType() {
			return TypeNode.class;
		}
	};
	
	private class OccurenceConstraintCreationFactory implements CreationFactory {

		private TopicType occurenceType;
		
		@Override
		public Object getNewObject() {
			OccurenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurenceTypeConstraint();
			if (occurenceType==null) {
				occurenceType = de.topicmapslab.tmcledit.model.ModelFactory.eINSTANCE.createTopicType();
				occurenceType.setId("foo:bar");
				occurenceType.setKind(KindOfTopicType.OCCURENCE_TYPE);
			}
			otc.setType(occurenceType);
			
			
			return otc;
		}

		public void setTopicType(TopicType topicType) {
			this.occurenceType = topicType;
		}
		
		@Override
		public Object getObjectType() {
			return OccurenceTypeConstraint.class;
		}
	};
}
