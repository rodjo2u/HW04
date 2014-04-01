package hr.fer.zemris.bool;

import java.util.LinkedList;
import java.util.List;

/**
 * Unmodifiable instance of {@link BooleanConstant}. Contains two instances TRUE
 * and FALSE.
 * <p>
 * Unmodifiable instance. It has two public final static class instances denoted
 * TRUEand FALSEwhich are statically initialized to hold an instance of constant
 * always providing value TRUEand an instance of constant always providing value
 * FALSE.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public class BooleanConstant implements BooleanSource {

	private BooleanValue value;

	private BooleanConstant(BooleanValue value) {
		this.value = value;
	}

	public BooleanValue getValue() {
		return this.value;
	}

	public List<BooleanVariable> getDomain() {
		return new LinkedList<BooleanVariable>();
	}

	public static final BooleanConstant TRUE = new BooleanConstant(
			BooleanValue.TRUE);

	public static final BooleanConstant FALSE = new BooleanConstant(
			BooleanValue.FALSE);
}
