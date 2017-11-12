package com.codingcompetition.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class Snowmen {

	static BufferedReader newInput() throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream("snowmen.in")));
//		 return new BufferedReader(new InputStreamReader(QueueMinImpl.class.getResourceAsStream("snowmen2.in")));
	}

	static PrintWriter newOutput() throws IOException {
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("snowmen.out"))), false);
	}
	
	public static void main(String[] args) throws IOException {

		
		
		try(BufferedReader in = newInput(); PrintWriter out = newOutput()){
			
			ArrayDeque<Integer>[] snowmen = new ArrayDeque[Integer.valueOf(in.readLine())+1];
			ArrayDeque<Integer> first = new ArrayDeque<>();
			first.push(0);
			snowmen[0] = first;
			String line;
			int count = 1;
			
			while((line = in.readLine()) != null){
				String [] ops = line.split(" ");
				int snowmenpos = Integer.valueOf(ops[0]);
				int snowmenvalue = Integer.valueOf(ops[1]);
				ArrayDeque<Integer> clone = cloneSnowmen(snowmen[snowmenpos]);
				
				if(snowmenvalue != 0){
					
					clone.push(snowmenvalue);
					snowmen[count] = clone;
					
				}else{
					clone.pop();
					snowmen[count] = clone;
				}
				count++;
			}
			
			int total = 0;
			for(int j = 0; j < snowmen.length; j++){
				total += snowmen[j].stream().mapToInt(Integer::intValue).sum();
			}
			out.println(total);
		}
		
	}
	
	private static ArrayDeque<Integer> cloneSnowmen(ArrayDeque<Integer> original){
		ArrayDeque<Integer> cloned = new ArrayDeque<>();
		
		original.stream().forEach((e)-> cloned.add(e));
		return cloned;
			
	}

}
