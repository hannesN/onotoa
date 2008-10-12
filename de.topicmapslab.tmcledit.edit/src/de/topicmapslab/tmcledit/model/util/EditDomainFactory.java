package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;

public class EditDomainFactory implements TransactionalEditingDomain.Factory {
	@Override
	public TransactionalEditingDomain createEditingDomain() {
		TransactionalEditingDomain result = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();
	      
	      /* add an exception handler to the editing domain's command stack
	      ((TransactionalCommandStack) result.getCommandStack()).setExceptionHandler(
	            new CommandStackExceptionHandler());
	      */
	      return result;

	}

	@Override
	public TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
		TransactionalEditingDomain result = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain(rset);
		return result;
	}

	@Override
	public TransactionalEditingDomain getEditingDomain(ResourceSet rset) {
		TransactionalEditingDomain result = WorkspaceEditingDomainFactory.INSTANCE.getEditingDomain(rset);
		return result;
	}

}
