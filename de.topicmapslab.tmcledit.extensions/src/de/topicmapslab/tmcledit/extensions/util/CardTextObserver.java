package de.topicmapslab.tmcledit.extensions.util;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.CardinalityContraint;
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;

/**
 * 
 * @author Hannes Niederhausen
 * 
 */
public class CardTextObserver implements FocusListener, DisposeListener,
		VerifyListener {

	private final IModelProvider modelProvider;
	private final Text text;
	private final boolean isMin;

	protected CardTextObserver(Text text, IModelProvider modelProvider,
			boolean isMin) {
		super();
		this.text = text;
		this.isMin = isMin;
		this.modelProvider = modelProvider;
		this.text.addDisposeListener(this);
		this.text.addFocusListener(this);
		this.text.addVerifyListener(this);

	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*
		 * EStructuralFeature feature =
		 * modelProvider.getModel().eClass().getEStructuralFeature(featureID);
		 * modelProvider.getModel().eSet(feature, text.getText());
		 */
		modelProvider.getCommandStack().execute(
				new SetCardinalityCommand((CardinalityContraint) modelProvider
						.getModel(), isMin, text.getText()));
	}

	@Override
	public void widgetDisposed(DisposeEvent e) {
		text.removeFocusListener(this);
		text.removeVerifyListener(this);
		text.removeDisposeListener(this);
	}

	public static void observe(Text text, IModelProvider modelProvider,
			boolean isMin) {
		new CardTextObserver(text, modelProvider, isMin);
	}

	@Override
	public void verifyText(VerifyEvent e) {
		String text = e.text;
		
		Text textField = (Text) e.getSource();
		
		if ((textField.getText().length()==0) && (text.equals("*")))
			return;

		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!Character.isDigit(chars[i])) {
				e.doit = false;
				return;
			}
		}
	}
}
