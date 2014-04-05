package test;

import hr.fer.zemris.bool.BooleanConstant;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.Mask;
import hr.fer.zemris.bool.opimpl.BooleanOperators;

public class Problem1 {
	public static void main(String[] args) {


		BooleanVariable varA = new BooleanVariable("A");
		varA.setValue(BooleanValue.TRUE);
		BooleanVariable varB = new BooleanVariable("B");
		BooleanVariable varC = new BooleanVariable("C");
		
		
		BooleanOperator izraz1 = BooleanOperators.or(
		BooleanConstant.FALSE, 
		varC,
		BooleanOperators.and(varA, BooleanOperators.not(varB))
		);
		
		System.out.println(izraz1.getValue());
		
		Mask.fromIndex(3, 1);
	}
}