package test;

import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.fimpl.IndexedBF;

import java.util.Arrays;

public class Primjer2 {
	public static void main(String[] args) {
		BooleanVariable varA = new BooleanVariable("A");
		BooleanVariable varB = new BooleanVariable("B");
		BooleanVariable varC = new BooleanVariable("C");

		BooleanFunction f1 = new IndexedBF("f1",
				Arrays.asList(varA, varB, varC), true,
				Arrays.asList(0, 1, 5, 7), Arrays.asList(2, 3));

		for (Integer i : f1.mintermIterable()) { // Ispis: 0, 1, 5, 7
			System.out.println("Imam minterm: " + i);
		}
		for (Integer i : f1.maxtermIterable()) { // Ispis: 4, 6
			System.out.println("Imam maxterm: " + i);
		}
		for (Integer i : f1.dontCareIterable()) { // 2, 3
			System.out.println("Imam dontcare: " + i);
		}
	}
}