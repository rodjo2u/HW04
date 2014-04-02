package hr.fer.zemris.bool.opimpl;

import java.util.List;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanSource;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;

public class BooleanOperatorAND extends BooleanOperator {

	public BooleanOperatorAND(List<BooleanSource> list) {
		super(list);
	}

	@Override
	public BooleanValue getValue() {
		boolean isDontCare = false;
		for (BooleanVariable var : this.getDomain()) {
			if (var.getValue().equals(BooleanValue.FALSE)) {
				return BooleanValue.FALSE;
			} else if (var.getValue().equals(BooleanValue.DONT_CARE)) {
				isDontCare = true;
			}
		}
		if (isDontCare) {
			return BooleanValue.DONT_CARE;
		}
		return BooleanValue.TRUE;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
