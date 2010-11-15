/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.topicmapslab.onotoa.genny.generator.Activator;

/**
 * Property page to set the maven binaries path
 * 
 * @author Hannes Niederhausen
 *
 */
public class GennyPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public final static String ID = "de.topicmapslab.onotoa.genny.generator.preferences.GennyPropertyPage";

	public GennyPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Preference page for the Generic Editor Generation.");
    }

	@Override
    protected void createFieldEditors() {
		addField(new DirectoryFieldEditor(IPreferenceConstants.P_MAVEN_HOME, "Maven Home", getFieldEditorParent()));
		addField(new StringFieldEditor(IPreferenceConstants.P_MAVEN_OPTS, "Maven Opts", getFieldEditorParent()));
    }

	@Override
    public void init(IWorkbench workbench) {
    }

}
