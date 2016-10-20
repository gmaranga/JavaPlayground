package com.mathandalgorithms;

import java.util.Arrays;

public class FractionsAndComplexNumbers {

	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(FractionsAndComplexNumbers.addFractions(new int[]{4, 9}, new int[]{1,6})));
		System.out.println(Arrays.toString(FractionsAndComplexNumbers.reduceFraction(new int[]{3,9})));

	}

	/**
	 * The numerator in the first element and the denominator in the second element. 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[] multiplyFractions(int[] a, int[] b){
		int[] c = {a[0]*b[0], a[1], b[1]};
		return c;
	}
	
	/**
	 * Only fractions with the same denominator can be added together. First of
	 * all we must find the common denominator of the two fractions and then use
	 * multiplication to transform the fractions such that they both have the
	 * common denominator as their denominator. The common denominator is a
	 * number which can divide both denominators and is simply the LCM. For
	 * example lets add 4/9 and 1/6. LCM of 9 and 6 is 18. Thus to transform the
	 * first fraction we need to multiply it by 2/2 and multiply the second one
	 * by 3/3: 4/9 + 1/6 = (4*2)/(9 * 2) + (1 * 3)/(6 * 3) = 8/18 + 3/18
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int [] addFractions(int[] a, int[] b){
		
		int denom = a[1] == b[1]?a[1]:GreatCommonDivisor.LCM(a[1], b[1]);
		System.out.println("denom: " + denom);
		System.out.println(denom + "/" + a[1] + " * " + a[0] + " + " + denom + " / " + b[1] + " * " + b[0]);
		int[] c = {denom/a[1]*a[0] + denom/b[1]*b[0], denom};
		return c;
	}
	
	public static int[] reduceFraction(int[] a){
		int b = GreatCommonDivisor.GDCEuclid(a[0], a[1]);
		System.out.println("GDC: " + b);
		a[0] /= b;
		a[1] /= b;
		return a;
	}
}
