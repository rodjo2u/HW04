package hr.fer.zemris.bool.fimpl;

import hr.fer.zemris.bool.BooleanFunction;
import hr.fer.zemris.bool.BooleanOperator;
import hr.fer.zemris.bool.BooleanValue;
import hr.fer.zemris.bool.BooleanVariable;
import hr.fer.zemris.bool.Mask;

import java.util.LinkedList;
import java.util.List;

public class OperatorTreeBF implements BooleanFunction {

	private String name;
	private List<BooleanVariable> domain;
	private BooleanOperator operatorTree;
	private IndexedBF indexedBF;

	public OperatorTreeBF(String name, List<BooleanVariable> domain,
			BooleanOperator operatorTree) {
		super();
		this.name = name;
		this.domain = domain;
		this.operatorTree = operatorTree;
		initialize();
	}

	public String getName() {
		return name;
	}

	public List<BooleanVariable> getDomain() {
		return domain;
	}

	public BooleanValue getValue() {
		return operatorTree.getValue();
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
		List<BooleanVariable> list = operatorTree.getDomain();
		if(!domain.containsAll(list)) {
			throw new IllegalArgumentException("Domain does not include all "
					+ "variables contained in operatorTree!");
		} else {
			List<Mask> listMasks = createMaskList(domain.size());
			List<Integer> minterms = new LinkedList<>();
			List<Integer> dontCares = new LinkedList<>();
			int counter = 0;
			for (Mask mask : listMasks) {
				for(int i=0; i<mask.getSize(); i++) {
					switch(mask.getValue(i)) {
					case ZERO :
						domain.get(i).setValue(BooleanValue.FALSE);
						break;
					case ONE :
						domain.get(i).setValue(BooleanValue.TRUE);
						break;
					case DONT_CARE :
						domain.get(i).setValue(BooleanValue.DONT_CARE);
						break;
					}
				}
				if(operatorTree.getValue().equals(BooleanValue.TRUE)) {
					minterms.add(counter);
				} else if (operatorTree.getValue().equals(BooleanValue.DONT_CARE)) {
					dontCares.add(counter);
				}
				counter++;
			}
			indexedBF = new IndexedBF(name, domain, true,
					minterms, dontCares);
		}
		
	}
	
	private List<Mask> createMaskList(int domainSize) {
		List<Mask> list = new LinkedList<>();
		for(int i=0; i<Math.pow(2, domainSize); i++) {
			list.add(Mask.fromIndex(domainSize, i));
		}
		return list;
	}

}
