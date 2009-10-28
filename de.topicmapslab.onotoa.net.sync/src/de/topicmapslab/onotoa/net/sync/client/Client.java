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
package de.topicmapslab.onotoa.net.sync.client;

import org.eclipse.ecf.datashare.events.IChannelEvent;

import de.topicmapslab.onotoa.net.sync.common.AbstractChannelHandler;


/**
 * @author Hannes Niederhausen
 *
 */
public class Client extends AbstractChannelHandler {

	public void connect() {
		createChannel();
	}
	
	public void disconnect() {
		super.disconnect();
		
	}
	
	
	
	public void handleChannelEvent(IChannelEvent event) {
		// TODO Auto-generated method stub	
	}

}
