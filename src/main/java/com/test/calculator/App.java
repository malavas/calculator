package com.test.calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.test.calculator.core.Calculator;

public class App {
	public static void main(String[] args) throws Exception {
		String input = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			input = br.readLine();
		}

		if (input != null) {
			Calculator.evaluate(input);
		}
	}
}
