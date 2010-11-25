package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;

/**
 * Helper for the edit part contextmenu providers
 * @author Hannes Niederhausen
 *
 */
public class EditPartUtil  {

	/**
	 * Sorts the actions and adds them to the sub menu afterwards.
	 * @param dataList a list of {@link SetTypeData} which represents one action 
	 * @param subMenu the sub menu which will get the actions
	 */
	static void sortAndAddAction(List<SetTypeData> dataList, MenuManager subMenu) {
		Collections.sort(dataList);
		for(SetTypeData d : dataList) {
			subMenu.add(new SetTypeAction(d));
		}
		
	}

}