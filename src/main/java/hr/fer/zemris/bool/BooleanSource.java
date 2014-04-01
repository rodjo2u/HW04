package hr.fer.zemris.bool;

import java.util.List;

/**
 * Interface BooleanSource represents any source capable of producing legal
 * {@link BooleanValue}.
 * 
 * @author Igor Petkovski
 * 
 */
public interface BooleanSource {

	/**
	 * Returns instances {@link BooleanValue}.
	 * 
	 * @return - {@link BooleanValue}
	 */
	public BooleanValue getValue();

	/**
	 * Returns the list of variables on which the method getValue() has produced
	 * it's value.
	 * 
	 * @return
	 */
	public List<BooleanVariable> getDomain();
}
