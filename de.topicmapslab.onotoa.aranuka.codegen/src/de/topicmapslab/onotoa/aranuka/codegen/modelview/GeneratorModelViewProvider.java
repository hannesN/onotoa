/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.modelview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.topicmapslab.onotoa.aranuka.codegen.actions.CreateAnnotationHub;
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
 * @author niederhausen
 * 
 */
public class GeneratorModelViewProvider implements IModelViewProvider {

	private CreateAnnotationHub createAnnotationHubAction;
	private CodeGeneratorModelPage codeGeneratorModelPage;

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
		// TODO Auto-generated method stub
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
		if (extension instanceof GeneratorData) {
			if (codeGeneratorModelPage == null)
				codeGeneratorModelPage = new CodeGeneratorModelPage();
			return codeGeneratorModelPage;
		}
		return null;
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public List<UpdateAction> getActions() {
		if (createAnnotationHubAction == null)
			return Collections.emptyList();
		return Arrays.asList((UpdateAction) createAnnotationHubAction);
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void createActions(ModelView modelView) {
		createAnnotationHubAction = new CreateAnnotationHub(modelView);
	}

	/**
	 * {@inheritDocs}
	 */
	@Override
	public void close() {

	}

}
