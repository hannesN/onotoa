package de.topicmapslab.onotoa.genny.generator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.topicmapslab.onotoa.genny.generator.Activator;
import de.topicmapslab.onotoa.genny.generator.ui.ITextListener;

public final class ProcessInputReader extends Thread {
    private final Process p;
    private final ITextListener listener;

    public ProcessInputReader(Process p, ITextListener listener) {
	    this.p = p;
	    this.listener = listener;
    }

    @Override
    public void run() {
    	InputStream is = p.getInputStream();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    	String line = null;
    	while (true) {
    		try {
    			while ((line = reader.readLine()) != null) {
    				listener.newText(line);
    			}
    		} catch (IOException e) {
    			Activator.logException(e);
    		}
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {
    		}
    	}
    }
}