package hr.fer.zemris.bool.fimpl;

import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.Mask;

import java.util.List;

public class MaskBasedBF {

	private String name;
	private List<BooleanVariable> domain;
	private boolean masksAreMinterms;
	private List<Mask> masks;
	private List<Mask> dontCareMasks;

	public MaskBasedBF(String name, List<BooleanVariable> domain,
			boolean masksAreMinterms, List<Mask> masks, List<Mask> dontCareMasks) {
		super();
		this.name = name;
		this.domain = domain;
		this.masksAreMinterms = masksAreMinterms;
		this.masks = masks;
		this.dontCareMasks = dontCareMasks;
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
