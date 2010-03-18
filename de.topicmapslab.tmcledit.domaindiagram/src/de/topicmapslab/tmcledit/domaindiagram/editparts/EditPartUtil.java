package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;

public class EditPartUtil  {

	static void sortAndAddAction(List<SetTypeData> dataList, MenuManager subMenu) {
		Collections.sort(dataList);
		for(SetTypeData d : dataList) {
			subMenu.add(new SetTypeAction(d));
		}
		
	}

}