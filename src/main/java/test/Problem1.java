package test;

import hr.fer.zemris.bool.BooleanConstant;
import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanSource;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.opimpl.BooleanOperatorNOT;

import java.util.Arrays;

public class Problem1 {
	public static void main(String[] args) {
		BooleanVariable varA = new BooleanVariable("A");
		BooleanVariable varB = new BooleanVariable("B");
		BooleanVariable varC = new BooleanVariable("C");
		
		BooleanValue izraz1 = new BooleanOperatorNOT(BooleanConstant.TRUE).getValue();
		
		System.out.println(izraz1);
	}
}