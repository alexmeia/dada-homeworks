package it.alexmeia.homeworks.dada.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class CalculatorUtils {

	public static int[] multiply(int... args) {

		int[] result = digitsToArray(args[0]);
		int i = 1;
		while (i < args.length) {
			result = multiplyBySum(result, digitsToArray(args[i]));
			i++;
		}
		return cleanZerosAtEnd(result);
	}

	static int[] digitsToArray(int number) {

		String sNumber = String.valueOf(Math.abs(number));
		int length = sNumber.length();
		int[] intArray = new int[length];

		for (int i = 0; i < length; i++) {
			intArray[i] = Character.getNumericValue(sNumber.charAt(i));
		}

		ArrayUtils.reverse(intArray);
		return intArray;
	}

	static int multiplyByRepeatedAddition(int multiplier, int multiplicand) {

		int result = 0;
		int i = 0;

		while (i < multiplicand) {
			result += multiplier;
			i++;
		}

		return result;
	}

	// if there are zeros at the end of the array they will be removed.
	// if the array is all made of zeros (as result of a multiplication by 0)
	// the last 0 is left in place.
	static int[] cleanZerosAtEnd(int[] array) {

		for (int k = array.length - 1; k >= 0; k--) {
			if (array[k] == 0 && k != 0) {
				array = ArrayUtils.remove(array, k);
			} else {
				break;
			}
		}

		return array;
	}

	static int[] multiplyBySum(int[] multiplier, int[] multiplicand) {

		int size = multiplier.length + multiplicand.length;
		List<int[]> arraysToSum = new ArrayList<int[]>();

		for (int i = 0; i < multiplier.length; i++) {
			for (int j = 0; j < multiplicand.length; j++) {
				int[] arrayToSum = new int[size];
				int product = multiplyByRepeatedAddition(multiplier[i], multiplicand[j]);
				int targetIndex = i + j;
				int[] splittedPoduct = digitsToArray(product);
				for (int k = 0; k < splittedPoduct.length; k++) {
					arrayToSum[targetIndex + k] += splittedPoduct[k];
				}
				arraysToSum.add(arrayToSum);
			}
		}

		return cleanZerosAtEnd(sumArrays(arraysToSum, size));
	}

	static int[] sumArrays(List<int[]> arraysToSum, int size) {

		int shift = 0;
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			int sum = 0;
			for (int[] addend : arraysToSum) {
				sum += addend[i];
			}
			result[i] = (sum + shift) % 10;
			shift = (sum + shift) / 10;
		}

		return result;
	}

}
