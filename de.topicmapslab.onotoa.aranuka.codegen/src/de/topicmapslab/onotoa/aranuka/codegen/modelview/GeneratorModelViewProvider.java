/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.topicmapslab.onotoa.aranuka.codegen.Activator;
import de.topicmapslab.onotoa.aranuka.codegen.model.CharacteristicData;
import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;
import de.topicmapslab.onotoa.aranuka.codegen.model.IdentifierData;
import de.topicmapslab.onotoa.aranuka.codegen.model.TopicMapSchemaData;
import de.topicmapslab.onotoa.aranuka.codegen.model.TopicTypeData;
import de.topicmapslab.onotoa.aranuka.codegen.preferences.IPreferenceConstants;
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
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

	private Map<Class<? extends GeneratorData>, CodeGeneratorModelPage> pageMap;

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
		if ((!Activator.getDefault().getPreferenceStore().getBoolean(IPreferenceConstants.P_HIDE_NODES))
		 && (parentNode.getModel() instanceof TMCLConstruct)) {
			
			// check if the node already has a generator node
			for (AbstractModelViewNode n : parentNode.getChildrenList()) {
				if (n instanceof GeneratorDataNode)
					return Collections.emptyList();
			}
			
			
			AbstractModelViewNode n = getNodeForConstruct(modelView, (TMCLConstruct) parentNode.getModel());

			if (n != null)
				return Arrays.asList(n);
		}

		return Collections.emptyList();
	}

	/**
	 * @param model
	 * @return
	 */
	private AbstractModelViewNode getNodeForConstruct(ModelView modelView, TMCLConstruct model) {
		if (Activator.getDefault().getPreferenceStore().getBoolean(IPreferenceConstants.P_HIDE_NODES))
			return null;
		
		GeneratorData data = null;
		if (model instanceof TopicMapSchema) {
			data = new TopicMapSchemaData(model);
		} else if (model instanceof TopicType) {
			data = new TopicTypeData(model);
		} else if ((model instanceof NameTypeConstraint) || (model instanceof OccurrenceTypeConstraint)) {
			data = new CharacteristicData(model);
		} else if ((model instanceof SubjectIdentifierConstraint) 
				|| (model instanceof SubjectLocatorConstraint)
				|| (model instanceof ItemIdentifierConstraint)) {
			data = new IdentifierData(model);
		} else {
			return null;
		}
		
		GeneratorDataNode n = new GeneratorDataNode(modelView);
		n.setModel(data);
		return n;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public boolean hasPageFor(IModelExtension extension) {
		if (Activator.getDefault().getPreferenceStore().getBoolean(IPreferenceConstants.P_HIDE_NODES))
			return false;
		
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
		return Collections.emptyList();
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void createActions(ModelView modelView) {
		// do nothing
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void close() {
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
