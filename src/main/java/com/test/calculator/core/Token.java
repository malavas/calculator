package com.test.calculator.core;

public class Token {
	public static final int TYPE_INTEGER = 0;
	public static final int TYPE_OPERAND = 1;

	private String token;
	private int type;

	public Token(String token, int type) {
		this.token = token;
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public int getType() {
		return type;
	}

	public int getInteger() {
		return Integer.parseInt(token);
	}

	public char getChar() {
		return token.charAt(0);
	}
}
