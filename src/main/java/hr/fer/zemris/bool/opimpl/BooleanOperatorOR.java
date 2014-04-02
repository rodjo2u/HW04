package hr.fer.zemris.bool.opimpl;

import java.util.List;

import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanSource;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;

public class BooleanOperatorOR extends BooleanOperator {

	BooleanOperatorOR(List<BooleanSource> booleanSourceList) {
		super(booleanSourceList);
	}

	@Override
	public BooleanValue getValue() {
		boolean isTrue = false;
		for (BooleanVariable var : this.getDomain()) {
			if(var.getValue().equals(BooleanValue.DONT_CARE)) {
				return BooleanValue.DONT_CARE;
			} else if(var.getValue().equals(BooleanValue.TRUE)) {
				isTrue = true;
			}
		}
		if(isTrue) {
			return BooleanValue.TRUE;
		}
		return BooleanValue.FALSE;
	}

}
