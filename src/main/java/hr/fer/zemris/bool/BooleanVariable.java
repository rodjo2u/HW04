package hr.fer.zemris.bool;

import java.util.LinkedList;
import java.util.List;

/**
 * NamedBooleanSource implementation. Has it's name and value attributes.
 * <p>
 * By default, variable's value is set to FALSE.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public class BooleanVariable implements NamedBooleanSource {

	private BooleanValue value = BooleanValue.FALSE;
	private String name;

	public BooleanVariable(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public BooleanValue getValue() {
		return this.value;
	}

	public void setValue(BooleanValue value) {
		this.value = value;
	}

	public List<BooleanVariable> getDomain() {
		LinkedList<BooleanVariable> list = new LinkedList<BooleanVariable>();
		list.add(this);
		return list;
	}
}
