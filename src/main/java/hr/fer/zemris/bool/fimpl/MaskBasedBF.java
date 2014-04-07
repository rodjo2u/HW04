package hr.fer.zemris.bool.fimpl;

import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.Mask;

import java.util.LinkedList;
import java.util.List;

public class MaskBasedBF implements BooleanFunction {

	private String name;
	private List<BooleanVariable> domain;
	private boolean masksAreMinterms;
	private List<Mask> masks;
	private List<Mask> dontCareMasks;

	private IndexedBF indexedBF;

	public MaskBasedBF(String name, List<BooleanVariable> domain,
			boolean masksAreMinterms, List<Mask> masks, List<Mask> dontCareMasks) {
		super();
		this.name = name;
		this.domain = domain;
		this.masksAreMinterms = masksAreMinterms;
		this.masks = masks;
		this.dontCareMasks = dontCareMasks;
		initialize();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<BooleanVariable> getDomain() {
		return domain;
	}

	/**
	 * Unavailable in Function context. Value is range of minterms, maxterms and
	 * dont_care's.
	 */
	@Override
	public BooleanValue getValue() {
		return null;
	}

	@Override
	public boolean hasMinterm(int index) {
		return indexedBF.hasMinterm(index);
	}

	@Override
	public boolean hasMaxterm(int index) {
		return indexedBF.hasMaxterm(index);
	}

	@Override
	public boolean hasDontCare(int index) {
		return indexedBF.hasDontCare(index);
	}

	@Override
	public Iterable<Integer> mintermIterable() {
		return indexedBF.mintermIterable();
	}

	@Override
	public Iterable<Integer> maxtermIterable() {
		return indexedBF.maxtermIterable();
	}

	@Override
	public Iterable<Integer> dontCareIterable() {
		return indexedBF.dontCareIterable();
	}

	private void initialize() {
		List<Mask> parsedMasksMinterms = removeDontCares(masks);
		List<Integer> mintermsOrMaxterms = new LinkedList<>();
		for (Mask mask : parsedMasksMinterms) {
			mintermsOrMaxterms.add(Integer.parseInt(mask.toString(), 2));
		}
		List<Mask> parsedMasksDontCares = removeDontCares(dontCareMasks);
		List<Integer> dontCares = new LinkedList<>();
		for (Mask mask : parsedMasksDontCares) {
			dontCares.add(Integer.parseInt(mask.toString(), 2));
		}
		indexedBF = new IndexedBF(name, domain, masksAreMinterms,
				mintermsOrMaxterms, dontCares);
	}

	/**
	 * Returns List<Mask> in which no mask contains DONT_CARE value.
	 * 
	 * @param maskList
	 *            -original Mask list
	 * @return - List<Mask>
	 */
	private List<Mask> removeDontCares(List<Mask> maskList) {
		List<Mask> newList = new LinkedList<>();
		for (Mask mask : maskList) {
			if (mask.getNumberOfDontCares() == 0) {
				newList.add(mask);
			} else {
				addParsedMask(newList, mask);
			}
		}
		return newList;
	}

	/**
	 * Method only used my removeDontCares. Parses masks containing x vales.
	 * Replaces x with 0 and 1.
	 * 
	 * @param list
	 * @param mask
	 */
	private void addParsedMask(List<Mask> list, Mask mask) {
		if (mask.getNumberOfDontCares() == 0) {
			list.add(mask);
		} else {
			addParsedMask(list,
					Mask.parse(mask.toString().replaceFirst("x", "0")));
			addParsedMask(list,
					Mask.parse(mask.toString().replaceFirst("x", "1")));
		}
	}
}
