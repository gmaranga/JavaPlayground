package com.mathandalgorithms;

import java.util.Arrays;

public class Primes {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(Primes.sieve(10)));
		
	}

	/**
	 * A number is prime if it is only divisible by 1 and itself.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n) {

		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * Need to check divisibility for values of i that are less or equal to the
	 * square root of n (call this m). If n divides a number that is greater
	 * than m then the result of that division will be some number less than m
	 * and thus n will also divide a number less or equal to m. Another
	 * optimization is to realize that there are no even primes greater than 2.
	 * Once we’ve checked that n is not even we can safely increment the value
	 * of i by 2.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrimeOptimized(int n) {

		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;

		int m = (int) Math.sqrt(n);

		for (int i = 3; i <= m; i += 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * Suppose we wanted to find all the primes from 1 to 100000, then we would
	 * have to call the above method 100000 times. This would be very
	 * inefficient since we would be repeating the same calculations over and
	 * over again. In this situation it is best to use a method known as the
	 * Sieve of Eratosthenes. The Sieve of Eratosthenes will generate all the
	 * primes from 2 to a given number n. It begins by assuming that all numbers
	 * are prime. It then takes the first prime number and removes all of its
	 * multiples. It then applies the same method to the next prime number. This
	 * is continued until all numbers have been processed
	 * 
	 * @param n
	 * @return
	 */
	public static boolean[] sieve(int n) {

		System.out.println("n: " + n);
		
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;
		int m = (int) Math.sqrt(n);

		System.out.println("m: " + m);
		
		for (int i = 2; i <= m; i++) {
			System.out.println("i: " + i);
			if (prime[i]) {
				for (int k = i * i; k <= n; k += i) {
					System.out.println("k: " + k);
					prime[k] = false;
				}
			}
		}

		return prime;
	}

}
