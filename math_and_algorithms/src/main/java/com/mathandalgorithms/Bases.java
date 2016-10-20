package com.mathandalgorithms;

public class Bases {

	public static void main(String[] args) {
//		Bases.toDecimal(1011, 2);
//		Bases.fromDecimal(43, 2);
		Bases.fromDecimal2(24, 16);
	}

	/**
	 * Consider the decimal number 4325. 4325 stands for 5 + 2 x 10 + 3 x 10 x
	 * 10 + 4 x 10 x 10 x 10. Notice that the "value" of each consequent digit
	 * increases by a factor of 10 as we go from right to left.
	 * 
	 * Converts a number n in base b (2<=b<=10) to a decimal number
	 * 
	 * @param n
	 * @param b
	 * @return
	 */
	public static int toDecimal(int n, int b) {

		System.out.println("n: " + n + " b: " + b);
		int result = 0;
		int multiplier = 1;

		while (n > 0) {

			System.out.println("n: " + n);
			System.out.println("n%10: " + n % 10);
			System.out.println("n%10*multiplier: " + n % 10 * multiplier);

			result += n % 10 * multiplier;
			System.out.println("result: " + result);

			multiplier *= b;
			System.out.println("multiplier: " + multiplier);
			n /= 10;
		}
		return result;
	}

	/**
	 * To convert from a decimal to a binary is just as easy. Suppose we wanted
	 * to convert 43 in decimal to binary. At each step of the method we divide
	 * 43 by 2 and memorize the remainder. The final list of remainders is the
	 * required binary representation
	 * 
	 * 43/2 = 21 + remainder 1 21/2 = 10 + remainder 1 10/2 = 5 + remainder 0
	 * 5/2 = 2 + remainder 1 2/2 = 1 + remainder 0 1/2 = 0 + remainder 1
	 * 
	 * By swapping all occurrences of 10 with b in our previous method we create
	 * a function which converts from a decimal number n to a number in base b
	 * (2<=b<=10)
	 * 
	 * @param n
	 * @param b
	 * @return
	 */
	public static int fromDecimal(int n, int b) {

		int result = 0;
		int multiplier = 1;

		System.out.println("n: " + n + " b: " + b);
		
		while (n > 0) {
		
			System.out.println("n: " + n);
			System.out.println("n%b: " + n % b);
			System.out.println("n%b*multiplier: " + n % b * multiplier);
			
			result += n % b * multiplier;
			System.out.println("result: " + result);
			
			multiplier *= 10;
			System.out.println("multiplier: " + multiplier);
			
			n /= b;
		}
		return result;
	}
	
	/**
	 * If the base b is above 10 then we must use non-numeric characters to
	 * represent digits that have a value of 10 and more. We can let ‘A’ stand
	 * for 10, ‘B’ stand for 11 and so on. The following code will convert from
	 * a decimal to any base (up to base 20):
	 * 
	 * @param n
	 * @param b
	 * @return
	 */
	public static String fromDecimal2(int n, int b) {

		String chars = "0123456789ABCDEFGHIJ";
		String result = "";

		while (n > 0) {

			System.out.println("n: " + n + " b: " + b);
			System.out.println("n%b: " + n % b);
			System.out.println("chars.charAt(n%b): " + chars.charAt(n % b));
			result = chars.charAt(n % b) + result;
			System.out.println("result: " + result);
			n /= b;
		}
		return result;
	}
}
