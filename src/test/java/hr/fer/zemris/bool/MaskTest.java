package hr.fer.zemris.bool;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaskTest {

	@Test
	public void testMask() {
		Mask mask = new Mask(new MaskValue[] {MaskValue.ONE, MaskValue.ZERO, MaskValue.DONT_CARE});
		assertEquals("This should create a new Mask instance!", Mask.class, mask.getClass());
	}

	@Test
	public void testGetValue() {
		Mask mask1 = Mask.parse("x01");
		
		assertEquals("Mask values should be the same", MaskValue.ZERO, mask1.getValue(1));
	}

	@Test
	public void testParse() {
		Mask mask1 = new Mask( new MaskValue[] {MaskValue.DONT_CARE, MaskValue.ZERO, MaskValue.ONE});
		Mask mask2 = Mask.parse("x01");
		
		assertEquals("mask1 should be equal to mask2.", true, mask1.equals(mask2));
	}

	@Test
	public void testIsMoreGeneralThan() {
		Mask mask1 = Mask.parse("01xx");
		Mask mask2 = Mask.parse("01x0");
		
		assertEquals("Mask 1 should be more general.", true, mask1.isMoreGeneralThan(mask2));
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
	public void testCombineSame() {
		assertEquals("Masks are not the same!", null,  Mask.combine(Mask.parse("10x"), Mask.parse("10x")));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testFromIndexFailSize() {
		Mask.fromIndex(0, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFromIndexFailValue() {
		Mask.fromIndex(2, 5);
	}
	
	@Test
	public void testFromIndex() {
		assertEquals("Masks should be equal!", Mask.parse("101"), Mask.fromIndex(3, 5));
	}

	@Test
	public void testGetNumberOfZeros() {
		assertEquals("Nuber of zeroes is not what it should be.", 2, Mask.parse("010").getNumberOfZeros());
	}
	
	@Test
	public void testGetNumberOfDontCares() {
		assertEquals("Nuber of dont cares is not what it should be.", 2, Mask.parse("x10x").getNumberOfDontCares());
	}

	@Test
	public void testGetNumberOfOnes() {
		assertEquals("Nuber of zeroes is not what it should be.", 1, Mask.parse("010").getNumberOfOnes());
	}

	@Test
	public void testGetSize() {
		assertEquals("Nuber of zeroes is not what it should be.", 3, Mask.parse("010").getSize());
	}

}
