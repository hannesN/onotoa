/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * As the name suggests, this class is the indexer for topic type nodes
 * 
 * @author Hannes Niederhausen
 *
 */
public class TopicTypeNodeIndexer extends AdapterImpl{

	private Map<TopicType, List<TypeNode>> nodeMap;
	private File file;
	
	
	public TopicTypeNodeIndexer() {
		
	}
	
	public void init(File file) {
		nodeMap = new HashMap<TopicType, List<TypeNode>>();
		file.eAdapters().add(this);
		for (Diagram d : file.getDiagrams()) {
			d.eAdapters().add(this);
			for (Node node : d.getNodes()) {
				if (node instanceof TypeNode) {
					TypeNode tmp = (TypeNode) node;
					List<TypeNode> list = nodeMap.get(tmp.getTopicType());
					if (list == null) {
						list = new ArrayList<TypeNode>(2);
						nodeMap.put(tmp.getTopicType(), list);
					}
					list.add(tmp);
				}
			}
		}
	}
	
	public void dispose() {
		if (file!=null) {
			file.eAdapters().remove(this);
			for (Diagram d : file.getDiagrams()) {
				d.eAdapters().remove(this);
			}
				
		}
	}
	
	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getEventType()==Notification.REMOVING_ADAPTER) {
			return;
		}
		
		if (msg.getNotifier().equals(file)) {
			handleFileNotification(msg);
		} else if (msg.getNotifier() instanceof Diagram) {
			handleDiagramNotification(msg);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void handleDiagramNotification(Notification msg) {
		if (msg.getFeatureID(EList.class)==ModelPackage.DIAGRAM__NODES) {
			if (msg.getEventType()==Notification.ADD) {
				if (msg.getNewValue() instanceof TypeNode) {
					TypeNode tmp = (TypeNode) msg.getNewValue();
					List<TypeNode> list = nodeMap.get(tmp.getTopicType());
					if (list==null) {
						list=new ArrayList<TypeNode>(2);
						nodeMap.put(tmp.getTopicType(), list);
					}
					list.add(tmp);
				}
				return;
			}
			if (msg.getEventType()==Notification.ADD_MANY) {
				for (Object tmpObj : (Collection) msg.getNewValue()) {
					if (tmpObj instanceof TypeNode) {
						TypeNode tmp = (TypeNode) tmpObj;
						List<TypeNode> list = nodeMap.get(tmp.getTopicType());
						if (list==null) {
							list=new ArrayList<TypeNode>(2);
							nodeMap.put(tmp.getTopicType(), list);
						}
						list.add(tmp);
					}
				}
				return;
			}
			if (msg.getEventType()==Notification.REMOVE) {
				if (msg.getOldValue() instanceof TypeNode) {
					TypeNode tmp = (TypeNode) msg.getNewValue();
					List<TypeNode> list = nodeMap.get(tmp.getTopicType());
					if (list==null) {
						return; // shouldn't happen
					}
					list.remove(tmp);
					// now some clean up so we don't have entries with an empty list
					if (list.isEmpty())
						nodeMap.remove(tmp.getTopicType());
					
				}
				return;
			}
			if (msg.getEventType()==Notification.REMOVE_MANY) {
				for (Object tmpObj : (Collection) msg.getOldValue()) {
					if (tmpObj instanceof TypeNode) {
						TypeNode tmp = (TypeNode) tmpObj;
						List<TypeNode> list = nodeMap.get(tmp.getTopicType());
						if (list==null) {
							return; // shouldn't happen
						}
						list.remove(tmp);
						// now some clean up so we don't have entries with an empty list
						if (list.isEmpty())
							nodeMap.remove(tmp.getTopicType());
						
					}
				}
				return;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void handleFileNotification(Notification msg) {
		if (msg.getFeatureID(EList.class)==ModelPackage.FILE__DIAGRAMS) {
			if (msg.getEventType()==Notification.ADD) {
				((Diagram)msg.getNewValue()).eAdapters().add(this);
			}
			if (msg.getEventType()==Notification.ADD_MANY) {
				for (Diagram d : (Collection<Diagram>) msg.getNewValue())
					d.eAdapters().add(this);
			}
			if (msg.getEventType()==Notification.REMOVE) {
				((Diagram)msg.getNewValue()).eAdapters().remove(this);
			}
			if (msg.getEventType()==Notification.REMOVE_MANY) {
				for (Diagram d : (Collection<Diagram>) msg.getNewValue())
					d.eAdapters().remove(this);
			}
		}
	}

	/**
	 * 
	 * @param topicType the topic type which is represented by the searched node
	 * @param diagram the diagram, containg the node
	 * @return <code>null</code> if non found, else the instance of the node
	 */
	public TypeNode getNodeFor(TopicType topicType, Diagram diagram) {
		List<TypeNode> list = nodeMap.get(topicType);
		for (TypeNode tn : list) {
			if (tn.eContainer().equals(diagram))
				return tn;
		}
		
		return null;
	}

	public List<Edge> getEdges(Diagram d, EdgeType type) {
		List<Edge> result = new ArrayList<Edge>();
		
		for (Edge e : d.getEdges()) {
			if (e.getType()==type) {
				result.add(e);
			}
		}
		
		return result;
	}
}
