package com.test.calculator.operands;

public class Subtract extends Operand {
	static {
		Operand.addHandler('-', "com.test.calculator.operands.Subtract");
		Operand.addRank('-', 0);
	}

	@Override
	public int operate(int left, int right) {
		return left - right;
	}
}
