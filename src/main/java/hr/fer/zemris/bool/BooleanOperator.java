package hr.fer.zemris.bool;

import java.util.LinkedList;
import java.util.List;

public abstract class BooleanOperator implements BooleanSource {

	private List<BooleanSource> booleanSourceList;

	protected BooleanOperator(List<BooleanSource> booleanSourceList) {
		this.booleanSourceList = booleanSourceList;
	}

	protected List<BooleanSource> getSources() {
		return booleanSourceList;
	}

	public List<BooleanVariable> getDomain() {
		List<BooleanVariable> booleanVariableList = new LinkedList<BooleanVariable>();
		for (BooleanSource s : booleanSourceList) {
			booleanVariableList.addAll(s.getDomain());
		}

		return booleanVariableList;
	}
}
