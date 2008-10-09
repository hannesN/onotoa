package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class TypeDropTransferListener extends AbstractTransferDropTargetListener {

	private TypeNodeCreationFactory fac = new TypeNodeCreationFactory();

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
		((CreateRequest)getTargetRequest()).setLocation(getDropLocation());
	}

	@Override
	protected Request createTargetRequest() {
		CreateRequest req = new CreateRequest();
		req.setFactory(fac);

		return req;
	}

	@Override
	protected void handleDragOver() {
		getCurrentEvent().detail = DND.DROP_COPY;
		super.handleDragOver();
	}

	@Override
	protected void handleDrop() {
		String objId = (String) getCurrentEvent().data;
		fac.setTopicType(null);
		
		for (TopicType tt : schema.getTopicTypes()) {
			if (tt.toString().equals(objId))
				fac.setTopicType(tt);
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
}
