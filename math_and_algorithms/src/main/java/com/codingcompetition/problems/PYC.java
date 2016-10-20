package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PYC {

	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new Scanner(new File("prepare.in"));
		} else {
			return new Scanner(System.in);
		}
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("prepare.out");
		} else {
			return new PrintWriter(System.out);
		}
	}

	public static void main(String[] args) throws IOException
	{
		try (Scanner in = newInput(); PrintWriter out = newOutput()) {
			
			int length = Integer.valueOf(in.nextLine());
			boolean[] atLeastOneOfEach = {false, false};
			List<Integer> diffs = new ArrayList<>();

			long result = 0;
			
			String[] ps = in.nextLine().split(" ");
			String[] ts = in.nextLine().split(" ");
			
			for(int i = 0;i < length; i++){
				
				int p = Integer.valueOf(ps[i]);
				int t = Integer.valueOf(ts[i]);
				boolean condition = p > t;
				
				result += (condition)?p:t; 
				
				if(condition){
					atLeastOneOfEach[0] = true;
				}else{
					atLeastOneOfEach[1] = true;
				}
				
				diffs.add(Math.abs(p - t));
			}
			
			if(!(atLeastOneOfEach[0] && atLeastOneOfEach[1])){
				result -= diffs.stream().reduce(Math::min).get();
			}
			
			out.println(result);
		}

	}

}
