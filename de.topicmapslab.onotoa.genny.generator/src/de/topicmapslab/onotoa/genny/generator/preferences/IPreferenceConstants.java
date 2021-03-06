/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.preferences;

/**
 * Constants for the preferences of this bundle.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IPreferenceConstants {

	/**
	 * last used target directory
	 */
	public final static String P_LAST_TARGET = "last_target";
	
	/**
	 * last used project id
	 */
	public final static String P_LAST_PROJECTID = "last_projct_id";
	
	/**
	 * last used project name
	 */
	public final static String P_LAST_PROJECTNAME = "last_project_name";
	
	/**
	 * the maven home directory
	 */
	public final static String P_MAVEN_HOME = "maven_home";
	
	/**
	 * Options for maven which will be set via env variable
	 */
	public final static String P_MAVEN_OPTS = "maven_opts";
	
	/**
	 * Options whether the annotaiton nodes should be shown
	 */
	public final static String P_SHOW_GENERATOR_NODES = "show_generator_nodes";
}
