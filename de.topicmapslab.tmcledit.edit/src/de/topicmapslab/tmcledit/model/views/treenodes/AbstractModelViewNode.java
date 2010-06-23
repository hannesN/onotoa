package de.topicmapslab.tmcledit.model.views.treenodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.views.ModelView;

public class AbstractModelViewNode implements IAdaptable {

	protected String name;
	protected EditingDomain editingDomain;
	private AbstractModelViewNode parent;
	private boolean syncView = false;
	protected boolean handleRename = false;
	protected int id = -1;
	private final ModelView modelView;
	private Object model;
	protected List<AbstractModelViewNode> children;

	public AbstractModelViewNode(ModelView modelView) {
		super();
		this.modelView = modelView;
	}

	public String getName() {
    	return name;
    }

	protected ModelView getModelView() {
    	return modelView;
    }

	public void setParent(AbstractModelViewNode parent) {
    	this.parent = parent;
    	if (parent != null)
    		this.syncView = parent.isSyncView();
    }

	public int getId() {
        return id;
    }

	public AbstractModelViewNode getParent() {
    	return parent;
    }

	public Object getAdapter(Class key) {
    	return null;
    }

	public Object getModel() {
    	return model;
    }

	public void setModel(Object model) {
    	dispose();
    	this.model = model;
    }

	public void dispose() {
    	for (AbstractModelViewNode to : getChildrenList()) {
    		to.dispose();
    	}
    }

	public Image getImage() {
    	return null;
    }

	public void refresh() {
    	if (syncView)
    		getModelView().getViewer().refresh(this);
    }

	protected boolean isSyncView() {
    	return syncView;
    }

	public void handleRename() {
    
    }

	public boolean canHandleRename() {
    	return handleRename;
    }

	public void addChild(AbstractModelViewNode child) {
    	if (children==null)
    		children = new ArrayList<AbstractModelViewNode>();
    	
    	children.add(child);
    	child.setParent(this);
    }

	public void removeChild(AbstractModelViewNode child) {
    	if (children==null)
    		return;
    	children.remove(child);
    	child.setParent(null);
    }

	public TreeObject[] getChildren() {
    	return (TreeObject[]) children.toArray(new TreeObject[children
    			.size()]);
    }

	public List<AbstractModelViewNode> getChildrenList() {
    	if (children==null)
    		return Collections.emptyList();
    	return children;
    }

	public boolean hasChildren() {
    	return getChildrenList().size() > 0;
    }

	public void setSyncView(boolean syncView) {
        this.syncView = syncView;
        for (AbstractModelViewNode child : getChildrenList()) {
        	child.setSyncView(syncView);
        }
    }

	protected void clearChildren() {
        for (Iterator<AbstractModelViewNode> it=getChildrenList().iterator(); it.hasNext();) {
        	AbstractModelViewNode child = it.next();
    		it.remove();
    		child.dispose();
    	}
    }
	
	public AbstractModelViewNode findChildPerModel(Object childModel) {
		for (AbstractModelViewNode o : getChildrenList()) {
			if (childModel.equals(o.getModel()))
				return o;
		}
		return null;
	}

	public EditingDomain getEditingDomain() {
	    return editingDomain;
    }
}