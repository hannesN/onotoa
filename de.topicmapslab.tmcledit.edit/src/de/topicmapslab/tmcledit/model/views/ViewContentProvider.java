package de.topicmapslab.tmcledit.model.views;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.extension.ModelViewExtensionInfo;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeAssocConstraint;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeDiagram;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeItemIdentifier;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeName;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeOccurrence;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectIdentifier;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectLocator;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeTopic;

class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	/**
     * 
     */
    private final ModelView modelView;

    private File currentFile;
    
	/**
     * @param modelView
     */
    ViewContentProvider(ModelView modelView) {
        this.modelView = modelView;
    }

	private AbstractModelViewNode invisibleRoot;
	private AbstractModelViewNode diagramNode;
	private AbstractModelViewNode schemaNode;

	private AdapterImpl tmsListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof TopicMapSchema) {
				if (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addType((TopicType) msg.getNewValue(), true);
						break;
					case Notification.REMOVE:
						removeType((TopicType) msg.getOldValue(), true);
						break;
					}
				} else if (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addAssocContraint((AssociationTypeConstraint) msg.getNewValue());
						break;
					case Notification.REMOVE:
						removeAssocContraint((AssociationTypeConstraint) msg.getOldValue());
						break;
					}
				}
			} else if ((msg.getNotifier() instanceof File)
			        && (msg.getFeatureID(EList.class) == ModelPackage.FILE__DIAGRAMS)) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					addDiagram((Diagram) msg.getNewValue());
					break;
				case Notification.REMOVE:
					removeDiagram((Diagram) msg.getOldValue());
					break;
				}
			}
		}
	};

	private TreeObject ttNode;
	private TreeObject rtNode;
	private TreeObject ntNode;
	private TreeObject otNode;
	private TreeObject atNode;
	private TreeObject acNode;

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		if (parent.equals(this.modelView.getViewSite())) {
			if (invisibleRoot == null)
				initialize();
			return getChildren(invisibleRoot);
		}
		return getChildren(parent);
	}

	public Object getParent(Object child) {
		if (child instanceof AbstractModelViewNode) {
			return ((AbstractModelViewNode) child).getParent();
		}
		return null;
	}

	public Object[] getChildren(Object parent) {
		if (parent instanceof AbstractModelViewNode) {
			return ((AbstractModelViewNode) parent).getChildren();
		}
		return new Object[0];
	}

	public boolean hasChildren(Object parent) {
		if (parent instanceof AbstractModelViewNode)
			return ((AbstractModelViewNode) parent).hasChildren();
		return false;
	}

	public void uninitialize() {
		if (currentFile != null) {
			currentFile.getTopicMapSchema().eAdapters().remove(tmsListener);
			currentFile.eAdapters().remove(tmsListener);
		}
		invisibleRoot.dispose();
	}

	public void initialize() {
		TopicMapSchema schema = this.modelView.getCurrentTopicMapSchema();
        if (schema!=null)
        	currentFile = (File) schema.eContainer();
        else
        	currentFile = null;
        
        if (currentFile != null) {
			currentFile.getTopicMapSchema().eAdapters().add(tmsListener);
			currentFile.eAdapters().add(tmsListener);
		}
		update();
	}

	public void update() {

		invisibleRoot = new TreeObject(this.modelView, "");
		if (currentFile != null) {
			schemaNode = new TreeObject(this.modelView, "Topic Map Schema", TreeObject.TOPIC_MAP_SCHEMA);

			schemaNode.setModel(currentFile.getTopicMapSchema());
			diagramNode = new TreeObject(this.modelView, "Diagrams", TreeObject.DIAGRAMS);

			invisibleRoot.addChild(diagramNode);
			invisibleRoot.addChild(schemaNode);

			ttNode = new TreeObject(this.modelView, "TopicTypes", KindOfTopicType.TOPIC_TYPE);
			rtNode = new TreeObject(this.modelView, "RoleTypes", KindOfTopicType.ROLE_TYPE);
			ntNode = new TreeObject(this.modelView, "NameTypes", KindOfTopicType.NAME_TYPE);
			otNode = new TreeObject(this.modelView, "OccurrenceTypes", KindOfTopicType.OCCURRENCE_TYPE);
			atNode = new TreeObject(this.modelView, "AssociationTypes", KindOfTopicType.ASSOCIATION_TYPE);

			acNode = new TreeObject(this.modelView, "Association Constraints");

			schemaNode.addChild(ttNode);
			schemaNode.addChild(rtNode);
			schemaNode.addChild(ntNode);
			schemaNode.addChild(otNode);
			schemaNode.addChild(atNode);
			schemaNode.addChild(acNode);

			for (TopicType tt : currentFile.getTopicMapSchema().getTopicTypes()) {
				addType(tt, false);
			}

			for (Diagram d : currentFile.getDiagrams()) {
				diagramNode.addChild(new TreeDiagram(this.modelView, d));
			}

			for (AssociationTypeConstraint ac : currentFile.getTopicMapSchema().getAssociationTypeConstraints()) {
				addAssocContraint(ac);
			}

			if (!this.modelView.viewer.isBusy())
				this.modelView.viewer.refresh();
			invisibleRoot.setSyncView(true);
		} else {
			TreeObject root = new TreeObject(this.modelView, "No Diagramm Editor Open", KindOfTopicType.TOPIC_TYPE);
			invisibleRoot.addChild(root);
		}
	}

	public AbstractModelViewNode findNodeFor(Object model) {
		return findNodeFor(invisibleRoot, model);
	}
	
	private AbstractModelViewNode findNodeFor(AbstractModelViewNode node, Object model) {
		
		if (model.equals(node.getModel())) {
			return node;
		}
		
		AbstractModelViewNode result = null;
		for (AbstractModelViewNode n : node.getChildrenList()) {
			result = findNodeFor(n, model);
			if (result!=null)
				break;
		}
		
		return result;
	}
	
	private void addAssocContraint(AssociationTypeConstraint constraint) {
		TreeAssocConstraint node = new TreeAssocConstraint(this.modelView, constraint);
		acNode.addChild(node);
		acNode.refresh();
	}

	private void removeAssocContraint(AssociationTypeConstraint constraint) {
		AbstractModelViewNode child = acNode.findChildPerModel(constraint);
		acNode.removeChild(child);
		child.dispose();
		acNode.refresh();
	}

	private void addDiagram(Diagram diagram) {
		diagramNode.addChild(new TreeDiagram(this.modelView, diagram));
		diagramNode.refresh();
	}

	private void removeDiagram(Diagram diagram) {
		AbstractModelViewNode child = diagramNode.findChildPerModel(diagram);
		diagramNode.removeChild(child);
		child.dispose();
		diagramNode.refresh();

	}

	private void addType(TopicType tt, boolean refresh) {
		TreeTopic to = new TreeTopic(this.modelView, tt);
		TreeObject parent = null;

		parent = getParentNode(tt);

		if (parent != null) {
			parent.setSyncView(refresh);
			parent.addChild(to);
			for (NameTypeConstraint ntc : tt.getNameConstraints()) {
				TreeName tn = new TreeName(this.modelView, ntc);
				to.addChild(tn);
				getExtensionChildren(tn);
			}
			for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
				TreeOccurrence tocc = new TreeOccurrence(this.modelView, otc);
				to.addChild(tocc);
				getExtensionChildren(tocc);
			}
			for (ItemIdentifierConstraint iic : tt.getItemIdentifierConstraints()) {
				to.addChild(new TreeItemIdentifier(this.modelView, iic));
			}
			for (SubjectIdentifierConstraint sic : tt.getSubjectIdentifierConstraints()) {
				to.addChild(new TreeSubjectIdentifier(this.modelView, sic));
			}
			for (SubjectLocatorConstraint slc : tt.getSubjectLocatorConstraints()) {
				to.addChild(new TreeSubjectLocator(this.modelView, slc));
			}
			
			getExtensionChildren(to);
			
			parent.refresh();
			parent.setSyncView(true);
		}
	}

	private void removeType(TopicType tt, boolean refresh) {
		TreeObject parent = ttNode;

		parent = getParentNode(tt);

		AbstractModelViewNode to = parent.findChildPerModel(tt);
		parent.removeChild(to);
		to.dispose();

		parent.setSyncView(refresh);
		parent.refresh();
		parent.setSyncView(true);
	}

	private TreeObject getParentNode(TopicType topicType) {
		TreeObject parent;
		switch (topicType.getKind()) {
		case ROLE_TYPE:
			parent = rtNode;
			break;
		case NAME_TYPE:
			parent = ntNode;
			break;
		case OCCURRENCE_TYPE:
			parent = otNode;
			break;
		case ASSOCIATION_TYPE:
			parent = atNode;
			break;
		default:
			parent = ttNode;
			break;
		}
		return parent;
	}
	
	private void getExtensionChildren(AbstractModelViewNode parent) {
		for (ModelViewExtensionInfo mvei : TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos()) {
			for (AbstractModelViewNode n : mvei.getProvider().getChildNodes(modelView, parent)) {
				parent.addChild(n);
			}
		}
	}
}