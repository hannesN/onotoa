package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class GenericSetCommand extends AbstractCommand {

	private final int featureID;
	private EObject model;
	private Object newValue;
	private Object oldValue;
	
	private EStructuralFeature feature;
	
	
	
	public GenericSetCommand(EObject model, int featureID, Object newValue) {
		super();
		this.model = model;
		this.featureID = featureID;
		this.newValue = newValue;
	}

	@Override
	protected boolean prepare() {
		oldValue = model.eGet(getFeature()); 
		return true;
	}
	
	private EStructuralFeature getFeature() {
		if (feature==null) {
			feature = model.eClass().getEStructuralFeature(featureID);
		}
		return feature;
	}
	
	@Override
	public void execute() {
		model.eSet(getFeature(), newValue);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		model.eSet(getFeature(), oldValue);
	}

	@Override
	public String getLabel() {
		return "Set "+getFeature().getName();
	}
	
}
