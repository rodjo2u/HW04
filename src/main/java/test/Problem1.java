package test;

import hr.fer.zemris.bool.BooleanSource;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.opimpl.BooleanOperatorOR;

import java.util.Arrays;

public class Problem1 {
	public static void main(String[] args) {
		BooleanVariable varA = new BooleanVariable("A");
		varA.setValue(BooleanValue.TRUE);
		BooleanVariable varB = new BooleanVariable("B");
		varB.setValue(BooleanValue.DONT_CARE); 
//		BooleanVariable varC = new BooleanVariable("C");
		
		BooleanValue izraz1 = new BooleanOperatorOR(Arrays.asList((BooleanSource)varA, varB)).getValue();
		
		System.out.println(izraz1);
	}
}