package hr.fer.zemris.bool;

import java.util.ArrayList;
import java.util.List;

public class Masks extends Mask {

	public Masks() {
		super(null);
	}

	public static List<Mask> fromIndexes(int size, int... values) {
		// TODO implement
		return null;
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
