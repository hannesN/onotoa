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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
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

	private Map<TopicType, List<TypeNode>> typeNodeMap;
	private Map<AssociationTypeConstraint, List<AssociationNode>> assNodeMap;
	private File file;
	
	
	public TopicTypeNodeIndexer() {
		
	}
	
	public void init(File file) {
		this.file = file;
		typeNodeMap = new HashMap<TopicType, List<TypeNode>>();
		assNodeMap = new HashMap<AssociationTypeConstraint, List<AssociationNode>>();
		file.eAdapters().add(this);
		
		for (Diagram d : file.getDiagrams()) {
			d.eAdapters().add(this);
			for (Node node : d.getNodes()) {
				if (node instanceof TypeNode) {
					addTypeNode((TypeNode) node);
				} else {
					addAssociationNode((AssociationNode) node);
				}
			}
		}
	}

	private void addAssociationNode(AssociationNode node) {
		List<AssociationNode> list = assNodeMap.get(node.getAssociationConstraint());
		if (list == null) {
			list = new ArrayList<AssociationNode>(2);
			assNodeMap.put(node.getAssociationConstraint(), list);
		}
		list.add(node);
	}

	private void addTypeNode(TypeNode node) {
		List<TypeNode> list = typeNodeMap.get(node.getTopicType());
		if (list == null) {
			list = new ArrayList<TypeNode>(2);
			typeNodeMap.put(node.getTopicType(), list);
		}
		list.add(node);
	}
	
	private void removeTypeNode(TypeNode node) {
		List<TypeNode> list = typeNodeMap.get(node.getTopicType());
		if (list==null) {
			return; // shouldn't happen
		}
		list.remove(node);
		// now some clean up so we don't have entries with an empty list
		if (list.isEmpty())
			typeNodeMap.remove(node.getTopicType());
	}

	private void removeAssociationNode(AssociationNode node) {
		List<AssociationNode> list = assNodeMap.get(node.getAssociationConstraint());
		if (list==null) {
			return; // shouldn't happen
		}
		list.remove(node);
		// now some clean up so we don't have entries with an empty list
		if (list.isEmpty())
			assNodeMap.remove(node.getAssociationConstraint());
	}
	
	@SuppressWarnings("unchecked")
	private void handleDiagramNotification(Notification msg) {
		if (msg.getFeatureID(EList.class)==ModelPackage.DIAGRAM__NODES) {
			if (msg.getEventType()==Notification.ADD) {
				if (msg.getNewValue() instanceof TypeNode) {
					TypeNode tmp = (TypeNode) msg.getNewValue();
					addTypeNode(tmp);
				}  else {
					addAssociationNode((AssociationNode) msg.getNewValue());
				}
				return;
			}
			if (msg.getEventType()==Notification.ADD_MANY) {
				for (Object tmpObj : (Collection) msg.getNewValue()) {
					if (tmpObj instanceof TypeNode) {
						TypeNode tmp = (TypeNode) tmpObj;
						addTypeNode(tmp);
					} else {
						addAssociationNode((AssociationNode) tmpObj);
					}
				}
				return;
			}
			if (msg.getEventType()==Notification.REMOVE) {
				if (msg.getOldValue() instanceof TypeNode) {
					removeTypeNode((TypeNode) msg.getOldValue());
				} else {
					removeAssociationNode((AssociationNode) msg.getOldValue());
				}
				return;
			}
			if (msg.getEventType()==Notification.REMOVE_MANY) {
				for (Object tmpObj : (Collection) msg.getOldValue()) {
					if (tmpObj instanceof TypeNode) {
						removeTypeNode((TypeNode) tmpObj);
					} else {
						removeAssociationNode((AssociationNode) tmpObj);
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
				((Diagram)msg.getOldValue()).eAdapters().remove(this);
			}
			if (msg.getEventType()==Notification.REMOVE_MANY) {
				for (Diagram d : (Collection<Diagram>) msg.getOldValue())
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

	public void dispose() {
		if (file!=null) {
			file.eAdapters().remove(this);
			for (Diagram d : file.getDiagrams()) {
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
	public Node getNodeFor(TopicType topicType, Diagram diagram) {
		List<TypeNode> list = typeNodeMap.get(topicType);
		if (list!=null) {
			for (TypeNode tn : list) {
				if (diagram.equals(tn.eContainer()))
					return tn;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param topicType the topic type which is represented by the searched node
	 * @param diagram the diagram, containg the node
	 * @return <code>null</code> if non found, else the instance of the node
	 */
	public Node getNodeFor(AssociationTypeConstraint assConstraint, Diagram diagram) {
		List<AssociationNode> list = assNodeMap.get(assConstraint);
		if (list==null)
			return null;
		for (AssociationNode an : list) {
			if (diagram.equals(an.eContainer()))
				return an;
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
	
	public List<Edge> getEdgesUsingTopicType(TopicType type) {
		List<Edge> result = new ArrayList<Edge>();
		
		for (Diagram d : file.getDiagrams()) {
			TypeNode node = (TypeNode) getNodeFor(type, d);
			if (node==null)
				continue;
			
			for (Edge e : d.getEdges()) {
				if ((e.getSource().equals(node)) || (e.getTarget().equals(node)))
					result.add(e);
			}
		}
		return result;
	}
}
