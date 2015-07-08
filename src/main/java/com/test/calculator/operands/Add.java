package com.test.calculator.operands;

public class Add extends Operand {
	static {
		Operand.addHandler('+', "com.test.calculator.operands.Add");
		Operand.addRank('+', 0);
	}

	@Override
	public int operate(int left, int right) {
		return left + right;
	}
}
