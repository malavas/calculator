package com.test.calculator.operands;

public class Divide extends Operand {
	static {
		Operand.addHandler('/', "com.test.calculator.operands.Divide");
		Operand.addRank('/', 1);
	}

	@Override
	public int operate(int left, int right) {
		return left / right;
	}
}
