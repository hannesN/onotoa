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
package de.topicmapslab.onotoa.search.container;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import de.topicmapslab.kuria.annotation.Text;
import de.topicmapslab.kuria.annotation.tree.Children;
import de.topicmapslab.kuria.annotation.tree.TreeNode;
import de.topicmapslab.onotoa.search.searchImpl.ISearcher;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * Container for search of TopicTypes without identifiers.
 * 
 * Adapter removes entries if an identifier was added (and vice versa )
 * 
 * @author Sebastian Lippert
 * 
 */
@TreeNode
public class TopicsWithoutIdentifierContainer extends AbstractContainer {

	/**
	 * @param label
	 *            for search at result view
	 * @param searcher
	 *            that found the content
	 */

	public TopicsWithoutIdentifierContainer(String label, ISearcher searcher) {
		super(label, searcher);
	}

	/**
	 * Get label for search at result view
	 * 
	 * @return label
	 */

	@Text
	public String getlabel() {
		return super.getLabel();
	}

	/**
	 * Get children list that are displayed
	 * 
	 * @return children list
	 */

	@Children
	public List<Object> getChildren() {
		return super.getContentList();
	}

	/**
	 * {@inheritDoc}
	 */

	public void addAdapter() {

		for (final Object o : super.getContentList()) {

			if (o instanceof TopicTypeWrapper)
				((TopicTypeWrapper) o).getTopicType().eAdapters().add(new Adapter() {

					// last position of deleted entries
					private int position;

					public void setTarget(Notifier newTarget) {
						// TODO Auto-generated method stub

					}

					public void notifyChanged(Notification notification) {

						// check if notification based on locator or identifier
						// action
						if (notification.getFeatureID(TopicType.class) == ModelPackage.TOPIC_TYPE__IDENTIFIERS
						        || notification.getFeatureID(TopicType.class) == ModelPackage.TOPIC_TYPE__LOCATORS) {

							// identifier or locator was added -> remove type
							if (notification.getEventType() == Notification.ADD) {
								position = getContentList().indexOf((TopicTypeWrapper) o);
								removeListElement(o);
							}
							// identifier or locator was removed -> add type
							if (notification.getEventType() == Notification.REMOVE)
								addListElement(position, o);

							// update view with actual results
							view.updateUI();
						}
					}

					public boolean isAdapterForType(Object type) {
						return false;
					}

					public Notifier getTarget() {
						return null;
					}
				});

		}

	}

	/**
	 * Remove all adapters from the elements
	 */

	@Override
	public void dispose() {

		for (final Object o : super.getContentList()) {
			if (o instanceof TopicTypeWrapper)
				((TopicTypeWrapper) o).getTopicType().eAdapters().clear();
		}

	}

}
