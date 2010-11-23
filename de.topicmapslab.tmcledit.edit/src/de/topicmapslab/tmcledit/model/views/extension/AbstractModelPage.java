package de.topicmapslab.tmcledit.model.views.extension;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

import de.topicmapslab.tmcledit.model.views.PropertyDetailView;

/**
 * The {@link AbstractModelPage} is the superclass for new {@link PropertyDetailView} pages.
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractModelPage extends Page implements IModelPage {

	protected Object model;
	private CommandStack commandStack;
	protected String id;
	protected CTabFolder folder;

	/**
	 * Default constructor
	 */
	public AbstractModelPage() {
		super();
	}

	/**
	 * Constructor with id
	 * 
	 * @param id id of the view
	 */
	public AbstractModelPage(String id) {
		super();
		this.id = id;
	}
	
	/**
	 * 
	 * @param pageSite the pageSite of the view containing this page
	 */
	public void setSite(IPageSite pageSite) {
    	init(pageSite);
    }

	@Override
    public void createControl(Composite parent) {
    	folder = new CTabFolder(parent, SWT.None);
    	createItems(folder);
    	folder.setSelection(0);
    }

	protected void createItems(CTabFolder folder) {
		if (this.folder == null)
			this.folder = folder;
	}
	
	protected CTabFolder getFolder() {
        return folder;
    }

	
	@Override
    public void setFocus() {
    }

	@Override
    public Control getControl() {
    	return folder;
    }

	public CommandStack getCommandStack() {
    	return commandStack;
    }

	public void setCommandStack(CommandStack commandStack) {
    	this.commandStack = commandStack;
    }

	public void setModel(Object model) {
		this.model = model;	    
	}
	
	public Object getModel() {
	    return model;
	}

	public String getId() {
    	return id;
    }

}