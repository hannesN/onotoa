/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Constants for the {@link IPreferenceStore} of the codegen plug-in.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IPreferenceConstants {

	/**
	 * Constant for the last source path entry in the preference store
	 */
	public final String P_LASTSOURCEPATH = "last_source_path";
	
	/**
	 * Constant for the last package name entry in the preference store
	 */
	public final String P_LASTPACKAGENAME = "last_package_name";
	
	/**
	 * Constant for the last kuria generation flag entry in the preference store
	 */
	public final String P_LASTKURIAGENERATION = "last_kuria_flag";
	
	/**
	 * Constant for the last genny generation flag entry in the preference store
	 */
	public final String P_LASTGENNYAGENERATION = "last_genny_flag";
	
	/**
	 * Constant for the lhide nodes flag entry in the preference store
	 */
	public final String P_HIDE_NODES = "hide_nodes";
}
