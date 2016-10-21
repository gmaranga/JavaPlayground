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
		
		try(Scanner in = newInput(); PrintWriter out = newOutput()){
			int k = in.nextInt();
			
			for(int i = 2; i <= k; i++){
				System.out.println("divs for: " + i + " =  " + countDivisors(i));
			}
		}
	}
	
	private static int countDivisors(int i){
		int count = 0;
		
		for(int j = 1; j <= Math.sqrt(i); j++){
			if (i % j == 0){
				count += 2;
			}
		}
		return count;
	}

}
