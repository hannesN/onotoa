package de.topicmapslab.tmcledit.diagram;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.Edge;
import de.topicmapslab.tmcledit.diagram.model.EdgeType;
import de.topicmapslab.tmcledit.diagram.model.ModelFactory;
import de.topicmapslab.tmcledit.diagram.model.Node;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * This controller listens to model changes and creates or removes edges if
 * necessary
 * 
 * @author Hannes Niederhausen
 * 
 */
public class DiagramController implements Adapter {

	private Notifier target;
	private final Diagram diagram;

	public DiagramController(Diagram diagram) {
		this.diagram = diagram;
		init();
	}

	private void init() {
		TopicMapSchema schema = diagram.getTopicMapSchema();
		schema.eAdapters().add(this);
		for (TopicType type : schema.getTopicTypes()) {
			type.eAdapters().add(this);
		}
	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return true;
	}

	@Override
	public void notifyChanged(Notification notification) {

		if ((notification.getNewValue() instanceof TopicType)
				&& (notification.getNotifier() instanceof TopicMapSchema)) {
			if (notification.getEventType() == Notification.ADD) {
				((TopicType) notification.getNewValue()).eAdapters().add(this);
			} else if (notification.getEventType() == Notification.REMOVE) {
				((TopicType) notification.getOldValue()).eAdapters().remove(
						this);
			}
		}
		if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__ISA) {
			if (notification.getEventType() == Notification.ADD) {
				addNewIsAEdge((TopicType) notification.getNotifier(),
						(TopicType) notification.getNewValue());
			} else {
				if (notification.getEventType() == Notification.REMOVE) {
					removeIsAEdge((TopicType) notification.getNotifier(),
							(TopicType) notification.getOldValue());
				}
			}

		}

	}

	private void removeIsAEdge(TopicType source, TopicType target) {
		List<Edge> removableEdges = new ArrayList<Edge>();
		for (Edge e : diagram.getEdges()) {
			if (e.getType() == EdgeType.IS_ATYPE) {
				TypeNode s = (TypeNode) e.getSource();
				if (s.getTopicType().equals(source)) {
					TypeNode t = (TypeNode) e.getTarget();
					if (t.getTopicType().equals(target)) {
						removableEdges.add(e);
					}
				}
			}
		}
		diagram.getEdges().removeAll(removableEdges);

	}

	private void addNewIsAEdge(TopicType source, TopicType target) {
		TypeNode s = null;
		TypeNode t = null;
		Iterator<Node> it = diagram.getNodes().iterator();

		while ((it.hasNext()) && ((s == null) || (t == null))) {
			Node n = it.next();
			if (n instanceof TypeNode) {
				TypeNode tn = (TypeNode) n;
				if (tn.getTopicType().equals(source))
					s = tn;
				else if (tn.getTopicType().equals(target))
					t = tn;
			}
		}

		if ((s != null) && (t != null)) {
			// check if an edge exists
			boolean found = false;
			for (Edge e : diagram.getEdges()) {
				if ((e.getSource().equals(s) && (e.getTarget().equals(t))))
					found = true;
			}
			if (!found) {
				Edge e = ModelFactory.eINSTANCE.createEdge();
				e.setType(EdgeType.IS_ATYPE);
				e.setSource(s);
				e.setTarget(t);
				diagram.getEdges().add(e);
			}
		}
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

}
