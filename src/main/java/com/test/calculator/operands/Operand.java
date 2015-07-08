package com.test.calculator.operands;

import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;

//users can extend this class and add new operands
public abstract class Operand {
	private static HashMap<Character, String> handlers = new HashMap<Character, String>();
	private static HashMap<Character, Integer> ranks = new HashMap<Character, Integer>();

	static {
		Reflections reflections = new Reflections();
		Set<Class<? extends Operand>> classes = reflections.getSubTypesOf(Operand.class);

		// initialize all handlers
		System.out.println("Found operand handlers:");
		for (Class<? extends Operand> clazz : classes) {
			System.out.println(clazz.getName());
			try {
				clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void addHandler(Character operand, String handler) {
		Operand.handlers.put(operand, handler);
	}

	public static void addRank(Character operand, int rank) {
		Operand.ranks.put(operand, rank);
	}

	public static HashMap<Character, String> getHandlers() {
		return new HashMap<Character, String>(handlers);
	}

	public static HashMap<Character, Integer> getRanks() {
		return new HashMap<Character, Integer>(ranks);
	}

	public abstract int operate(int left, int right);
}
