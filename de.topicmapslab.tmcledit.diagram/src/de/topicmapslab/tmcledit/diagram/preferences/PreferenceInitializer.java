package de.topicmapslab.tmcledit.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = DiagramActivator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_ACTIVE_SCHEME, "default");
	}

}
