package de.topicmapslab.tmcledit.model.views.treenodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.PropertyDetailView;


/**
 * The super class of every node in the model view.
 * <p>
 * Extensions for the {@link ModelView} must extend this class.
 * </p>
 * <p>Note: Two nodes are euqal if there parent and model are equal.</p> 
 * 
 * @author Hannes Niederhausen
 *
 */
public class AbstractModelViewNode implements IAdaptable {

	/**
	 * The node name, which will be used as label for the node.
	 */
	protected String name;
	
	/**
	 * The parent node.
	 */
	private AbstractModelViewNode parent;
	
	/**
	 * Flag which indicates if the node should be updated after an operation in the command stack
	 */
	private boolean syncView = false;
	
	/**
	 * Flag which indicates that a rename operation of this node is supported
	 */
	protected boolean handleRename = false;
	
	/**
	 * An id which specifies the kind of node or -1 if none is set
	 */
	protected int id = -1;
	
	/**
	 * The {@link ModelView} containing the tree
	 */
	private final ModelView modelView;
	
	/**
	 * The represented model or <code>null</code>
	 */
	private Object model;
	
	/**
	 * The list of children of the node
	 */
	protected List<AbstractModelViewNode> children;

	/**
	 * Constructor for the node
	 * 
	 * @param modelView the modelview containing the tree of the node
	 */
	public AbstractModelViewNode(ModelView modelView) {
		super();
		this.modelView = modelView;
	}

	/**
	 * 
	 * @return the name of the node used as label
	 */
	public String getName() {
		return name;
	}

	protected ModelView getModelView() {
		return modelView;
	}

	/**
	 * Sets the parent node
	 * 
	 * @param parent the parent tree node
	 */
	public void setParent(AbstractModelViewNode parent) {
		this.parent = parent;
		if (parent != null)
			this.syncView = parent.isSyncView();
	}

	
	final protected void setName(String newName) {
		this.name = newName;
	}

	/**
	 * 
	 * @return the id of the node or -1
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return the parent node
	 */
	public AbstractModelViewNode getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(Class key) {
		return null;
	}

	/**
	 * 
	 * @return the model of the node
	 */
	public Object getModel() {
		return model;
	}

	/**
	 * Sets the model of the node
	 * @param model the new model
	 */
	public void setModel(Object model) {
		dispose();
		this.model = model;
	}

	/**
	 * Clears some cached data and removes listeners
	 */
	public void dispose() {
		for (AbstractModelViewNode to : getChildrenList()) {
			to.dispose();
		}
	}

	/**
	 * Opens the Property Detilas View if it is closed
	 */
	public void handleDoubleClick() {
		try {
			getModelView().getViewSite().getWorkbenchWindow().getActivePage()
			        .showView(PropertyDetailView.ID, null, IWorkbenchPage.VIEW_VISIBLE);
		} catch (PartInitException e) {
			TmcleditEditPlugin.getPlugin().log(e);
		}
	}

	/**
	 * Returns an image for the node.
	 * @return the image or <code>null</code>
	 */
	public Image getImage() {
		return null;
	}

	/**
	 * Refreshs the node in the {@link ModelView}s {@link TreeViewer}
	 */
	public void refresh() {
		if (syncView)
			getModelView().getViewer().refresh(this);
	}

	protected boolean isSyncView() {
		return syncView;
	}

	/**
	 * Executes an action to rename the model, which is the property of the model used as label. 
	 */
	public void handleRename() {
	}

	/**
	 * Flag if rename is possible.
	 * @return <code>true</code> if {@link #handleRename()} does something <code>false</code> else
	 */
	public boolean canHandleRename() {
		return handleRename;
	}

	/**
	 * Adds a child to this node at the end of the children list.
	 * 
	 * If another child contains the model of the child nothing happens.
	 * 
	 * @param child the new child
	 */
	public void addChild(AbstractModelViewNode child) {
		addChild(-1, child);
	}

	/**
	 * Adds a child to this node at the given position of the children list.
	 * 
	 * If another child contains the model of the child nothing happens.
	 * 
	 * @param index the index where the child should be
	 * @param child the new child
	 * 
	 */
	public void addChild(int index, AbstractModelViewNode child) {
		if (children == null)
			children = new ArrayList<AbstractModelViewNode>();

		
		if (findChildPerModel(child.getModel())!=null) {
			TmcleditEditPlugin.getPlugin().log("child: "+child.getName()+" already in list");
			return;
		}
		
		if (index > 0)
			children.add(index, child);
		else
			children.add(child);

		child.setParent(this);
		if (isSyncView())
			refresh();
	}

	public void removeChild(AbstractModelViewNode child) {
		if (children == null)
			return;
		children.remove(child);
		child.setParent(null);
	}

	public AbstractModelViewNode[] getChildren() {
		return (AbstractModelViewNode[]) getChildrenList().toArray(new AbstractModelViewNode[getChildrenList().size()]);
	}

	public List<AbstractModelViewNode> getChildrenList() {
		if (children == null)
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
		for (Iterator<AbstractModelViewNode> it = getChildrenList().iterator(); it.hasNext();) {
			AbstractModelViewNode child = it.next();
			it.remove();
			child.dispose();
		}
	}

	/**
	 * Returns the child with the given model
	 * 
	 * @param childModel the child model
	 * @return the child or <code>null</code> if none exists
	 */
	public AbstractModelViewNode findChildPerModel(Object childModel) {
		for (AbstractModelViewNode o : getChildrenList()) {
			if (childModel.equals(o.getModel()))
				return o;
		}
		return null;
	}

	
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((model == null) ? 0 : model.hashCode());
	    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
	    return result;
    }

	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	    if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }
	    AbstractModelViewNode other = (AbstractModelViewNode) obj;
	    if (model == null) {
		    if (other.model != null) {
			    return false;
		    }
	    } else if (!model.equals(other.model)) {
		    return false;
	    }
	    if (parent == null) {
		    if (other.parent != null) {
			    return false;
		    }
	    } else if (!parent.equals(other.parent)) {
		    return false;
	    }
	    return true;
    }
	
	
}