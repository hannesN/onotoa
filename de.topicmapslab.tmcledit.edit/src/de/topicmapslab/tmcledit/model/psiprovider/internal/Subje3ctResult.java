package de.topicmapslab.tmcledit.model.psiprovider.internal;
/**
 * 
 */

public class Subje3ctResult {
	public String name;
	public String identifier;
	public String description;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subje3ctResult [description=");
		builder.append(description);
		builder.append(", identifier=");
		builder.append(identifier);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
}