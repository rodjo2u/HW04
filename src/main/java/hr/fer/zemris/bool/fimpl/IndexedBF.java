package hr.fer.zemris.bool.fimpl;

import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.pow;

/**
 * Implementation of user specific boolean function in which user submits
 * indexes wanted boolean variable results.
 * 
 * @author Igor Petkovski
 * 
 */
public class IndexedBF implements BooleanFunction {

	private String name;
	private List<BooleanVariable> domain;
	private boolean indexesAreMinterms;
	private List<Integer> indexes;
	private List<Integer> dontCares;

	public IndexedBF(String name, List<BooleanVariable> domain,
			boolean indexesAreMinterms, List<Integer> indexes,
			List<Integer> dontCares) {
		super();
		this.name = name;
		this.domain = domain;
		this.indexesAreMinterms = indexesAreMinterms;
		this.indexes = indexes;
		this.dontCares = dontCares;
	}

	public String getName() {
		return name;
	}

	public List<BooleanVariable> getDomain() {
		return domain;
	}

	/**
	 * 
	 */
	public boolean hasMinterm(int index) {
		if (index < 1 || index > pow(2d, domain.size())) {
			throw new IndexOutOfBoundsException(
					"This function contains less resul values!");
		}
		if (indexesAreMinterms && indexes.contains(index)) {
			return true;
		} else if (!indexesAreMinterms && !indexes.contains(index)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if function contains Maxterm at given index.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean hasMaxterm(int index) {
		return !hasMinterm(index);
	}

	/**
	 * Checks if the value at the given index is DONT_CARE.
	 * 
	 * @param index
	 *            - index at which to check the value
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean hasDontCare(int index) {
		if (domain.get(index).getValue().equals(BooleanValue.DONT_CARE)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the list of DONT_CARE indexes.
	 * 
	 * @return List of Integer indexes of dont_care values.
	 */
	public Iterable<Integer> dontCareIterable() {
		return dontCares;
	}

	/**
	 * Returns the list of maxterm indexes.
	 */
	@Override
	public Iterable<Integer> maxtermIterable() {
		Iterator<Integer> iterator = new MaxtermIterator();
		List<Integer> list = new LinkedList<>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	/**
	 * Implements an iterator over maxterm indexes.
	 * 
	 * @author Igor Petkovski
	 * 
	 */
	private class MaxtermIterator implements Iterator<Integer> {

		private int current = 0;
		private List<Integer> newList = new ArrayList<>();

		private MaxtermIterator() {
			if (indexesAreMinterms) {
				for (int i = 0; i < pow(2, domain.size()); i++) {
					if (!indexes.contains(i) && !dontCares.contains(i)) {
						newList.add(i);
					}
				}
			} else {
				newList = indexes;
			}
		}

		@Override
		public boolean hasNext() {
			return current < newList.size();
		}

		@Override
		public Integer next() {
			return newList.get(current++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"You cannot remove an element from boolean function: "
							+ name);
		}

	}

	/**
	 * Returns the list of minterm indexes.
	 * 
	 * @return - list of Integer indexes.
	 */
	public Iterable<Integer> mintermIterable() {
		Iterator<Integer> iterator = new MintermIterator();
		List<Integer> list = new LinkedList<>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	/**
	 * Creates iterator over minterm indexes.
	 * 
	 * @author Igor Petkovski
	 * 
	 */
	private class MintermIterator implements Iterator<Integer> {

		private int current = 0;
		private List<Integer> newList = new ArrayList<>();

		private MintermIterator() {
			if (!indexesAreMinterms) {
				for (int i = 0; i < domain.size(); i++) {
					if (!indexes.contains(i) && !dontCares.contains(i)) {
						newList.add(i);
					}
				}
			} else {
				newList = indexes;
			}
		}

		@Override
		public boolean hasNext() {
			return current < newList.size();
		}

		@Override
		public Integer next() {
			return newList.get(current++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"You cannot remove an element from boolean function: "
							+ name);
		}

	}

	/**
	 * Unavailable in Function context. Value is range of minterms, maxterms and
	 * dont_care's.
	 */
	@Override
	public BooleanValue getValue() {
		return null;
	}

}
