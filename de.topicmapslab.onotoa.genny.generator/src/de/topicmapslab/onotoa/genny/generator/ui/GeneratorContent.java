/**
 * 
 */
package de.topicmapslab.onotoa.genny.generator.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;

/**
 * Content provided by the generator class using a {@link StringBuilder}
 * 
 * @author Hannes Niederhausen
 * 
 */
public class GeneratorContent implements StyledTextContent {

	private List<TextChangeListener> listeners;
	
	
	private List<String> lines = new ArrayList<String>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTextChangeListener(TextChangeListener listener) {
		if (listeners == null)
			listeners = new ArrayList<TextChangeListener>();
		listeners.add(listener);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCharCount() {
		int count = 0;
		for (String line : lines) {
			count += line.length();
		}
		return count+((lines.size()-1)*getLineDelimiter().length());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLine(int lineIndex) {
		if (lines.isEmpty())
			return "";
		return lines.get(lineIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLineAtOffset(int offset) {
		if (offset==getCharCount())
			return getLineCount(); 
			
		int currOffset = 0;
		
		for (String line : lines) {
			currOffset += line.length();
			currOffset += getLineDelimiter().length();
			if (currOffset>=offset) {
				return lines.indexOf(line);
			}
		}
		
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLineCount() {
		return (lines.isEmpty()) ? 1 : lines.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLineDelimiter() {
		return System.getProperty("line.separator");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getOffsetAtLine(int lineIndex) {
		if (lines.isEmpty())
			return 0;
		
		int offset = 0;
		for (int i=0; i<lineIndex-1; i++) {
			offset += lines.get(i).length();
			offset += getLineDelimiter().length(); 
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTextRange(int start, int length) {
		StringBuilder res = new StringBuilder();
		
		int toRead = length;
		
		int line = getLineAtOffset(start);
		int offset = getOffsetAtLine(line);
		
		int subStringStart = start-offset;
		
		
		String subString = lines.get(line).substring(subStringStart);
		// substring is the text we want
		if (subString.length()==length)
			return subString;
		
		// substring has more text than we want, so cut it and 
		if (subString.length()<length) {
			return subString.substring(0, length+1);
		}
				
		toRead -= subString.length();
		res.append(subString);
		
		// read the next lines as long we still have to read chars
		while(toRead>0) {
			line++;
			String l = lines.get(line);
			if (l.length()<toRead) {
				res.append(l.substring(toRead));
				toRead=0;
			} else {
				res.append(l);
				toRead -= l.length();
			}
		}
		
		return res.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeTextChangeListener(TextChangeListener listener) {
		if (listeners != null)
			listeners.remove(listener);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceTextRange(int start, int replaceLength, String text) {
		// TODO Auto-generated method stub

		notifyTextChangedListener();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setText(String text) {
		String[] split = text.split(getLineDelimiter());
		lines.clear();
		for (String s : split) {
			lines.add(s);
		}
		notifyTextSetListener();
	}

	private List<TextChangeListener> getListeners() {
    	if (listeners==null)
    		return Collections.emptyList();
    	return listeners;
    }

	private void notifyTextChangedListener() {
		TextChangedEvent e = new TextChangedEvent(this);
		
		for (TextChangeListener tcl : getListeners()) {
			tcl.textChanged(e);
		}
    }

	private void notifyTextSetListener() {
		TextChangedEvent e = new TextChangedEvent(this);
		
		for (TextChangeListener tcl : getListeners()) {
			tcl.textSet(e);
		}
    }
}
