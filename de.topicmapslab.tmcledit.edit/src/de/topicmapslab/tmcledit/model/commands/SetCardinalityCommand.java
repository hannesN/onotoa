/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetCardinalityCommand extends AbstractCommand {

	private AbstractCardinalityContraint cardinalityContraint;
	private boolean isMin;
	private String newValue;
	private String oldValue;
	private String oldMax;
	
	public SetCardinalityCommand(AbstractCardinalityContraint cardinalityContraint,
			boolean isMin, String newValue) {
		super();
		this.cardinalityContraint = cardinalityContraint;
		this.isMin = isMin;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		if (isMin) {
			cardinalityContraint.setCardMin(newValue);
			if (cardinalityContraint.getCardMax().equals("*"))
				return;
			try {
				int min = Integer.parseInt(cardinalityContraint.getCardMin());
				int max = Integer.parseInt(cardinalityContraint.getCardMax());
			
				if (min>max) {
					oldMax = cardinalityContraint.getCardMax();
					cardinalityContraint.setCardMax(newValue);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else
			cardinalityContraint.setCardMax(newValue);
	}

	@Override
	public void undo() {
		if (isMin) {
			cardinalityContraint.setCardMin(oldValue);
			if (oldMax!=null)
				cardinalityContraint.setCardMax(oldMax);
		} else {
			cardinalityContraint.setCardMax(oldValue);
		}
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		if (isMin)
			oldValue = cardinalityContraint.getCardMin();
		else
			oldValue = cardinalityContraint.getCardMax();
		
		return true;
	}

}
