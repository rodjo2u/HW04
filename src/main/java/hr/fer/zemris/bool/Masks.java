package hr.fer.zemris.bool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Masks extends Mask {

	private Masks() {
		super(null);
	}

	/**
	 * Creates a list of masks based on Mask.fromIndex function. You can supply
	 * the method with arbitrary number of values, but all masks will be of
	 * constant size.
	 * 
	 * Masks will only have values 0 and 1.
	 * 
	 * @param size
	 *            length of the masks
	 * @param values
	 *            list of masks with the values values
	 * @return List<Mask>
	 */
	public static List<Mask> fromIndexes(int size, int... values) {
		List<Mask> list = new LinkedList<>();
		for (int value : values) {
			list.add(Mask.fromIndex(size, value));
		}
		return list;
	}

	/**
	 * Creates new list of {@link Mask}s from string resources.
	 * 
	 * @param strings
	 *            - unlimited number of arguments
	 * @return new List<Mask> (ArrayList internally)
	 */
	public static List<Mask> fromStrings(String... strings) {
		List<Mask> list = new ArrayList<Mask>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			list.add(Mask.parse(strings[i]));
		}
		return list;
	}
}
