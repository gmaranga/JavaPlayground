package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteCodeTemplate {

	
	static Scanner newInput() throws IOException {
//		if (System.getProperty("JUDGE") != null) {
			return new Scanner(new File("template.in"));
//		} else {
//			return new Scanner(System.in);
//		}
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("template.out");
		} else {
			return new PrintWriter(System.out);
		}
	}
	
	public static void main(String[] args) throws IOException{

		try(Scanner in = newInput(); PrintWriter out = newOutput()){
//			int columns = in.nextInt();
//			int rows = in.nextInt();
//			String keyboard = in.next();
//			String next = in.next();
//					
//			out.println("c:" + columns);
//			out.println("r:" + rows);
//			out.println("k:"+keyboard);
//			out.println("n:"+next);
			
			while(in.hasNext()){
				out.println(in.next());
			}
		}
		
	}

}
