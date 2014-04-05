package hr.fer.zemris.bool;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaskTest {

	@Test
	public void testMask() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsMoreGeneralThan() {
		fail("Not yet implemented");
	}

	@Test
	public void testCombine() {
		Mask mask1 = Mask.parse("100x0");
		Mask mask2 = Mask.parse("1011x");
		Mask result = Mask.combine(mask1, mask2);

		assertEquals("Masks in method combine are not equal.",
				Mask.parse("10xxx"), result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCombineException() {
		Mask.combine(Mask.parse("100x"), Mask.parse("1011x"));
	}

	@Test
	public void testFromIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfZeros() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfOnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

}
