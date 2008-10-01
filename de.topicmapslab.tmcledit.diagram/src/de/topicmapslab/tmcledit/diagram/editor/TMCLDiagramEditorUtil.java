/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreationFactory;

import de.topicmapslab.tmcledit.diagram.editparts.DiagramEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.NameTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.OccurenceTypeConstraintEditPart;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.ModelFactory;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLDiagramEditorUtil  {


	public static final PaletteRoot getPaletteRoot() {
		PaletteRoot pr = new PaletteRoot();
		PaletteGroup group = new PaletteGroup("Tools");
		SelectionToolEntry selEntry = new SelectionToolEntry();
		group.add(selEntry);
		group.add(new MarqueeToolEntry());
		pr.add(group);
		pr.setDefaultEntry(selEntry);
		
		pr.add(getTypePaletteGroup());
		
		
		return pr;
	}
	
	public static EditPartFactory getEditPartFactory() {
		return new EditPartFactory() {

			@Override
			public EditPart createEditPart(EditPart context, Object model) {
				EditPart part = null;
				if (model instanceof Diagram) {
					part = new DiagramEditPart();
				} if (model instanceof TypeNode) {
					part = new TypeNodeEditPart();
				} if (model instanceof OccurenceTypeConstraint) {
					part = new OccurenceTypeConstraintEditPart();
				} if (model instanceof NameTypeConstraint) {
					part = new NameTypeConstraintEditPart();
				}
				
					
				part.setModel(model);
				return part;
			}
			
		};
	}
	
	private static PaletteGroup getTypePaletteGroup() {
		PaletteGroup group = new PaletteGroup("Topic Types");
		
		group.add(new CreationToolEntry("Topic Type", "Topic Type", new CreationFactory() {

			@Override
			public Object getNewObject() {
				return ModelFactory.eINSTANCE.createTypeNode();
			}

			@Override
			public Object getObjectType() {
				return TypeNode.class;
			}
			
			
			
		}, null, null));
		
		return group;
	}

}
