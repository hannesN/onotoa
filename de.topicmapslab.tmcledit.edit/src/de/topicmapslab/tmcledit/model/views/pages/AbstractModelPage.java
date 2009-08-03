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
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.IModelProvider;

public abstract class AbstractModelPage extends Page implements Adapter,
		IModelProvider {

	private EObject model;

	private Notifier target;

	private CommandStack commandStack;

	private String ID;

	private CTabFolder folder;
	private CTabItem descrItem;

	private Text descriptionText;

	private Text commentText;

	private Text seeAlsoText;

	public AbstractModelPage(String id) {
		super();
		ID = id;
	}

	public void setSite(IPageSite pageSite) {
		init(pageSite);
	}
	
	@Override
	public final void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		folder = new CTabFolder(parent, SWT.None);

		createItems(folder);

		if (hasDocumentation()) {
			createDescriptionTab(toolkit);
			hookModifyListeners();
		}
		folder.setSelection(0);
	}

	protected boolean hasDocumentation() {
		return true;
	}

	private void hookModifyListeners() {
		descriptionText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Text w = (Text) e.widget;
				getCommandStack().execute(
						new GenericSetCommand(getModel(),
								ModelPackage.TMCL_CONSTRUCT__DESCRIPTION, w
										.getText()));
			}
		});

		commentText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Text w = (Text) e.widget;
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

	private void createDescriptionTab(FormToolkit toolkit) {
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

	protected void createItems(CTabFolder folder) {
		if (this.folder == null)
			this.folder = folder;
	}

	private TMCLConstruct getCastedModel() {
		if (model instanceof TMCLConstruct)
			return (TMCLConstruct) model;
		return null;
	}
	
	public void setModel(Object model) {
		if (this.model == model) {
			if ((this.model!=null) && (!this.model.eAdapters().contains(this)) )
				this.model.eAdapters().add(this);
			return;
		}

		folder.setSelection(0);
		ScrolledPageBook pb = (ScrolledPageBook) folder.getParent().getParent();
		pb.setOrigin(0, 0);

		if (this.model != null)
			this.model.eAdapters().remove(this);

		if (model == null) {
			setEnabled(false);
		} else {
			setEnabled(true);
		}

		if (descrItem != null)
			descrItem.getControl().setEnabled((model instanceof TMCLConstruct));

		this.model = (EObject) model;
		if (model != null)
			this.model.eAdapters().add(this);
		updateUI();
	}

	protected abstract void setEnabled(boolean enabled);

	@Override
	public void setFocus() {
	}

	public Notifier getTarget() {
		return target;
	}

	@Override
	public Control getControl() {
		return folder;
	}

	public boolean isAdapterForType(Object type) {
		return true;
	}

	public void setTarget(Notifier newTarget) {
		this.target = newTarget;
	}

	public CommandStack getCommandStack() {
		return commandStack;
	}

	public void setCommandStack(CommandStack commandStack) {
		this.commandStack = commandStack;
	}

	public void aboutToHide() {
		if (this.getModel() != null)
			this.getModel().eAdapters().remove(this);
	}

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

	public EObject getModel() {
		return model;
	}

	public String getID() {
		return ID;
	}

	private final class SeeAlsoListener extends KeyAdapter implements
			FocusListener {
		@Override
		public void keyPressed(KeyEvent e) {
			handleEvent(e.widget);
		}

		private void handleEvent(Widget widget) {
			Text w = (Text) widget;
			getCommandStack()
					.execute(
							new GenericSetCommand(getModel(),
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