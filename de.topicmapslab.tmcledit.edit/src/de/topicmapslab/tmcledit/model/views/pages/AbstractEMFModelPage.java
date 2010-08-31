/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.IModelProvider;
import de.topicmapslab.tmcledit.model.views.extension.AbstractModelPage;
import de.topicmapslab.tmcledit.model.views.extension.IModelPage;
import de.topicmapslab.tmcledit.model.views.widgets.AnnotationWidget;

public abstract class AbstractEMFModelPage extends AbstractModelPage implements Adapter,
		IModelProvider, IModelPage {

	private Notifier target;

	private CTabItem descrItem;
	private CTabItem annotationItem;

	Text descriptionText;

	Text commentText;

	Text seeAlsoText;

	AnnotationWidget annotationWidget;

	public AbstractEMFModelPage(String id) {
		super(id);
	}

	public boolean canAnnotate() {
		return true;
	}
	
	protected boolean hasDocumentation() {
		return true;
	}

	void hookModifyListeners() {
		descriptionText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (getCastedModel()==null)
					return;
				Text w = (Text) e.widget;
				if (w.getText().equals(getCastedModel().getDescription()))
					return;
				getCommandStack().execute(
						new GenericSetCommand(getModel(),
								ModelPackage.TMCL_CONSTRUCT__DESCRIPTION, w.getText()));
			}
		});

		commentText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (getCastedModel()==null)
					return;
				Text w = (Text) e.widget;
				if (w.getText().equals(getCastedModel().getComment()))
					return;
				getCommandStack().execute(
						new GenericSetCommand(getModel(),
								ModelPackage.TMCL_CONSTRUCT__COMMENT, w
										.getText()));
			}
		});

		SeeAlsoListener l = new SeeAlsoListener();

		seeAlsoText.addFocusListener(l);
		seeAlsoText.addKeyListener(l);
	}

	void createDescriptionTab(FormToolkit toolkit) {
		descrItem = new CTabItem(folder, SWT.None);
		Composite comp = toolkit.createComposite(folder);
		comp.setLayout(new GridLayout(2, false));
		toolkit.createLabel(comp, "Description:");
		descriptionText = toolkit.createText(comp, "", SWT.MULTI | SWT.V_SCROLL
				| SWT.WRAP | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 80;
		descriptionText.setLayoutData(gd);

		toolkit.createLabel(comp, "Comment:");
		commentText = toolkit.createText(comp, "", SWT.MULTI | SWT.V_SCROLL
				| SWT.WRAP | SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 80;
		commentText.setLayoutData(gd);

		toolkit.createLabel(comp, "See also:");
		seeAlsoText = toolkit.createText(comp, "", SWT.BORDER);
		seeAlsoText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		descrItem.setText("Documentation");
		descrItem.setControl(comp);
	}

	
	@Override
	public void createControl(Composite parent) {
	    super.createControl(parent);

	    FormToolkit toolkit = new FormToolkit(folder.getDisplay());
	    if (canAnnotate())
    		createAnnotationWidgets(toolkit);
    	if (hasDocumentation()) {
    		createDescriptionTab(toolkit);
    		hookModifyListeners();
    	}
	}

	private TMCLConstruct getCastedModel() {
		if (model instanceof TMCLConstruct)
			return (TMCLConstruct) model;
		return null;
	}
	
	@Override
	public void setCommandStack(CommandStack commandStack) {
	    super.setCommandStack(commandStack);
	    if (this.annotationWidget!=null)
    		this.annotationWidget.setCommandStack(commandStack);
	}
	
	/* (non-Javadoc)
     * @see de.topicmapslab.tmcledit.model.views.pages.IModelPage#setModel(java.lang.Object)
     */
	public void setModel(Object model) {
		setEnabled(model!=null);
		if (getModel() == model) {
			if ((getModel()!=null) && (!getModel().eAdapters().contains(this)) )
				getModel().eAdapters().add(this);
			return;
		}

		folder.setSelection(0);
		ScrolledPageBook pb = (ScrolledPageBook) folder.getParent().getParent();
		pb.setOrigin(0, 0);

		if (getModel() != null)
			getModel().eAdapters().remove(this);

		super.setModel(model);
		
		if (descrItem != null)
			descrItem.getControl().setEnabled((model instanceof TMCLConstruct));

		if (annotationItem!=null)
			((AnnotationWidget) annotationItem.getControl()).setModel(model);
		
		if (getModel() != null)
			getModel().eAdapters().add(this);

		updateUI();
	}

	@Override
	public EObject getModel() {
	    return (EObject) super.getModel();
	}
	
	protected void setEnabled(boolean enabled) {
		seeAlsoText.setEnabled(enabled);
		commentText.setEnabled(enabled);
		descriptionText.setEnabled(enabled);
	}

	public Notifier getTarget() {
		return target;
	}

	public boolean isAdapterForType(Object type) {
		return true;
	}

	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

	/* (non-Javadoc)
     * @see de.topicmapslab.tmcledit.model.views.pages.IModelPage#aboutToHide()
     */
	public void aboutToHide() {
		if (this.getModel() != null)
			this.getModel().eAdapters().remove(this);
	}

	/* (non-Javadoc)
     * @see de.topicmapslab.tmcledit.model.views.pages.IModelPage#updateUI()
     */
	public void updateUI() {
		TMCLConstruct model = getCastedModel();
		if (model == null)
			return;

		if (descriptionText==null)
			return;
		
		String tmp = model.getDescription();
		if (tmp == null)
			descriptionText.setText("");
		else
			descriptionText.setText(tmp);

		tmp = model.getSee_also();
		if (tmp == null)
			seeAlsoText.setText("");
		else
			seeAlsoText.setText(tmp);

		tmp = model.getComment();
		if (tmp == null)
			commentText.setText("");
		else
			commentText.setText(tmp);
	}

	void createAnnotationWidgets(FormToolkit toolkit) {
		annotationItem = new CTabItem(folder, SWT.NONE);
		annotationItem.setText("Annotations");
		annotationWidget = new AnnotationWidget(folder, SWT.NONE, toolkit);
		annotationItem.setControl(annotationWidget);
	}
	
	private final class SeeAlsoListener extends KeyAdapter implements
			FocusListener {
		@Override
		public void keyPressed(KeyEvent e) {
			handleEvent(e.widget);
		}

		private void handleEvent(Widget widget) {
			if (getCastedModel()==null)
				return;
			Text w = (Text) widget;
			if (w.getText().equals(getCastedModel().getSee_also()))
				return;
			getCommandStack()
					.execute(new GenericSetCommand(getModel(),
									ModelPackage.TMCL_CONSTRUCT__SEE_ALSO, w
											.getText()));
		}

		public void focusGained(FocusEvent e) {
		}

		public void focusLost(FocusEvent e) {
			handleEvent(e.widget);
		}
	}
}