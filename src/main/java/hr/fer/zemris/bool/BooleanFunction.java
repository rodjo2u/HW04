package hr.fer.zemris.bool;

/**
 * Interface BooleanFunctionrepresents an abstract boolean function.
 * <p>
 * It declares methods like hasMinterm, hasMaxterm and hasDontCare; they accept
 * a single argument: index, and check if function contains appropriate
 * minterm/maxterm/dontcare. Index uniquely specifies the values for domain's
 * variables.
 * </p>
 * <p>
 * For example, if domain is list {A,B,C}, index 3, binary 011, represents value
 * assignment A=0, B=1, C=1. However, if domain is list {B,C,A}, index 3, binary
 * 011, represents value assignment B=0, C=1, A=1.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public interface BooleanFunction extends NamedBooleanSource {

	public boolean hasMinterm(int index);

	public boolean hasMaxterm(int index);

	public boolean hasDontCare(int index);

	public Iterable<Integer> mintermIterable();

	public Iterable<Integer> maxtermIterable();

	public Iterable<Integer> dontCareIterable();

}
