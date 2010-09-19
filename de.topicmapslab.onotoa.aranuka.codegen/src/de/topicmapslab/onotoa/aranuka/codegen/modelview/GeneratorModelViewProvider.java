/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.topicmapslab.onotoa.aranuka.codegen.actions.AbstractSelectionAction;
import de.topicmapslab.onotoa.aranuka.codegen.actions.CreateAnnotationHub;
import de.topicmapslab.onotoa.aranuka.codegen.actions.DeleteAnnotationHub;
import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;
import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.extension.IModelExtension;
import de.topicmapslab.tmcledit.model.views.extension.IModelPage;
import de.topicmapslab.tmcledit.model.views.extension.IModelViewProvider;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * @author Hannes Niederhausen
 *
 */
/**
 * @author Hannes Niederhausen
 * 
 */
public class GeneratorModelViewProvider implements IModelViewProvider {

	private List<UpdateAction> actions;

	private Map<Class<? extends GeneratorData>, CodeGeneratorModelPage> pageMap;

	/**
	 * 
	 */
	public GeneratorModelViewProvider() {

	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public String serialize(IModelExtension modelEx) {
		return null;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public IModelExtension deserialize(String model) {
		return null;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public List<AbstractModelViewNode> getChildNodes(ModelView modelView, AbstractModelViewNode parentNode) {
		return Collections.emptyList();
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public boolean hasPageFor(IModelExtension extension) {
		if (extension instanceof GeneratorData) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public IModelPage getPageFor(IModelExtension extension) {
		@SuppressWarnings("unchecked")
        Class<? extends GeneratorData> extClass = (Class<? extends GeneratorData>) extension.getClass();
		
		CodeGeneratorModelPage page = getPageMap().get(extClass);
		if (page == null) {
			page = new CodeGeneratorModelPage(extClass);
			putPage(extClass, page);
		}
		
		return page;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public List<UpdateAction> getActions() {
		if (actions == null)
			return Collections.emptyList();
		return actions;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void createActions(ModelView modelView) {
		actions = new ArrayList<UpdateAction>();

		actions.add(new CreateAnnotationHub(modelView));
		actions.add(new DeleteAnnotationHub(modelView));

	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void close() {
		for (UpdateAction a : getActions()) {
			((AbstractSelectionAction) a).dispose();
		}
		actions = null;
		
		for (CodeGeneratorModelPage page : getPageMap().values()) {
			page.dispose();
		}
		pageMap = null;
	}

	/**
	 * @return the pageMap
	 */
	private Map<Class<? extends GeneratorData>, CodeGeneratorModelPage> getPageMap() {
		if (pageMap == null)
			return Collections.emptyMap();
		return pageMap;
	}

	private void putPage(Class<? extends GeneratorData> key, CodeGeneratorModelPage val) {
		if (pageMap == null)
			pageMap = new HashMap<Class<? extends GeneratorData>, CodeGeneratorModelPage>();
		pageMap.put(key, val);
	}
	
}
