package hr.fer.zemris.bool;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class MasksTest {

	@Test
	public void testFromIndexes() {
		List<Mask> list1 = Masks.fromIndexes(5, 1, 2, 3, 4, 5);
		List<Mask> list2 = new LinkedList<>();
		assertEquals("Lists should be the same class.", list1.getClass(),
				list2.getClass());
	}

	@Test
	public void testFromStrings() {
		List<Mask> list1 = Masks.fromStrings("x", "01", "101x");
		List<Mask> list2 = new ArrayList<>();
		assertEquals("Lists should be the same class.", list1.getClass(),
				list2.getClass());
	}

}
