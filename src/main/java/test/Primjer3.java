package test;

import java.util.Arrays;

import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.Masks;
import hr.fer.zemris.bool.fimpl.MaskBasedBF;

public class Primjer3 {

	public static void main(String[] args) {

		BooleanVariable varA = new BooleanVariable("A");
		BooleanVariable varB = new BooleanVariable("B");
		BooleanVariable varC = new BooleanVariable("C");

		BooleanFunction f1 = new MaskBasedBF("f1", Arrays.asList(varA, varB,
				varC), true, Masks.fromStrings("0x0"), Masks.fromStrings("10x"));
		
		
		for (Integer i : f1.mintermIterable()) { // Ispis: 0, 2
			System.out.println("Imam minterm: " + i);
		}
		for (Integer i : f1.maxtermIterable()) { // Ispis: 1, 3, 6, 7
			System.out.println("Imam maxterm: " + i);
		}
		for (Integer i : f1.dontCareIterable()) { // Ispis: 4, 5
			System.out.println("Imam dontcare: " + i);
		}

	}

}
