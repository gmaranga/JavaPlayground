package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GenerateTests {

	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("testgen.in"));
        } else {
            return new Scanner(System.in);
        }
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("testgen.out");
		} else {
			return new PrintWriter(System.out);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		long start = System.currentTimeMillis();
		
		try(Scanner in = newInput(); PrintWriter out = newOutput()){
			int k = in.nextInt();
			long numberWithMaxDivs = 2;
			long maxDivs = 0;
			
			for(int i = 2; i <= k; i++){
				
				if(i % 6 != 0) continue; 
				long divs = countDivisors(i);
				if(divs > maxDivs){
					maxDivs = divs;
					numberWithMaxDivs = i;
				}
			}
			
			out.println(k - numberWithMaxDivs + 1);
			long end = System.currentTimeMillis();
			out.println((end-start)*1000);
			out.println(numberWithMaxDivs);
			out.println(maxDivs);
		}
		
		
	}
	
	private static int countDivisors(int i){
		int count = 0;
		
		for(int j = 1; j <= Math.sqrt(i); j++){
			if (i % j == 0){
				count +=(j==i/j)?1:2;
			}
		}
		return count;
	}

}
