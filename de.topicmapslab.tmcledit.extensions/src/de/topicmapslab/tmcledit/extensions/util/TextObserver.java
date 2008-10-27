package de.topicmapslab.tmcledit.extensions.util;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TextObserver implements FocusListener, DisposeListener {

	private final IModelProvider modelProvider;
	private final int featureID;
	private final Text text;
	
	protected TextObserver(Text text, IModelProvider modelProvider, int featureID) {
		super();
		this.text = text;
		this.featureID = featureID;
		this.modelProvider = modelProvider;
		this.text.addDisposeListener(this);
		this.text.addFocusListener(this);
		
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*
		EStructuralFeature feature = modelProvider.getModel().eClass().getEStructuralFeature(featureID);
		modelProvider.getModel().eSet(feature, text.getText());
		*/
		modelProvider.getCommandStack().execute(new GenericSetCommand(modelProvider.getModel(), featureID, text.getText()));
	}

	@Override
	public void widgetDisposed(DisposeEvent e) {
		text.removeFocusListener(this);
	}

	
	public static void observe(Text text, IModelProvider modelProvider,
			int featureID) {
		new TextObserver(text, modelProvider, featureID);
	}
}
