package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinCompetition {

	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("win.in"));
        } else {
            return new Scanner(System.in);
        }
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("win.out");
		} else {
			return new PrintWriter(System.out);
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		try(Scanner in = newInput(); PrintWriter out = newOutput()){
		
			int maxtime = 18000;
			int problems = 0;
			in.nextLine();
			List<String> times = Arrays.asList(in.nextLine().split(" "));
			int[] timeNumbers = times.stream().mapToInt(Integer::parseInt).toArray();
			Arrays.sort(timeNumbers);
			
			for(int i = 0; i < timeNumbers.length; i++){
				if((maxtime -= timeNumbers[i]) >= 0)
					problems +=1;
				else{
					break;
				}
			}
			
			out.println(problems);
		}
		
	}

}
