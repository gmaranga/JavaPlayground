package com.mathandalgorithms;

public class GreatCommonDivisor {

	public static void main(String[] args) {
		System.out.println(GreatCommonDivisor.GDCEuclid(2336, 1314));
		System.out.println(GreatCommonDivisor.LCM(3, 9));
	}

	/**
	 * The greatest common divisor (GCD) of two numbers a and b is the greatest
	 * number that divides evenly into both a and b. Naively we could start from
	 * the smallest of the two numbers and work our way downwards until we find
	 * a number that divides into both of them
	 * 
	 * @return
	 */
	public static int GCDNaive(int a, int b) {

		for (int i = Math.min(a, b); i >= 1; i--) {
			if (a % i == 0 && b % i == 0)
				return i;
		}
		return -1;
	}

	/**
	 * There is a faster method called Euclid’s algorithm. Euclid’s algorithm
	 * iterates over the two numbers until a remainder of 0 is found. For
	 * example, suppose we want to find the GCD of 2336 and 1314. We begin by
	 * expressing the larger number (2336) in terms of the smaller number (1314)
	 * plus a remainder
	 * 
	 * 2336 = 1314 x 1 + 1022
	 * 
	 * We now do the same with 1314 and 1022:
	 * 
	 * 1314 = 1022 x 1 + 292
	 * 
	 * We continue this process until we reach a remainder of 0:
	 * 
	 * 1022 = 292 x 3 + 146
	 * 292 = 146 x 2 + 0
	 * 
	 * The last non-zero remainder is the GCD. So the GCD of 2336 and 1314 is 146. 
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int GDCEuclid(int a, int b) {
		
		System.out.println("a: " + a + " b: " + b);
		if(b == 0) return a;
		
		return GDCEuclid(b, a%b);
	}
	
	/**
	 * Using this algorithm we can find the lowest common multiple (LCM) of two
	 * numbers. The smallest positive integer that is divisible by both a and b.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int LCM(int a, int b){
		
		return b*a/GreatCommonDivisor.GDCEuclid(a, b);
	}

}
