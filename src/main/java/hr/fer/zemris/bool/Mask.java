package hr.fer.zemris.bool;

import java.util.Arrays;

/**
 * This class supports work with boolean functions by concept of masks.
 * <p>
 * For example, consider a Boolean function defined over variables A, B, C and D
 * (i.e. its domain is {A, B, C, D}). Mask “0x1x” represents all value
 * assignments in which A=0 and C=1 (we don't care for B and D)); here there are
 * for such assignments which we can specify by more specific masks: “0010”,
 * “0011”, “0110” and “0111”.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public class Mask {

	private MaskValue[] maskValues;

	public Mask(MaskValue[] maskValues) {
		this.maskValues = maskValues;
	}

	/**
	 * Method retrieves MaskValue at the specified index of the Mask instance.
	 * 
	 * @param index
	 *            - Mask index
	 * @return - MaskValue at submitted index
	 */
	public MaskValue getValue(int index) {
		if (index < 0 || index >= maskValues.length) {
			throw new IndexOutOfBoundsException("Mask length is: "
					+ maskValues.length + ". You have entered index: " + index);
		}
		return maskValues[index];
	}

	/**
	 * Parses input string and creates new Mask instance. Using fromIndex, you
	 * can specify masks which do not contain don't cares.
	 * <p>
	 * Example: m1 and m2 are equivalent <code>
	 * Mask m1 = new Mask(
	 *   new MaskValue[] { 
	 *     MaskValue.ZERO, MaskValue.DONT_CARE, MaskValue.ONE,
	 * MaskValue.DONT_CASE } ); 
	 * 
	 * Mask m2 = Mask.parse("0x1x");
	 * </code>
	 * </p>
	 * 
	 * @param input
	 *            String containing: '0', '1' or 'x' characters!
	 * @return - new Mask instance
	 */
	public static Mask parse(String input) {
		if (!input.matches("[0x1]+")) {
			throw new IllegalArgumentException(
					"Unsupported string type. Please enter the string only containing: '0', '1' or 'x' characters!");
		}
		MaskValue[] newMaskValues = new MaskValue[input.length()];
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '0') {
				newMaskValues[i] = MaskValue.ZERO;
			} else if (input.charAt(i) == '1') {
				newMaskValues[i] = MaskValue.ONE;
			} else {
				newMaskValues[i] = MaskValue.DONT_CARE;
			}
		}
		return new Mask(newMaskValues);
	}

	/**
	 * Compares if the current Mask instance is more general than submitted one.
	 * 
	 * @param otherMask
	 *            - must be of equal size as the current Mask instance
	 * @return - true or false
	 */
	public boolean isMoreGeneralThan(Mask otherMask) {
		if (maskValues.length != otherMask.getSize()) {
			throw new IllegalArgumentException("Masks differ in size!");
		}
		// check if otherMask is not more general
		for (int i = 0; i < maskValues.length; i++) {
			switch (maskValues[i]) {
				case DONT_CARE :
					continue;
				case ONE :
					if (otherMask.maskValues[i].equals(MaskValue.ONE)) {
						continue;
					} else {
						return false;
					}
				case ZERO :
					if (otherMask.maskValues[i].equals(MaskValue.ZERO)) {
						continue;
					} else {
						return false;
					}
			}
		}
		// if masks are not equal this.mask is more general
		if (!maskValues.equals(otherMask.maskValues)) {
			return true;
		}
		// otherwise they are equal and result is false
		return false;
	}

	/**
	 * Combines two masks into new, more general Mask instance.
	 * 
	 * @param mask1
	 *            - must be same size of mask2
	 * @param mask2
	 *            - must be same size of mask1
	 * @return new Mask instance
	 */
	public static Mask combine(Mask mask1, Mask mask2) {
		if (mask1 == null || mask2 == null) {
			throw new IllegalArgumentException("Mask cannot me null!");
		}
		if (mask1.getSize() != mask2.getSize()) {
			throw new IllegalArgumentException("Masks differ in size!");
		}
		MaskValue[] newMaskValues = new MaskValue[mask1.getSize()];
		// if diffCounter==0 at the end masks are the same
		int diffCounter = 0;
		for (int i = 0; i < mask1.getSize(); i++) {
			// if both mask value is DONT_CARE
			if (mask1.maskValues[i].equals(MaskValue.DONT_CARE)
					&& mask2.maskValues[i].equals(MaskValue.DONT_CARE)) {
				newMaskValues[i] = MaskValue.DONT_CARE;
			}
			// if one mask value is DONT_CARE
			if (mask1.maskValues[i].equals(MaskValue.DONT_CARE)
					&& !mask2.maskValues[i].equals(MaskValue.DONT_CARE)) {
				newMaskValues[i] = MaskValue.DONT_CARE;
				diffCounter++;
			}
			if (!mask1.maskValues[i].equals(MaskValue.DONT_CARE)
					&& mask2.maskValues[i].equals(MaskValue.DONT_CARE)) {
				newMaskValues[i] = MaskValue.DONT_CARE;
				diffCounter++;
			}
			// if mask values are the same
			if (mask1.maskValues[i].equals(mask2.maskValues[i])) {
				newMaskValues[i] = mask1.maskValues[i];
			} else {
				newMaskValues[i] = MaskValue.DONT_CARE;
				diffCounter++;
			}
		}
		if (diffCounter == 0) {
			return null;
		}
		return new Mask(newMaskValues);
	}

	public static Mask fromIndex(int size, int value) {
		// check arguments
		if (size < 1) {
			throw new IllegalArgumentException("Mask size must be > 0.");
		}
		if (value > Math.pow(2, size)) {
			throw new IllegalArgumentException(
					"Designated value is greater than size allows.");
		}
		// create new mask
		return Mask.parse(addPadding(Integer.toBinaryString(value), "0", size));
	}

	/**
	 * Returns the number of MaskValue.ZERO values in Mask instance.
	 * 
	 * @return number of ZERO values
	 */
	public int getNumberOfZeros() {
		int numberOfZeros = 0;
		for (MaskValue value : maskValues) {
			if (value.equals(MaskValue.ZERO)) {
				numberOfZeros++;
			}
		}
		return numberOfZeros;
	}

	/**
	 * Returns the number of MaskValue.ONE values in Mask instance.
	 * 
	 * @return number of ONE values
	 */
	public int getNumberOfOnes() {
		int numberOfOnes = 0;
		for (MaskValue value : maskValues) {
			if (value.equals(MaskValue.ONE)) {
				numberOfOnes++;
			}
		}
		return numberOfOnes;
	}

	/**
	 * Returns the number of MaskValue.DONT_CARE values in Mask instance.
	 * 
	 * @return number of DONT_CARE values
	 */
	public int getNumberOfDontCares() {
		int numberOfDontCares = 0;
		for (MaskValue value : maskValues) {
			if (value.equals(MaskValue.DONT_CARE)) {
				numberOfDontCares++;
			}
		}
		return numberOfDontCares;
	}

	public int getSize() {
		return maskValues.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(maskValues);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mask other = (Mask) obj;
		if (!Arrays.equals(maskValues, other.maskValues))
			return false;
		return true;
	}

	/**
	 * Adds padding at the start of the original String.
	 * 
	 * @param originalString
	 *            - original string value
	 * @param padding
	 *            - string value to use as a padding
	 * @param maxLength
	 *            - new String length
	 * @return new padded String instance
	 */
	private static String addPadding(String originalString, String padding,
			int maxLength) {
		String prefix = "";
		for (int i = 0; i < maxLength - originalString.length(); i++) {
			prefix = prefix.concat(padding);
		}
		return prefix + originalString;
	}

	@Override
	public String toString() {
		String result = "";
		for(int i=0; i<maskValues.length; i++) {
			switch(maskValues[i]) {
				case ZERO :
					result = result.concat("0");
					break;
				case ONE :
					result = result.concat("1");
					break;
				case DONT_CARE :
					result = result.concat("x");
					break;
			}
		}
		return result;
	}
}
