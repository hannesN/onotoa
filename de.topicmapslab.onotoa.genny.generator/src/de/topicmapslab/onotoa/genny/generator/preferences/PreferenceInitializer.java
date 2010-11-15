package de.topicmapslab.onotoa.genny.generator.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.topicmapslab.onotoa.genny.generator.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(IPreferenceConstants.P_LAST_PROJECTID, "org.example.application");
		store.setDefault(IPreferenceConstants.P_LAST_PROJECTNAME, "Example Application");
		store.setDefault(IPreferenceConstants.P_MAVEN_OPTS, "-Xmx512m -XX:MaxPermSize=256m");
	}

}
