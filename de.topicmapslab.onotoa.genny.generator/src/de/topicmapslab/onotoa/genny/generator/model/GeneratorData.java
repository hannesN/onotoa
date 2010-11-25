/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.model;

import static de.topicmapslab.onotoa.genny.generator.preferences.IPreferenceConstants.P_LAST_PROJECTID;
import static de.topicmapslab.onotoa.genny.generator.preferences.IPreferenceConstants.P_LAST_PROJECTNAME;
import static de.topicmapslab.onotoa.genny.generator.preferences.IPreferenceConstants.P_LAST_TARGET;

import org.eclipse.jface.preference.IPreferenceStore;

import de.topicmapslab.kuria.annotation.widgets.Directory;
import de.topicmapslab.kuria.annotation.widgets.Editable;
import de.topicmapslab.kuria.annotation.widgets.Hidden;
import de.topicmapslab.kuria.annotation.widgets.TextField;

/**
 * Class containing the generator data.
 * 
 * @author Hannes Niederhausen
 *
 */
@Editable
public class GeneratorData {

	@Directory(label="Target Directory")
	private String targetDir;
	
	@TextField(label="Application ID", description="Similiar to a package name. Will be used to create the packages and the bundle ids for the application.")
	private String applicationId;
	
	@TextField(label="Name", description="The name of the application, which will be the name of the executable.")
	private String applicationName;

	@Hidden
	private boolean deleteTemporaryFiles;
	
	@Hidden
	private String mavenPath;
	
	@Hidden
	private String mavenOpts;

	/**
     * @return the targetDir
     */
    public String getTargetDir() {
    	return targetDir;
    }

	/**
     * @param targetDir the targetDir to set
     */
    public void setTargetDir(String targetDir) {
    	this.targetDir = targetDir;
    }

	/**
     * @return the applicationId
     */
    public String getApplicationId() {
    	return applicationId;
    }

	/**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
    	this.applicationId = applicationId;
    }

	/**
     * @return the applicationName
     */
    public String getApplicationName() {
    	return applicationName;
    }

	/**
     * @param applicationName the applicationName to set
     */
    public void setApplicationName(String applicationName) {
    	this.applicationName = applicationName;
    }

	/**
     * @return the deleteTemporaryFiles
     */
    public boolean isDeleteTemporaryFiles() {
    	return deleteTemporaryFiles;
    }

	/**
     * @param deleteTemporaryFiles the deleteTemporaryFiles to set
     */
    public void setDeleteTemporaryFiles(boolean deleteTemporaryFiles) {
    	this.deleteTemporaryFiles = deleteTemporaryFiles;
    }
	
    /**
     * 
     * @param mavenpath the new mavenpath
     */
	public void setMavenpath(String mavenpath) {
	    this.mavenPath = mavenpath;
    }
	
	/**
	 * 
	 * @return the maven path
	 */
	public String getMavenpath() {
	    return mavenPath;
    }
	
	/**
	 * Sets the maven oprions set in an environment variable.
	 * 
	 * @param mavenOpts the new maven options 
	 */
	public void setMavenOpts(String mavenOpts) {
	    this.mavenOpts = mavenOpts;
    }
	
	/**
	 * 
	 * @return the maven options
	 */
	public String getMavenOpts() {
	    return mavenOpts;
    }
	
	/**
	 * Initializes the fields with data from the preference store
	 * @param ps the preference store to use
	 */
	public void init(IPreferenceStore ps) {
		applicationId = ps.getString(P_LAST_PROJECTID);
		applicationName = ps.getString(P_LAST_PROJECTNAME);
		targetDir = ps.getString(P_LAST_TARGET);
	}
	
	/**
	 * Saves the field values into the given preference store.
	 * @param ps the preference store to use to save
	 */
	public void save(IPreferenceStore ps) {
		ps.setValue(P_LAST_PROJECTID, applicationId);
		ps.setValue(P_LAST_PROJECTNAME, applicationName);
		ps.setValue(P_LAST_TARGET, targetDir);
	}
}
