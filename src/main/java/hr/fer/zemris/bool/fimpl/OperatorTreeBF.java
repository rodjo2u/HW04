package hr.fer.zemris.bool.fimpl;

import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;

import java.util.List;

public class OperatorTreeBF {

	private String name;
	private List<BooleanVariable> domain;
	private BooleanOperator operatorTree;
	
	public OperatorTreeBF(String name, List<BooleanVariable> domain,
			BooleanOperator operatorTree) {
		super();
		this.name = name;
		this.domain = domain;
		this.operatorTree = operatorTree;
	}
	
	
	
	
	
	public String getName() {
		return name;
	}

	public List<BooleanVariable> getDomain() {
		return domain;
	}
	
	public BooleanValue getValue() {
		// TODO implement
		return BooleanValue.DONT_CARE;
	}

	
	
	
}
