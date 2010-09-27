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
package de.topicmapslab.onotoa.aranuka.codegen.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.onotoa.aranuka.codegen.model.GeneratorData;

/**
 * This is a comamnd which stores the generator data values so they can be
 * undone
 * 
 * @author Hannes Niederhausen
 * 
 */
public class SetAnnotationsCommand extends AbstractCommand {

	private final GeneratorData data;

	Map<Method, Object> oldMap;
	Map<Method, Object> newMap;

	/**
     * 
     */
	public SetAnnotationsCommand(GeneratorData data) {
		this.data = data;
		setLabel("Set Annoation...");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		try {
			newMap = new HashMap<Method, Object>();
			storeSetterMethods(data.getClass(), newMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		try {
			for (Method m : newMap.keySet()) {
				m.invoke(data, newMap.get(m));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		try {
			for (Method m : oldMap.keySet()) {
				m.invoke(data, oldMap.get(m));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUndo() {
	    return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
	    return true;
	}
	
	/**
     * @return the data
     */
    public GeneratorData getData() {
	    return data;
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean prepare() {
		if (isPrepared)
			return true;
		try {
			oldMap = new HashMap<Method, Object>();
			storeSetterMethods(data.getClass(), oldMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		this.isPrepared = true;
		return true;
	}

	/**
	 * @param class1
	 * @param oldMap2
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private void storeSetterMethods(Class<?> c, Map<Method, Object> map) throws IllegalArgumentException,
	        IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		for (Method m : c.getDeclaredMethods()) {
			if ((m.getName().startsWith("get")) || (m.getName().startsWith("is"))) {
				// now non public methods, please
				if ((m.getModifiers() & Modifier.PUBLIC) == 0)
					continue;
				Object val = m.invoke(data);
				Method setter = getSetter(m, c);
				if (setter != null)
					map.put(setter, val);
			}
		}
		if (c.getSuperclass() != Object.class)
			storeSetterMethods(c.getSuperclass(), map);

	}

	/**
	 * @param m
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Method getSetter(Method m, Class<?> c) throws SecurityException, NoSuchMethodException {
		Type genericType = m.getGenericReturnType();
		// get parameters of setters, needed to find a method with a specific
		// name
		Class<?> param = null;
		if (genericType instanceof Class<?>) {
			param = (Class<?>) genericType;
		} else if (genericType instanceof ParameterizedType) {
			// generic type like List<Lala>
			param = (Class<?>) ((ParameterizedType) genericType).getRawType();
		}
		// check if we have a "boolean getter"
		if (m.getName().startsWith("is"))
			return c.getDeclaredMethod("set" + m.getName().substring(2), param);
		else
			return c.getDeclaredMethod("s" + m.getName().substring(1), param);

	}

}
