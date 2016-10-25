package it.alexmeia.homeworks.dada.calculator;

import java.util.Arrays;

import org.junit.Test;

import it.alexmeia.homeworks.dada.calculator.CalculatorUtils;
import junit.framework.TestCase;

public class CalculatorUtilsTest extends TestCase {

	@Test
	public void testDigitsToArray() {

		int[] expected = { 5, 1 };
		assertTrue("Integer is converted in a single digit array.",
				Arrays.equals(expected, CalculatorUtils.digitsToArray(15)));
		assertTrue("The sign of the integer is ignored. Only its absolute value is taken into account.",
				Arrays.equals(expected, CalculatorUtils.digitsToArray(-15)));
	}

	@Test
	public void testMultiplyByRepeatedAddition() {

		assertEquals(20, CalculatorUtils.multiplyByRepeatedAddition(4, 5));
		assertEquals((42 * 75), CalculatorUtils.multiplyByRepeatedAddition(42, 75));
	}

	@Test
	public void testMultiplyBySum() {

		assertTrue("Multiplication by 1 produces the returns result.",
				Arrays.equals(new int[] { 8, 4 }, CalculatorUtils.multiplyBySum(new int[] { 8, 4 }, new int[] { 1 })));

		assertTrue("Multiplication between arrays of length 2 returns the expected result.", Arrays.equals(
				new int[] { 8, 0, 1, 3 }, CalculatorUtils.multiplyBySum(new int[] { 2, 4 }, new int[] { 4, 7 })));

		assertTrue("Multiplication between arrays of various lengths returns the expected result.", Arrays.equals(
				new int[] { 2, 5, 7, 9 }, CalculatorUtils.multiplyBySum(new int[] { 2, 1, 2 }, new int[] { 6, 4 })));

		assertTrue("Multiplication by 0 produce an array with one element of value 0 (by design).",
				Arrays.equals(new int[] { 0 }, CalculatorUtils.multiplyBySum(new int[] { 9, 9, 9 }, new int[] { 0 })));
	}

	@Test
	public void testCleanZerosAtEnd() {

		int[] dirtyArray = { 2, 5, 7, 1, 0 };
		int[] expectedArray = { 2, 5, 7, 1 };

		assertTrue("There are no zeros at the end of teh array...",
				Arrays.equals(expectedArray, CalculatorUtils.cleanZerosAtEnd(dirtyArray)));

		int[] arrayOfZeros = { 0, 0, 0, 0 };
		int[] arrayOfOneZero = { 0 };

		assertTrue("...unless 0 is the first element too.",
				Arrays.equals(arrayOfOneZero, CalculatorUtils.cleanZerosAtEnd(arrayOfZeros)));
	}

	@Test
	public void testMultiply() {

		assertTrue("Multiplication between 2 integers returns the expected result.",
				Arrays.equals(new int[] { 8, 0, 1, 3 }, CalculatorUtils.multiply(42, 74)));

		assertTrue("Multiplication between 4 integers returns the expected result.",
				Arrays.equals(new int[] { 0, 0, 6, 3, 5, 7 }, CalculatorUtils.multiply(5, 96, 785, 2)));

		assertTrue("Multiplication by 0 returns the expected result.",
				Arrays.equals(new int[] { 0 }, CalculatorUtils.multiply(42, 74, 77, 0)));

		assertTrue("Multiplication by 1 returns the expected result.",
				Arrays.equals(new int[] { 2, 4 }, CalculatorUtils.multiply(1, 42)));

		assertTrue("Multiplication with negative factors returns the expected result (factors sign is ignored).",
				Arrays.equals(new int[] { 0, 6 }, CalculatorUtils.multiply(3, -4, 5)));

		assertTrue("Multiplication between 23, 56 and 785 returns the expected result.",
				Arrays.equals(new int[] { 0, 8, 0, 1, 1, 0, 1 }, CalculatorUtils.multiply(23, 56, 785)));
	}

}
