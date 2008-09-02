package de.topicmapslab.tmcledit.model.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.diagram.part.TmcleditVisualIDRegistry;

/**
 * @generated
 */
public class TmcleditEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (TmcleditVisualIDRegistry.getVisualID(view)) {

			case TopicMapSchemaEditPart.VISUAL_ID:
				return new TopicMapSchemaEditPart(view);

			case NameTypeEditPart.VISUAL_ID:
				return new NameTypeEditPart(view);

			case NameTypeIdEditPart.VISUAL_ID:
				return new NameTypeIdEditPart(view);

			case ScopeTypeEditPart.VISUAL_ID:
				return new ScopeTypeEditPart(view);

			case ScopeTypeIdEditPart.VISUAL_ID:
				return new ScopeTypeIdEditPart(view);

			case RoleTypeEditPart.VISUAL_ID:
				return new RoleTypeEditPart(view);

			case RoleTypeIdEditPart.VISUAL_ID:
				return new RoleTypeIdEditPart(view);

			case AssociationsTypeEditPart.VISUAL_ID:
				return new AssociationsTypeEditPart(view);

			case AssociationsTypeIdEditPart.VISUAL_ID:
				return new AssociationsTypeIdEditPart(view);

			case OccurenceTypeEditPart.VISUAL_ID:
				return new OccurenceTypeEditPart(view);

			case OccurenceTypeIdEditPart.VISUAL_ID:
				return new OccurenceTypeIdEditPart(view);

			case AssociationTypeConstraintEditPart.VISUAL_ID:
				return new AssociationTypeConstraintEditPart(view);

			case TopicTypeEditPart.VISUAL_ID:
				return new TopicTypeEditPart(view);

			case TopicTypeIdEditPart.VISUAL_ID:
				return new TopicTypeIdEditPart(view);

			case NameTypeConstraintEditPart.VISUAL_ID:
				return new NameTypeConstraintEditPart(view);

			case OccurenceTypeConstraintEditPart.VISUAL_ID:
				return new OccurenceTypeConstraintEditPart(view);

			case SubjectIdentifierConstraintEditPart.VISUAL_ID:
				return new SubjectIdentifierConstraintEditPart(view);

			case SubjectLocatorConstraintEditPart.VISUAL_ID:
				return new SubjectLocatorConstraintEditPart(view);

			case SubjectLocatorConstraint2EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint2EditPart(view);

			case OccurenceTypeConstraint2EditPart.VISUAL_ID:
				return new OccurenceTypeConstraint2EditPart(view);

			case SubjectIdentifierConstraint2EditPart.VISUAL_ID:
				return new SubjectIdentifierConstraint2EditPart(view);

			case SubjectLocatorConstraint3EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint3EditPart(view);

			case NameTypeConstraint2EditPart.VISUAL_ID:
				return new NameTypeConstraint2EditPart(view);

			case OccurenceTypeConstraint3EditPart.VISUAL_ID:
				return new OccurenceTypeConstraint3EditPart(view);

			case SubjectIdentifierConstraint3EditPart.VISUAL_ID:
				return new SubjectIdentifierConstraint3EditPart(view);

			case SubjectLocatorConstraint4EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint4EditPart(view);

			case NameTypeConstraint3EditPart.VISUAL_ID:
				return new NameTypeConstraint3EditPart(view);

			case OccurenceTypeConstraint4EditPart.VISUAL_ID:
				return new OccurenceTypeConstraint4EditPart(view);

			case SubjectIdentifierConstraint4EditPart.VISUAL_ID:
				return new SubjectIdentifierConstraint4EditPart(view);

			case SubjectLocatorConstraint5EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint5EditPart(view);

			case NameTypeConstraint4EditPart.VISUAL_ID:
				return new NameTypeConstraint4EditPart(view);

			case OccurenceTypeConstraint5EditPart.VISUAL_ID:
				return new OccurenceTypeConstraint5EditPart(view);

			case SubjectIdentifierConstraint5EditPart.VISUAL_ID:
				return new SubjectIdentifierConstraint5EditPart(view);

			case SubjectLocatorConstraint6EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint6EditPart(view);

			case NameTypeConstraint5EditPart.VISUAL_ID:
				return new NameTypeConstraint5EditPart(view);

			case OccurenceTypeConstraint6EditPart.VISUAL_ID:
				return new OccurenceTypeConstraint6EditPart(view);

			case SubjectIdentifierConstraint6EditPart.VISUAL_ID:
				return new SubjectIdentifierConstraint6EditPart(view);

			case SubjectLocatorConstraint7EditPart.VISUAL_ID:
				return new SubjectLocatorConstraint7EditPart(view);

			case TopicTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				return new TopicTypeTopicTypeNameCompartmentEditPart(view);

			case TopicTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				return new TopicTypeTopicTypOccurenceCompartmentEditPart(view);

			case TopicTypeIdentifierCompartmentEditPart.VISUAL_ID:
				return new TopicTypeIdentifierCompartmentEditPart(view);

			case TopicTypeIdentifierCompartment2EditPart.VISUAL_ID:
				return new TopicTypeIdentifierCompartment2EditPart(view);

			case TopicTypeTopicTypOccurenceCompartment2EditPart.VISUAL_ID:
				return new TopicTypeTopicTypOccurenceCompartment2EditPart(view);

			case TopicTypeIdentifierCompartment3EditPart.VISUAL_ID:
				return new TopicTypeIdentifierCompartment3EditPart(view);

			case RoleTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				return new RoleTypeTopicTypeNameCompartmentEditPart(view);

			case RoleTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				return new RoleTypeTopicTypOccurenceCompartmentEditPart(view);

			case RoleTypeIdentifierCompartmentEditPart.VISUAL_ID:
				return new RoleTypeIdentifierCompartmentEditPart(view);

			case AssociationsTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				return new AssociationsTypeTopicTypeNameCompartmentEditPart(
						view);

			case AssociationsTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				return new AssociationsTypeTopicTypOccurenceCompartmentEditPart(
						view);

			case AssociationsTypeIdentifierCompartmentEditPart.VISUAL_ID:
				return new AssociationsTypeIdentifierCompartmentEditPart(view);

			case OccurenceTypeTopicTypeNameCompartmentEditPart.VISUAL_ID:
				return new OccurenceTypeTopicTypeNameCompartmentEditPart(view);

			case OccurenceTypeTopicTypOccurenceCompartmentEditPart.VISUAL_ID:
				return new OccurenceTypeTopicTypOccurenceCompartmentEditPart(
						view);

			case OccurenceTypeIdentifierCompartmentEditPart.VISUAL_ID:
				return new OccurenceTypeIdentifierCompartmentEditPart(view);

			case TopicTypeTopicTypeNameCompartment2EditPart.VISUAL_ID:
				return new TopicTypeTopicTypeNameCompartment2EditPart(view);

			case TopicTypeTopicTypOccurenceCompartment3EditPart.VISUAL_ID:
				return new TopicTypeTopicTypOccurenceCompartment3EditPart(view);

			case TopicTypeIdentifierCompartment4EditPart.VISUAL_ID:
				return new TopicTypeIdentifierCompartment4EditPart(view);

			case RoleTypeConstraintsEditPart.VISUAL_ID:
				return new RoleTypeConstraintsEditPart(view);

			case RoleTypeConstraintsCardMinCardMaxEditPart.VISUAL_ID:
				return new RoleTypeConstraintsCardMinCardMaxEditPart(view);

			case RoleTypeConstraintsRoleEditPart.VISUAL_ID:
				return new RoleTypeConstraintsRoleEditPart(view);

			case TopicTypeAkoEditPart.VISUAL_ID:
				return new TopicTypeAkoEditPart(view);

			case TopicTypeIsaEditPart.VISUAL_ID:
				return new TopicTypeIsaEditPart(view);
			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (getWrapLabel().isTextWrapOn()
					&& getWrapLabel().getText().length() > 0) {
				rect.setSize(new Dimension(text.computeSize(rect.width,
						SWT.DEFAULT)));
			} else {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont())
					.getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
					SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
