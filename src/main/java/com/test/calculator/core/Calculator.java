package com.test.calculator.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.test.calculator.operands.Operand;

public class Calculator {
	public static void evaluate(String input) throws Exception {
		char[] inputArr = input.toCharArray();
		Integer currDigit = null;
		ArrayList<Token> tokens = new ArrayList<Token>();

		HashMap<Character, String> handlers = Operand.getHandlers();
		HashMap<Character, Integer> ranks = Operand.getRanks();

		// parse and find tokens
		for (int i = 0; i < inputArr.length; i++) {
			char currChar = inputArr[i];
			if (Character.isDigit(currChar)) {
				if (currDigit == null)
					currDigit = 0;
				currDigit = (currDigit * 10) + Character.getNumericValue(currChar);
				continue;
			}

			if (currDigit != null)
				tokens.add(new Token(Integer.toString(currDigit), Token.TYPE_INTEGER));

			currDigit = null;
			if (handlers.containsKey(currChar)) {
				tokens.add(new Token(Character.toString(currChar), Token.TYPE_OPERAND));
			}
		}

		tokens.add(new Token(Integer.toString(currDigit), Token.TYPE_INTEGER));

		boolean foundOperand = true;
		while (foundOperand) {
			foundOperand = false;

			Integer index = null;
			int rank = Integer.MIN_VALUE;

			// find the first highest ranked operand
			for (int i = 0; i < tokens.size(); i++) {
				Token token = tokens.get(i);
				if (token.getType() == Token.TYPE_OPERAND) {
					foundOperand = true;
					if (ranks.get(token.getChar()) > rank) {
						index = i;
						rank = ranks.get(token.getChar());
					}
				}
			}

			if (index != null) {
				int i = index;
				Token token = tokens.get(i);
				if (tokens.get(i - 1).getType() == Token.TYPE_INTEGER && tokens.get(i + 1).getType() == Token.TYPE_INTEGER) {
					// dynamically initialize handler based on mapping
					Operand handler = (Operand) Class.forName(handlers.get(token.getChar())).newInstance();

					// use handler to compute the result
					tokens.set(i, new Token(Integer.toString(handler.operate(tokens.get(i - 1).getInteger(), tokens.get(i + 1).getInteger())), Token.TYPE_INTEGER));

					// remove tokens that are already processed
					tokens.remove(i - 1);
					tokens.remove(i);
				}
			}
		}

		for (int j = 0; j < tokens.size(); j++) {
			System.out.println("Result: " + tokens.get(j).getToken());
		}
	}
}
