package com.test.calculator.operands;

public class Multiply extends Operand {
	static {
		Operand.addHandler('*', "com.test.calculator.operands.Multiply");
		Operand.addRank('*', 1);
	}

	@Override
	public int operate(int left, int right) {
		return left * right;
	}
}
