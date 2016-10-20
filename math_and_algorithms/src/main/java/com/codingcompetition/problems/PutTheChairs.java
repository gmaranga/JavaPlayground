package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PutTheChairs {

	
	/**
	 * http://www.regentsprep.org/regents/math/geometry/gp10/midlinel.htm
	 * @return
	 * @throws IOException
	 */
	
	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new Scanner(new File("chairs.in"));
		} else {
			return new Scanner(System.in);
		}
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("chairs.out");
		} else {
			return new PrintWriter(System.out);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		try(Scanner in = newInput(); PrintWriter out = newOutput()){
			
			double firstDistance = in.nextDouble()/2;
			double secondDistance = in.nextDouble()/2;
			double thirdDistance = in.nextDouble()/2;
			out.println((firstDistance + secondDistance + thirdDistance) / 3);
		}

	}

}
