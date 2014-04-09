package hr.fer.zemris.bool;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * BooleanOperator is an abstraction over boolean operators.
 * <p>
 * It has private list of sources based on which final result is calculated;
 * this list must be provided through BooleanOperator's protected constructor as
 * its only argument. Operators determine their domain by inspecting domains of
 * given sources and producing an union (here, the ordering of boolean variables
 * is not important).
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public abstract class BooleanOperator implements BooleanSource {

	private List<BooleanSource> booleanSourceList;

	protected BooleanOperator(List<BooleanSource> booleanSourceList) {
		this.booleanSourceList = booleanSourceList;
	}

	protected List<BooleanSource> getSources() {
		return booleanSourceList;
	}

	/**
	 * Inspects all sources domains and returns their union in a new
	 * List<BooleanVariable>.
	 */
	public List<BooleanVariable> getDomain() {
		List<BooleanVariable> booleanVariableList = new LinkedList<BooleanVariable>();
		for (BooleanSource s : booleanSourceList) {
			if(!booleanVariableList.contains(s)) {
				booleanVariableList.addAll(s.getDomain());
			}
			
		}

		return booleanVariableList;
	}
}
