package hr.fer.zemris.bool.opimpl;

import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanSource;

import java.util.Arrays;

public class BooleanOperators {

	private BooleanOperators() {
		super();
	}
	
	public static BooleanOperator and(BooleanSource ... booleanSources) {
		return new BooleanOperatorAND(Arrays.asList(booleanSources));
	}
	
	public static BooleanOperator or(BooleanSource ... booleanSources) {
		return new BooleanOperatorOR(Arrays.asList(booleanSources));
	}
	
	public static BooleanOperator not(BooleanSource source) {
		return new BooleanOperatorNOT(source);
	}
}
