package test;

import hr.fer.zemris.bool.BooleanConstant;
import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.fimpl.IndexedBF;
import hr.fer.zemris.bool.fimpl.OperatorTreeBF;
import hr.fer.zemris.bool.opimpl.BooleanOperators;

import java.util.Arrays;

public class Primjer1 {
	public static void main(String[] args) {
		BooleanVariable varA = new BooleanVariable("A");
		BooleanVariable varB = new BooleanVariable("B");
		BooleanVariable varC = new BooleanVariable("C");

		BooleanOperator izraz1 = BooleanOperators.or(BooleanConstant.FALSE,
				varC, BooleanOperators.and(varA, BooleanOperators.not(varB)));
		
		BooleanFunction f1 = new OperatorTreeBF("f1",
				Arrays.asList(varA, varB, varC), izraz1);

		for (Integer i : f1.mintermIterable()) { // Ispis: 1, 3, 5, 6, 7
			System.out.println("Imam minterm: " + i);
		}
		for (Integer i : f1.maxtermIterable()) { // Ispis: 0, 2, 4
			System.out.println("Imam maxterm: " + i);
		}
		for (Integer i : f1.dontCareIterable()) { // Ispis:
			System.out.println("Imam dontcare: " + i);
		}
	}
}