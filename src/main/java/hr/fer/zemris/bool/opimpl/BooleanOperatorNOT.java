package hr.fer.zemris.bool.opimpl;

import hr.fer.zemris.bool.BooleanConstant;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanSource;
import hr.fer.zemris.bool.BooleanValue;

import java.util.Arrays;

public class BooleanOperatorNOT extends BooleanOperator {

	BooleanOperatorNOT(BooleanSource source) {
		super(Arrays.asList(source));
	}

	@Override
	public BooleanValue getValue() {
		for(BooleanSource s : this.getSources()) {
			if (s.equals(BooleanConstant.TRUE)) {
				return BooleanValue.FALSE;
			} else if ( s.equals(BooleanConstant.FALSE)) {
				return BooleanValue.TRUE;
			}
		}
		return BooleanValue.DONT_CARE;
	}
}
