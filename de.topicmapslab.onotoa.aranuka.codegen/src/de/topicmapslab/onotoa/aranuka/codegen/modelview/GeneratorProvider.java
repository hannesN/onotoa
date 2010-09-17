/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import java.util.List;

import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.extension.IModelExtension;
import de.topicmapslab.tmcledit.model.views.extension.IModelPage;
import de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * @author niederhausen
 *
 */
public class GeneratorProvider implements IModelViewProvider {

	/**
	 * 
	 */
	public GeneratorProvider() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#serialize(de.topicmapslab.tmcledit.model.views.extension.IModelExtension)
	 */
	@Override
	public String serialize(IModelExtension modelEx) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#deserialize(java.lang.String)
	 */
	@Override
	public IModelExtension deserialize(String model) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#getAnnotationKey(de.topicmapslab.tmcledit.model.views.extension.IModelExtension)
	 */
	@Override
	public String getAnnotationKey(IModelExtension modelExtension) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#getChildNodes(de.topicmapslab.tmcledit.model.views.ModelView, de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode)
	 */
	@Override
	public List<AbstractModelViewNode> getChildNodes(ModelView modelView, AbstractModelViewNode parentNode) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#hasPageFor(de.topicmapslab.tmcledit.model.views.extension.IModelExtension)
	 */
	@Override
	public boolean hasPageFor(IModelExtension extension) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#getPageFor(de.topicmapslab.tmcledit.model.views.extension.IModelExtension)
	 */
	@Override
	public IModelPage getPageFor(IModelExtension extension) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#getActions()
	 */
	@Override
	public List<UpdateAction> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#createActions(de.topicmapslab.tmcledit.model.views.ModelView)
	 */
	@Override
	public void createActions(ModelView modelView) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
