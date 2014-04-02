package hr.fer.zemris.bool;

/**
 * Interface NamedBooleanSource represents a source with associated name.
 * 
 * @author Igor Petkovski
 * 
 */
public interface NamedBooleanSource extends BooleanSource {

	/**
	 * Returns String representation of the named source.
	 * 
	 * @return - String name
	 */
	public String getName();
}
