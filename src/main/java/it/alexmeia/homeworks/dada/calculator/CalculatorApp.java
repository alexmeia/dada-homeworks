package it.alexmeia.homeworks.dada.calculator;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

/**
 * This class provides a main method to test the multiplication of integers
 * using the addition operator and not the the multiplication operator, and
 * storing the values in arrays. It takes an arbitrary number of integers as
 * arguments. If less than two arguments are passed, it multiplies two random
 * integers. It prints in the standard output the values of the factors as
 * arrays, the result as array, the factors as integer and the result as
 * integer.
 */

public class CalculatorApp {

	public static void main(String[] args) {

		int result = 1;
		String factorPrefix = "";

		StringBuilder footer = new StringBuilder("(");

		if (args.length < 2) {

			System.out.println("Arguments must be at least 2 integers. Let's multiply some random numbers.");

			args = new String[2];
			String multiplier = String.valueOf(new Random().nextInt(100));
			String multiplicand = String.valueOf(new Random().nextInt(100));
			args[0] = multiplier;
			args[1] = multiplicand;
		}

		System.out.println("Arrays to multiply:");

		int[] factors = new int[args.length];

		for (int i = 0; i < args.length; i++) {

			try {
				factors[i] = Integer.parseInt(args[i]);
				result *= factors[i];
				System.out.println(ArrayUtils.toString(CalculatorUtils.digitsToArray(factors[i])));
				footer.append(factorPrefix).append(args[i]);
				factorPrefix = " * ";
			} catch (NumberFormatException nfe) {
				// log omitted.
				System.out.println(String.format("Arguments must be integers. Failed to parse '%s'.", args[i]));
			}
		}

		footer.append(" = ").append(result).append(")");

		System.out.println("Result:");
		System.out.println(ArrayUtils.toString(CalculatorUtils.multiply(factors)));
		System.out.println(footer);
	}

}
